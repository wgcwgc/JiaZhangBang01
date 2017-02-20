package com.runcom.jiazhangbang.mainActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.runcom.jiazhangbang.R;
import com.runcom.jiazhangbang.Chinese.Chinese;
import com.umeng.analytics.MobclickAgent;

public class MainActivity extends Activity
{

	private Spinner spinner;
	private ArrayAdapter < CharSequence > arrayAdapter;

	int selected;

	private ImageView Chinese_imageView , math_imageView , English_imageView;

	private TextView Chinese_textView , math_textView , English_textView;

	private TextView course_textView , animation_textView , story_textView;

	@Override
	protected void onCreate(Bundle savedInstanceState )
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		arrayAdapter = ArrayAdapter.createFromResource(this ,R.array.classes ,android.R.layout.simple_spinner_item);
		arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinner = (Spinner) findViewById(R.id.main_spinner);
		spinner.setAdapter(arrayAdapter);
		selected = getIntent().getIntExtra("selected" ,0);
		spinner.setSelection(selected);
		++ selected;

		Chinese_textView = (TextView) findViewById(R.id.Chinese_textView);
		math_textView = (TextView) findViewById(R.id.math_textView);
		English_textView = (TextView) findViewById(R.id.English_textView);
		Chinese_textView.setText(selected + "年级语文");
		math_textView.setText(selected + "年级数学");
		English_textView.setText(selected + "年级英语");

		spinner.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView < ? > arg0 , View arg1 , int arg2 , long arg3 )
			{
				// TODO Auto-generated method stub
				selected = arg2 + 1;
				Chinese_textView.setText(selected + "年级语文");
				math_textView.setText(selected + "年级数学");
				English_textView.setText(selected + "年级英语");
			}

			@Override
			public void onNothingSelected(AdapterView < ? > arg0 )
			{
				// TODO Auto-generated method stub

			}
		});

		Chinese_imageView = (ImageView) findViewById(R.id.Chinese_imageView);
		math_imageView = (ImageView) findViewById(R.id.math_imageView);
		English_imageView = (ImageView) findViewById(R.id.English_imageView);

		Chinese_imageView.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v )
			{
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext() ,selected + "年级语文" ,Toast.LENGTH_SHORT).show();

				Intent intent = new Intent();
				intent.setClass(getApplicationContext() ,Chinese.class);
				intent.putExtra("selected" ,selected);

				startActivity(intent);
			}
		});

		math_imageView.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v )
			{
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext() ,selected + "年级数学" ,Toast.LENGTH_SHORT).show();

			}
		});

		English_imageView.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v )
			{
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext() ,selected + "年级英语" ,Toast.LENGTH_SHORT).show();

			}
		});

		course_textView = (TextView) findViewById(R.id.course_textView);
		animation_textView = (TextView) findViewById(R.id.animation_textView);
		story_textView = (TextView) findViewById(R.id.story_textView);

		course_textView.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v )
			{
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext() ,"培训课程..." ,Toast.LENGTH_SHORT).show();
			}
		});
		animation_textView.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v )
			{
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext() ,"动画配音..." ,Toast.LENGTH_SHORT).show();
			}
		});
		story_textView.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v )
			{
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext() ,"听故事..." ,Toast.LENGTH_SHORT).show();
			}
		});

	}

	// 两秒内按返回键两次退出程序
	private long exitTime = 0;

	@Override
	public boolean onKeyDown(int keyCode , KeyEvent event )
	{
		if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN)
		{
			if((System.currentTimeMillis() - exitTime) > 2000)
			{
				Toast.makeText(getApplicationContext() ,"再按一次退出程序" ,Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			}
			else
			{
				MobclickAgent.onKillProcess(this);
				finish();
				System.exit(0);
			}
			return true;
		}
		return super.onKeyDown(keyCode ,event);
	}

	@Override
	public void onResume()
	{
		super.onResume();
		MobclickAgent.onResume(this);
	}

	@Override
	public void onPause()
	{
		super.onPause();
		MobclickAgent.onPause(this);
	}
}
