package com.example.splashscreen;
 

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;



public class GhostBoard extends View {

	private Paint p;
    private Rect sprite1Boundary = new Rect(0,0,0,0);
    private Rect sprite2Boundary = new Rect(0,0,0,0);
    private Point sprite1;
    private Point sprite2;
    private boolean touched;
    
    public static Bitmap bm1 = null;
    public static Bitmap bm2 = null;
    
    synchronized public void setSprite1Position(int x, int y){
    	sprite1=new Point(x,y);
    }
    synchronized public int getSprite1XPos(){
    	return sprite1.x;
    }
    synchronized public int getSprite1YPos(){
    	return sprite1.y;
    }
    
    public boolean isTouched() {
    	return touched;
    }
    
    
    
    public void setTouched(boolean touched) {
    	this.touched = touched;
    }
    
    synchronized public void setSprite2Position(int x, int y){
    	sprite2=new Point(x,y);
    }
    synchronized public int getSprite2XPos(){
    	return sprite2.x;
    }
    synchronized public int getSprite2YPos(){
    	return sprite2.y;
    }
    
    synchronized public int getSprite1Width(){
    	return sprite1Boundary.width();
    }
    synchronized public int getSprite1Height(){
    	return sprite1Boundary.height();
    }
    
    synchronized public int getSprite2Width(){
    	return sprite2Boundary.width();
    }
    synchronized public int getSprite2Height(){
    	return sprite2Boundary.height();
    }
    
    
  
    
	public GhostBoard(Context context, AttributeSet aset) {
		super(context, aset);
		
		p = new Paint();
		sprite1 = new Point(-1,-1);
		sprite2 = new Point(-1,-1);
	
		p = new Paint();
		bm1 = BitmapFactory.decodeResource(getResources(), R.drawable.obama);
		sprite1Boundary = new Rect(0,0, 0, 0);
		
		p = new Paint();
		bm2 = BitmapFactory.decodeResource(getResources(), R.drawable.ghost);
		sprite2Boundary = new Rect(0,0,0,0);
	
	}

	synchronized public void onDraw(Canvas canvas) {
		
		p.setColor(Color.WHITE);
		p.setAlpha(0);
		canvas.drawRect(0, 0, getWidth(), getHeight(), p);
		
		if (sprite1.x>=0 && sprite1.x <=((GhostBoard)findViewById(R.id.the_canvas)).getWidth()) {
			canvas.drawBitmap(bm1, sprite1.x, sprite1.y, null);
		}
		
		if (sprite2.x>=0 && sprite2.x <=((GhostBoard)findViewById(R.id.the_canvas)).getWidth()) {
			canvas.drawBitmap(bm2, sprite2.x, sprite2.y, null);
		}
	}
	

}
