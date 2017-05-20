package cfake;

import com.zhy.http.okhttp.OkHttpUtils;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import tumblrvideo.TumblrUtil;
import util.TumbrJsonUtil;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by root on 17-5-20.
 */
public class BtsouMagnetManager {
    static Logger log = Logger.getLogger(BtsouMagnetManager.class.getName());
    public ArrayList<String> urlList;
    public String fileName="default";
    private static BtsouMagnetManager ourInstance = new BtsouMagnetManager();
    public static BtsouMagnetManager getInstance() {
        return ourInstance;
    }
    private BtsouMagnetManager() {
    }
    public void init(String url,int size){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .build();
        OkHttpUtils.initClient(okHttpClient);
        setFileName(url);
        urlList=new ArrayList<>();
        for(int i=1;i<=size;i++){
             urlList.add(url+"/"+i+"/time_d");
        }
        startTask();
    }
    public String getHtmlFromUrl(String url){
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
        }
        return "";
    }
    public void startTask(){
        while(urlList!=null&&urlList.size()!=0){
            String url=urlList.remove(0);
            if(url!=null&&!url.equals("")){
                try{
                    String result=getHtmlFromUrl(url);
                    if(!"".endsWith(result)){
                        Document doc= Jsoup.parse(result);
                        Elements link = doc.select("a");
                        String resultLink="";
                        for(Element element:link){
                            String jpgUrl=element.toString();
                            if(jpgUrl.contains("magnet")){
                                String magnetLink=formatString(jpgUrl);
                                System.out.println(magnetLink);
                                resultLink=resultLink+magnetLink+"\n";
                            }
                        }
                        TumbrJsonUtil.putStringToTxt(fileName,resultLink);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    public static String formatString(String url){
        return  url.substring(url.indexOf("magnet"),url.indexOf("磁力链接")-3);
    }
    public  void setFileName(String url){
        fileName =url.substring(url.indexOf("list")+5);
    }
}
