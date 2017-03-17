/**
 * 
 */
package com.runcom.jiazhangbang.reciteText;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.baoyz.swipemenulistview.SwipeMenuListView.OnMenuItemClickListener;
import com.baoyz.swipemenulistview.SwipeMenuListView.OnSwipeListener;
import com.runcom.jiazhangbang.R;
import com.runcom.jiazhangbang.util.NetUtil;
import com.runcom.jiazhangbang.util.Util;
import com.umeng.analytics.MobclickAgent;

/**
 * @author Administrator
 * 
 */
public class ReciteText extends Activity
{

	Intent intent = new Intent();
	int selected;

	String audio , lyric , name;
	private SwipeMenuListView listView;
	MyAudio myAudio = new MyAudio();
	ArrayList < MyAudio > audioList01 = new ArrayList < MyAudio >();
	MyListViewAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState )
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recite_text);

		selected = getIntent().getIntExtra("selected" ,0);

		ActionBar actionbar = getActionBar();
		actionbar.setDisplayHomeAsUpEnabled(false);
		actionbar.setDisplayShowHomeEnabled(true);
		actionbar.setDisplayUseLogoEnabled(true);
		actionbar.setDisplayShowTitleEnabled(true);
		actionbar.setDisplayShowCustomEnabled(true);
		actionbar.setTitle("背课文  " + selected + "年级");

		listView = (SwipeMenuListView) findViewById(R.id.fragment_tab1_listView);

		if(NetUtil.getNetworkState(getApplicationContext()) == NetUtil.NETWORK_NONE)
		{
			Toast.makeText(getApplicationContext() ,"请检查网络连接" ,Toast.LENGTH_SHORT).show();
		}

		new GetThread_getList1().start();
		adapter = new MyListViewAdapter(getApplicationContext() , audioList01);
		listView.setAdapter(adapter);
		adapter.notifyDataSetChanged();

		listView.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView < ? > arg0 , View arg1 , int arg2 , long arg3 )
			{
				// System.out.println(flag);
				// Log.d("LOG" , flag + "item");
				// if(flag)
				// {
				// Toast.makeText(getContext() ,"您单击了" +
				// audioList01.get(arg2).getName().toString()
				// ,Toast.LENGTH_SHORT).show();
				// }
				// else
				// flag = true;
				Toast.makeText(getApplicationContext() ,"您单击了" + audioList01.get(arg2).getName().toString() ,Toast.LENGTH_SHORT).show();
			}

		});

		listView.setOnItemLongClickListener(new OnItemLongClickListener()
		{

			@Override
			public boolean onItemLongClick(AdapterView < ? > arg0 , View arg1 , int arg2 , long arg3 )
			{
				Toast.makeText(getApplicationContext() ,"您长按了" + audioList01.get(arg2).getName().toString() ,Toast.LENGTH_SHORT).show();
				return false;
			}

		});

		listView.setOnSwipeListener(new OnSwipeListener()
		{
			@Override
			public void onSwipeStart(int arg0 )
			{
				// flag = false;
			}

			@Override
			public void onSwipeEnd(int arg0 )
			{
				// Toast.makeText(getContext() ,"arg0: " + arg0 +
				// "onSwipeEnd..." ,Toast.LENGTH_SHORT).show();
				// flag = true;
				// Log.d("LOG" , flag + "End");
			}
		});

		SwipeMenuCreator creator = new SwipeMenuCreator()
		{
			@Override
			public void create(SwipeMenu menu )
			{
				SwipeMenuItem openItem = new SwipeMenuItem(getApplicationContext());
				openItem.setBackground(new ColorDrawable(Color.rgb(0xC9 ,0xC9 ,0xCE)));
				openItem.setWidth(Util.dp2px(getApplicationContext() ,90));
				openItem.setTitle("Open");
				openItem.setTitleSize(18);
				openItem.setTitleColor(Color.BLACK);
				menu.addMenuItem(openItem);

				// SwipeMenuItem deleteItem = new SwipeMenuItem(getContext());
				// deleteItem.setBackground(new ColorDrawable(Color.rgb(0xA9
				// ,0xA9 ,0xEF)));
				// deleteItem.setWidth(dp2px(90));
				// deleteItem.setTitle("Delete");
				// deleteItem.setTitleSize(18);
				// deleteItem.setTitleColor(Color.BLACK);
				// menu.addMenuItem(deleteItem);

				SwipeMenuItem shareItem = new SwipeMenuItem(getApplicationContext());
				shareItem.setBackground(new ColorDrawable(Color.rgb(0xF9 ,0x3F ,0x25)));
				shareItem.setWidth(Util.dp2px(getApplicationContext() ,90));
				shareItem.setTitle("Share");
				shareItem.setTitleSize(18);
				shareItem.setTitleColor(Color.BLACK);

				menu.addMenuItem(shareItem);
			}
		};
		listView.setMenuCreator(creator);

		listView.setOnMenuItemClickListener(new OnMenuItemClickListener()
		{
			@Override
			public boolean onMenuItemClick(int position , SwipeMenu menu , int index )
			{
				// String s = (String) adapter.getItem(position);
				switch(index)
				{
					case 0:
						Toast.makeText(getApplicationContext() ,"您点击了" + audioList01.get(position).getName().toString() ,Toast.LENGTH_SHORT).show();
						Intent open_intent = new Intent(getApplicationContext() , ReciteText.class);
						String source = audioList01.get(position).getSource();
						// source = "http://abv.cn/music/红豆.mp3";// 千千阙歌 红豆
						// 光辉岁月.mp3
						open_intent.putExtra("source" ,source);
						String lyric = audioList01.get(position).getLyric();
						// lyric = "http://abv.cn/music/王菲_红豆.lrc";
						open_intent.putExtra("lyric" ,lyric);
						String name = audioList01.get(position).getName();
						open_intent.putExtra("name" ,name);
						Log.d("LOG" ,"audio: " + source + "\nlyric: " + lyric + "\nname: " + name);
						getApplicationContext().startActivity(open_intent);
						break;
					case 1:
						Toast.makeText(getApplicationContext() ,"正在分享" + audioList01.get(position).getName().toString() + "..." ,Toast.LENGTH_SHORT).show();
						Intent share_intent = new Intent(Intent.ACTION_SEND);
						share_intent.setType("text/*");
						share_intent.putExtra(Intent.EXTRA_SUBJECT ,"Share");
						String url = (audioList01.get(position).getSource().toString()).toString();
						share_intent.putExtra(Intent.EXTRA_TEXT ,url);
						share_intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						getApplicationContext().startActivity(Intent.createChooser(share_intent ,"分享"));
						break;
					case 2:
						Toast.makeText(getApplicationContext() ,"正在删除" + audioList01.get(position).getName().toString() + "..." ,Toast.LENGTH_SHORT).show();

						break;
				}
				return false;
			}
		});

	}

	class GetThread_getList1 extends Thread
	{

		public GetThread_getList1()
		{

		}

		@Override
		public void run()
		{

			String url = "http://172.16.0.63:8080/wgc/List00.jsp?type=0";
			HttpGet httpGet = new HttpGet(url);
			try
			{
				HttpClient httpClient = new DefaultHttpClient();
				// HttpClient httpClient =
				// SSLSocketFactoryEx.getNewHttpClient();
				HttpResponse response = httpClient.execute(httpGet);
				if(response.getStatusLine().getStatusCode() == 200)
				{
					HttpEntity entity = response.getEntity();

					BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
					String line = "";
					String returnLine = "";
					while((line = reader.readLine()) != null)
					{
						returnLine += line;
						// System.out.println("*" + line + "*\n");
					}
					JSONObject jsonObject = new JSONObject(returnLine);
					audio = jsonObject.getString("audio");
					lyric = jsonObject.getString("lyric");
					name = jsonObject.getString("name");
					audioList01.clear();
					for(int i = 1 ; i < 17 ; i ++ )
					{
						myAudio = new MyAudio();
						lyric = lyric.substring(0 ,lyric.lastIndexOf("/")) + "/00" + i + ".lrc";
						myAudio.setLyric(lyric);
						// new Thread(new DownloadTask(getContext() , lyric ,
						// new File(Util.lyricsPath))).start();
						// new LrcFileDownloader(lyric).start();
						myAudio.setName(name + i);
						myAudio.setSource(audio.substring(0 ,audio.lastIndexOf("/")) + "/00" + i + ".mp3");
						audioList01.add(myAudio);
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				Log.d("LOG" ,"bug");
			}
		};
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
