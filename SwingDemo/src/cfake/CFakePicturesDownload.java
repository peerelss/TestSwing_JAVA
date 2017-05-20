package cfake;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.TumbrJsonUtil;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by root on 17-5-20.
 */
public class CFakePicturesDownload {
    static Logger log = Logger.getLogger(CFakePicturesDownload.class.getName());
    public ArrayList<String> urlList;
    public String fileName="default";
    private static CFakePicturesDownload ourInstance = new CFakePicturesDownload();
    public static CFakePicturesDownload getInstance() {
        return ourInstance;
    }
    private CFakePicturesDownload() {
    }
    public void init(String url,int size){
        urlList=new ArrayList<>();
        for(int i=1;i<=size;i++){
            urlList.add(url+"p"+(i-1)*30);
        }
        setFileName(url);
        startTask();
    }
    public void startTask(){
        while(urlList!=null&&urlList.size()!=0){
            String url=urlList.remove(0);
            if(url!=null&&!url.equals("")){
                try{
                    Document doc= Jsoup.connect(url).get();
                    Elements link = doc.select("img");
                    String resultLink="";
                    for(Element element:link){
                        String jpgUrl=element.toString();
                        if(jpgUrl.contains("thumbs")){
                            String magnetLink=formatString(jpgUrl);
                            System.out.println(magnetLink);
                            resultLink=resultLink+magnetLink+"\n";
                        }
                    }
                    TumbrJsonUtil.putStringToTxt(fileName,resultLink);
                    log.info(doc.title());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    private String formatString(String url){
        return "http://cfake.com/medias/photos"+url.substring(url.indexOf("thumbs")+6,url.indexOf("jpg")+3);
    }
    public  void setFileName(String url){
        fileName =url.substring(url.indexOf("picture")+8,url.length()-5);
    }
}
