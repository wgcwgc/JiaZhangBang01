package com.runcom.jiazhangbang.util;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;

@SuppressWarnings("deprecation")
public class ServerUtil extends AsyncTask < String , String , String >
{

	/**
	 * 
	 * �жϷ������Ƿ���
	 * 
	 * @param path
	 *            �����������ַ
	 * @return ���������� ������δ����
	 */

	@Override
	public String doInBackground(String...params )
	{
		try
		{
			HttpGet get = new HttpGet(params[0]);
			Log.d("LOG" ,params[0]);
			@SuppressWarnings("resource")
			HttpResponse response = new DefaultHttpClient().execute(get);

			Log.d("LOG" ,params[0]);
			if(response.getStatusLine().getStatusCode() == 200)
			{
				return "success";
			}
		}
		catch(ClientProtocolException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ClientError";
		}
		catch(IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ServerError";
		}
		return "success";
	}
}
