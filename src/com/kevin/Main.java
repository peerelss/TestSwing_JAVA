package com.kevin;

import javax.xml.soap.Text;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String url="app/doDirectGiftCardApp?uin=4076&type=5&appType=3&paymentCode=wxap&salesRequireId=752&total=6.74";
        String url1=url.substring(url.indexOf("?")+1);
        String[] urls=url1.split("&");
        Map<String,String> map=new HashMap<>();
        for(int i=0;i<urls.length;i++){
            map.put(getKeyFromString(urls[i]),getValueFromString(urls[i]));
        }
        for(String s:map.keySet()){
            System.out.println(s+" = "+map.get(s));
        }
    }
    private static String getKeyFromString(String url){
        if(url.contains("=")){
            return url.substring(0,url.indexOf("="));
        }else {
            return url;
        }
    }
    private static String getValueFromString(String url){
        if(url.contains("=")){
            return url.substring(url.indexOf("=")+1);
        }else {
            return url;
        }
    }
}
