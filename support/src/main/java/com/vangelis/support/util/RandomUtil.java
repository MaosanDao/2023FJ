package com.vangelis.support.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomUtil {

    private RandomUtil() {
    }

    /*
     * 随机数
     */
    private static Random random = new Random();
    /*
     * 随机字符字典
     */
    private static final char[] CHARS = {'2', '3', '4', '5', '6', '7', '8',
            '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M',
            'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private static final char[] SIMPLECHARS = {'1', '2', '3', '4', '5', '6', '7', '8',
            '9'};
    private static final char[] OTPCHARS = {'2', '3', '4', '5', '6', '7',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M',
            'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static String getComplexRandomString(Integer length) {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < length; i++) {
            buffer.append(CHARS[random.nextInt(CHARS.length)]);
        }
        return buffer.toString();
    }

    public static String getNumRandomString(Integer length) {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < length; i++) {
            buffer.append(SIMPLECHARS[random.nextInt(SIMPLECHARS.length)]);
        }
        return buffer.toString();
    }

    public static String getOtpKey(String lengthStr) {
        int length = Integer.parseInt(lengthStr);
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < length; i++) {
            buffer.append(OTPCHARS[random.nextInt(OTPCHARS.length)]);
        }
        return buffer.toString();
    }

    //计算两个逗号分隔字符串的交集(qr,sms与otp,sms)的交集为sms返回list
    public static List getStrIntersection(String a, String b) {
        String[] arrayA = a.split(",");
        String[] arrayB = b.split(",");
        List listA = Arrays.asList(arrayA);
        List listB = Arrays.asList(arrayB);
        List list = new ArrayList(listA);
        list.retainAll(listB);
        return list;

    }

    // 求解两个字符号的最长公共子串
    public static String maxSubstring(String strOne, String strTwo) {
        // 参数检查
        if (strOne == null || strTwo == null) {
            return null;
        }
        if ("".equals(strOne) || "".equals(strTwo)) {
            return null;
        }
        // 二者中较长的字符串
        String max = "";
        // 二者中较短的字符串
        String min = "";
        if (strOne.length() < strTwo.length()) {
            max = strTwo;
            min = strOne;
        } else {
            max = strTwo;
            min = strOne;
        }
        String current = "";
        // 遍历较短的字符串，并依次减少短字符串的字符数量，判断长字符是否包含该子串
        for (int i = 0; i < min.length(); i++) {
            for (int begin = 0, end = min.length() - i; end <= min.length(); begin++, end++) {
                current = min.substring(begin, end);
                if (max.contains(current)) {
                    return current;
                }
            }
        }
        return null;
    }


}
