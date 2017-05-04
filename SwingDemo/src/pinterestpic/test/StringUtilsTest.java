package pinterestpic.test;

import model.TumblrImagesManager;

import java.net.URLEncoder;
import java.util.logging.Logger;

/**
 * Created by root on 17-4-27.
 */
public class StringUtilsTest {
    static Logger log = Logger.getLogger(TumblrImagesManager.class.getName());
    public static void main(String[] args)throws  Exception{
        String url="/crazyb7/dita-von-teese/&data={\"options\":{\"bookmarks\":[\"LT4zMjE1MTQ4NjA4ODM3MjYzNDI6MjU6MjV8NjQ0M2NjYjg3MzhjNmFkMGY5Y2U1OWRmZGFjN2VjMTY4YTdjNzlmMWM3MGM3Nzc5ZDBmODc3ZTBmYTZmNmZhOQ==\"],\"access\":[],\"board_id\":\"321514929586702272\",\"board_url\":\"/crazyb7/dita-von-teese/\",\"field_set_key\":\"react_grid_pin\",\"layout\":\"default\",\"page_size\":25},\"context\":{}}&_=1493260546926";
        System.out.println(URLEncoder.encode(url,"UTF-8"));
    }
}
