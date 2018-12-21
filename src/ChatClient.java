
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ChatClient extends Frame {
    TextField tf = new TextField();
    TextArea ta = new TextArea();

    public void launchFrame() {
        setTitle("chat");
        setLocation(400, 300);
        setSize(300, 300);
        add(tf, BorderLayout.SOUTH);
        add(ta, BorderLayout.NORTH);
        pack();
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        tf.addActionListener(new TFListener());
        setVisible(true);
    }

    private class TFListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String s = tf.getText().trim();
            ta.setText(s);
        }
    }

    public static void main(String[] args) {
        new ChatClient().launchFrame();
    }
}
