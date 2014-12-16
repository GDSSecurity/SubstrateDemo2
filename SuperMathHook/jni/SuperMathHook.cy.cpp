#include "substrate.h"
#include <android/log.h>

#define TAG "NDK_HOOK"

MSConfig(MSFilterExecutable, "/system/bin/app_process")

/*
 * Original method template
 */
static jstring (*oldMulMethod)(JNIEnv *jni, jobject _this, ...);

/*
 * Modified method
 */
static jstring newMulMethod(JNIEnv *jni, jobject _this, jint param1, jint param2) {

	// Let user know that the method has been hooked
	__android_log_print(ANDROID_LOG_ERROR, TAG, "Hooked into mul.");

	// Print all original parameters
	__android_log_print(ANDROID_LOG_ERROR, TAG, "Param1 : %d", param1);
	__android_log_print(ANDROID_LOG_ERROR, TAG, "Param2 : %d", param2);

	// Call original method
	jstring originalRetVal = (*oldMulMethod)(jni, _this, param1, param2);
	__android_log_print(ANDROID_LOG_ERROR, TAG, "Original Answer : %s", jni->GetStringUTFChars(originalRetVal, 0));

	// Modify return value of original method
	char *modifiedRetValC = (char*) malloc(3);
	strcpy(modifiedRetValC, "10");
	__android_log_print(ANDROID_LOG_ERROR, TAG, "Modified Answer : %s", modifiedRetValC);
	jstring modifiedRetValS = jni->NewStringUTF(modifiedRetValC);

	// Be a nice kid, return what you've got
	free(modifiedRetValC);

	// Return modified return value
	return modifiedRetValS;
}

/*
 * This method should be used to perform various operations
 *  within the class
 */
static void OnClazzLoad(JNIEnv *jniPtr, jclass clazz, void *data) {

	// Let user know that the class has been hooked
	__android_log_print(ANDROID_LOG_ERROR, TAG, "Hooked into the application.");

	// Search for method
	jmethodID methodMul = jniPtr->GetMethodID(clazz, "mul", "(II)Ljava/lang/String;");
	if (methodMul != NULL) {
		// Let user know that we have found the method
		__android_log_print(ANDROID_LOG_ERROR, TAG, "mul called.");
		// Hook into method
		MSJavaHookMethod(jniPtr, clazz, methodMul, &newMulMethod, &oldMulMethod);
	}
}

/*
 * Substrate entry point
 */
MSInitialize
{
	// Let the user know that the extension has been
	// extension has been registered
	__android_log_print(ANDROID_LOG_ERROR, TAG, "Substrate initialized.");
	// Hook into specified class and call OnClazzLoad
	// method when hooked
	MSJavaHookClassLoad(NULL, "me/rahilparikh/SuperMath/ShowMathOps", &OnClazzLoad);
}
