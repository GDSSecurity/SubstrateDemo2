package me.rahilparikh.SuperMath;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class ShowMathOps extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_math_ops);
		TextView txt = (TextView)findViewById(R.id.editText1);
		txt.append("Welcome to SuperMath!\n");
		txt.append("We will do series of operations on number 2 and number 3.\n");
		txt.append("Addition (2+3):" + add(2, 3) + "\n");
		txt.append("Subtraction (2-3): " + sub(2, 3) + "\n");
		txt.append("Multiplication (2x3): " + mul(2,3) + "\n");
		txt.append("Thanks for you time! Bye bye!");

	}

	public String add(int a, int b){
		return "" + (a + b);
	}

	protected String sub(int a, int b){
		return "" + (a - b);
	}

	private String mul(int a, int b){
		return "" + (a * b);
	}
}
