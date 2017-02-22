package instagrampic;

import instagrampic.utils.InstagramStringUtils;
import org.apache.http.util.TextUtils;
import pinterestpic.utils.PintJsonUtil;

import javax.xml.soap.Text;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by root on 17-2-17.
 */
public class Main {
    public static String DATA="q=ig_user(3305267982)+%7B+media.after(1438845910787541525%2C+12)+%7B%0A++count%2C%0A++nodes+%7B%0A++++__typename%2C%0A++++caption%2C%0A++++code%2C%0A++++comments+%7B%0A++++++count%0A++++%7D%2C%0A++++comments_disabled%2C%0A++++date%2C%0A++++dimensions+%7B%0A++++++height%2C%0A++++++width%0A++++%7D%2C%0A++++display_src%2C%0A++++id%2C%0A++++is_video%2C%0A++++likes+%7B%0A++++++count%0A++++%7D%2C%0A++++owner+%7B%0A++++++id%0A++++%7D%2C%0A++++thumbnail_src%2C%0A++++video_views%0A++%7D%2C%0A++page_info%0A%7D%0A+%7D&ref=users%3A%3Ashow&query_id=17849115430193904";
    public static String REPLACE_DATA="1438845910787541525%2C";
    public static void main(String[] args) {
        InstagramStringUtils.getInstPicFromJsonString(testCurl(""));
    }
    public static String testCurl(String dataNew){
        String resultReplace="";
        if(TextUtils.isEmpty(dataNew)){
            resultReplace=REPLACE_DATA;
        }else {
            resultReplace=dataNew;
        }
        String []cmds = {"curl", "-x","127.0.0.1:1080", "https://www.instagram.com/query/"
                , "-H","Host: www.instagram.com"
                , "-H","User-Agent: Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:50.0) Gecko/20100101 Firefox/50.0"
                , "-H","Accept: */*"
                , "-H","Accept-Language: en-US,en;q=0.5"
                , "-H","X-CSRFToken: Ch9FKU4v1BDxTU5SgaX8Kr7kHJ7xGZCy"
                , "-H","X-Instagram-AJAX: 1"
                , "-H","Content-Type: application/x-www-form-urlencoded"
                , "-H","X-Requested-With: XMLHttpRequest"
                , "-H","Referer: https://www.instagram.com/mspervology/"
                , "-H","Cookie: csrftoken=Ch9FKU4v1BDxTU5SgaX8Kr7kHJ7xGZCy; mid=WGHUfQAEAAFNhY5nAkhrXn7Crfuh; fbm_124024574287414=base_domain=.instagram.com; sessionid=IGSC3aa9f2686916aada5633e5a44bc7369ac2ee91f937810f9b45030711d3a6444e%3A7LrfTJhkd54jpKKbjcsp9snY2AwOezIG%3A%7B%22_token%22%3A%223650320505%3AGcALY6TZ1KzTntKlkVnzTCp4q2CTUBLl%3Aef1fbb99d8a1c524dbfc439d77a5377e0016f79e1a63df5ec4955164accca2ac%22%2C%22_token_ver%22%3A2%2C%22_platform%22%3A4%2C%22asns%22%3A%7B%22time%22%3A1487225751%7D%2C%22_auth_user_backend%22%3A%22accounts.backends.CaseInsensitiveModelBackend%22%2C%22_auth_user_id%22%3A3650320505%2C%22_auth_user_hash%22%3A%22%22%2C%22last_refreshed%22%3A1487225752.1594532%7D; ds_user_id=3650320505; fbsr_124024574287414=-VxiaxEadmjfDL8u2ZYhlsI8cL8NK2oVbaKkRApBWKU.eyJhbGdvcml0aG0iOiJITUFDLVNIQTI1NiIsImNvZGUiOiJBUUNoRGc4MTNmNDI3MkxiQURGaHJOSGNDQXZDMGhYVjZqMVYtLVVLelRWcHJUeDhHbEhTMXhyTzFCYU8zZ0VhT09FVTIyNWt6X1VVRi1OcFVGdC1iY29hN1lDN1ZMZ3MxZlZFQ2lxMGtTM0ppUUMzU2hEVHNuWFVrbnhWbWtOeWstaFVCOTdzcnBJM0c4cTA2RXY3MzZaNWRGUkFieGVINUMyeEZvRzY1MWlLaE9pS3BScENUQ2NaOTZQMmtQcE5rRmNseEN0Zk5KTlFKcUFoQXRyNDhvNDhuU1FDZUdsZDFqMDJrZDI4T09DQmRYUVZSUnNnRUJCOUpDM3g4eGNIMlJldlNGcUlyVU1lZDZwSF8tTHdPNVJNa2tuLWNxN1k1MEF6TEZ2LXpPS2dGUDlTSEZha3RpMlBfdGs3Y1diVXdVZnBCc3FSQUNnaUpMNzFQU1FsYVFBVCIsImlzc3VlZF9hdCI6MTQ4NzIzOTMzMiwidXNlcl9pZCI6IjEwMDAwNzAwMTEwOTMzNyJ9; ig_pr=1; ig_vw=1859; s_network=\"\""
                , "-H","Connection: keep-alive"
                ,"--data",DATA.replace(REPLACE_DATA,resultReplace)
        };
        ProcessBuilder pb=new ProcessBuilder(cmds);
        pb.redirectErrorStream(true);
        Process p;
        try {
            p = pb.start();
            BufferedReader br=null;
            String line=null;

            br=new BufferedReader(new InputStreamReader(p.getInputStream()));
            String result="";
            result= PintJsonUtil.getStringFromBuffer(br);

            br.close();
        //    System.out.println(result);
            return result;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
    }

}
