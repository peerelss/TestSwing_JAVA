package instagrampic.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.util.TextUtils;
import util.TumbrJsonUtil;

/**
 * Created by root on 17-2-17.
 */
public class InstagramStringUtils {
    public static String ID_FROM="";
    public static String getInstPicFromJsonString(String json){
        if(TextUtils.isEmpty(json)){
            json= TumbrJsonUtil.getStringFromTxt("/home/kevin/Documents/instagram/in_2.json");
        }
        JSONObject jsonObject= JSON.parseObject(json);
        JSONArray nodes=jsonObject.getJSONObject("media").getJSONArray("nodes");
        if(nodes!=null){
            for(int i=0;i<nodes.size();i++){
                JSONObject node= JSON.parseObject(nodes.get(i).toString());
                System.out.println(node.getString("id"));
                System.out.println(node.getString("display_src"));
            }
        }
        return "";
    }
}
