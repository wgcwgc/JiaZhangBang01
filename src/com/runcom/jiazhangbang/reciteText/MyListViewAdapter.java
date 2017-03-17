package com.runcom.jiazhangbang.reciteText;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.runcom.jiazhangbang.R;
import com.runcom.jiazhangbang.util.NetUtil;
import com.runcom.jiazhangbang.util.Util;

@SuppressLint("InflateParams")
public class MyListViewAdapter extends BaseAdapter
{
	public static MyAudio myAudio;
	public static ArrayList < MyAudio > audioList;

	LayoutInflater inflater;
	static int flag;
	private Context context;

	public MyListViewAdapter(LayoutInflater inflater , ArrayList < MyAudio > audioList)
	{
		this.inflater = inflater;
		// this.inflater=LayoutInflater.from(this);
		MyListViewAdapter.audioList = audioList;
	}

	public MyListViewAdapter(Context context , ArrayList < MyAudio > audioList)
	{
		this.context = context;
		MyListViewAdapter.audioList = audioList;
		inflater = LayoutInflater.from(this.context);
	}

	// @SuppressWarnings("unchecked")
	// public void setAudioList(ArrayList < MyAudio > audioList)
	// {
	// if(audioList != null)
	// {
	// audioList = (ArrayList < MyAudio >) audioList.clone();
	// notifyDataSetChanged();
	// }
	// }
	//
	// public void clearAudioList()
	// {
	// if(audioList != null)
	// {
	// audioList.clear();
	// }
	// notifyDataSetChanged();
	// }

	@Override
	public int getCount()
	{
		// return audioList == null ? 0 : audioList.size();
		return audioList.size();
	}

	@Override
	public Object getItem(int position )
	{
		return audioList.get(position);
	}

	@Override
	public long getItemId(int position )
	{
		return position;
	}

	@Override
	public View getView(final int position , View convertView , ViewGroup parent )
	{
		Holder holder;
		if(convertView == null)
		{
			convertView = inflater.inflate(R.layout.tab1_fragment_list_item ,null);
			holder = new Holder();
			holder.name = (TextView) convertView.findViewById(R.id.tab1_fragment_list_item_name);
			convertView.setTag(holder);
		}
		else
		{
			holder = (Holder) convertView.getTag();
		}

		if(audioList.size() == 0 || audioList.size() <= position)
		{

		}
		else
			holder.name.setText(audioList.get(position).getName());

		// convertView.setOnClickListener(new View.OnClickListener()
		// {
		// @Override
		// public void onClick(View v )
		// {
		// if(NetUtil.getNetworkState(inflater.getContext()) ==
		// NetUtil.NETWORN_NONE)
		// {
		// Toast.makeText(inflater.getContext() ,"������������"
		// ,Toast.LENGTH_SHORT).show();
		// }
		// else
		// {
		// // TODO download lyrics and get source link
		// Toast.makeText(inflater.getContext() ,"�������" +
		// audioList.get(position).getName().toString()
		// ,Toast.LENGTH_SHORT).show();
		// Intent intent = new Intent(inflater.getContext() , Play.class);
		// String source = audioList.get(position).getSource();
		// // source = "http://abv.cn/music/�춹.mp3";// ǧǧ�ڸ� �춹 �������.mp3
		// intent.putExtra("source" ,source);
		// String lyric = audioList.get(position).getLyric();
		// Log.d("LOG" ,"audio: " + source + "\nlyric: " + lyric);
		// // lyric = "http://abv.cn/music/����_�춹.lrc";
		// intent.putExtra("lyric" ,lyric);
		// inflater.getContext().startActivity(intent);
		// }
		// }
		//
		// });

		ImageButton imageButton_share = (ImageButton) convertView.findViewById(R.id.tab1_fragment_list_item_share);
		final ImageButton imageButton_download = (ImageButton) convertView.findViewById(R.id.tab1_fragement_list_item_download);
		imageButton_share.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v )
			{
				if(NetUtil.getNetworkState(inflater.getContext()) == NetUtil.NETWORK_NONE)
				{
					Toast.makeText(inflater.getContext() ,"������������" ,Toast.LENGTH_SHORT).show();
				}
				else
				{
					Toast.makeText(inflater.getContext() ,"���ڷ���" + audioList.get(position).getName().toString() + "..." ,Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(Intent.ACTION_SEND);
					intent.setType("text/*");
					intent.putExtra(Intent.EXTRA_SUBJECT ,"Share");
					String url = ("www.baidu.com").toString();
					intent.putExtra(Intent.EXTRA_TEXT ,url);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					inflater.getContext().startActivity(Intent.createChooser(intent ,"����"));
				}
			}
		});

		imageButton_download.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v )
			{
				if(NetUtil.getNetworkState(inflater.getContext()) == NetUtil.NETWORK_NONE)
				{
					Toast.makeText(inflater.getContext() ,"������������" ,Toast.LENGTH_SHORT).show();
				}
				else
				{
					// TODO download musics
					Toast.makeText(inflater.getContext() ,"��������" + audioList.get(position).getName().toString() + "..." ,Toast.LENGTH_SHORT).show();
					String urlString = audioList.get(position).getLink().toString();
					urlString = "http://abv.cn/music/�춹.mp3";// ǧǧ�ڸ� �춹 �������.mp3
					String fileName = urlString.substring(urlString.lastIndexOf("/") + 1);
					try
					{
						fileName = URLEncoder.encode(fileName ,"UTF-8");
					}
					catch(UnsupportedEncodingException e)
					{
						e.printStackTrace();
					}

					urlString = urlString.substring(0 ,urlString.lastIndexOf("/") + 1) + fileName;
					if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
					{
						File saveDir = new File(Util.musicsPath);
						try
						{
							download(URLDecoder.decode(fileName.substring(0 ,fileName.lastIndexOf(".")) + " " ,"UTF-8") ,urlString ,saveDir);
						}
						catch(UnsupportedEncodingException e)
						{
							e.printStackTrace();
						}
						imageButton_download.setEnabled(false);

					}
					else
					{
						Toast.makeText(inflater.getContext() ,"����SD��" ,Toast.LENGTH_LONG).show();
					}
				}
			}
		});

		return convertView;
	}

	class Holder
	{
		TextView id , name , data , source , link , other;
		ImageButton share , download;
	}

	public void download(String filename , String path , File savDir )
	{
		// DownloadTask task = new DownloadTask(filename , handler ,
		// inflater.getContext() , path , savDir);
		// new Thread(task).start();
	}

	public Handler handler = new UIHandler();

	static class UIHandler extends Handler
	{
		public void handleMessage(Message msg )
		{
			switch(msg.what)
			{
				case 1: // ���½���
					break;
				case -1: // ����ʧ��
					break;
			}
		}
	}

}
