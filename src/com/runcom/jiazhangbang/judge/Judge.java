package com.runcom.jiazhangbang.judge;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Judge
{
	// �ж�email��ʽ�Ƿ���ȷ
	public boolean isEmail(String email )
	{
		String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern pattern = Pattern.compile(str);
		Matcher matcher = pattern.matcher(email);

		return matcher.matches();
	}

	// �ж��Ƿ�ȫ������
	public boolean isNumeric(String str )
	{
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches())
		{
			return false;
		}
		return true;
	}

	// �ж��ֻ���ʽ�Ƿ���ȷ
	public static boolean isMobilePhoneNumber(String mobiles )
	{
		Pattern pattern = Pattern.compile("^1(3[0-9]|4[579]|5[0-35-9]|7[0135-8]|8[0-9])\\d{8}$");
		Matcher matcher = pattern.matcher(mobiles);

		// ��ֹ��2016��5��
		// ���źŶ�:133/149/153/173/177/180/181/189
		// ��ͨ�Ŷ�:130/131/132/145/155/156/171/175/176/185/186
		// �ƶ��Ŷ�:134/135/136/137/138/139/147/150/151/152/157/158/159/178/182/183/184/187/188
		// ������Ӫ��:170
		// "^1(3[0-9]|4[579]|5[0-35-9]|7[0135-8]|8[0-9])\\d{8}$"
		return matcher.matches();
	}

	// �ж�����ʱ�䣬�������
	public boolean isTime(String time )
	{
		String str = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
		Pattern pattern = Pattern.compile(str);
		Matcher matcher = pattern.matcher(str);

		return matcher.matches();

	}

	// �ж�ip��ַ
	public boolean isIP(String ip )
	{
		Pattern pattern = Pattern.compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
		Matcher matcher = pattern.matcher(ip);

		return matcher.matches();
	}

}
