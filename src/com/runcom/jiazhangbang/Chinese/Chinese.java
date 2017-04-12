package com.runcom.jiazhangbang.Chinese;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.runcom.jiazhangbang.R;
import com.runcom.jiazhangbang.findNewWords.FindNewWords;
import com.runcom.jiazhangbang.listenText.ListenText;
import com.runcom.jiazhangbang.listenWrite.ListenWrite;
import com.runcom.jiazhangbang.notification.MyNotification;
import com.runcom.jiazhangbang.reciteText.ReciteText;
import com.runcom.jiazhangbang.repeat.Repeat;
import com.runcom.jiazhangbang.util.NetUtil;
import com.runcom.jiazhangbang.welcome.Welcome;
import com.umeng.analytics.MobclickAgent;

public class Chinese extends Activity
{
	Intent intent = new Intent();
	int selected;

	@Override
	protected void onCreate(Bundle savedInstanceState )
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chinese);
		selected = getIntent().getIntExtra("selected" ,0);

		ActionBar actionbar = getActionBar();
		actionbar.setDisplayHomeAsUpEnabled(false);
		actionbar.setDisplayShowHomeEnabled(true);
		actionbar.setDisplayUseLogoEnabled(true);
		actionbar.setDisplayShowTitleEnabled(true);
		actionbar.setDisplayShowCustomEnabled(true);
		actionbar.setTitle(selected + "�꼶����");

	}

	public void ListenText(View v )
	{
		intent.putExtra("selected" ,selected);
		intent.setClass(getApplicationContext() ,ListenText.class);
		if(NetUtil.getNetworkState(getApplicationContext()) == NetUtil.NETWORK_NONE)
		{
			Toast.makeText(getApplicationContext() ,"������������" ,Toast.LENGTH_SHORT).show();
		}
		else
			startActivity(intent);
	}

	public void ListenAndWrite(View v )
	{
		intent.putExtra("selected" ,selected);
		intent.setClass(getApplicationContext() ,ListenWrite.class);
		if(NetUtil.getNetworkState(getApplicationContext()) == NetUtil.NETWORK_NONE)
		{
			Toast.makeText(getApplicationContext() ,"������������" ,Toast.LENGTH_SHORT).show();
		}
		else
			startActivity(intent);
	}

	public void ReciteText(View v )
	{
		intent.putExtra("selected" ,selected);
		intent.setClass(getApplicationContext() ,ReciteText.class);
		if(NetUtil.getNetworkState(getApplicationContext()) == NetUtil.NETWORK_NONE)
		{
			Toast.makeText(getApplicationContext() ,"������������" ,Toast.LENGTH_SHORT).show();
		}
		else
			// if( !new
			// ServerUtil().execute(Util.serverAddress).equals("success") )
			// {
			// Toast.makeText(getApplicationContext() ,"������δ���� !\n����ϵ�������Ա."
			// ,Toast.LENGTH_SHORT).show();
			// }
			// else
			startActivity(intent);
	}

	public void Repeat(View v )
	{
		// Toast.makeText(getApplicationContext() ,"����"
		// ,Toast.LENGTH_SHORT).show();
		// TODO ����
		intent.putExtra("selected" ,selected);
		intent.setClass(getApplicationContext() ,Repeat.class);
		if(NetUtil.getNetworkState(getApplicationContext()) == NetUtil.NETWORK_NONE)
		{
			Toast.makeText(getApplicationContext() ,"������������" ,Toast.LENGTH_SHORT).show();
		}
		else
			// if( !new
			// ServerUtil().execute(Util.serverAddress).equals("success") )
			// {
			// Toast.makeText(getApplicationContext() ,"������δ���� !\n����ϵ�������Ա."
			// ,Toast.LENGTH_SHORT).show();
			// }
			// else
			startActivity(intent);
	}

	public void FindNewWords(View v )
	{
		intent.putExtra("selected" ,selected);
		intent.setClass(getApplicationContext() ,FindNewWords.class);
		if(NetUtil.getNetworkState(getApplicationContext()) == NetUtil.NETWORK_NONE)
		{
			Toast.makeText(getApplicationContext() ,"������������" ,Toast.LENGTH_SHORT).show();
		}
		else
			// if( !new
			// ServerUtil().execute(Util.serverAddress).equals("success") )
			// {
			// Toast.makeText(getApplicationContext() ,"������δ���� !\n����ϵ�������Ա."
			// ,Toast.LENGTH_SHORT).show();
			// }
			// else
			startActivity(intent);
		// Toast.makeText(getApplicationContext() ,"������"
		// ,Toast.LENGTH_SHORT).show();
	}

	public void ComingSoon(View v )
	{
		MyNotification.myNotification(getApplicationContext());
		// Toast.makeText(getApplicationContext() ,"Coming soon !"
		// ,Toast.LENGTH_SHORT).show();
		intent.putExtra("selected" ,selected);
		intent.setClass(getApplicationContext() ,Welcome.class);
		startActivity(intent);
		// this.finish();
	}

	// @Override
	// public boolean onMenuOpened(int featureId , Menu menu )
	// {
	// if(featureId == Window.FEATURE_ACTION_BAR && menu != null)
	// {
	// if(menu.getClass().getSimpleName().equals("MenuBuilder"))
	// {
	// try
	// {
	// Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible"
	// ,Boolean.TYPE);
	// m.setAccessible(true);
	// m.invoke(menu ,true);
	// }
	// catch(Exception e)
	// {
	// Toast.makeText(this ,"overflow չ����ʾitemͼ���쳣" ,Toast.LENGTH_LONG).show();
	// }
	// }
	// }
	//
	// return super.onMenuOpened(featureId ,menu);
	// }

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
