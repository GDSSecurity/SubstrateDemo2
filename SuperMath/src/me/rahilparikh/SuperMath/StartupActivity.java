package me.rahilparikh.SuperMath;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class StartupActivity extends Activity {

	private static final long AUTO_HIDE_DELAY_MILLIS = 3000;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_startup);
		int currentapiVersion = android.os.Build.VERSION.SDK_INT;
		TextView tv = (TextView)findViewById(R.id.splashText);
		tv.setShadowLayer((float) 1.5, 1, 1, Color.LTGRAY);
		if(currentapiVersion >= android.os.Build.VERSION_CODES.HONEYCOMB){
			tv.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		}
		Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");
		tv.setTypeface(font);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// Show level information once
				// splash activity is done
				Intent i = new Intent(StartupActivity.this, ShowMathOps.class);
				startActivity(i);
			}
		}, AUTO_HIDE_DELAY_MILLIS);
	}
}
