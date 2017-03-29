import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by root on 17-3-29.
 */
public class CFakeImageForm {
    private JButton mStartButton;
    private JTextField mTextField1;
    private JTextField mTextField2;
    private JTextArea mTextArea1;
    private JScrollPane jp1;
    private JPanel jpl1;

    public CFakeImageForm() {
        mStartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("CFakeImageForm");
        frame.setContentPane(new CFakeImageForm().jpl1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}
