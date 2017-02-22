package model;

import com.zhy.http.okhttp.OkHttpUtils;
import net.NetRequest;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.apache.http.util.TextUtils;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.util.NodeList;
import util.TumbrJsonUtil;
import util.TumblrTest;
import javax.xml.soap.Text;
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
 * Created by root on 16-12-29.
 */
public class TumblrManager2 {
    public static Logger log = Logger.getLogger(TumblrManager2.class.getName());
    //文件路径名
    public static String path= "/tumblr/result";
    //异常文件路径名
    public static String exceptionPath="/tumblr/exception";
    public static String PAGE="page/";
    //需要保存的文件名
    private String fileName="";
    //请求的域名
    private String domName="";
    //原生任务列表
    private Queue<String> allTaskList=new LinkedList<>();
    //二次读取图片列表
    private Queue<String> imagesFrameTaskList=new LinkedList<>();
    //二次读取视频列表
    private Queue<String> videosFrameTaskList=new LinkedList<>();

    private static TumblrManager2 ourInstance = new TumblrManager2();
    public static TumblrManager2 getInstance() {
        return ourInstance;
    }
    private TumblrManager2() {
    }

    //处理返回的接口
    NetRequest netRequest;

    //是否读取背景图片
    private boolean isBack=false;
    //是否读取JSON
    private boolean isJson=false;
    private int beginPage,endPage;
    public void init(String domName,int begin,int size,NetRequest netRequest){
        if(allTaskList.size()!=0||videosFrameTaskList.size()!=0||videosFrameTaskList.size()!=0){
            log.info("当前任务未完成，返回");
            return;
        }
        this.domName=domName;
        setFileName(domName);
        this.beginPage=begin;
        this.endPage=size;
        this.netRequest=netRequest;

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 1080)))
                .build();
        OkHttpUtils.initClient(okHttpClient);

        createAllTask();
        beginTask();
    }

    //开始抓取
    private void beginTask(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(allTaskList.size()!=0||videosFrameTaskList.size()!=0||imagesFrameTaskList.size()!=0){
                    while (videosFrameTaskList.size()!=0){
                        String videoString=getStringFromUrl(videosFrameTaskList.remove());
                            if(!TextUtils.isEmpty(videoString)){
                                TumblrTest.getVideoFrameFromURL(videoString);
                        }
                    }
                    while (imagesFrameTaskList.size()!=0){
                        String imageString=getStringFromUrl(imagesFrameTaskList.remove());
                        if(!TextUtils.isEmpty(imageString)){
                            getImageFromString(imageString);
                        }
                    }
                    if(!allTaskList.isEmpty()){
                        String str=getStringFromUrl(allTaskList.remove());
                        if(!TextUtils.isEmpty(str)){
                            getAllFromString(str);
                        }
                    }
                }
            }
        }).start();
    }

    //从图片框架里获取图片
    private void getImageFromString(String html){
        try{
            Parser parser=new Parser(html);
            parser.setEncoding(parser.getEncoding());
            NodeFilter filter = new NodeClassFilter(ImageTag.class);
            NodeList list = parser.extractAllNodesThatMatch(filter);
            TumbrJsonUtil.getUrlFromList(list);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //从视频框架里获取视频
    private void getVideoFromString(String html){
        try{
            Parser parser=new Parser(html);
            parser.setEncoding(parser.getEncoding());


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //从主源文件里获取图片/图片集/视频框架
    private void getAllFromString(String html){

    }

    private void createAllTask(){
        if(domName.endsWith("com"))
            domName=domName+"/";
        for(int i=beginPage;i<=endPage;i++){
            allTaskList.add(domName+PAGE+i);
        }

    }
    private void setFileName(String s){
        String temp=s;
        if(temp.endsWith("com")){
            temp=temp+"/";
        }
        if(!TextUtils.isEmpty(temp)&&temp.contains("//")&&temp.contains("tumblr")){
            fileName=temp.substring(temp.indexOf("//")+2,temp.indexOf("tumblr")-1);
        }else {
            temp=temp.replace("http://","");
            temp=temp.replace("https://","");
            fileName=temp;
        }
    }
    private String getStringFromUrl(String url){
        log.info("url = "+url);
        try {
            Response response = OkHttpUtils
                    .get()//
                    .url(url)//
                    .build()//
                    .execute();
            String html=response.body().string();
            return html;

        }catch (Exception e){
            e.printStackTrace();
            log.info(" get url "+url+" fail,put url into txt ");
            TumbrJsonUtil.putStringToTxt(exceptionPath+fileName,url);
        }
        return "";
    }
}
