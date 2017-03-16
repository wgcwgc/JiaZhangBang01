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

	private Switch sequenceSwitch , randomSwitch , countSwith , wordShowSwith;
	private int playModeFlag = 0;
	private boolean wordShowFlag = false;
	private static final String sharedPreferencesKey = "ListenWriteSetting";
	private static final String sharedPreferencesPlayModeFlag = "ListenWritePlayMode";
	private static final String sharedPreferencesInterval = "ListenWriteInterval";
	private static final String sharedPreferencesFrequency = "ListenWriteFrequency";
	private static final String sharedPreferencesWordShow = "ListenWriteWordShow";
	TextView setting_me_textView , setting_log_textView ,
	        setting_account_textView , setting_mark_textView;
	TextView setting_clearCache_textView , setting_opinion_textView ,
	        setting_checkUpdate_textView , setting_aboutUs_textView;
	ImageView setting_me_detail , setting_account_detail , setting_mark_detail ,
	        setting_log_detail;
	ImageView setting_clearCache_detail , setting_opinion_detail ,
	        setting_checkUpdate_detail , setting_aboutUs_detail;

	private NumberPicker intervalNumberPicker , frequencyNumberPicker;

	@Override
	protected void onCreate(Bundle savedInstanceState )
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_and_help);

		ActionBar actionbar = getActionBar();
		// ��ʾ���ؼ�ͷĬ���ǲ���ʾ��
		actionbar.setDisplayHomeAsUpEnabled(false);
		// int i;
		// ��ʾ���ķ��ؼ�ͷ�����ҷ��ؼ�ͷ��titleһ�����ã����ؼ�ͷ������ʾ
		actionbar.setDisplayShowHomeEnabled(true);
		actionbar.setDisplayUseLogoEnabled(true);
		// ��ʾ����
		actionbar.setDisplayShowTitleEnabled(true);
		actionbar.setDisplayShowCustomEnabled(true);
		actionbar.setTitle(" ���� ");

		initSwitch();
		initSwith();
		initNumberPicker();

	}

	private void initSwitch()
	{
		// account
		setting_account_textView = (TextView) findViewById(R.id.setting_account_textView);
		setting_account_textView.setOnClickListener(listener);

		setting_mark_textView = (TextView) findViewById(R.id.setting_mark_textView);
		setting_mark_textView.setOnClickListener(listener);

		setting_log_textView = (TextView) findViewById(R.id.setting_log_textView);
		setting_log_textView.setOnClickListener(listener);

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

		// TODO other setting
		intervalNumberPicker = (NumberPicker) findViewById(R.id.setting_intervalNumberPicker);
		frequencyNumberPicker = (NumberPicker) findViewById(R.id.setting_frequencyNumberPicker);
		wordShowSwith = (Switch) findViewById(R.id.setting_wordShow);

		// subtitleShow_switch
		sequenceSwitch = (Switch) findViewById(R.id.SequenceSwitch);
		randomSwitch = (Switch) findViewById(R.id.RandomSwitch);
		countSwith = (Switch) findViewById(R.id.CountSwitch);

	}

	private void initSwith()
	{
		// wordShow
		wordShowFlag = MySharedPreferences.getValue(getApplicationContext() ,sharedPreferencesKey ,sharedPreferencesWordShow ,false);
		if(wordShowFlag)
		{
			wordShowSwith.setChecked(true);
		}
		else
		{
			wordShowSwith.setChecked(false);
		}
		wordShowSwith.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{

			@Override
			public void onCheckedChanged(CompoundButton buttonView , boolean isChecked )
			{
				if(isChecked)
					MySharedPreferences.putValue(getApplicationContext() ,sharedPreferencesKey ,sharedPreferencesWordShow ,true);
				else
					MySharedPreferences.putValue(getApplicationContext() ,sharedPreferencesKey ,sharedPreferencesWordShow ,false);
			}
		});
		// playMode
		playModeFlag = MySharedPreferences.getValue(getApplicationContext() ,sharedPreferencesKey ,sharedPreferencesPlayModeFlag ,0);
		if(1 == playModeFlag)
		{
			sequenceSwitch.setEnabled(false);
			randomSwitch.setEnabled(true);
			randomSwitch.setChecked(true);
			countSwith.setEnabled(false);
		}
		else
			if(2 == playModeFlag)
			{
				sequenceSwitch.setEnabled(false);
				randomSwitch.setEnabled(false);
				countSwith.setEnabled(true);
				countSwith.setChecked(true);
			}
			else
			{
				sequenceSwitch.setEnabled(true);
				sequenceSwitch.setChecked(true);
				randomSwitch.setEnabled(false);
				countSwith.setEnabled(false);
			}

		sequenceSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{

			@Override
			public void onCheckedChanged(CompoundButton buttonView , boolean isChecked )
			{
				if(isChecked)
				{
					randomSwitch.setEnabled(false);
					countSwith.setEnabled(false);
					playModeFlag = 0;
					MySharedPreferences.putValue(getApplicationContext() ,sharedPreferencesKey ,sharedPreferencesPlayModeFlag ,playModeFlag);
				}
				else
				{
					randomSwitch.setEnabled(true);
					countSwith.setEnabled(true);
				}
			}
		});
		randomSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{

			@Override
			public void onCheckedChanged(CompoundButton buttonView , boolean isChecked )
			{
				if(isChecked)
				{
					sequenceSwitch.setEnabled(false);
					countSwith.setEnabled(false);
					playModeFlag = 1;
					MySharedPreferences.putValue(getApplicationContext() ,sharedPreferencesKey ,sharedPreferencesPlayModeFlag ,playModeFlag);
				}
				else
				{
					sequenceSwitch.setEnabled(true);
					countSwith.setEnabled(true);

				}
			}
		});
		countSwith.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{

			@Override
			public void onCheckedChanged(CompoundButton buttonView , boolean isChecked )
			{
				if(isChecked)
				{
					sequenceSwitch.setEnabled(false);
					randomSwitch.setEnabled(false);
					playModeFlag = 2;
					MySharedPreferences.putValue(getApplicationContext() ,sharedPreferencesKey ,sharedPreferencesPlayModeFlag ,playModeFlag);
				}
				else
				{
					sequenceSwitch.setEnabled(true);
					randomSwitch.setEnabled(true);
				}
			}
		});
	}

	private void initNumberPicker()
	{
		// ��������������
		intervalNumberPicker.setMaxValue(10);
		intervalNumberPicker.setMinValue(0);
		intervalNumberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);// �û����ɱ༭����
		intervalNumberPicker.setFormatter(new Formatter()
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

		int intervalValue = MySharedPreferences.getValue(getApplicationContext() ,sharedPreferencesKey ,sharedPreferencesInterval ,0);
		intervalNumberPicker.setValue(intervalValue);
		intervalNumberPicker.setOnValueChangedListener(new OnValueChangeListener()
		{
			@Override
			public void onValueChange(NumberPicker picker , int oldVal , int newVal )
			{
				intervalNumberPicker.setValue(newVal);
				MySharedPreferences.putValue(getApplicationContext() ,sharedPreferencesKey ,sharedPreferencesInterval ,newVal);
			}
		});
		// ���������Ķ�����
		frequencyNumberPicker.setMaxValue(7);
		frequencyNumberPicker.setMinValue(1);
		frequencyNumberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
		frequencyNumberPicker.setFormatter(new Formatter()
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
		int frequencyValue = MySharedPreferences.getValue(getApplicationContext() ,sharedPreferencesKey ,sharedPreferencesFrequency ,1);
		frequencyNumberPicker.setValue(frequencyValue);
		frequencyNumberPicker.setOnValueChangedListener(new OnValueChangeListener()
		{
			@Override
			public void onValueChange(NumberPicker picker , int oldVal , int newVal )
			{
				frequencyNumberPicker.setValue(newVal);
				MySharedPreferences.putValue(getApplicationContext() ,sharedPreferencesKey ,sharedPreferencesFrequency ,newVal);
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
				case R.id.setting_intervalNumberPicker:
					break;
				case R.id.setting_account_textView:
					Toast.makeText(getApplicationContext() ,"account..." ,Toast.LENGTH_SHORT).show();
					break;
				case R.id.setting_mark_textView:
					Toast.makeText(getApplicationContext() ,"mark..." ,Toast.LENGTH_SHORT).show();
					break;
				case R.id.setting_log_textView:
					Toast.makeText(getApplicationContext() ,"log..." ,Toast.LENGTH_SHORT).show();
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

	// ��д�����ؼ��˳�����
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
