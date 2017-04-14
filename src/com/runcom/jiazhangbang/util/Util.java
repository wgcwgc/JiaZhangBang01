package com.runcom.jiazhangbang.util;

import android.content.Context;

public class Util
{
	public static final String ROOTPATH = SDCardHelper.getSDCardPath();
	
	public static final String appPath = "/&abc_record/";
	public static final String APPPATH = ROOTPATH + appPath;

	public static final String articlesPath = "articles/";
	public static final String ARTICLESPATH = APPPATH + articlesPath;

	public static final String audiosPath = "audios/";
	public static final String AUDIOSPATH = APPPATH + audiosPath;
	
	public static final String lyricsPath = "lyrics/";
	public static final String LYRICSPATH = APPPATH + lyricsPath;

	public static final String musicsPath = "musics/";
	public static final String MUSICSPATH = APPPATH + musicsPath;
	
	public static final String cachePath = "cache/";
	public static final String CACHEPATH = APPPATH + cachePath;

	public static final String picturesPath = "pictures/";
	public static final String PICTURESPATH = APPPATH + picturesPath;

	public static final String localAudioListCache = "localAudioList.log";
	public static final String LOCALAUDIOLISTCACHE = CACHEPATH + localAudioListCache;

	public static final String recordPath = "wgcwgcRecord_";
	public static final String RECORDPATH = APPPATH + recordPath;

	public static final String SERVERADDRESS = "http://172.16.0.63:24680/wgcwgc/mp3/001.mp3";

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
