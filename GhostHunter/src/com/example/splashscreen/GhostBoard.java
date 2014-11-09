package com.example.splashscreen;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;



public class GhostBoard extends View {

	private Paint p;	
	
	public GhostBoard(Context context, AttributeSet aset) {
		super(context, aset);
		
		p = new Paint();
	}

	synchronized public void onDraw(Canvas canvas) {
		p.setColor(Color.BLACK);
		p.setAlpha(255);
		canvas.drawRect(0, 0, getWidth(), getHeight(), p);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
