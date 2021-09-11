package com.example.demo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gpk on 2019/7/18.
 */
public class RegExpUtil {
    public static boolean checkEmail(String str){
        // 邮箱验证规则
        String regEx = "[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}";
        return  regCheck(str,regEx);
    }
    public static boolean checkName(String str){
        // 用户名验证规则 只包含中文、英文、下划线
        String regEx = "^[一-龥A-Za-z0-9_]{1,10}$";
        return  regCheck(str,regEx);
    }
    public static boolean checkPsw(String str){
        //检查密码， 密码(长度在6~30之间，只能包含字母、数字和下划线)：
        String regEx = "^[A-Za-z0-9_]{6,30}$";
        return  regCheck(str,regEx);
    }

    /**
     * 校验IP地址和端口（127.0.0.1：80）
     * @param str
     * @return
     */
    public static boolean checkUrl(String str){
        if(str.isEmpty()) return false;
        if(!str.contains(":")) str+=":80";
        //检查ip;port
        String regEx = "((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}:(\\d+)";
        return  regCheck(str,regEx);
    }

    public static String getCheckUrl(String str)
    {
        String regEx = "((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}:(\\d+)";
        String result=getRegCheckData(str,regEx);
        if(!result.isEmpty()){
            result="http://"+result+"/";
        }
        return result;
    }

    /**
     * @param str 被匹配的字符串
     * @param regEx 正则表达式
     * @return  返回匹配字符串
     */
    private static String getRegCheckData(String str, String regEx ) {
        if (str == null || str.equals("")) {
            return "";
        }
        String result = "";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            result=matcher.group();
        }
        return  result;
    }

    /**
     * @param str 被匹配的字符串
     * @param regEx 正则表达式
     * @return  是否匹配成功
     */
    private static boolean regCheck(String str, String regEx ){
        if (str == null || str.equals("")) {
            return false;
        }
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        // 字符串是否与正则表达式相匹配
        boolean rs = matcher.matches();
        return rs;
    }
}
