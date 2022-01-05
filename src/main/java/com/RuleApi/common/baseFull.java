package com.RuleApi.common;

//常用数据处理类

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class baseFull {
    //数组去重
    public Object[] threeClear(Object[] arr){
        List list = new ArrayList();
        for(int i=0;i<arr.length;i++){
            if(!list.contains(arr[i])){
                list.add(arr[i]);
            }
        }
        return list.toArray();
    }
    //获取字符串内图片地址
    public List<String> getImageSrc(String htmlCode) {
        List<String> containedUrls = new ArrayList<String>();
        String urlRegex = "((https?|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
        Matcher urlMatcher = pattern.matcher(htmlCode);

        while (urlMatcher.find())
        {
            containedUrls.add(htmlCode.substring(urlMatcher.start(0),
                    urlMatcher.end(0)));
        }
        List<String> imageList = new ArrayList<String>();
        for (int i = 0; i < containedUrls.size(); i++) {
            String word = containedUrls.get(i);
            if(word.indexOf(".jpg") != -1||word.indexOf(".jpeg") != -1||word.indexOf(".png") != -1||word.indexOf(".bmp") != -1||word.indexOf(".gif") != -1){
                imageList.add(word.replaceAll("\\)",""));
            }
        }
        return imageList;
    }
    //获取markdown内图片引用
    public List<String> getImageCode(String htmlCode) {
        List<String> containedUrls = new ArrayList<String>();
        String urlRegex = "((!\\[)[\\s\\S]+?(\\]\\[)[\\s\\S]+?(\\]))";
        Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
        Matcher urlMatcher = pattern.matcher(htmlCode);

        while (urlMatcher.find())
        {
            containedUrls.add(htmlCode.substring(urlMatcher.start(0),
                    urlMatcher.end(0)));
        }
        List<String> codeList = new ArrayList<String>();
        for (int i = 0; i < containedUrls.size(); i++) {
            String word = containedUrls.get(i);

            codeList.add(word);
        }
        return codeList;
    }
    public static boolean isEmail(String string) {
        if (string == null)
            return false;
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(string);
        if (m.matches())
            return true;
        else
            return false;
    }
    //获取markdown引用的图片地址
    public List<String> getImageMk(String htmlCode) {
        List<String> containedUrls = new ArrayList<String>();
       // String urlRegex = "\\\\[\\\\d\\\\]:\\\\s(https?|http):((//)|(\\\\\\\\))+[\\\\w\\\\d:#@%/;$()~_?\\\\+-=\\\\\\\\\\\\.&]*";
        String urlRegex = "\\[\\d\\]:\\s(https?|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*";
        Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
        Matcher urlMatcher = pattern.matcher(htmlCode);

        while (urlMatcher.find())
        {
            containedUrls.add(htmlCode.substring(urlMatcher.start(0),
                    urlMatcher.end(0)));
        }
        List<String> imageCode = new ArrayList<String>();
        for (int i = 0; i < containedUrls.size(); i++) {
            String word = containedUrls.get(i);
            if(word.indexOf(".jpg") != -1||word.indexOf(".jpeg") != -1||word.indexOf(".png") != -1||word.indexOf(".bmp") != -1||word.indexOf(".gif") != -1){
                imageCode.add(word.replaceAll("\\)",""));
            }
        }
        return imageCode;
    }
    //获取ip地址
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress="";
        }
        // ipAddress = this.getRequest().getRemoteAddr();

        return ipAddress;
    }
}
