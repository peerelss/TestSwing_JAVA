package model;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import net.NetRequest;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.logging.Log;
import org.apache.http.util.TextUtils;
import util.TumbrJsonUtil;

import javax.swing.*;
import javax.swing.plaf.TextUI;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static util.TumbrJsonUtil.getImageUrlFromHtml;

/**
 * Created by kevin on 2016/12/13.
 */
public class TumblrImagesManager {
    private  String sFileName="";
    static Logger log = Logger.getLogger(TumblrImagesManager.class.getName());
    public static final String LINE_END="\r\n",PAGE="page/";
    private String mName="";
    //是否取背景图片
    //是否读取JSON
    /**
    * 读取JSON使用因为某些域名对应的会跳转到DashBoard，将会以JSON的形式交互。
     * */
    private boolean isBack=false;
    private boolean isJson=false;

    int mBeginSize=0;
    int mEndSize=Integer.MAX_VALUE;

    NetRequest netRequest;


    private Queue<String> mUrls=new LinkedList<>();
    private Queue<String> mFrameUrls=new LinkedList<>();
    private static TumblrImagesManager ourInstance = new TumblrImagesManager();
    public static TumblrImagesManager getInstance() {
        return ourInstance;
    }

    private TumblrImagesManager() {
    }
    public List<String> tumblrUrls=new ArrayList<>();
    public void getImages(String str, NetRequest netRequest){
        if(null!=netRequest){
            netRequest.onOperaSuccess(str);
        }
    }
    public void setsFileName(String s){
        //http://strictwidows.tumblr.com/
        // https://www.tumblr.com/dashboard/blog/bigpunisher2b
        if(isJson){
            if(!TextUtils.isEmpty(s)&&s.contains("dashboard")&&s.contains("blog")){
                sFileName=s.substring(s.indexOf("blog")+5);
            }else {
                sFileName=s;
            }
        }else {
            if(!TextUtils.isEmpty(s)&&s.contains("//")&&s.contains("tumblr")){
                sFileName=s.substring(s.indexOf("//")+2,s.indexOf("tumblr")-1);
            }else {
                sFileName=s;
            }
        }

    }
    public String getsFileName(){
        if(TextUtils.isEmpty(sFileName)){
            return "temp";
        }else {
            return sFileName;
        }
    }
    public void setmBeginSize(int i){
        this.mBeginSize=i;
    }
    public void setmEndSize(int i){
        this.mEndSize=i;
    }
    public void init(String domName,int begin,int size,NetRequest netRequest){
        this.mName=domName;
        this.mBeginSize=begin;
        this.mEndSize=size;

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 1080)))
                .build();
        OkHttpUtils.initClient(okHttpClient);
        setsFileName(mName);
        this.netRequest=netRequest;
        createTaskList();
        getImages();
    }


    private void getImages(){
        if(mUrls==null||mUrls.size()==0){
            return;
        }
        //开启新一个线程，但是在新线程里使用同步方法，避免对服务请求过多
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                while (mFrameUrls!=null&&!mFrameUrls.isEmpty()){
                    getImageFromHtml(mFrameUrls.remove());
                }
                while (mUrls!=null&&!mUrls.isEmpty()){
                    final String s=mUrls.remove();
                    if(netRequest!=null){
                        netRequest.onOperaSuccess(s);
                    }
                    if(isJson){
                        getImagesFromJson(s);
                    } else {
                        getImageFromHtml(s);
                    }
                }
            }
        });
        thread.start();
    }
    //请求JSON
    private void getImagesFromJson(String s){

        try{
        Response response   =  OkHttpUtils
                .get()
                .url(s)
                .addHeader("Cookie", "tmgioct=584bd56b034f640208077880; rxx=385763q1nus.jqrxs9b&v=1; _ga=GA1.2.1832574784.1481612290;" +
                        " __utma=189990958.1832574784.1481612290.1481612291.1481612291.1; __utmb=189990958.0.10.1481612291;" +
                        " __utmc=189990958; __utmz=189990958.1481612291.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none);" +
                        " yx=42waufup4i6ii%26o%3D3%26f%3Dhy; devicePixelRatio=1; documentWidth=1412;" +
                        " anon_id=LZLCEVYSNUXYFMUEEEDPKJOAOLWVNKSA; pfp=rl9LFif9q4AX8nFVLqULAZJRJkskdPMYH2RjbBqN;" +
                        " pfs=MEo0yq0n8Zh68Hib8SzvGx3d4; pfe=1489388660; pfu=240913938; language=%2Czh_CN; logged_in=1; nts=false")
                .addHeader("Accept",
                        "application/json, text/javascript, */*; q=0.01")
                .addHeader("Accept-Language",
                        "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                .addHeader("Connection",
                        "keep-alive")
                .addHeader("Host",
                        "www.tumblr.com")
                .addHeader("Referer",
                        "https://www.tumblr.com/dashboard/blog/bigpunisher2b")
                .addHeader("Host",
                        "www.tumblr.com")
                .addHeader("X-Requested-With",
                        "XMLHttpRequest")
                .addHeader("User-Agent",
                        "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:50.0) Gecko/20100101 Firefox/50.0")
                .addHeader("X-tumblr-form-key",
                        "wWoV0Es0Ij4n2gGAFJldazDnMk")
                .build()
                .execute();
            String html=response.body().string();
            TumbrJsonUtil.getImageUrlFromJson(html, new NetRequest() {
                @Override
                public void onOperaSuccess(String str) {
                    /*if(!TextUtils.isEmpty(str)){
                        System.out.println("result = "+str);
                        String[] strings=str.split(",");
                        for(int i=0;i<strings.length;i++){
                            mFrameUrls.add(strings[i]);
                        }
                    }*/
                }

                @Override
                public void onOperaFailure() {

                }

                @Override
                public void onOver() {

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void getImageFromHtml(String s){
        System.out.println("url = "+s);
        try {
            Response response = OkHttpUtils
                    .get()//
                    .url(s)//
                    .build()//
                    .execute();
            String html=response.body().string();
            getImageUrlFromHtml(html, new NetRequest() {
                @Override
                public void onOperaSuccess(String str) {
                    if(!TextUtils.isEmpty(str)){
                        System.out.println("result = "+str);
                        String[] strings=str.split(",");
                        for(int i=0;i<strings.length;i++){
                            mFrameUrls.add(strings[i]);
                        }
                    }
                }

                @Override
                public void onOperaFailure() {

                }
                @Override
                public void onOver() {
                    mUrls.clear();
                    log.info("抓取结束");

                }
            });

        }catch (Exception e){
            e.printStackTrace();

        }


    }
    private void getBackGroundImage(String s){

    }
    private void createTaskList(){
        mUrls=new LinkedList<>();
        String urlStr="https://www.tumblr.com/svc/indash_blog/posts?tumblelog_name_or_id="
                +sFileName+"&post_id=&limit=10&offset=";
        if(mName.endsWith("com")){
            mName=mName+"/";
        }
        for(int i=mBeginSize;i<=mEndSize;i++){
            if(isJson){
                mUrls.add(urlStr+i*10);
            }else {
                mUrls.add(mName+PAGE+i);
            }

        }
    }


}
