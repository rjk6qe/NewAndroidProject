package com.example.splashscreen;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewSwitcher;



public class MainActivity extends Activity {

	private ViewSwitcher switcher;
	private static final int refresh_screen = 1;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View decorView = getWindow().getDecorView();
		// Hide the status bar.
		int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
		decorView.setSystemUiVisibility(uiOptions);
		// Remember that you should never show the action bar if the
		// status bar is hidden, so hide that too if necessary.
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		setContentView(R.layout.activity_main);
		// Instantiate all needed fonts
		Typeface titleFont = Typeface.createFromAsset(this.getAssets(),
				"fonts/fatt.ttf");
		final Typeface menuFont = Typeface.createFromAsset(this.getAssets(),
				"fonts/skinny.ttf");
		// Instantiate title TextView and apply font
		TextView title = (TextView) findViewById(R.id.textView_title);
		title.setTypeface(titleFont);
		// Instantiate all buttons
		final Button startButton = (Button) findViewById(R.id.startButton);
		final Button leaderboardButton = (Button) findViewById(R.id.leaderboardButton);
		final Button settingsButton = (Button) findViewById(R.id.settingsButton);
		startButton.setVisibility(View.GONE);
		leaderboardButton.setVisibility(View.GONE);
		settingsButton.setVisibility(View.GONE);
		class fadeAnimator {
			TextView tempText;
			public String[] text = new String[] { "" };
			public int position = 0;
			Animation fadeiInAnimationObject;
			Animation textDisplayAnimationObject;
			Animation delayBetweenAnimations;
			Animation fadeOutAnimationObject;
			int fadeEffectDuration;
			int delayDuration;
			int displayFor;
			boolean isDone;
			public fadeAnimator(TextView textV, String[] textList) {
				this(textV, 600, 1000, 1200, textList);
			}
			public fadeAnimator(TextView textView, int fadeEffectDuration,
					int delayDuration, int displayLength, String[] textList) {
				tempText = textView;
				text = textList;
				this.fadeEffectDuration = fadeEffectDuration;
				this.delayDuration = delayDuration;
				this.displayFor = displayLength;
				isDone = false;
				InnitializeAnimation();
			}
			private void InnitializeAnimation() {
				fadeiInAnimationObject = new AlphaAnimation(0f, 1f);
				fadeiInAnimationObject.setDuration(fadeEffectDuration);
				textDisplayAnimationObject = new AlphaAnimation(1f, 1f);
				textDisplayAnimationObject.setDuration(displayFor);
				delayBetweenAnimations = new AlphaAnimation(0f, 0f);
				delayBetweenAnimations.setDuration(delayDuration);
				fadeOutAnimationObject = new AlphaAnimation(1f, 0f);
				fadeOutAnimationObject.setDuration(fadeEffectDuration);
				fadeiInAnimationObject
						.setAnimationListener(new AnimationListener() {
							@Override
							public void onAnimationStart(Animation animation) {
								tempText.setText(text[position]);
								position++;
							}
							@Override
							public void onAnimationRepeat(Animation animation) {
							}
							@Override
							public void onAnimationEnd(Animation animation) {
								// if (position < text.length)
								tempText.startAnimation(textDisplayAnimationObject);
							}
						});
				textDisplayAnimationObject
						.setAnimationListener(new AnimationListener() {
							@Override
							public void onAnimationStart(Animation animation) {
							}
							@Override
							public void onAnimationRepeat(Animation animation) {
							}
							@Override
							public void onAnimationEnd(Animation animation) {
								tempText.startAnimation(fadeOutAnimationObject);
							}
						});
				fadeOutAnimationObject
						.setAnimationListener(new AnimationListener() {
							@Override
							public void onAnimationStart(Animation animation) {
							}
							@Override
							public void onAnimationRepeat(Animation animation) {
							}
							@Override
							public void onAnimationEnd(Animation animation) {
								if (position < text.length) {
									tempText.startAnimation(delayBetweenAnimations);
									tempText.setVisibility(View.GONE);
								} else {
									startButton.setTypeface(menuFont);
									leaderboardButton.setTypeface(menuFont);
									settingsButton.setTypeface(menuFont);
									startButton.setVisibility(View.VISIBLE);
									leaderboardButton
											.setVisibility(View.VISIBLE);
									settingsButton.setVisibility(View.VISIBLE);
								}
							}
						});
				delayBetweenAnimations
						.setAnimationListener(new AnimationListener() {
							@Override
							public void onAnimationStart(Animation animation) {
							}
							@Override
							public void onAnimationRepeat(Animation animation) {
							}
							@Override
							public void onAnimationEnd(Animation animation) {
								tempText.startAnimation(fadeiInAnimationObject);
							}
						});
			}
			public boolean startAnimation() {
				tempText.startAnimation(fadeOutAnimationObject);
				return isDone;
			}
		}
		fadeAnimator animator = new fadeAnimator(title, new String[] {
				"University of Virginia", "Aditya Bindra", "Richard Kane",
				"Aman Kapoor", "Jeremy Wang", "Obama Hunter" });
		animator.startAnimation();
	}
	
    public void startGame(View view) {
    	Intent intent = new Intent(this, GhostGame.class);
    	MainActivity.this.startActivity(intent);
    }
    
    public void showLeaderboard(View view) {
    	Intent intent = new Intent(this, Leaderboard.class);
    	MainActivity.this.startActivity(intent);
    }
    
    public void openSettings(View view) {
    	Intent intent = new Intent(this, SettingsMenu.class);
    	MainActivity.this.startActivity(intent);
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
