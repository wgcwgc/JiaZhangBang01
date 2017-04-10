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
 * @time ����5:02:50
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
			tempString += "\u3000\u3000" + i + "adsfasdf���谢˹�ٷ������µķ����ķ����ķ������͵����͵����͵����͵����͵��֡�\n";
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
				Toast.makeText(getApplicationContext() ,"���ύ�� " + contents_editText_score + " ��" ,Toast.LENGTH_SHORT).show();

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
				builder.setTitle("������ʷ�ɼ�");
				final String [] scores ={ "��01�γɼ�:          57", "��02�γɼ�:          77", "��03�γɼ�:          87", "��04�γɼ�:          97",
						"��05�γɼ�:         100", "��06�γɼ�:          57", "��07�γɼ�:          77", "��08�γɼ�:          87",
						"��09�γɼ�:          97", "��10�γɼ�:         100", "��11�γɼ�:          57", "��12�γɼ�:          77",
						"��13�γɼ�:          87", "��14�γɼ�:          97", "��15�γɼ�:         100", "��16�γɼ�:          57",
						"��17�γɼ�:          77", "��18�γɼ�:          87", "��19�γɼ�:          97", "��20�γɼ�:         100" };
//				for(int j = 0 ; j < 27 ; j ++ )
//				{
//					scores[j] = ( "��" + j + "�γɼ�:         " + (57 + j) ).toString();
//					if(j < 10)
//					{
//						scores[j] = ( "��0" + j + "�γɼ�:         " + (57 + j) ).toString();
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
			menu.add(0 ,1 ,0 ,"����ʼ�");
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

	// ��д�����ؼ�
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
