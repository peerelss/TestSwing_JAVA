package pinterestpic.test;

import model.TumblrImagesManager;

import java.net.URLEncoder;
import java.util.logging.Logger;

/**
 * Created by root on 17-4-27.
 */
public class StringUtilsTest {
    static Logger log = Logger.getLogger(TumblrImagesManager.class.getName());
    public static void main(String[] args)throws  Exception{
        String url="http://cfake.com/picture/Strapon/62/3";
        url=url.substring(url.indexOf("picture")+8,url.length()-5);
        System.out.println(url);
    }
}
