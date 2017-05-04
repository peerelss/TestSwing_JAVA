package pinterestpic.test;

import model.TumblrImagesManager;
import pinterestpic.conf.PinterestConf;
import pinterestpic.utils.CurlUtil;
import pinterestpic.utils.PintJsonUtil;
import pinterestpic.utils.PinterestStringUtils;
import sun.rmi.runtime.Log;
import util.TumbrJsonUtil;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.logging.Logger;

/**
 * Created by root on 17-4-27.
 */
public class TestPinterest {
    static Logger log = Logger.getLogger(TumblrImagesManager.class.getName());
    static int i=0;
    public static void main(String[] args)throws Exception{
      //  CurlUtil.getStringFromCurl(PinterestConf.urlInit,"baidu.html");
     //   log.info(PinterestStringUtils.getPinterestAJAXURL(""));
        getPinterest("");

    }
    public static void getPinterest(String book){
        try{
        log.info(" page = "+i);
        i++;
        CurlUtil.getStringFromCurl(PinterestStringUtils.getPinterestAJAXURL(book),"baidu.html");
        String result = TumbrJsonUtil.getStringFromTxt("/home/kevin/Documents/word_space/javaproject/TestSwing/baidu.html");
        System.out.println(result);
        PintJsonUtil.getPicFromAjaxJsonCacha(result);
        String bookMarks=PintJsonUtil.getBookMarksFromJson(result);
        log.info("book marks = "+bookMarks);
        if(i>0){
            return;
        }
        if(bookMarks==null|bookMarks.equals("")){
            return;
        }else {
            getPinterest(bookMarks);
        }}catch (Exception e){
            e.printStackTrace();
        }
    }
}

