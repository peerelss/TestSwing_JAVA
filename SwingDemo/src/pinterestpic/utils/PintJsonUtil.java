package pinterestpic.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;

/**
 * Created by root on 17-2-21.
 */
public class PintJsonUtil {
    public static int result_sum =0;
    public static void getUrlFromJson(String json){
        try{
            JSONObject jsonObject= JSON.parseObject(json);
            JSONObject tree=jsonObject.getJSONObject("tree");
            JSONArray children=tree.getJSONArray("children");
            if(children!=null){
                for(int i=0;i<children.size();i++){
                    JSONObject child=children.getJSONObject(i);
                    JSONArray children1=child.getJSONArray("children");
                    JSONObject data=children1.getJSONObject(0).getJSONObject("data");
                    getChildPicFromJson(data);
                    JSONArray children_1=children1.getJSONObject(0).getJSONArray("children");
                    getChildPicFromJson(children_1);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void getChildPicFromJson(JSONObject data){
        JSONArray board_feed=data.getJSONArray("board_feed");
        for(int i=0;i<board_feed.size();i++){
            result_sum++;
            JSONObject image=JSON.parseObject(board_feed.get(i).toString());
            System.out.println(image.getString("domain"));
            System.out.println(image.getJSONObject("images").getJSONObject("736x").getString("url"));
        }
        System.out.println(" result_sum = "+ result_sum);

    }
    public static void getChildPicFromJson(JSONArray children){
        for(int i=0;i<children.size();i++){
            JSONObject jsonObject=JSON.parseObject(children.get(i).toString());
            JSONObject options=jsonObject.getJSONObject("options");
            JSONObject props=options.getJSONObject("props");
            JSONObject data=props.getJSONObject("data");
            getChildPicFromJson(data);

        }
    }
    public static String getStringFromBuffer(BufferedReader br){
        try{
            String result="",line="";
            while((line=br.readLine())!=null){
                /*if(line.contains("{")){
                    if(line.startsWith("\"")){
                        result=result+line;
                    }else {
                        result=result+line.substring(line.indexOf("{"));
                    }
                }*/
                System.out.println(line);
                result=result+line;
            }
            System.out.println(result);
            return  result;
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }
    public static void getPicFromAjaxJsonCacha(String str){
        JSONObject jsonObject=JSON.parseObject(str);
        JSONArray datas=jsonObject.getJSONArray("resource_data_cache").getJSONObject(0).getJSONArray("data");
        for(int i=0;i<datas.size();i++){
            JSONObject data=datas.getJSONObject(i);
            System.out.println(data.getString("domain"));
            String originUrl=data.getJSONObject("images").getJSONObject("orig").getString("url");
            System.out.println(originUrl);
        }
    }
}
