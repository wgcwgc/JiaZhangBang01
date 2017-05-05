/**
 * 
 */
package com.runcom.jiazhangbang.mainActivity;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.runcom.jiazhangbang.R;
import com.runcom.jiazhangbang.util.SDCardHelper;
import com.runcom.jiazhangbang.util.Util;

/**
 * @author Administrator
 * @copyright wgcwgc
 * @date 2017-4-13
 * @time ����11:20:16
 * @project_name JiaZhangBang
 * @package_name com.runcom.jiazhangbang.mainActivity
 * @file_name MyTask.java
 * @type_name MyTask
 * @enclosing_type
 * @tags
 * @todo
 * @others
 * 
 */

public class MyTask extends AsyncTask < String , Void , byte [] >
{
	private Context context;
	private ProgressDialog pDialog;
	private String path = Util.PICTURESPATH;
	private String fileName;

	public MyTask(Context context)
	{
		this.context = context;
		pDialog = new ProgressDialog(context);
		pDialog.setIcon(R.drawable.ic_launcher);
		pDialog.setMessage("ͼƬ������...");
	}

	public MyTask(Context context , String fileName)
	{
		this.context = context;
		this.fileName = fileName;
		pDialog = new ProgressDialog(context);
		pDialog.setIcon(R.drawable.ic_launcher);
		pDialog.setMessage("ͼƬ������...");
	}
	
	public MyTask(Context context , String path , String fileName)
	{
		this.context = context;
		this.path = path;
		this.fileName = fileName;
		pDialog = new ProgressDialog(context);
		pDialog.setIcon(R.drawable.ic_launcher);
		pDialog.setMessage("ͼƬ������...");
	}

	@Override
	protected void onPreExecute()
	{
		super.onPreExecute();
		pDialog.show();
	}

	@Override
	protected byte [] doInBackground(String...params )
	{
		BufferedInputStream bis = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try
		{
			URL url = new URL(params[0]);
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setDoInput(true);
			httpConn.connect();

			if(httpConn.getResponseCode() == 200)
			{
				bis = new BufferedInputStream(httpConn.getInputStream());
				byte [] buffer = new byte [1024 * 8];
				int c = 0;
				while((c = bis.read(buffer)) != -1)
				{
					baos.write(buffer ,0 ,c);
					baos.flush();
				}
				return baos.toByteArray();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(byte [] result )
	{
		super.onPostExecute(result);
		if(result == null)
		{
			Toast.makeText(context ,"ͼƬ����ʧ�ܣ�" ,Toast.LENGTH_LONG).show();
		}
		else
		{
			// ���ֽ�����ת��Bitmap��Ȼ��bitmap���ص�imageview�ؼ���
			// Bitmap bitmap = BitmapFactory.decodeByteArray(result, 0,
			// result.length);
			// imageView_main_img.setImageBitmap(bitmap);
			if(SDCardHelper.saveFileToSDCard(result , path ,fileName))
			{// �����Լ���װ�ķ��������ļ� ��SD����
				Toast.makeText(context ,"ͼƬ����OK��" ,Toast.LENGTH_LONG).show();
			}
			else
			{
				Toast.makeText(context ,"ͼƬ����ʧ�ܣ�" ,Toast.LENGTH_LONG).show();
			}
		}
		pDialog.dismiss();
	}
}
