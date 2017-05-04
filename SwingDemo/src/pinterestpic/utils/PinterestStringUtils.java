package pinterestpic.utils;

import model.TumblrImagesManager;
import pinterestpic.conf.PinterestConf;

import java.net.URLEncoder;
import java.util.logging.Logger;

/**
 * Created by root on 17-4-27.
 * 第一个URL手动输入，第二个开始从链接列解析
 */
public class PinterestStringUtils {
    static Logger log = Logger.getLogger(TumblrImagesManager.class.getName());
    public static String getPinterestAJAXURL(String tag){
        try{
            String url="";
            if(tag==null||tag.equals("")){
                url= PinterestConf.urlInit;
            }else {
                url=PinterestConf.urlNormal.replace(PinterestConf.PINTEREST_TAG,tag);
            }
            url=PinterestConf.PINTERES_HEAD_URL+URLEncoder.encode(url,"UTF-8");
            url=url.replace("%26","&").replace("%3D","=");
            log.info("url  = " +url);
            return url;
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }

    }
}
