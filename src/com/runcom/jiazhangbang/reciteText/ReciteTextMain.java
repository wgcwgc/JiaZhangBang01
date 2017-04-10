/**
 * 
 */
package com.runcom.jiazhangbang.reciteText;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
	TextView contents_textView , score_textView , historyScore_textView;
	EditText contents_editText;
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

	/**
	 * 
	 */
	int touch_count = 0;

	private void initView()
	{
		contents_textView = (TextView) findViewById(R.id.recite_text_main_text);
		String tempString = "start...\n";
		int i;
		for(i = 0 ; i < 7 ; i ++ )
		{
			tempString += "\u3000\u3000" + i + "adsfasdf爱疯阿斯顿发生大事的发生的发生的发生发送到发送到发送到发送到发送到分。\n";
		}
		tempString += "end...\n";

		contents_editText = (EditText) findViewById(R.id.recite_text_main_edit_text_score);

		score_textView = (TextView) findViewById(R.id.recite_text_main_textview_userjudge);
		score_textView.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v )
			{
				// TODO Auto-generated method stub
				String contents_editText_score = contents_editText.getText().toString();
				Toast.makeText(getApplicationContext() ,"您提交了 " + contents_editText_score + " 分" ,Toast.LENGTH_SHORT).show();

			}
		});

		historyScore_textView = (TextView) findViewById(R.id.recite_text_main_textview_history_score);
		historyScore_textView.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v )
			{
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(ReciteTextMain.this , R.style.NoBackGroundDialog);
				builder.setIcon(R.drawable.ic_launcher);
				getWindow().setBackgroundDrawableResource(android.R.color.transparent);
				builder.setTitle("您的历史成绩");
				final String [] scores ={ "第01次成绩:          57", "第02次成绩:          77", "第03次成绩:          87", "第04次成绩:          97",
						"第05次成绩:         100", "第06次成绩:          57", "第07次成绩:          77", "第08次成绩:          87",
						"第09次成绩:          97", "第10次成绩:         100", "第11次成绩:          57", "第12次成绩:          77",
						"第13次成绩:          87", "第14次成绩:          97", "第15次成绩:         100", "第16次成绩:          57",
						"第17次成绩:          77", "第18次成绩:          87", "第19次成绩:          97", "第20次成绩:         100" };
//				for(int j = 0 ; j < 27 ; j ++ )
//				{
//					scores[j] = ( "第" + j + "次成绩:         " + (57 + j) ).toString();
//					if(j < 10)
//					{
//						scores[j] = ( "第0" + j + "次成绩:         " + (57 + j) ).toString();
//					}
//				}
				builder.setItems(scores ,new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog , int which )
					{
						Toast.makeText(getApplication() , scores[which] ,Toast.LENGTH_SHORT).show();
					}
				});
				builder.show();
			}
		});

		contents_textView.setText(tempString);
		contents_textView.setOnTouchListener(new OnTouchListener()
		{
			@SuppressLint("ClickableViewAccessibility")
			@Override
			public boolean onTouch(View v , MotionEvent event )
			{
				// touch_count ++ ;
				// switch(touch_count)
				// {
				// case 1:
				// Toast.makeText(getApplicationContext() ,"touch"
				// ,Toast.LENGTH_SHORT).show();
				//
				// break;
				// default:
				// Toast.makeText(getApplicationContext() ,"touch: " +
				// touch_count ,Toast.LENGTH_SHORT).show();
				//
				// }
				return false;
			}
		});
		contents_textView.addTextChangedListener(new TextWatcher()
		{

			@Override
			public void onTextChanged(CharSequence s , int start , int before , int count )
			{
				// System.out.println("start: " + start + "\nbefore: " + before
				// + "\ncount: " + count);
				// new SpannableString(s).setSpan(new
				// ForegroundColorSpan(Color.RED) ,start ,start + count
				// ,Spannable.SPAN_INCLUSIVE_INCLUSIVE);
			}

			@Override
			public void beforeTextChanged(CharSequence s , int start , int count , int after )
			{
			}

			@Override
			public void afterTextChanged(Editable s )
			{
			}
		});
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu , View view , ContextMenuInfo menuInfo )
	{
		super.onCreateContextMenu(menu ,view ,menuInfo);
		// menu.removeItem(android.R.id.selectAll);
		// menu.removeItem(android.R.id.paste);
		// menu.removeItem(android.R.id.cut);
		menu.removeItem(android.R.id.copy);
		MenuItem item = menu.findItem(android.R.id.copy);

		try
		{
			String ChkMenu = item.getTitle().toString();
			Log.d("LOG" ,item.toString() + "\nchkmenu: " + ChkMenu);
			menu.add(0 ,1 ,0 ,"加入笔记");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

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
