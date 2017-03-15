package com.runcom.jiazhangbang.setting;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.NumberPicker.Formatter;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.runcom.jiazhangbang.R;
import com.runcom.jiazhangbang.storage.MySharedPreferences;
import com.umeng.analytics.MobclickAgent;

public class PlaySetting extends Activity
{

	private Switch Sequence_switch , Random_switch , Count_swith;
	private int flag = 0;
	private static final String sharedPreferencesKey = "ListenWriteSetting";
	private static final String sharedPreferencesPlayModeFlag = "ListenWritePlayMode";
	private static final String sharedPreferencesIntervalSecond = "ListenWriteIntervalSecond";

	TextView setting_me_textView , setting_log_textView ,
	        setting_account_textView , setting_mark_textView;
	TextView setting_clearCache_textView , setting_opinion_textView ,
	        setting_checkUpdate_textView , setting_aboutUs_textView;
	ImageView setting_me_detail , setting_account_detail , setting_mark_detail ,
	        setting_log_detail;
	ImageView setting_clearCache_detail , setting_opinion_detail ,
	        setting_checkUpdate_detail , setting_aboutUs_detail;

	private NumberPicker numberPicker;

	@Override
	protected void onCreate(Bundle savedInstanceState )
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_and_help);

		ActionBar actionbar = getActionBar();
		// 显示返回箭头默认是不显示的
		actionbar.setDisplayHomeAsUpEnabled(false);
		// int i;
		// 显示左侧的返回箭头，并且返回箭头和title一起设置，返回箭头才能显示
		actionbar.setDisplayShowHomeEnabled(true);
		actionbar.setDisplayUseLogoEnabled(true);
		// 显示标题
		actionbar.setDisplayShowTitleEnabled(true);
		actionbar.setDisplayShowCustomEnabled(true);
		actionbar.setTitle(" 设置 ");

		initSwitch();

	}

	private void initSwitch()
	{
		// account
		setting_account_textView = (TextView) findViewById(R.id.setting_account_textView);
		setting_account_textView.setOnClickListener(listener);

		setting_mark_textView = (TextView) findViewById(R.id.setting_mark_textView);
		setting_mark_textView.setOnClickListener(listener);
		setting_mark_detail = (ImageView) findViewById(R.id.setting_mark_detail);
		setting_mark_detail.setOnClickListener(listener);

		setting_log_textView = (TextView) findViewById(R.id.setting_log_textView);
		setting_log_textView.setOnClickListener(listener);
		setting_log_detail = (ImageView) findViewById(R.id.setting_log_detail);
		setting_log_detail.setOnClickListener(listener);

		// other setting
		setting_clearCache_textView = (TextView) findViewById(R.id.setting_clearCache_textView);
		setting_clearCache_textView.setOnClickListener(listener);
		setting_clearCache_detail = (ImageView) findViewById(R.id.setting_clearCache_detail);
		setting_clearCache_detail.setOnClickListener(listener);

		setting_opinion_textView = (TextView) findViewById(R.id.setting_opinion_textView);
		setting_opinion_textView.setOnClickListener(listener);
		setting_opinion_detail = (ImageView) findViewById(R.id.setting_opinion_detail);
		setting_opinion_detail.setOnClickListener(listener);

		setting_checkUpdate_textView = (TextView) findViewById(R.id.setting_checkUpdate_textView);
		setting_checkUpdate_textView.setOnClickListener(listener);
		setting_checkUpdate_detail = (ImageView) findViewById(R.id.setting_checkUpdate_detail);
		setting_checkUpdate_detail.setOnClickListener(listener);

		setting_aboutUs_textView = (TextView) findViewById(R.id.setting_aboutUs_textView);
		setting_aboutUs_textView.setOnClickListener(listener);
		setting_aboutUs_detail = (ImageView) findViewById(R.id.setting_aboutUs_detail);
		setting_aboutUs_detail.setOnClickListener(listener);

		// other setting
		numberPicker = (NumberPicker) findViewById(R.id.setting_second_numberPicker);
		numberPicker.setOnClickListener(listener);
		// subtitleShow_switch
		Sequence_switch = (Switch) findViewById(R.id.SequenceSwitch);
		Random_switch = (Switch) findViewById(R.id.RandomSwitch);
		Count_swith = (Switch) findViewById(R.id.CountSwitch);

		flag = MySharedPreferences.getValue(getApplicationContext() ,sharedPreferencesKey ,sharedPreferencesPlayModeFlag ,0);
		if(1 == flag)
		{
			Sequence_switch.setEnabled(false);
			Random_switch.setEnabled(true);
			Random_switch.setChecked(true);
			Count_swith.setEnabled(false);
		}
		else
			if(2 == flag)
			{
				Sequence_switch.setEnabled(false);
				Random_switch.setEnabled(false);
				Count_swith.setEnabled(true);
				Count_swith.setChecked(true);
			}
			else
			{
				Sequence_switch.setEnabled(true);
				Sequence_switch.setChecked(true);
				Random_switch.setEnabled(false);
				Count_swith.setEnabled(false);
			}

		Sequence_switch.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{

			@Override
			public void onCheckedChanged(CompoundButton buttonView , boolean isChecked )
			{
				if(isChecked)
				{
					Random_switch.setEnabled(false);
					Count_swith.setEnabled(false);
					flag = 0;
					MySharedPreferences.putValue(getApplicationContext() ,sharedPreferencesKey ,sharedPreferencesPlayModeFlag ,flag);
				}
				else
				{
					Random_switch.setEnabled(true);
					Count_swith.setEnabled(true);
				}
			}
		});
		Random_switch.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{

			@Override
			public void onCheckedChanged(CompoundButton buttonView , boolean isChecked )
			{
				if(isChecked)
				{
					Sequence_switch.setEnabled(false);
					Count_swith.setEnabled(false);
					flag = 1;
					MySharedPreferences.putValue(getApplicationContext() ,sharedPreferencesKey ,sharedPreferencesPlayModeFlag ,flag);
				}
				else
				{
					Sequence_switch.setEnabled(true);
					Count_swith.setEnabled(true);

				}
			}
		});
		Count_swith.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{

			@Override
			public void onCheckedChanged(CompoundButton buttonView , boolean isChecked )
			{
				if(isChecked)
				{
					Sequence_switch.setEnabled(false);
					Random_switch.setEnabled(false);
					flag = 2;
					MySharedPreferences.putValue(getApplicationContext() ,sharedPreferencesKey ,sharedPreferencesPlayModeFlag ,flag);
				}
				else
				{
					Sequence_switch.setEnabled(true);
					Random_switch.setEnabled(true);
				}
			}
		});

		initNumberPicker();

	}

	private void initNumberPicker()
	{
		numberPicker.setMaxValue(10);
		numberPicker.setMinValue(0);
		numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);// 不可用户编辑输入
		numberPicker.setFormatter(new Formatter()
		{

			@Override
			public String format(int value )
			{
				if(value < 10)
				{
					return "0" + String.valueOf(value);
				}
				return String.valueOf(value);
			}
		});

		int value = MySharedPreferences.getValue(getApplicationContext() ,sharedPreferencesKey ,sharedPreferencesIntervalSecond ,0);
		numberPicker.setValue(value);
		numberPicker.setOnValueChangedListener(new OnValueChangeListener()
		{
			@Override
			public void onValueChange(NumberPicker picker , int oldVal , int newVal )
			{
				numberPicker.setValue(newVal);
				MySharedPreferences.putValue(getApplicationContext() ,sharedPreferencesKey ,sharedPreferencesIntervalSecond ,newVal);
			}
		});
	}

	/**
	 * 
	 */
	OnClickListener listener = new OnClickListener()
	{
		@Override
		public void onClick(View v )
		{
			switch(v.getId())
			{
				case R.id.setting_second_numberPicker:
					break;
				case R.id.setting_account_textView:
					Toast.makeText(getApplicationContext() ,"account..." ,Toast.LENGTH_SHORT).show();
					break;
				case R.id.setting_mark_textView:
					Toast.makeText(getApplicationContext() ,"mark..." ,Toast.LENGTH_SHORT).show();
					break;
				case R.id.setting_mark_detail:
					Toast.makeText(getApplicationContext() ,"mark_detail..." ,Toast.LENGTH_SHORT).show();
					break;
				case R.id.setting_log_textView:
					Toast.makeText(getApplicationContext() ,"log..." ,Toast.LENGTH_SHORT).show();
					break;
				case R.id.setting_log_detail:
					Toast.makeText(getApplicationContext() ,"log_detail..." ,Toast.LENGTH_SHORT).show();
					break;
				case R.id.setting_clearCache_textView:
					Toast.makeText(getApplicationContext() ,"clearCache..." ,Toast.LENGTH_SHORT).show();
					break;
				case R.id.setting_clearCache_detail:
					Toast.makeText(getApplicationContext() ,"clearCache_detail..." ,Toast.LENGTH_SHORT).show();
					break;
				case R.id.setting_opinion_textView:
					Toast.makeText(getApplicationContext() ,"opinion..." ,Toast.LENGTH_SHORT).show();
					break;
				case R.id.setting_opinion_detail:
					Toast.makeText(getApplicationContext() ,"opinion_detail..." ,Toast.LENGTH_SHORT).show();
					break;
				case R.id.setting_checkUpdate_textView:
					Toast.makeText(getApplicationContext() ,"checkUpdate..." ,Toast.LENGTH_SHORT).show();
					break;
				case R.id.setting_checkUpdate_detail:
					Toast.makeText(getApplicationContext() ,"checkUpdate_detail..." ,Toast.LENGTH_SHORT).show();
					break;
				case R.id.setting_aboutUs_textView:
					Toast.makeText(getApplicationContext() ,"aboutUs..." ,Toast.LENGTH_SHORT).show();
					break;
				case R.id.setting_aboutUs_detail:
					Toast.makeText(getApplicationContext() ,"aboutUs_detail..." ,Toast.LENGTH_SHORT).show();
					break;
			}
		}

	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu )
	{
		// getMenuInflater().inflate(R.menu.audio_menu_local ,menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item )
	{
		switch(item.getItemId())
		{
			case android.R.id.home:
				onBackPressed();
				finish();
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	// 重写按返回键退出播放
	@Override
	public boolean onKeyDown(int keyCode , KeyEvent event )
	{
		if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN)
		{
			this.finish();
			return true;
		}
		return super.onKeyDown(keyCode ,event);
	}

	@Override
	public void onResume()
	{
		super.onResume();
		// new MyAudio().setOther(String.valueOf(flag));
		MobclickAgent.onPageStart("PlaySettingScreen");
		MobclickAgent.onResume(this);
	}

	@Override
	public void onPause()
	{
		super.onPause();
		MobclickAgent.onPageEnd("PlaySettingScreen");
		MobclickAgent.onPause(this);
	}
}
