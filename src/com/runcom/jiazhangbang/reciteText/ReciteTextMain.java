/**
 * 
 */
package com.runcom.jiazhangbang.reciteText;

import java.util.HashMap;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.EditText;

import com.runcom.jiazhangbang.R;
import com.umeng.analytics.MobclickAgent;

/**
 * @author Administrator
 * @copyright wgcwgc
 * @date 2017-3-20
 * @time 下午5:02:50
 * @project_name JiaZhangBang
 * @package_name com.runcom.jiazhangbang.reciteText
 * @file_name ReciteTextMain.java
 * @type_name ReciteTextMain
 * @enclosing_type
 * @tags
 * @todo
 * @others
 * 
 */

public class ReciteTextMain extends Activity
{
	EditText textView;
	Intent intent;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState )
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recite_text_main);

		intent = getIntent();
		String name = intent.getStringExtra("name");

		ActionBar actionbar = getActionBar();
		actionbar.setDisplayHomeAsUpEnabled(false);
		actionbar.setDisplayShowHomeEnabled(true);
		actionbar.setDisplayUseLogoEnabled(true);
		actionbar.setDisplayShowTitleEnabled(true);
		actionbar.setDisplayShowCustomEnabled(true);
		actionbar.setTitle(name);

		initView();

	}

	SpannableString spannableString;
	HashMap < String , Integer > key = new HashMap < String , Integer >();

	/**
	 * 
	 */
	private void initView()
	{
		// TODO Auto-generated method stub
		textView = (EditText) findViewById(R.id.recite_text_main_text);
		String tempString = "start...\n";
		int i;
		for(i = 0 ; i < 7 ; i ++ )
		{
			tempString += "\u3000\u3000" + i + "adsfasdf爱疯阿斯顿发生大事的发生的发生的发生发送到发送到发送到发送到发送到分。\n";
		}
		tempString += "end...\n";

		spannableString = new SpannableString("");

		key.put("(" ,Color.RED);
		key.put(")" ,Color.RED);
		key.put("[" ,Color.BLUE);
		key.put("]" ,Color.BLUE);
		key.put("0" ,Color.GREEN);
		key.put("1" ,Color.GREEN);
		key.put("2" ,Color.GREEN);
		key.put("3" ,Color.GREEN);
		key.put("4" ,Color.GREEN);
		key.put("5" ,Color.GREEN);
		key.put("6" ,Color.GREEN);
		key.put("7" ,Color.GREEN);
		key.put("8" ,Color.GREEN);
		key.put("9" ,Color.GREEN);

		textView.setText(tempString);

		textView.addTextChangedListener(new TextWatcher()
		{

			@Override
			public void onTextChanged(CharSequence s , int start , int before , int count )
			{
				System.out.println("start: " + start + "\nbefore: " + before + "\ncount: " + count);
				// ((Spannable) s).setSpan(new
				// ForegroundColorSpan(key.get(s.toString().indexOf(start)))
				// ,start ,start + count ,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
			}

			@Override
			public void beforeTextChanged(CharSequence s , int start , int count , int after )
			{
			}

			@Override
			public void afterTextChanged(Editable s )
			{
				// Iterator < String > iterator = key.keySet().iterator();
				// String text = s.toString();
				// while(iterator.hasNext())
				// {
				// String strTemp = iterator.next();
				// if(text.contains(strTemp))
				// {
				// int index = 0;
				// while((index = text.indexOf(strTemp ,index)) != -1)
				// {
				// System.out.println("strTemp: " + strTemp);
				// // s.setSpan(new
				// // ForegroundColorSpan(key.get(strTemp)) ,index
				// // ,index + 1 ,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
				// index += strTemp.length();
				// }
				// }
				// }
			}
		});

		textView.setTextIsSelectable(true);
		textView.setOnLongClickListener(new OnLongClickListener()
		{

			@Override
			public boolean onLongClick(View v )
			{
				// TODO Auto-generated method stub
				System.out.println("setOnLongClickListener...");
				return false;
			}
		});
		textView.onTextContextMenuItem(3);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu )
	{
		// getMenuInflater().inflate(R.menu.welcome ,menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item )
	{
		switch(item.getItemId())
		{
			case android.R.id.home:
				onBackPressed();
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	// 重写按返回键
	@Override
	public boolean onKeyDown(int keyCode , KeyEvent event )
	{
		if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN)
		{
			finish();
			return true;
		}
		return super.onKeyDown(keyCode ,event);
	}

	@Override
	public void onResume()
	{
		super.onResume();
		// MobclickAgent.onPageStart("ChineseScreen");
		MobclickAgent.onResume(this);
	}

	@Override
	public void onPause()
	{
		super.onPause();
		// MobclickAgent.onPageEnd("ChineseScreen");
		MobclickAgent.onPause(this);
	}
}
