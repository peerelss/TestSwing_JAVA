package pinterestpic.utils;

import com.zhy.http.okhttp.OkHttpUtils;
import model.TumblrImagesManager;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

/**
 * Created by root on 17-2-21.
 */
public class OKUtil  {
    private static OKUtil ourInstance = new OKUtil();
    public static OKUtil getInstance() {
        return ourInstance;
    }
    private OKUtil() {
    }
    public void initOkHttp(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
        //        .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 1080)))
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }
    public String getStringFromUrl(String url){
        try {
            Response response   =  OkHttpUtils
                    .get()
                    .url(url)
                    .build()
                    .execute();
            return response.body().string();
        }catch (Exception e){
            return "";
        }

    }
    public String getJsonFromUrl(String url){
        try {
            Response response   =  OkHttpUtils
                    .get()
                    .addHeader("Host","www.pinterest.com")
                    .addHeader("User-Agent","Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:50.0) Gecko/20100101 Firefox/50.0")
                    .addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                    .addHeader("Accept-Language","en-US,en;q=0.5")
                    .addHeader("Referer","https://www.google.com/")
                    .addHeader("Upgrade-Insecure-Requests","1")
                    .addHeader("Connection","keep-alive")
                    .addHeader("Cache-Control","max-age=0")
                    .addHeader("Cookie"," _auth=1; " +
                            "csrftoken=h1t07nv1EwoACnj4YATo3JdulEFeTgrC; " +
                            "_pinterest_sess=\"TWc9PSZyeEkvcER6V25IbGpZKzJwQjQxSnlRajJYcFZBWFZHb0xoTGxNVU8yQVpSeS9ZWlpZSE1scEkxL0hxdzdqRHFaZmFpcDB3M25CbHgrTTdKSnI1Z05Oc3l6N1o2ZDBmKzNTOGZKaHA4U3UzbGdkMlFmbm5ZaGRkTXl6VmxyZnRDdG0xYi80WjVFMUoxckRpMEhLU1NaMnhDa0QwY1hnMjBMMHU2alVEekVEQVR3U2tweFgxc051RjNtcjd1VFhxR0ozaWsvQWp6bHdhdmU5WXRZNk43MnhpYjFwbDBRSGx0OXh4QWt5RDdMby9xMnpRanZIQkNoTUEvemk3YTdKR3NWNUNpd3IrZVRLZTZkVFh3ejRuUUE3Zm15LzI2bWFiL0lSTFpGaS9LZFNobkE3aVJUV040Qm1PMzVUUXlTb0xSNGhBandyU0R1K1RmenBXZ29mK1p5QW1hY2R5cGtLU3U2dS9jMU9zK1J6OHMrMmk2OVJaZzZlR1ROOGVZNXZLV0hLbTZZYkwyYktrb25ka2M2OStVNUZFRGQ3UFp6bTl0NER2d09Qc0ttb0VVQklDYXZsNE9MaktHYUpNaU1hUFNtZksyRzF2bXFoUENPNXRHbVNZM0UySHZONkJEd2JGMEl0dm1HRUJJZjVMMD0md3A3MGVIMHM4WnlNSTExUmUvcTQ0Yk1FYjRvPQ==\"; " +
                            "cm_sub=none; us_open_group=enabled4; " +
                            "c_dpr=1; " +
                            "bei=false; " +
                            "_pinterest_referrer=\"https://www.google.com/\"; " +
                            "sessionFunnelEventLogged=1; " +
                            "_b=\"ASVDqvk7IwNIt7lGeUGGlLtUxxeRbC8vhgLEy1UYBuu69V15P4E7a0XHL9K5Aw/9S7k=\"")
                    .url(url)
                    .build()
                    .execute();
            return response.body().string();
        }catch (Exception e){
            return "";
        }

    }

}
