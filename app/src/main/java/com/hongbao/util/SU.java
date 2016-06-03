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
	 * 判断字符串是否是數字
	 * 
	 * @author huajun
	 * */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 判断是否是手机号
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
	 * 判断字符串是否包含中文
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
	 * 判断字符串是否为空
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
	 * 根据毫秒值返回字符串.e.g."0天0小时0分钟0秒"
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
			sb.append(" 天 ");
		}
		long hour = millisSecond % d / h;
		sb.append(hour < 10 ? "0" + hour : hour);
		sb.append(" 时 ");
		long minute = millisSecond % d % h / m;
		sb.append(minute < 10 ? "0" + minute : minute);
		sb.append(" 分 ");
		long second = millisSecond % d % h % m / s;
		sb.append(second < 10 ? "0" + second : second);
		sb.append(" 秒 ");
		return sb.toString();
	}

	/***
	 * 将输入金额num转换为汉字大写格式
	 * 
	 * @param num
	 *            输入金额（小于10000000）
	 * @return 金额的大写格式
	 */
	public static String transferPriceToHanzi(String number) {
		BigDecimal num;
		if (TextUtils.isEmpty(number.trim())) {
			return "零";
		} else if (number.startsWith("-")) {
			return "输入金额不能为负数";
		} else {
			num = new BigDecimal(number.trim());
		}
		String title = "人民币:";
		String[] upChinese = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌",
				"玖", };
		String[] upChinese2 = { "分", "角", "圆", "拾", "佰", "仟", "萬", "拾", "佰",
				"仟", "亿", "拾", "佰", "仟", "兆" };
		StringBuffer result = new StringBuffer();
		int count = 0;
		int zeroflag = 0;
		boolean mantissa = false;
		if (num.compareTo(BigDecimal.ZERO) < 0) {
			// 输入值小于零
			return "输入金额不能为负数";
		}
		if (num.compareTo(BigDecimal.ZERO) == 0) {
			// 输入值等于零
			return "零";
		}
		if (String.valueOf(num).length() > 12) { // 输入值过大转为科学计数法本方法无法转换
			return "您输入的金额过大";
		}
		BigDecimal temp = num.multiply(BigDecimal.TEN.pow(2));
		BigDecimal[] divideAndRemainder = temp
				.divideAndRemainder(BigDecimal.TEN.pow(2));
		if (divideAndRemainder[1].compareTo(BigDecimal.ZERO) == 0) {
			// 金额为整时
			if (temp.compareTo(BigDecimal.ZERO) == 0)
				return "您输入的金额过小";
			// 输入额为e:0.0012小于分计量单位时
			result.insert(0, "整");
			temp = temp.divide(BigDecimal.TEN.pow(2));
			count = 2;
			mantissa = true;
		}
		while (temp.compareTo(BigDecimal.ZERO) > 0) {
			BigDecimal[] divideAndRemainder2 = temp
					.divideAndRemainder(BigDecimal.TEN);
			BigDecimal t = divideAndRemainder2[1];
			// 取得最后一位
			if (t.compareTo(BigDecimal.ZERO) != 0) {
				// 最后一位不为零时
				if (zeroflag >= 1) {
					// 对该位前的单个或多个零位进行处理
					if (((!mantissa) && count == 1)) {
						// 不是整数金额且分为为零
					} else if (count > 2 && count - zeroflag < 2) {
						// 输入金额为400.04小数点前后都有零

						result.insert(1, "零");
					} else if (count > 6 && count - zeroflag < 6 && count < 10) {
						// 万位后为零且万位为零
						if (count - zeroflag == 2) {
							// 输入值如400000
							result.insert(0, "萬");
						} else {
							result.insert(0, "萬零");
							// 输入值如400101
						}
					} else if (count > 10 && count - zeroflag < 10) {
						if (count - zeroflag == 2) {
							result.insert(0, "亿");
						} else {
							result.insert(0, "亿零");
						}
					} else if (((count - zeroflag) == 2)) {
						// 个位为零
					} else if (count > 6 && count - zeroflag == 6 && count < 10) { // 以万位开始出现零如4001000
						result.insert(0, "萬");
					} else if (count == 11 && zeroflag == 1) {
						result.insert(0, "亿");
					} else {
						result.insert(0, "零");
					}
				}
				result.insert(0, upChinese[t.intValue()] + upChinese2[count]);
				zeroflag = 0;
			} else {
				if (count == 2) {
					result.insert(0, upChinese2[count]);
					// 个位为零补上"圆"字
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
	 * 半角转换为全角
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
	 * * 去除特殊字符或将所有中文标号替换为英文标号
	 * 
	 * @param str
	 * @return
	 */
	public static String stringFilter(String str) {
		str = str.replaceAll("【", "[").replaceAll("】", "]")
				.replaceAll("！", "!").replaceAll("：", ":");// 替换中文标号
		String regEx = "[『』]"; // 清除掉特殊字符
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}
}

