package pinterestpic;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import pinterestpic.utils.CurlUtil;
import pinterestpic.utils.OKUtil;
import pinterestpic.utils.PintJsonUtil;
import util.TumbrJsonUtil;

/**
 * Created by root on 17-2-21.
 */
public class main {
    public static String url="https://www.pinterest.com/gurra68/encasement/";
    public static String file_path="/home/kevin/Documents/word_space/javaproject/TestSwing/baidu.html";
    public static void main(String[] args){
       // CurlUtil.getStringFromCurl("","baidu.html");
       /* String result= TumbrJsonUtil.getStringFromTxt("/media/kevin/SWAP/pinterest/encase.html");
        Document document= Jsoup.parse(result);
        Element element=document.getElementById("jsInit1");
        String e=element.html();
        PintJsonUtil.getUrlFromJson(e);*/
        String result= TumbrJsonUtil.getStringFromTxt(file_path);
      //  PintJsonUtil.getPicFromAjaxJson(result);
    }
}
