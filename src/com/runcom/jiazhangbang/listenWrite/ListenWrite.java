package com.runcom.jiazhangbang.listenWrite;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.runcom.jiazhangbang.R;
import com.runcom.wgcwgc.storage.MySharedPreferences;
import com.umeng.analytics.MobclickAgent;

public class ListenWrite extends Activity
{
	Intent intent;
	int selected = 0;

	private final String sharedPreferencesName = "listenWrite";

	@Override
	protected void onCreate(Bundle savedInstanceState )
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listen_write);

		intent = getIntent();

		selected = intent.getIntExtra("selected" ,0);

		ActionBar actionbar = getActionBar();
		actionbar.setDisplayHomeAsUpEnabled(false);
		actionbar.setDisplayShowHomeEnabled(true);
		actionbar.setDisplayUseLogoEnabled(true);
		actionbar.setDisplayShowTitleEnabled(true);
		actionbar.setDisplayShowCustomEnabled(true);
		actionbar.setTitle(" ��д " + selected + "�꼶");

		initView();

	}

	private ImageButton btnPlay;
	private TextView showTextView;

	private void initView()
	{
		// TODO Auto-generated method stub
		btnPlay = (ImageButton) findViewById(R.id.media_play);
		showTextView = (TextView) findViewById(R.id.listen_write_textView);

	}

	public void showText(View v )
	{
		Toast.makeText(getApplicationContext() ,"��ʾ����..." ,Toast.LENGTH_SHORT).show();
	}

	public void previousText(View v )
	{
		Toast.makeText(getApplicationContext() ,"��һ������..." ,Toast.LENGTH_SHORT).show();
	}

	// ���嵱ǰ��������״̬
	private static final int IDLE = 0;
	private static final int PAUSE = 1;
	private static final int START = 2;

	private int currState = IDLE; // ��ǰ��������״̬

	public void playText(View v )
	{
		play();
	}

	public void play()
	{
		switch(currState)
		{
			case IDLE:
				start();
				showTextView.setText("����������д...");
				// Toast.makeText(getApplicationContext() ,"����״̬..."
				// ,Toast.LENGTH_SHORT).show();
				break;
			case PAUSE:
				// mp.pause();
				btnPlay.setImageResource(R.drawable.play_pause);
				currState = START;
				showTextView.setText("��ͣ��д...");
				// Toast.makeText(getApplicationContext() ,"��ͣ..."
				// ,Toast.LENGTH_SHORT).show();
				break;
			case START:
				// mp.start();
				btnPlay.setImageResource(R.drawable.play_start);
				showTextView.setText("������д...");
				// Toast.makeText(getApplicationContext() ,"����..."
				// ,Toast.LENGTH_SHORT).show();
				currState = PAUSE;
		}

	}

	private void start()
	{
		// TODO Auto-generated method stub
		btnPlay.setImageResource(R.drawable.play_start);
		currState = PAUSE;
	}

	public void nextText(View v )
	{
		Toast.makeText(getApplicationContext() ,"��һ������..." ,Toast.LENGTH_SHORT).show();

	}
	
	int selectedId = R.id.action_oo;

	public void detailSetting(View v )
	{
		// TODO
		Toast.makeText(getApplicationContext() ,"����..." ,Toast.LENGTH_SHORT).show();
		MySharedPreferences.getValue(getApplicationContext() ,sharedPreferencesName ,"playModeSetting" ,0);
		
		final PopupMenu popup = new PopupMenu(this , v);
		getMenuInflater().inflate(R.menu.time_setting ,popup.getMenu());
		popup.show();
		popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
		{
			@Override
			public boolean onMenuItemClick(MenuItem item )
			{
				item.setCheckable(true);
				switch(item.getItemId())
				{
					case R.id.action_5:
						selectedId = R.id.action_5;
						break;

					default:
						selectedId = R.id.action_oo;
						break;

				}
				Toast.makeText(getApplicationContext() ,"��" + item.getTitle() + "��" ,Toast.LENGTH_SHORT).show();
				return true;
			}
		});
		popup.getMenu().findItem(selectedId).setChecked(true);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu )
	{
		// getMenuInflater().inflate(R.menu.time_setting ,menu);
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

	// ��д�����ؼ��˳�����
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
		MobclickAgent.onResume(this);
	}

	@Override
	public void onPause()
	{
		super.onPause();
		MobclickAgent.onPause(this);
	}
}
