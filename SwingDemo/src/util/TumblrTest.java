package util;

import com.zhy.http.okhttp.OkHttpUtils;
import model.TumblrManager2;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeList;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by root on 16-12-28.
 */
public class TumblrTest {
    public static final String TUMBLR_VIDEO="iframe src='https://www.tumblr.com/";
    public static NodeFilter iframeFilter = new NodeFilter() {
        public boolean accept(Node node) {
            if (node.getText().startsWith(TUMBLR_VIDEO)) {
                return true;
            } else {
                return false;
            }
        }
    };
    static Logger log = Logger.getLogger(TumblrTest.class.getName());
    public static void main(String[] args){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 1080)))
                .build();
        OkHttpUtils.initClient(okHttpClient);
        String html=getStringFromUrl("http://lipstixxx-noir.tumblr.com/archive/2016/11");
        TumbrJsonUtil.putStringToTxt("noir_video_201611",html);
    //    String htmlTxt=TumbrJsonUtil.getStringFromTxt("/home/kevin/Documents/tumblr/noir_video_json.html");
    //    getVideoUrlFromString(htmlTxt);
    }
    public static void getVideoUrlFromString(String html){
    //    log.info(html);
        try{
            Parser parser=new Parser(html);
            NodeFilter iframeFilter = new NodeFilter() {
                public boolean accept(Node node) {
                    if (node.getText().startsWith("source src")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            };
            NodeList nodeFrameList = parser.extractAllNodesThatMatch(iframeFilter);
            log.info("size ="+nodeFrameList.size());
            for (int i = 0; i < nodeFrameList.size(); i++) {
                Node node = nodeFrameList.elementAt(i);
                log.info(node.getText().toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void getVideoFromList(NodeList nodeFrameList){
        for (int i = 0; i < nodeFrameList.size(); i++) {
            Node node = nodeFrameList.elementAt(i);
            String str = node.getText().toString();
            log.info(str);
            String[] strs=str.split(" ");
            for(int j=0;j<strs.length;j++){
                if(strs[j].startsWith("src")){
                    String url=strs[j].substring(5,strs[j].length()-1);
                    getVideoUrlFromString(getStringFromUrl(url));
                    log.info(url);
                }
            }
        }
    }
    public static void getVideoFrameFromURL(String html){
    //    log.info(html);

        try{
            Parser parser=new Parser(html);
            NodeList nodeFrameList = parser.extractAllNodesThatMatch(iframeFilter);
            log.info("size ="+nodeFrameList.size());
            getVideoFromList(nodeFrameList);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static String  getStringFromUrl(String url){
        try {
            Response response = OkHttpUtils
                    .get()//
                    .url("https://www.tumblr.com/video/lipstixxx-noir/154881010199/500/")//
                    .build()//
                    .execute();
            String html = response.body().string();
            return html;

        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }
}
