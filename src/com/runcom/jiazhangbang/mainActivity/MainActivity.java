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
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

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
		Chinese_textView.setText(selected + "年级语文");
		math_textView.setText(selected + "年级数学");
		English_textView.setText(selected + "年级英语");
		spinner.setDropDownHorizontalOffset(2);
		spinner.setDropDownVerticalOffset(2);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView < ? > arg0 , View arg1 , int arg2 , long arg3 )
			{
				selected = arg2 + 1;
				Chinese_textView.setText(selected + "年级语文");
				math_textView.setText(selected + "年级数学");
				English_textView.setText(selected + "年级英语");
				Toast.makeText(getApplicationContext() ,"arg0: " + arg0.getSelectedItem().toString() + "\nspinner: " + spinner.getSelectedItem().toString() ,Toast.LENGTH_LONG).show();
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

		// Chinese_imageView.setBackground(getResources().getDrawable(R.drawable.main_first_up));
		// math_imageView.setBackground(getResources().getDrawable(R.drawable.main_first_up));
		// English_imageView.setBackground(getResources().getDrawable(R.drawable.main_first_up));

		initImageView(selected);

		Chinese_imageView.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v )
			{
				// Toast.makeText(getApplicationContext() ,selected + "年级语文"
				// ,Toast.LENGTH_SHORT).show();

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

		initEndTextView();

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
				// Intent intent = new Intent();
				// intent.setAction(Intent.ACTION_MAIN);
				// intent.addCategory(Intent.CATEGORY_HOME);
				// startActivity(intent);
			}
		});

		// Bitmap icon = BitmapFactory.decodeResource(getResources()
		// ,R.drawable.ic_launcher);
		// Notification notification = new
		// NotificationCompat.Builder(getApplicationContext()).setSmallIcon(R.drawable.ic_interface)
		// .setContentTitle("contentTitle").setContentText("contentText").setLargeIcon(icon).setOngoing(false)
		// .setTicker("ticker").setContentInfo("contentInfo").setDefaults(Notification.DEFAULT_ALL)
		// .setContentIntent(PendingIntent.getActivity(getApplicationContext()
		// ,100 ,
		// new Intent(this , ReciteTextMain.class)
		// ,PendingIntent.FLAG_ONE_SHOT))
		// .setAutoCancel(true).build();
		// NotificationManager notificationManager = (NotificationManager)
		// getSystemService(this.NOTIFICATION_SERVICE);
		// notificationManager.notify(1 ,notification);
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
