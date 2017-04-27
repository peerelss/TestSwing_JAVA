package pinterestpic.test;

import model.TumblrImagesManager;
import pinterestpic.conf.PinterestConf;
import pinterestpic.utils.CurlUtil;
import pinterestpic.utils.PintJsonUtil;
import pinterestpic.utils.PinterestStringUtils;
import sun.rmi.runtime.Log;
import util.TumbrJsonUtil;

import java.util.logging.Logger;

/**
 * Created by root on 17-4-27.
 */
public class TestPinterest {
    static Logger log = Logger.getLogger(TumblrImagesManager.class.getName());
    public static void main(String[] args){
      //  CurlUtil.getStringFromCurl(PinterestConf.urlInit,"baidu.html");
        getPinterest();

    }
    public static void getPinterest(){
        CurlUtil.getStringFromCurl(PinterestConf.urlInit,"baidu.html");
        String result = TumbrJsonUtil.getStringFromTxt("/home/kevin/Documents/word_space/javaproject/TestSwing/baidu.html");
        System.out.println(result);
        PintJsonUtil.getPicFromAjaxJsonCacha(result);
        log.info(PintJsonUtil.getBookMarksFromJson(result));
    }
}

