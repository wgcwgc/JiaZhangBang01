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

public class LyricView extends TextView
{
	private float high;
	private float width;
	private Paint CurrentPaint;
	private Paint NotCurrentPaint;
	private float TextHigh = 128;
	private float TextSize = 37;
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

		// int myScreenWidth = Util.getScreenWidth(context);
		// int myScreenHeigth = Util.getScreenHeight(context);
		// float myScreenDensity = Util.getScreenDensity(context);
		// Log.d("LOG" ,"宽度:" + myScreenWidth + "\n高度:" + myScreenHeigth +
		// "\n密度:" + myScreenDensity);
		init();
	}

	private void init()
	{
		setFocusable(true);
		// 高亮部分
		CurrentPaint = new Paint();
		CurrentPaint.setAntiAlias(true);
		CurrentPaint.setTextAlign(Paint.Align.CENTER);
		// 非高亮部分
		NotCurrentPaint = new Paint();
		NotCurrentPaint.setAntiAlias(true);
		NotCurrentPaint.setTextAlign(Paint.Align.CENTER);

	}

	@Override
	protected void onDraw(Canvas canvas )
	{
		super.onDraw(canvas);

		if(canvas == null)
		{
			return;
		}

		// CurrentPaint.setColor(Color.BLUE);
		// NotCurrentPaint.setColor(Color.GREEN);
		CurrentPaint.setColor(Color.parseColor("#39DF7C"));
		NotCurrentPaint.setColor(Color.parseColor("#726463"));

		CurrentPaint.setTextSize(TextSize);
		CurrentPaint.setTypeface(Typeface.SERIF);

		NotCurrentPaint.setTextSize(TextSize);
		NotCurrentPaint.setTypeface(Typeface.SERIF);

		try
		{
			canvas.drawText(mySentenceEntities.get(Index).getLyric() ,width / 2 ,high / 2 ,NotCurrentPaint);

			int leng = mySentenceEntities.get(Index).getLyric().length();
			String content = mySentenceEntities.get(Index).getLyric().substring(0 , (int) (progress * leng));
			Log.d("LOG" ,"progress:" + progress);
			canvas.drawText(content ,width / 2 - leng + 1 ,high / 2 ,CurrentPaint);
			
			

			float tempY = high / 2;
			// 画出本句之前的句子
			for(int i = Index - 1 ; i >= 0 ; i -- )
			{
				tempY = tempY - TextHigh;

				canvas.drawText(mySentenceEntities.get(i).getLyric() ,width / 2 ,tempY ,CurrentPaint);

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

	public void SetIndex(int index )
	{
		this.Index = index;
		// System.out.println(index);
	}

	public void SetProgress(float progress )
	{
		this.progress = progress;
		// System.out.println(progress);
	}

}
