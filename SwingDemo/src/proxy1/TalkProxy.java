package proxy1;

/**
 * Created by root on 17-3-2.
 */
public class TalkProxy implements ITalk {
    private ITalk talker;

    public TalkProxy(ITalk talker) {
        // super();
        this.talker = talker;
    }

    public void talk(String msg) {
        talker.talk(msg);
    }

    public void talk(String msg, String singname) {
        talker.talk(msg);
        sing(singname);
    }

    private void sing(String singname) {
        System.out.println("唱歌：" + singname);
    }
}
