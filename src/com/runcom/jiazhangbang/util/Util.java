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

	public static final String serverAddress = "http://172.16.0.63:24680/wgcwgc/mp3/001.mp3";

	// public static final String serverAddress = "http://abv.cn/music/�춹.mp3";
	// public static final String serverAddress = "http://www.baidu.com";

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

	/**
	 * �����ֻ��ķֱ��ʴ� dip �ĵ�λ ת��Ϊ px(����)
	 */
	public static int dp2px(Context context , float dpValue )
	{
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * �����ֻ��ķֱ��ʴ� px(����) �ĵ�λ ת��Ϊ dp
	 */
	public static int px2dip(Context context , float pxValue )
	{
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * ��pxֵת��Ϊspֵ����֤���ִ�С����
	 */
	public static int px2sp(Context context , float pxValue )
	{
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	public static int sp2px(Context context , float spValue )
	{
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}
}
