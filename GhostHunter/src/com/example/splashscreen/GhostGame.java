package com.example.splashscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;



public class GhostGame extends Activity implements OnClickListener {

	private Handler frame = new Handler();
	
	private static final int FRAME_RATE = 10;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_ghost_game);
		
		//getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		Handler h = new Handler();
		
		h.postDelayed(new Runnable() {
			@Override
			public void run() {
				initiateGraphics();
			}

		}, 1000);
	}

	synchronized public void initiateGraphics() {
	
		frame.removeCallbacks(frameUpdate);
		frame.postDelayed(frameUpdate, FRAME_RATE);
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ghost_game, menu);
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


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		initiateGraphics();
		
	}
	
	private Runnable frameUpdate = new Runnable() {
		@Override
		synchronized public void run() {
			frame.removeCallbacks(frameUpdate);
			((GhostBoard)findViewById(R.id.the_canvas)).invalidate();
			frame.postDelayed(frameUpdate, FRAME_RATE);
		}
	};
}
