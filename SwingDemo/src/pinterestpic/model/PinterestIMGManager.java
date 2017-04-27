package pinterestpic.model;

/**
 * Created by root on 17-4-27.
 */
public class PinterestIMGManager {

    private static PinterestIMGManager ourInstance = new PinterestIMGManager();
    public static PinterestIMGManager getInstance() {
        return ourInstance;
    }
    private PinterestIMGManager() {
    }

}
