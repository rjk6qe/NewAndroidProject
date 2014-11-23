package com.example.splashscreen;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;



public class GhostBoard extends View {

	private Paint p;	
	
	public GhostBoard(Context context, AttributeSet aSet) {
		super(context, aSet);
		
		p = new Paint();
	}

	synchronized public void onDraw(Canvas canvas) {
		p.setColor(Color.GRAY);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
