package com.runcom.jiazhangbang.util;

import android.content.Context;
import android.os.Environment;

public class Util
{
	private static final String rootPath = Environment.getExternalStorageDirectory().toString();
	public static final String appPath = rootPath + "/&abc_record/";
	public static final String articlesPath = appPath + "articles/";
	public static final String audiosPath = appPath + "audios/";
	public static final String cachePath = appPath + "cache/";
	public static final String lyricsPath = appPath + "lyrics/";
	public static final String musicsPath = appPath + "musics/";

	public static final String localAudioListCacheName = "localAudioList.log";
	public static final String recordFileName = "wgcwgcRecord_";

	/**
	 * �õ��豸��Ļ�Ŀ��
	 */
	public static int getScreenWidth(Context context )
	{
		return context.getResources().getDisplayMetrics().widthPixels;
	}

	/**
	 * �õ��豸��Ļ�ĸ߶�
	 */
	public static int getScreenHeight(Context context )
	{
		return context.getResources().getDisplayMetrics().heightPixels;
	}

	/**
	 * �õ��豸���ܶ�
	 */
	public static float getScreenDensity(Context context )
	{
		return context.getResources().getDisplayMetrics().density;
	}

	/**
	 * ���ܶ�ת��Ϊ����
	 */
	public static int dip2px(Context context , float px )
	{
		final float scale = getScreenDensity(context);
		return (int) (px * scale + 0.5);
	}
}
