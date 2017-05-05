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

import com.iflytek.cloud.SpeechUtility;
import com.runcom.jiazhangbang.R;
import com.runcom.jiazhangbang.Chinese.Chinese;
import com.runcom.jiazhangbang.util.Text2Speech;
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
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		SpeechUtility.createUtility(this ,"appid=590aeb53");

		arrayAdapter = ArrayAdapter.createFromResource(this ,R.array.classes ,R.layout.simple_spinner_item);
		arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);

		spinner = (Spinner) findViewById(R.id.main_spinner);
		spinner.setAdapter(arrayAdapter);
		selected = getIntent().getIntExtra("selected" ,0);
		spinner.setSelection(selected);
		++ selected;

		Chinese_textView = (TextView) findViewById(R.id.Chinese_textView);
		math_textView = (TextView) findViewById(R.id.math_textView);
		English_textView = (TextView) findViewById(R.id.English_textView);
		Chinese_textView.setText(selected + "�꼶����");
		math_textView.setText(selected + "�꼶��ѧ");
		English_textView.setText(selected + "�꼶Ӣ��");
		spinner.setDropDownHorizontalOffset(2);
		spinner.setDropDownVerticalOffset(2);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView < ? > arg0 , View arg1 , int arg2 , long arg3 )
			{
				selected = arg2 + 1;
				Chinese_textView.setText(selected + "�꼶����");
				math_textView.setText(selected + "�꼶��ѧ");
				English_textView.setText(selected + "�꼶Ӣ��");
				initImageView(selected);
			}

			@Override
			public void onNothingSelected(AdapterView < ? > arg0 )
			{
			}
		});

		Chinese_imageView = (ImageView) findViewById(R.id.Chinese_imageView);
		math_imageView = (ImageView) findViewById(R.id.math_imageView);
		English_imageView = (ImageView) findViewById(R.id.English_imageView);

		initImageView(selected);

		Chinese_imageView.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v )
			{
				new Text2Speech(getApplicationContext() , selected + "�꼶����").play();
				Intent intent = new Intent();
				intent.setClass(getApplicationContext() ,Chinese.class);
				intent.putExtra("selected" ,selected);
				startActivity(intent);
			}
		});

		// final String urlString = "https://www.baidu.com/img/bd_logo1.png";
		math_imageView.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v )
			{
				Toast.makeText(getApplicationContext() ,selected + "�꼶��ѧ" ,Toast.LENGTH_SHORT).show();
				// new MyTask(MainActivity.this ,
				// urlString.substring(urlString.lastIndexOf("/"))).execute(urlString);
				// 1.����SpeechSynthesizer����, �ڶ������������غϳ�ʱ��InitListener
				new Text2Speech(getApplicationContext() , selected + "�꼶��ѧ").play();
			}
		});

		English_imageView.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v )
			{
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext() ,selected + "�꼶Ӣ��" ,Toast.LENGTH_SHORT).show();
				new Text2Speech(getApplicationContext() , selected + "�꼶Ӣ��").play();

				// String filepath = SDCardHelper.getSDCardPath() +
				// File.separator + "&abc_record/pictures" + File.separator +
				// urlString.substring(urlString.lastIndexOf("/"));
				// byte [] data = SDCardHelper.loadFileFromSDCard(filepath);
				// if(data != null)
				// {// ����Ѿ��оɵ�����,��ֱ�Ӵ�SD���ж�ȡ������ʾ��ImageView��
				// Bitmap bm = BitmapFactory.decodeByteArray(data ,0
				// ,data.length);
				// English_imageView.setImageBitmap(bm);
				// }
				// else
				// {
				// Toast.makeText(getApplicationContext() ,"û�и�ͼƬ��"
				// ,Toast.LENGTH_LONG).show();
				// }

			}
		});

		course_textView = (TextView) findViewById(R.id.course_textView);
		animation_textView = (TextView) findViewById(R.id.animation_textView);
		story_textView = (TextView) findViewById(R.id.story_textView);

		initEndTextView();

		course_textView.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v )
			{
				Toast.makeText(getApplicationContext() ,"��ѵ�γ�..." ,Toast.LENGTH_SHORT).show();
			}
		});
		animation_textView.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v )
			{
				Toast.makeText(getApplicationContext() ,"��������..." ,Toast.LENGTH_SHORT).show();
			}
		});
		story_textView.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v )
			{
				Toast.makeText(getApplicationContext() ,"������..." ,Toast.LENGTH_SHORT).show();
			}
		});

	}

	/**
	 * 
	 */
	private void initEndTextView()
	{
		course_textView.setBackground(getResources().getDrawable(R.drawable.main_course_textview));
		animation_textView.setBackground(getResources().getDrawable(R.drawable.main_animation_textview));
		story_textView.setBackground(getResources().getDrawable(R.drawable.main_story_textview));
	}

	/**
	 * @param selected
	 */
	private void initImageView(int selected )
	{
		switch(selected)
		{
			case 1:
				Chinese_imageView.setImageResource(R.drawable.main_first_up);
				math_imageView.setImageResource(R.drawable.main_first_up);
				English_imageView.setImageResource(R.drawable.main_first_up);
				break;
			case 2:
				Chinese_imageView.setImageResource(R.drawable.main_second_up);
				math_imageView.setImageResource(R.drawable.main_second_up);
				English_imageView.setImageResource(R.drawable.main_second_up);
				break;
			case 3:
				Chinese_imageView.setImageResource(R.drawable.main_third_up);
				math_imageView.setImageResource(R.drawable.main_third_up);
				English_imageView.setImageResource(R.drawable.main_third_up);
				break;
			case 4:
				Chinese_imageView.setImageResource(R.drawable.main_fourth_up);
				math_imageView.setImageResource(R.drawable.main_fourth_up);
				English_imageView.setImageResource(R.drawable.main_fourth_up);
				break;
			case 5:
				Chinese_imageView.setImageResource(R.drawable.main_fifth_up);
				math_imageView.setImageResource(R.drawable.main_fifth_up);
				English_imageView.setImageResource(R.drawable.main_fifth_up);
				break;
			case 6:
				Chinese_imageView.setImageResource(R.drawable.main_sixth_up);
				math_imageView.setImageResource(R.drawable.main_sixth_up);
				English_imageView.setImageResource(R.drawable.main_sixth_up);
				break;
			default:
				Toast.makeText(getApplicationContext() ,"selected error" ,Toast.LENGTH_SHORT).show();
		}

	}

	// �����ڰ����ؼ������˳�����
	private long exitTime = 0;

	@Override
	public boolean onKeyDown(int keyCode , KeyEvent event )
	{
		if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN)
		{
			if((System.currentTimeMillis() - exitTime) > 2000)
			{
				Toast.makeText(getApplicationContext() ,"�ٰ�һ���˳�����" ,Toast.LENGTH_SHORT).show();
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
