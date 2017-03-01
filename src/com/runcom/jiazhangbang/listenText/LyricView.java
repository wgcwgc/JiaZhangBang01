package com.runcom.jiazhangbang.listenText;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.runcom.jiazhangbang.util.Util;

public class LyricView extends TextView
{
	private float high;
	private float width;
	private Paint CurrentPaint;
	private Paint NotCurrentPaint;
	private Paint ThirdCurrentPaint;
	public float TextHigh = Util.sp2px(getContext() ,57);
	private float TextSize = Util.sp2px(getContext() ,15);
	private int Index = 0;
	float progress = 0;
	private List < LyricContent > mySentenceEntities = new ArrayList < LyricContent >();

	public void setSentenceEntities(List < LyricContent > mySentenceEntities )
	{
		this.mySentenceEntities = mySentenceEntities;

	}

	public LyricView(Context context)
	{
		super(context);
		init();

	}

	public LyricView(Context context , AttributeSet attrs , int defStyle)
	{
		super(context , attrs , defStyle);
		init();
	}

	public LyricView(Context context , AttributeSet attrs)
	{
		super(context , attrs);
		init();
	}

	private void init()
	{
		setFocusable(true);

		// 高亮部分
		CurrentPaint = new Paint();
		CurrentPaint.setAntiAlias(true);
		// CurrentPaint.setTextAlign(Paint.Align.CENTER);
		CurrentPaint.setColor(Color.parseColor("#39DF7C"));
		CurrentPaint.setTextSize(TextSize);
		CurrentPaint.setTypeface(Typeface.SERIF);

		// 滚动部分
		ThirdCurrentPaint = new Paint();
		ThirdCurrentPaint.setAntiAlias(true);
		ThirdCurrentPaint.setColor(Color.parseColor("#726463"));
		ThirdCurrentPaint.setTextSize(TextSize);
		ThirdCurrentPaint.setTypeface(Typeface.SERIF);

		// 非高亮部分
		NotCurrentPaint = new Paint();
		NotCurrentPaint.setAntiAlias(true);
		NotCurrentPaint.setTextAlign(Paint.Align.CENTER);
		NotCurrentPaint.setTextSize(TextSize);
		NotCurrentPaint.setTypeface(Typeface.SERIF);
		NotCurrentPaint.setColor(Color.parseColor("#726463"));
	}

	@Override
	protected void onDraw(Canvas canvas )
	{
		super.onDraw(canvas);

		if(canvas == null)
		{
			return;
		}

		try
		{
			int leng = mySentenceEntities.get(Index).getLyric().length();
			String content = mySentenceEntities.get(Index).getLyric().substring(0 ,(int) ((progress) * leng));
			int distance[] =
			{ 100, 200, 190, 150, 140, 150, 130, 130, 130, 120, 120, 120, 110, 90, 90, 80 };
			int baseX = 0;
			baseX = Util.sp2px(getContext() ,leng <= 15 ? distance[leng] : leng >= 50 ? 0 : 1700 / leng);

			canvas.drawText(mySentenceEntities.get(Index).getLyric() ,baseX ,high / 2 ,ThirdCurrentPaint);
			canvas.drawText(content ,baseX ,high / 2 ,CurrentPaint);

			Log.d("LOG" ,"index: " + Index + " progress:" + progress + " leng: " + leng + " width: " + width + " baseX:" + baseX);
			float tempY = high / 2;
			// 画出本句之前的句子
			for(int i = Index - 1 ; i >= 0 ; i -- )
			{
				tempY = tempY - TextHigh;

				canvas.drawText(mySentenceEntities.get(i).getLyric() ,width / 2 ,tempY ,NotCurrentPaint);

			}

			tempY = high / 2;
			// 画出本句之后的句子
			for(int i = Index + 1 ; i < mySentenceEntities.size() ; i ++ )
			{
				tempY = tempY + TextHigh;

				canvas.drawText(mySentenceEntities.get(i).getLyric() ,width / 2 ,tempY ,NotCurrentPaint);

			}
		}
		catch(Exception e)
		{
		}

	}

	@Override
	protected void onSizeChanged(int w , int h , int oldw , int oldh )
	{
		super.onSizeChanged(w ,h ,oldw ,oldh);
		this.high = h;
		this.width = w;
	}

	// @Override
	// public boolean onTouchEvent(MotionEvent event )
	// {
	// // TODO Auto-generated method stub
	//
	// // System.out.println("x: " + event.getX() + " y: " + event.getY());
	//
	// return super.onTouchEvent(event);
	// }

	public void SetIndex(int index )
	{
		this.Index = index;
		// System.out.println("x: " + getX() + " y: " + getY());
	}

	public void SetProgress(float progress )
	{
		this.progress = progress;
		// System.out.println(progress);
	}

}
