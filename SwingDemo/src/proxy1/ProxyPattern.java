package proxy1;

/**
 * Created by root on 17-3-2.
 */
public class ProxyPattern {
    public static void main(String[] args) {
        // 不需要执行额外方法的。
        ITalk people = new PeopleTalk("AOP", "18");
        people.talk("No ProXY Test");
        System.out.println("-----------------------------");

        // 需要执行额外方法的（切面）
        TalkProxy talker = new TalkProxy(people);
        talker.talk("ProXY Test", "代理");
    }
}

