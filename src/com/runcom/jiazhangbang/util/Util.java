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

	public static final String SERVERADDRESS = "http://123.206.133.214:8080/JiaZhangBang/listenText.jsp?type=0";
	public static final String SERVERADDRESS_findNewWords = "http://172.16.0.63:8080/wgc/findNewWords.jsp?type=0";
	public static final String SERVERADDRESS_listenText = "http://123.206.133.214:8080/JiaZhangBang/listenText.jsp?type=0";
	public static final String SERVERADDRESS_listenWrite = "http://123.206.133.214:8080/JiaZhangBang/listenWrite.jsp?type=0";
	public static final String SERVERADDRESS_reciteText = "http://123.206.133.214:8080/JiaZhangBang/reciteText.jsp?type=0";
	public static final String SERVERADDRESS_repeat = "http://123.206.133.214:8080/JiaZhangBang/repeat.jsp?type=0";

	// public static final String serverAddress00 =
	// "http://abv.cn/music/红豆.mp3";
	// public static final String serverAddress01 = "http://www.baidu.com";
	public static final int lyricEnglishShow = 0;
	public static final int lyricChineseShow = 1;
	public static final int lyricAllShow = 2;

	/**
	 * 得到设备屏幕的宽度
	 */
	public static int getScreenWidth(Context context )
	{
		return context.getResources().getDisplayMetrics().widthPixels;
	}

	/**
	 * 得到设备屏幕的高度
	 */
	public static int getScreenHeight(Context context )
	{
		return context.getResources().getDisplayMetrics().heightPixels;
	}

	/**
	 * 得到设备的密度
	 */
	public static float getScreenDensity(Context context )
	{
		return context.getResources().getDisplayMetrics().density;
	}

	/**
	 * 把密度转换为像素
	 */
	public static int dip2px(Context context , float px )
	{
		final float scale = getScreenDensity(context);
		return (int) (px * scale + 0.5);
	}

	/**
	 * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
	 */
	public static int dp2px(Context context , float dpValue )
	{
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context , float pxValue )
	{
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 将px值转换为sp值，保证文字大小不变
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
