package pinterestpic.utils;

import org.apache.http.util.TextUtils;
import util.TumbrJsonUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by root on 17-2-21.
 */
public class CurlUtil {
    public static String getStringFromCurl(String url,String fileName){

        String []cmds = {"curl"
                ,"-o"+fileName
              //  ,url
                , "https://www.pinterest.com/resource/BoardFeedResource/get/?source_url=%2Fgurra68%2Fencasement%2F&data=%7B%22options%22%3A%7B%22board_id%22%3A%22388224499067219110%22%2C%22is_collaborative%22%3Afalse%2C%22prepend%22%3Atrue%2C%22show_done%22%3Afalse%2C%22centered%22%3Atrue%2C%22board_url%22%3A%22%2Fgurra68%2Fencasement%2F%22%2C%22page_size%22%3Anull%2C%22add_vase%22%3Atrue%2C%22access%22%3A%5B%5D%2C%22board_layout%22%3A%22default%22%2C%22show_rich_snippet%22%3Afalse%2C%22owner%22%3A%22gurra68%22%2C%22bookmarks%22%3A%5B%22LT4zODgyMjQ0MzAzNjEyMzY0NDA6MjA6MjB8M2QxZmRmMGQzMTM0NmRlMGFiZTZhY2IxMzAwYTJiM2NlNDdjZmY4NzdkY2I3Y2MxOTRmZmFmZmVmZGQ1YWZjNw%3D%3D%22%5D%2C%22followed_by_me%22%3Afalse%7D%2C%22context%22%3A%7B%7D%7D&module_path=App%3EBoardPageWrapper%3EBoardPage%3EGrid%3EGridItems%3EPin(component_type%3D0%2C+dynamic_insertion_channel%3Dboard%2C+main_module_name%3Dnull%2C+shown_from_own_board%3Dfalse%2C+show_board%3Dfalse%2C+use_native_image_width%3Dtrue%2C+show_comments%3Dtrue%2C+delink_commenter%3Dfalse%2C+squish_giraffe_pins%3Dnone%2C+show_pinner%3Dfalse%2C+show_pinned_from%3Dtrue%2C+resource%3DPinResource(main_module_name%3Dnull%2C+id%3D388224430364339715))&_=1487660253070"
                , "-H","Host: www.pinterest.com"
                , "-H","User-Agent: Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:50.0) Gecko/20100101 Firefox/50.0"
                , "-H","Accept: application/json, text/javascript, */*; q=0.01"
                , "-H","Accept-Language: en-US,en;q=0.5"
                , "-H","Referer: https://www.pinterest.com/"
                , "-H","X-Pinterest-AppState: active"
                , "-H","X-Pinterest-ExperimentHash: 2546638680766220712"
                , "-H","X-NEW-APP: 1"
                , "-H","X-APP-VERSION: 9b58c3a"
                , "-H","X-Requested-With: XMLHttpRequest"
                , "-H","Cookie: _auth=1; csrftoken=h1t07nv1EwoACnj4YATo3JdulEFeTgrC; _pinterest_sess=\"TWc9PSZyeEkvcER6V25IbGpZKzJwQjQxSnlRajJYcFZBWFZHb0xoTGxNVU8yQVpSeS9ZWlpZSE1scEkxL0hxdzdqRHFaZmFpcDB3M25CbHgrTTdKSnI1Z05Oc3l6N1o2ZDBmKzNTOGZKaHA4U3UzbGdkMlFmbm5ZaGRkTXl6VmxyZnRDdG0xYi80WjVFMUoxckRpMEhLU1NaMnhDa0QwY1hnMjBMMHU2alVEekVEQVR3U2tweFgxc051RjNtcjd1VFhxR0ozaWsvQWp6bHdhdmU5WXRZNk43MnhpYjFwbDBRSGx0OXh4QWt5RDdMby9xMnpRanZIQkNoTUEvemk3YTdKR3NWNUNpd3IrZVRLZTZkVFh3ejRuUUE3Zm15LzI2bWFiL0lSTFpGaS9LZFNobkE3aVJUV040Qm1PMzVUUXlTb0xSNGhBandyU0R1K1RmenBXZ29mK1p5QW1hY2R5cGtLU3U2dS9jMU9zK1J6OHMrMmk2OVJaZzZlR1ROOGVZNXZLV0hLbTZZYkwyYktrb25ka2M2OStVNUZFRGQ3UFp6bTl0NER2d09Qc0ttb0VVQklDYXZsNE9MaktHYUpNaU1hUFNtZksyRzF2bXFoUENPNXRHbVNZM0UySHZONkJEd2JGMEl0dm1HRUJJZjVMMD0md3A3MGVIMHM4WnlNSTExUmUvcTQ0Yk1FYjRvPQ==\"; cm_sub=none; us_open_group=enabled4; c_dpr=1; bei=false; _pinterest_referrer=\"https://www.google.com/\"; sessionFunnelEventLogged=1; _b=\"ASVDqvk7IwNIt7lGeUGGlLtUxxeRbC8vhgLEy1UYBuu69V15P4E7a0XHL9K5Aw/9S7k=\""
                , "-H","Connection: keep-alive"
        };
        ProcessBuilder pb=new ProcessBuilder(cmds);
        pb.redirectErrorStream(true);
        Process p;
        try {
            p = pb.start();
            BufferedReader br=null;
            String line=null;

            br=new BufferedReader(new InputStreamReader(p.getInputStream()));
            String result=PintJsonUtil.getStringFromBuffer(br);
            br.close();
            return result;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
    }
    public static void main(String[] args) {
        /*String []cmds = {"curl", "-l","-o baidu.html","http://www.baidu.com"};
        ProcessBuilder pb=new ProcessBuilder(cmds);
        pb.redirectErrorStream(true);
        Process p;
        try {
            p = pb.start();
            BufferedReader br=null;
            String line=null;

            br=new BufferedReader(new InputStreamReader(p.getInputStream()));
            while((line=br.readLine())!=null){
                System.out.println("\t"+line);
            }
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
        String result =TumbrJsonUtil.getStringFromTxt("../../../../baidu.html");
        System.out.println(result);
    }
}
