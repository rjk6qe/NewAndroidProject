package com.example.splashscreen;

import java.util.Random; 


import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ViewSwitcher;


public class GhostGame extends Activity implements OnClickListener {

	private Handler frame = new Handler();
	
	private Point sprite1velocity;
	
	private ViewSwitcher switcher;
	private static final int refresh_screen = 1;
	private boolean outside = false;
	
	private int score = 0;
	
	private int sprite1maxofX;
	private int sprite1maxofY;
	//private int sprite2maxofX;
	//private int sprite2maxofY;
	
	public static final int DIRECTION_RIGHT = 1;
	public static final int DIRECTION_LEFT  = -1;
	public static final int DIRECTION_UP    = -1;
	public static final int DIRECTION_DOWN  = 1;

	
	float downx = 0;
	float downy = 0;
	float upx = 0;
	float upy = 0;
	
	
	private static final int FRAME_RATE = 20;

	// private static final int FRAME_RATE = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		 getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		 WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_ghost_game);
		
		switcher = (ViewSwitcher) findViewById(R.id.ViewSwitcher2);

		startScan();
		
		
		Handler h = new Handler();

		h.postDelayed(new Runnable() {
			@Override
			public void run() {
				initiateGraphics();
			}

		}, 1000);
		
	}
	
	public void startScan() {
		new Thread() {
			public void run() {
				try {
					if(outside=true) {
						Refresh.sendEmptyMessage(refresh_screen);
					}
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
	
	
	private Point getRandomVelocity() {
		Random r = new Random();
		int min = 1;
		int max = 5;
		int x = r.nextInt(max-min+1)+min;
		int y = r.nextInt(max-min+1)+min;
		return new Point (x,y); 
	}

	private Point getRandomPoint() {
		Random r = new Random();
		int minofX = 0;
		int maxofX = findViewById(R.id.the_canvas).getWidth()
				- ((GhostBoard) findViewById(R.id.the_canvas))
						.getSprite1Width();
		int x = 0;
		int minofY = 0;
		int maxofY = findViewById(R.id.the_canvas).getHeight()
				- ((GhostBoard) findViewById(R.id.the_canvas))
						.getSprite1Height();
		int y = 0;
		x = r.nextInt(maxofX - minofX + 1) + minofX;
		y = r.nextInt(maxofY - minofY + 1) + minofY;
		return new Point(x, y);
	}

	synchronized public void initiateGraphics() {

		
		Point position1 = new Point(0,0);
		//Point position2 = new Point(80,80);
		
		/**
		while (Math.abs(position1.x - position2.x) < ((GhostBoard) findViewById(R.id.the_canvas))
				.getSprite1Width()) {
			position1 = getRandomPoint();
			position2 = getRandomPoint();
		}
		**/
		((GhostBoard) findViewById(R.id.the_canvas)).setSprite1Position(
				position1.x, position1.y);
		/**
		((GhostBoard) findViewById(R.id.the_canvas)).setSprite2Position(
				position2.x, position2.y);
				**/
		sprite1maxofX = findViewById(R.id.the_canvas).getWidth() - 
				((GhostBoard)findViewById(R.id.the_canvas)).getSprite1Width();
		sprite1maxofY = findViewById(R.id.the_canvas).getHeight() - 
				((GhostBoard)findViewById(R.id.the_canvas)).getSprite1Height();
		/**
		 sprite2maxofX = findViewById(R.id.the_canvas).getWidth() - 
		 
				((GhostBoard)findViewById(R.id.the_canvas)).getSprite2Width();
		sprite2maxofY = findViewById(R.id.the_canvas).getHeight() - 
				((GhostBoard)findViewById(R.id.the_canvas)).getSprite2Height();
		**/
		
		sprite1velocity = new Point(0,1);
		
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
	
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Point sprite1 = new Point (((GhostBoard)findViewById(R.id.the_canvas)).getSprite1XPos(),
				((GhostBoard)findViewById(R.id.the_canvas)).getSprite1YPos()) ;
		
		
		sprite1.x = (int)event.getX();
		sprite1.y = (int)event.getY();
		
		
		((GhostBoard)findViewById(R.id.the_canvas)).setSprite1Position(sprite1.x, sprite1.y);
		
		return true;
		
	}

	private Runnable frameUpdate = new Runnable() {
		@Override
		synchronized public void run() {
			frame.removeCallbacks(frameUpdate);
			
			Point sprite1 = new Point (((GhostBoard)findViewById(R.id.the_canvas)).getSprite1XPos(),
					((GhostBoard)findViewById(R.id.the_canvas)).getSprite1YPos()) ;
			
			sprite1.x = sprite1.x + sprite1velocity.x;
			sprite1.y = sprite1.y + sprite1velocity.y;
			/**
			if (sprite1.x > sprite1maxofX) {
				sprite1velocity.x *= -1;
				((GhostBoard)findViewById(R.id.the_canvas)).setSprite1Position(sprite1maxofX,((GhostBoard)findViewById(R.id.the_canvas)).getSprite1YPos());
				
			} 
			**/
			if (sprite1velocity.x == DIRECTION_RIGHT && sprite1.x + GhostBoard.bm1.getWidth() / 2 >= ((GhostBoard)findViewById(R.id.the_canvas)).getWidth()) {
				 sprite1velocity.x *= -1;
			}
			/**
			 else if(sprite1.x < 5) {
				sprite1velocity.x *= -1;
				((GhostBoard)findViewById(R.id.the_canvas)).setSprite1Position(5,((GhostBoard)findViewById(R.id.the_canvas)).getSprite1YPos());
			} 
			**/
			 
			if (sprite1velocity.x == DIRECTION_LEFT && sprite1.x - GhostBoard.bm1.getWidth() / 2 <= 0) {
				 sprite1velocity.x *= -1;					     }
			/**
			if (sprite1.y > sprite1maxofY) {
				sprite1velocity.y *= -1;
				((GhostBoard)findViewById(R.id.the_canvas)).setSprite1Position(((GhostBoard)findViewById(R.id.the_canvas)).getSprite1XPos(),sprite1maxofY);
			}
			
			**/
			if (sprite1.y == DIRECTION_DOWN && sprite1.y + GhostBoard.bm1.getHeight() / 2 >= ((GhostBoard)findViewById(R.id.the_canvas)).getHeight()) {
				sprite1velocity.y *= -1;
			}

			/**
			else if(sprite1.y < 5) {
				sprite1velocity.y *= -1;
				((GhostBoard)findViewById(R.id.the_canvas)).setSprite1Position(((GhostBoard)findViewById(R.id.the_canvas)).getSprite1XPos(),5);
			}
			
			**/
			if (sprite1velocity.y == DIRECTION_UP && sprite1.y - GhostBoard.bm1.getHeight() / 2 <= 0) {
				sprite1velocity.y *= -1;
			}
			
			if(sprite1.y > (findViewById(R.id.the_canvas)).getHeight()) {
				outside = true;
			}
			
			((GhostBoard)findViewById(R.id.the_canvas)).setSprite1Position(sprite1.x, sprite1.y);
					
			frame.removeCallbacks(frameUpdate);
			((GhostBoard) findViewById(R.id.the_canvas)).invalidate();
			frame.postDelayed(frameUpdate, FRAME_RATE);
		}
	};
}
