/**
 * 
 */
package com.runcom.jiazhangbang.reciteText;

import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Response;

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
import com.gr.okhttp.OkHttpUtils;
import com.gr.okhttp.callback.Callback;
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

	String source , lyric , name;
	private SwipeMenuListView listView;
	MyText myText = new MyText();
	ArrayList < MyText > textList = new ArrayList < MyText >();
	MyListViewAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState )
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recite_text_listview);

		selected = getIntent().getIntExtra("selected" ,1);

		ActionBar actionbar = getActionBar();
		actionbar.setDisplayHomeAsUpEnabled(false);
		actionbar.setDisplayShowHomeEnabled(true);
		actionbar.setDisplayUseLogoEnabled(true);
		actionbar.setDisplayShowTitleEnabled(true);
		actionbar.setDisplayShowCustomEnabled(true);
		actionbar.setTitle("������  " + selected + "�꼶");

		initData();
	}

	private void initData()
	{
		if(NetUtil.getNetworkState(getApplicationContext()) == NetUtil.NETWORK_NONE)
		{
			Toast.makeText(getApplicationContext() ,"������������" ,Toast.LENGTH_SHORT).show();
		}
		else
			OkHttpUtils.get().url(Util.SERVERADDRESS).build().execute(new Callback < String >()
			{
				@Override
				public void onError(Call arg0 , Exception arg1 , int arg2 )
				{
				}

				@Override
				public void onResponse(String arg0 , int arg1 )
				{
					initListView();
				}

				@Override
				public String parseNetworkResponse(Response arg0 , int arg1 ) throws Exception
				{
					JSONObject jsonObject = new JSONObject(arg0.body().string());
					source = jsonObject.getString("source");
					lyric = jsonObject.getString("lyric");
					name = jsonObject.getString("name");
					textList.clear();
					for(int i = 0 ; i < 16 ; i ++ )
					{
						int index = (i % 8 + 1);
						myText = new MyText();
						lyric = lyric.substring(0 ,lyric.lastIndexOf("/")) + "/00" + index + ".lrc";
						myText.setLyric(lyric);
						myText.setName(name + (i + 1));
						myText.setMode("ȫ�ı���" + (i + 1));
						myText.setSource(source.substring(0 ,source.lastIndexOf("/")) + "/00" + index + ".mp3");
						textList.add(myText);
					}
					return null;
				}
			});
	}

	private void initListView()
	{
		listView = (SwipeMenuListView) findViewById(R.id.recitText_swipeMenu_listView);
		adapter = new MyListViewAdapter(getApplicationContext() , textList);
		listView.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		listView.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView < ? > arg0 , View arg1 , int arg2 , long arg3 )
			{
				Toast.makeText(getApplicationContext() ,"��������" + textList.get(arg2).getName().toString() ,Toast.LENGTH_SHORT).show();
			}

		});

		listView.setOnItemLongClickListener(new OnItemLongClickListener()
		{

			@Override
			public boolean onItemLongClick(AdapterView < ? > arg0 , View arg1 , int arg2 , long arg3 )
			{
				Toast.makeText(getApplicationContext() ,"��������" + textList.get(arg2).getName().toString() ,Toast.LENGTH_SHORT).show();
				return false;
			}

		});

		listView.setOnSwipeListener(new OnSwipeListener()
		{
			@Override
			public void onSwipeStart(int arg0 )
			{
			}

			@Override
			public void onSwipeEnd(int arg0 )
			{
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
				switch(index)
				{
					case 0:
						Toast.makeText(getApplicationContext() ,"�������" + textList.get(position).getName().toString() ,Toast.LENGTH_SHORT).show();
						Intent open_intent = new Intent(getApplicationContext() , ReciteTextMain.class);
						String source = textList.get(position).getSource();
						open_intent.putExtra("source" ,source);
						String lyric = textList.get(position).getLyric();
						open_intent.putExtra("lyric" ,lyric);
						String name = textList.get(position).getName();
						open_intent.putExtra("name" ,name);
						Log.d("LOG" ,"audio: " + source + "\nlyric: " + lyric + "\nname: " + name);
						Toast.makeText(getApplicationContext() ,"audio: " + source + "\nlyric: " + lyric + "\nname: " + name ,Toast.LENGTH_SHORT).show();
						startActivity(open_intent);
						break;
					case 1:
						Toast.makeText(getApplicationContext() ,"���ڷ���" + textList.get(position).getName().toString() + "..." ,Toast.LENGTH_SHORT).show();
						Intent share_intent = new Intent(Intent.ACTION_SEND);
						share_intent.setType("text/*");
						share_intent.putExtra(Intent.EXTRA_SUBJECT ,"Share");
						String url = (textList.get(position).getSource().toString()).toString();
						share_intent.putExtra(Intent.EXTRA_TEXT ,url);
						share_intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						startActivity(Intent.createChooser(share_intent ,"����"));
						break;
				}
				return false;
			}
		});
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
