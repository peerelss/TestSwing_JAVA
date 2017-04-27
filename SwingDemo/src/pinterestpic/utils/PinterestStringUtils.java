package pinterestpic.utils;

import pinterestpic.conf.PinterestConf;

/**
 * Created by root on 17-4-27.
 * 第一个URL手动输入，第二个开始从链接列解析
 */
public class PinterestStringUtils {
    public static String getPinterestAJAXURL(String tag){
        return PinterestConf.urlNormal.replace(PinterestConf.PINTEREST_TAG,tag);
    }
}
