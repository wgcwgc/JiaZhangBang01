package com.runcom.jiazhangbang.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.util.Log;

public class LrcFileDownloader extends Thread
{
	private String lyric;

	public LrcFileDownloader(String lyric)
	{
		this.lyric = lyric;
	}

	@Override
	public void run()
	{

		FileOutputStream fos = null;
		HttpURLConnection conn = null;
		try
		{
			// �ĵ�ַ�������� ���������κ���Դ
			URL url = new URL(lyric);
			// �˴���������url����Ҫ���ص���ҳ
			// 2 �������Ӷ���
			conn = (HttpURLConnection) url.openConnection();// һ������������
			conn.setReadTimeout(3000);// ���ÿͻ������ӳ�ʱ������������ָ��ʱ�� ����˻�û����Ӧ �Ͳ�Ҫ����
			// �жϷ���������ķ����Ƿ��Ѿ������� �ͻ���
			if(conn.getResponseCode() == HttpURLConnection.HTTP_OK || conn.getResponseCode() == 200)
			{
				// ��������ֽ�����������
				InputStream is = conn.getInputStream();
				File saveFilePath = new File(Util.LYRICSPATH + lyric.substring(lyric.lastIndexOf("/") + 1));
				Log.d("LOG" ,"1:" + saveFilePath.toString());
				if(!saveFilePath.getParentFile().exists())
				{
					saveFilePath.getParentFile().mkdirs();
				}
				if( !saveFilePath.exists())
				{
					// �����ڴ浽Ӳ�̵�����
					fos = new FileOutputStream(saveFilePath);
					Log.d("LOG" ,"2: " + saveFilePath.toString());
					// ������ д�ļ�
					byte [] b = new byte [1024];
					int len = 0;
					while((len = is.read(b)) != -1)
					{ // �ȶ����ڴ�
						fos.write(b ,0 ,len);
					}
					fos.flush();
					Log.d("LOG" ,lyric + "���سɹ�");
				}
			}

		}
		catch(MalformedURLException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(fos != null)
			{
				try
				{
					fos.close();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

}
