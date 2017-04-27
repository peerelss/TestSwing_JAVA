package pinterestpic;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by root on 17-4-27.
 */
public class ImagesDownloadPinterest {
    private JTextArea mTextArea1;
    private JPanel mPanel1;
    private JTextField mTextField1;
    private JTextField mTextField2;
    private JButton mButton1;

    public ImagesDownloadPinterest() {
        mButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<100;i++){
                    mTextArea1.append("\n i = "+i);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ImagesDownloadPinterest");
        frame.setContentPane(new ImagesDownloadPinterest().mPanel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}
