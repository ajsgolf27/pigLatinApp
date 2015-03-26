package com.stallone.piglatin;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView showText;
	EditText enteredString;
	String wordToModify;
	char firstLetter;
	char secondLetter;
	char thirdLetter;
	String[] stringArray;
	String wordToCheck;
	String newSentance = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		enteredString = (EditText) findViewById(R.id.englishWord);
		showText = (TextView) findViewById(R.id.showText);
		
		Button  bt_exButton = (Button) findViewById(R.id.translateButton);
		

		 bt_exButton.getBackground().setColorFilter(0xFFB45D6A, PorterDuff.Mode.MULTIPLY);  
		  
		
	        //Set the color of the text displayed inside the button  
	        bt_exButton.setTextColor(0xFFFFFFFF);  
	       //enteredString.setCompoundDrawables(null, null, getResources().getDrawable(R.drawable.line), null);
	        //Render this Button again  
	        bt_exButton.invalidate();  
	        //enteredString.invalidate();
		//btnTab1.setBackgroundResource(Color.parseColor("#B45D6A"));
		
		
		ActionBar ab = getActionBar(); 
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#B45D6A"));     
        ab.setBackgroundDrawable(colorDrawable);

		
	}

	/**
	 * hides keyboard.
	 */
	private void hideKeyboard() {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
	}

	/**
	 * splits the string at the spaces and inserts to an array of strings.
	 */
	private String[] splitWord(String wordToSplit) {

		stringArray = wordToModify.split("\\s+");
		return stringArray;
	}

	/**
	 * concatanates the ay and way depending on the vowels and consants.
	 */
	private void createPigLatin(String[] s) {
		stringArray = s;
		for (int i = 0; i < stringArray.length; i++) {
			wordToCheck = stringArray[i];
			if (wordToCheck.isEmpty()) {
				showText.setText("please enter a word");
			} else if (wordToCheck.length() == 2) {
				if (wordToCheck.charAt(0) != 'a'
						|| wordToCheck.charAt(0) != 'e'
						|| wordToCheck.charAt(0) != 'i'
						|| wordToCheck.charAt(0) != 'o'
						|| wordToCheck.charAt(0) != 'u') {
					StringBuilder sb = new StringBuilder(wordToCheck);
					firstLetter = sb.charAt(0);
					sb.deleteCharAt(0);
					sb.append(firstLetter).append("ay");
					wordToCheck = sb.toString();
					newSentance = newSentance + wordToCheck + " ";
				}
			} else if (wordToCheck.length() == 1) {
				if (wordToCheck.startsWith("a") || wordToCheck.startsWith("e")
						|| wordToCheck.startsWith("i")
						|| wordToCheck.startsWith("o")
						|| wordToCheck.startsWith("u")) {
					wordToCheck = wordToCheck.concat("way");
					newSentance = newSentance + wordToCheck + " ";
				} else {
					wordToCheck = wordToCheck.concat("ay");
					newSentance = newSentance + wordToCheck + " ";
				}
			} else if (wordToCheck.startsWith("a")
					|| wordToCheck.startsWith("e")
					|| wordToCheck.startsWith("i")
					|| wordToCheck.startsWith("o")
					|| wordToCheck.startsWith("u")) {
				wordToCheck = wordToCheck.concat("way");
				newSentance = newSentance + wordToCheck + " ";
			} else {
				if (wordToCheck.charAt(1) == 'a'
						|| wordToCheck.charAt(1) == 'e'
						|| wordToCheck.charAt(1) == 'i'
						|| wordToCheck.charAt(1) == 'o'
						|| wordToCheck.charAt(1) == 'u') {
					StringBuilder sb = new StringBuilder(wordToCheck);
					firstLetter = sb.charAt(0);
					sb.deleteCharAt(0);
					sb.append(firstLetter).append("ay");
					wordToCheck = sb.toString();
					newSentance = newSentance + wordToCheck + " ";
				} else {
					if (wordToCheck.charAt(2) == 'a'
							|| wordToCheck.charAt(2) == 'e'
							|| wordToCheck.charAt(2) == 'i'
							|| wordToCheck.charAt(2) == 'o'
							|| wordToCheck.charAt(2) == 'u') {
						StringBuilder sb = new StringBuilder(wordToCheck);
						firstLetter = sb.charAt(0);
						secondLetter = sb.charAt(1);
						sb.delete(0, 2);
						sb.append(firstLetter).append(secondLetter)
								.append("ay");
						wordToCheck = sb.toString();
						newSentance = newSentance + wordToCheck + " ";
					} else {
						StringBuilder sb = new StringBuilder(wordToCheck);
						firstLetter = sb.charAt(0);
						secondLetter = sb.charAt(1);
						thirdLetter = sb.charAt(2);
						sb.delete(0, 3);
						sb.append(firstLetter).append(secondLetter)
								.append(thirdLetter).append("ay");
						wordToCheck = sb.toString();
						newSentance = newSentance + wordToCheck + " ";
					}
				}

			}

		}

	}

	/**
	 * button to translate.
	 */
	public void toPigLatin(View v) {

		// to hide the keyboard
		hideKeyboard();

		// get entered text
		wordToModify = enteredString.getText().toString();

		splitWord(wordToModify);

		createPigLatin(stringArray);

		// displays text in textview
		showText.setText(newSentance);

		// clears the text
		enteredString.setText("");

		// starts a new sentance
		newSentance = "";

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		hideKeyboard();
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
