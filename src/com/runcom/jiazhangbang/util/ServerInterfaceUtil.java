/**
 * 
 */
package com.runcom.jiazhangbang.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.runcom.jiazhangbang.web.SSLSocketFactoryEx;

/**
 * @author Administrator
 * @copyright wgcwgc
 * @date 2017-4-17
 * @time ÉÏÎç11:49:31
 * @project_name JiaZhangBang
 * @package_name com.runcom.jiazhangbang.util
 * @file_name ServerInterfaceUtil.java
 * @type_name ServerInterfaceUtil
 * @enclosing_type
 * @tags
 * @todo
 * @others
 * 
 */

public class ServerInterfaceUtil
{

	public String audio , lyric , name;
	static String separator = ",";
	Handler handler;

	public ServerInterfaceUtil()
	{

	}

	public String serverData = "";

	@SuppressLint("HandlerLeak")
	public String getData(String url )
	{
		new GetValueThread(url).start();
		handler = new Handler()
		{
			@Override
			public void handleMessage(Message msg )
			{
				if(100 == msg.what)
				{
					serverData = (String) msg.obj;
				}
			}
		};
		return serverData;
	}

	class GetValueThread extends Thread
	{

		private String url;

		public GetValueThread()
		{

		}

		public GetValueThread(String url)
		{
			this.url = url;
		}

		@Override
		public void run()
		{
			HttpGet httpGet = new HttpGet(url);
			try
			{
				// HttpClient httpClient = new DefaultHttpClient();
				HttpClient httpClient = SSLSocketFactoryEx.getNewHttpClient();
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
					}
					JSONObject jsonObject = new JSONObject(returnLine);
					audio = jsonObject.getString("audio");
					lyric = jsonObject.getString("lyric");
					name = jsonObject.getString("name");
					String json = "audio:" + audio + " lyric:" + lyric + " name:" + name;
					Log.d("1.5: LOG" ,json);
					Message msg = new Message();
					msg.obj = json;
					msg.what = 100;
					handler.sendMessage(msg);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				Log.d("LOG" ,"bug");
			}
		};
	}
}
