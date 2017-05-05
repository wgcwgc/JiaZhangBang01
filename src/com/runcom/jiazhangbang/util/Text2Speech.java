/**
 * 
 */
package com.runcom.jiazhangbang.util;

import android.content.Context;
import android.os.Bundle;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;

/**
 * @author Administrator
 * @copyright wgcwgc
 * @date 2017-5-5
 * @time ����4:59:45
 * @project_name JiaZhangBang
 * @package_name com.runcom.jiazhangbang.util
 * @file_name Text2Speech.java
 * @type_name Text2Speech
 * @enclosing_type
 * @tags
 * @todo
 * @others
 * 
 */

public class Text2Speech
{

	private String contents;
	private Context context;

	public Text2Speech()
	{

	}

	public Text2Speech(Context context , String contents)
	{
		this.context = context;
		this.contents = contents;
	}

	/**
	 * @param args
	 */
	public void play()
	{
		SpeechSynthesizer mTts = SpeechSynthesizer.createSynthesizer(context ,null);
		mTts.setParameter(SpeechConstant.VOICE_NAME ,"xiaoyan"); // ���÷�����
		mTts.setParameter(SpeechConstant.SPEED ,"50");// ��������
		mTts.setParameter(SpeechConstant.VOLUME ,"80");// ������������Χ0~100
		mTts.setParameter(SpeechConstant.ENGINE_TYPE ,SpeechConstant.TYPE_CLOUD); // �����ƶ�
		mTts.setParameter(SpeechConstant.AUDIO_FORMAT ,"wav");
		mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH ,Util.AUDIOSPATH + "tts.wav");
		mTts.startSpeaking(contents ,mSynListener);
	}

	private SynthesizerListener mSynListener = new SynthesizerListener()
	{
		// �Ự�����ص��ӿڣ�û�д���ʱ��errorΪnull
		public void onCompleted(SpeechError error )
		{
		}

		// ������Ȼص�
		// percentΪ�������0~100��beginPosΪ������Ƶ���ı��п�ʼλ�ã�endPos��ʾ������Ƶ���ı��н���λ�ã�infoΪ������Ϣ��
		public void onBufferProgress(int percent , int beginPos , int endPos , String info )
		{
		}

		// ��ʼ����
		public void onSpeakBegin()
		{
		}

		// ��ͣ����
		public void onSpeakPaused()
		{
		}

		// ���Ž��Ȼص�
		// percentΪ���Ž���0~100,beginPosΪ������Ƶ���ı��п�ʼλ�ã�endPos��ʾ������Ƶ���ı��н���λ��.
		public void onSpeakProgress(int percent , int beginPos , int endPos )
		{
		}

		// �ָ����Żص��ӿ�
		public void onSpeakResumed()
		{
		}

		// �Ự�¼��ص��ӿ�
		public void onEvent(int arg0 , int arg1 , int arg2 , Bundle arg3 )
		{
		}
	};

}
