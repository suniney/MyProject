package com.example.yanxu.myproject.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yanxu on 2016/9/1.
 */
public class PatternUtils {
    /**
     * 提现金额
     *
     * @param pwd
     * @return
     */
    public static boolean isCorrectMoney(String pwd) {
        Pattern p = Pattern.compile("^(([1-9]\\d{0,9})|0)(\\.\\d{1,2})?$");
        Matcher m = p.matcher(pwd);
        System.out.println(m.matches() + "-pwd-");
        return m.matches();
    }

    /**
     * 密码匹配，以字母开头，长度 在6-18之间，只能包含字符、数字和下划线。
     *
     * @param pwd
     * @return
     */
    public static boolean isCorrectUserPwd(String pwd) {
//        Pattern p = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$");
        Pattern p = Pattern.compile("^[0-9A-Za-z]{6,20}$");
        Matcher m = p.matcher(pwd);
        System.out.println(m.matches() + "-pwd-");
        return m.matches();
    }

    /**
     * 手机号码格式匹配
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern
                .compile("^1(3[0-9]|4[57]|5[0-35-9]|8[0-9]|70)\\d{8}$");
        Matcher m = p.matcher(mobiles);
        System.out.println(m.matches() + "-telnum-");
        return m.matches();
    }

    /**
     * 手机号加密
     *
     * @param phoneNumber
     * @return
     */
    private static String getProtectedMobile(String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber) || phoneNumber.length() < 11) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(phoneNumber.subSequence(0, 3));
        builder.append("****");
        builder.append(phoneNumber.subSequence(7, 11));
        return builder.toString();
    }

    /**
     * 验证身份证位数,15位和18位身份证
     *
     * @param code
     * @return
     */

    public static boolean verifyLength(String code) {
        int length = code.length();
        if (length == 15 || length == 18) {
            return true;
        } else {
            return false;
        }
    }

}
