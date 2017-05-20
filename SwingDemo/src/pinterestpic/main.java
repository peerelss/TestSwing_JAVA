package pinterestpic;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import pinterestpic.utils.CurlUtil;
import pinterestpic.utils.OKUtil;
import pinterestpic.utils.PintJsonUtil;
import util.TumbrJsonUtil;


import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;

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
     //   String result= TumbrJsonUtil.getStringFromTxt(file_path);
      //  PintJsonUtil.getPicFromAjaxJson(result);
        try {
            Files.write(Paths.get("test.txt"), "Hello World".getBytes());
        }catch (Exception e){

        }
    }
    public static String big5ToChinese( String s )
    {
        try{
            if ( s == null || s.equals( "" ) )
                return("");
            String newstring = null;
            newstring = new String( s.getBytes( "big5" ), "gb2312" );
            return(newstring);
        }
        catch ( UnsupportedEncodingException e )
        {
            return(s);
        }
    }


    public static String ChineseTobig5( String s )
    {
        try{
            if ( s == null || s.equals( "" ) )
                return("");
            String newstring = null;
            newstring = new String( s.getBytes( "gb2312" ), "big5" );
            return(newstring);
        }
        catch ( UnsupportedEncodingException e )
        {
            return(s);
        }
    }
}
