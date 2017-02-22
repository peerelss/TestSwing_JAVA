import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import model.TumblrImagesManager;
import model.TumblrManager2;
import net.NetRequest;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.apache.http.util.TextUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * Created by kevin on 2016/12/13.
 */
public class SwingDemoMain {
    public static final String LINE_END="\r\n",PAGE="/page/";

    private JTextField mDomName;
    private JCheckBox isBackGround;
    private JTextArea mCurrentTask;
    private JPanel From1;
    private JButton button1;
    private JScrollPane jsp;
    private JTextField beginTf;
    private JTextField endTf;
    private JCheckBox isJson;

    private String mName="";
    private boolean isBack=false;
    private String result="";

    NetRequest netRequest=new NetRequest() {
        @Override
        public void onOperaSuccess(String str) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    mCurrentTask.append(str+LINE_END);
                }
            });
        }

        @Override
        public void onOperaFailure() {

        }
        @Override
        public void onOver() {

        }

    };

    public SwingDemoMain() {

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String beginStr=beginTf.getText();
                String endStr=endTf.getText();
                int beginInt=0,endInt=0;
                try{
                    if(!TextUtils.isEmpty(beginStr)){
                        beginInt=Integer.valueOf(beginStr);
                    }
                    if(!TextUtils.isEmpty(endStr)){
                        endInt=Integer.valueOf(endStr);
                    }
                }catch (NumberFormatException e1){
                    mCurrentTask.append("开始或者结束页码输入错误，请重新输入"+LINE_END);
                    return;
                }
                isBack=isBackGround.isSelected();
                if(isBack){
                    mCurrentTask.append("抓取背景页"+LINE_END);
                }else {
                    mCurrentTask.append("不抓取背景页"+LINE_END);
                }
                mName=mDomName.getText();
                mCurrentTask.setLineWrap(true);

                mCurrentTask.append("抓取页面网址为"+mName+ LINE_END);
                //初始化
                TumblrImagesManager.getInstance().init(mName,beginInt,endInt,netRequest);
                //TumblrManager2.getInstance().init(mName,beginInt,endInt,netRequest);

            }
        });
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("SwingDemoMain");
        frame.setContentPane(new SwingDemoMain().From1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(800, 600);
        frame.setVisible(true);

    }
}
