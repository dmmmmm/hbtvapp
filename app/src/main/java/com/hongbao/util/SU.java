package com.hongbao.util;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;

public class SU {
	/**
	 * �ж��ַ����Ƿ��ǔ���
	 * 
	 * @author huajun
	 * */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * �ж��Ƿ����ֻ���
	 * 
	 * @param phoneNumber
	 * @return
	 */
	public static boolean isPhoneNumber(String phoneNumber) {
		String expression = "^1[3|4|5|7|8][0-9]{9}$";
		CharSequence inputStr = phoneNumber;
		Pattern pattern = Pattern.compile(expression);
		Matcher matcher = pattern.matcher(inputStr);
		return matcher.matches();
	}

	/**
	 * �ж��ַ����Ƿ��������
	 * 
	 * @author lvliuyan
	 * */
	public static final boolean isChinese(String strName) {
		char[] ch = strName.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (isChinese(c)) {
				return true;
			}
		}
		return false;
	}

	private static final boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	/**
	 * �ж��ַ����Ƿ�Ϊ��
	 * 
	 * @author lvliuyan
	 * */
	public static boolean isEmpty(String str) {
		if (str != null && !str.equals("")) {
			return false;
		} else {
			return true;
		}
	}

	public static String getLocalMacAddress(Context context) {
		WifiManager wifi = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = wifi.getConnectionInfo();
		return info.getMacAddress();
	}

	public static int strToInt(String number) {
		int n = 0;
		try {
			n = Integer.valueOf(number);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return n;
	}

	public static BigDecimal strToBigDecimal(String number) {
		BigDecimal n = null;
		try {
			n = new BigDecimal(number);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return n;
	}

	public static double strToDouble(String number) {
		double n = 0l;
		try {
			n = Double.valueOf(number);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return n;
	}

	/**
	 * ���ݺ���ֵ�����ַ���.e.g."0��0Сʱ0����0��"
	 * 
	 * @param millisSecond
	 * @return
	 */
	public static String millisToString(long millisSecond) {
		int s = 1000;
		int m = 60 * s;
		int h = 60 * m;
		int d = 24 * h;
		StringBuffer sb = new StringBuffer();
		if (millisSecond / d > 0) {
			long day = millisSecond / d;
			sb.append(day < 10 ? "0" + day : day);
			sb.append(" �� ");
		}
		long hour = millisSecond % d / h;
		sb.append(hour < 10 ? "0" + hour : hour);
		sb.append(" ʱ ");
		long minute = millisSecond % d % h / m;
		sb.append(minute < 10 ? "0" + minute : minute);
		sb.append(" �� ");
		long second = millisSecond % d % h % m / s;
		sb.append(second < 10 ? "0" + second : second);
		sb.append(" �� ");
		return sb.toString();
	}

	/***
	 * ��������numת��Ϊ���ִ�д��ʽ
	 * 
	 * @param num
	 *            �����С��10000000��
	 * @return ���Ĵ�д��ʽ
	 */
	public static String transferPriceToHanzi(String number) {
		BigDecimal num;
		if (TextUtils.isEmpty(number.trim())) {
			return "��";
		} else if (number.startsWith("-")) {
			return "�������Ϊ����";
		} else {
			num = new BigDecimal(number.trim());
		}
		String title = "�����:";
		String[] upChinese = { "��", "Ҽ", "��", "��", "��", "��", "½", "��", "��",
				"��", };
		String[] upChinese2 = { "��", "��", "Բ", "ʰ", "��", "Ǫ", "�f", "ʰ", "��",
				"Ǫ", "��", "ʰ", "��", "Ǫ", "��" };
		StringBuffer result = new StringBuffer();
		int count = 0;
		int zeroflag = 0;
		boolean mantissa = false;
		if (num.compareTo(BigDecimal.ZERO) < 0) {
			// ����ֵС����
			return "�������Ϊ����";
		}
		if (num.compareTo(BigDecimal.ZERO) == 0) {
			// ����ֵ������
			return "��";
		}
		if (String.valueOf(num).length() > 12) { // ����ֵ����תΪ��ѧ�������������޷�ת��
			return "������Ľ�����";
		}
		BigDecimal temp = num.multiply(BigDecimal.TEN.pow(2));
		BigDecimal[] divideAndRemainder = temp
				.divideAndRemainder(BigDecimal.TEN.pow(2));
		if (divideAndRemainder[1].compareTo(BigDecimal.ZERO) == 0) {
			// ���Ϊ��ʱ
			if (temp.compareTo(BigDecimal.ZERO) == 0)
				return "������Ľ���С";
			// �����Ϊe:0.0012С�ڷּ�����λʱ
			result.insert(0, "��");
			temp = temp.divide(BigDecimal.TEN.pow(2));
			count = 2;
			mantissa = true;
		}
		while (temp.compareTo(BigDecimal.ZERO) > 0) {
			BigDecimal[] divideAndRemainder2 = temp
					.divideAndRemainder(BigDecimal.TEN);
			BigDecimal t = divideAndRemainder2[1];
			// ȡ�����һλ
			if (t.compareTo(BigDecimal.ZERO) != 0) {
				// ���һλ��Ϊ��ʱ
				if (zeroflag >= 1) {
					// �Ը�λǰ�ĵ���������λ���д���
					if (((!mantissa) && count == 1)) {
						// ������������ҷ�ΪΪ��
					} else if (count > 2 && count - zeroflag < 2) {
						// ������Ϊ400.04С����ǰ������

						result.insert(1, "��");
					} else if (count > 6 && count - zeroflag < 6 && count < 10) {
						// ��λ��Ϊ������λΪ��
						if (count - zeroflag == 2) {
							// ����ֵ��400000
							result.insert(0, "�f");
						} else {
							result.insert(0, "�f��");
							// ����ֵ��400101
						}
					} else if (count > 10 && count - zeroflag < 10) {
						if (count - zeroflag == 2) {
							result.insert(0, "��");
						} else {
							result.insert(0, "����");
						}
					} else if (((count - zeroflag) == 2)) {
						// ��λΪ��
					} else if (count > 6 && count - zeroflag == 6 && count < 10) { // ����λ��ʼ��������4001000
						result.insert(0, "�f");
					} else if (count == 11 && zeroflag == 1) {
						result.insert(0, "��");
					} else {
						result.insert(0, "��");
					}
				}
				result.insert(0, upChinese[t.intValue()] + upChinese2[count]);
				zeroflag = 0;
			} else {
				if (count == 2) {
					result.insert(0, upChinese2[count]);
					// ��λΪ�㲹��"Բ"��
				}
				zeroflag++;
			}
			BigDecimal[] divideAndRemainder3 = temp
					.divideAndRemainder(BigDecimal.TEN);
			temp = divideAndRemainder3[0];
			// System.out.println("count=" + count + "---zero=" + zeroflag
			// + "----" + result.toString());
			count++;
			if (count > 14)
				break;
		}
		return title + result.toString();
	}

	/***
	 * ���ת��Ϊȫ��
	 * 
	 * @param input
	 * @return
	 */
	public static String ToDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375)
				c[i] = (char) (c[i] - 65248);
		}
		return new String(c);
	}

	/**
	 * * ȥ�������ַ����������ı���滻ΪӢ�ı��
	 * 
	 * @param str
	 * @return
	 */
	public static String stringFilter(String str) {
		str = str.replaceAll("��", "[").replaceAll("��", "]")
				.replaceAll("��", "!").replaceAll("��", ":");// �滻���ı��
		String regEx = "[����]"; // ����������ַ�
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}
}

