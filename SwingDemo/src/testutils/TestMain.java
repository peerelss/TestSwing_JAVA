package testutils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import okhttp3.*;
import org.apache.http.util.TextUtils;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.FrameTag;
import org.htmlparser.tags.ScriptTag;
import org.htmlparser.tags.StyleTag;
import org.htmlparser.util.NodeList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.TumbrJsonUtil;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by kevin on 2016/12/14.
 */
public class TestMain {
    public static void main(String[] args){
        try {
            Response response = OkHttpUtils
                    .post()//
                    .url("")//
                    .addParams("q","ig_user(3305267982)+{+media.after(1445998907329897377,+12)+{++count,++nodes+{++++__typename,++++caption,++++code,++++comments+{++++++count++++},++++comments_disabled,++++date,++++dimensions+{++++++height,++++++width++++},++++display_src,++++id,++++is_video,++++likes+{++++++count++++},++++owner+{++++++id++++},++++thumbnail_src,++++video_views++},++page_info}+}")
                    .addParams("ref","users::show")
                    .addParams("query_id","17849115430193904")
                    .build()//
                    .execute();
            String html = response.body().string();
            System.out.println(html);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void getStringFromTxt(){
        String filePath="/media/kevin/E/instagramwithcookit.html";
        String result= TumbrJsonUtil.getStringFromTxt(filePath);
        if(!TextUtils.isEmpty(result)){
            try{
                Document document=Jsoup.parse(result);
                Elements elements=document.select("script");
                for(Element element:elements){
                    String str=element.toString();
                    if(str.startsWith("<script type=\"text/javascript\">window._sharedData")){
                        str=str.replace("<script type=\"text/javascript\">window._sharedData = ","").replace(";</script>","");
                        JSONObject jsonObject= JSON.parseObject(str);
                        getInstagram(jsonObject);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
    public static void getInstagram(JSONObject jsonObject){
        JSONObject data=jsonObject.getJSONObject("entry_data");
        JSONArray page=data.getJSONArray("ProfilePage");
        JSONObject page1=page.getJSONObject(0);
        JSONObject user=page1.getJSONObject("user");
        JSONObject media=user.getJSONObject("media");
        JSONArray nodes=media.getJSONArray("nodes");
        for(int i=0;i<nodes.size();i++){
            JSONObject object=JSON.parseObject(nodes.get(i).toString());
            System.out.println(object.getString("display_src"));
        }
    }
    static class ScriptFilter implements NodeFilter {
        public boolean accept(Node node) {
            if(node == null)
                return false;
            if(node instanceof Tag){
                String tag = ((Tag)node).getTagName();
                if("iframe".equalsIgnoreCase(tag))
                    return false;
            }
            if(node instanceof StyleTag)
                return false;
            if(node instanceof ScriptTag)
                return true;
            if(node instanceof FrameTag)
                return false;
            //if(node.getParent()!=null)
            //  return accept(node.getParent());
            return false;
        }
    }
}
