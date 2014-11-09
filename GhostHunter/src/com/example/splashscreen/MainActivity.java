package com.example.splashscreen;

import android.app.Activity; 
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ViewSwitcher;

public class MainActivity extends Activity {

	private ViewSwitcher switcher;
	private static final int refresh_screen = 1;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		switcher = (ViewSwitcher) findViewById(R.id.ViewSwitcher);

		startScan();
		
	}
	
	public void startScan() {
		new Thread() {
			public void run() {
				try {
					Thread.sleep(5000);
					Refresh.sendEmptyMessage(refresh_screen);
				} catch(Exception e) {
				}
			}
		}.start();
		
	}
	
	Handler Refresh = new Handler() {
			public void handleMessage(Message message) {
				switch(message.what){
				
				case refresh_screen:
						switcher.showNext();
						break;	
				default:
						break;
				}
			}
	};
	
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
