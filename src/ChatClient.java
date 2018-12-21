
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatClient extends Frame {
    TextField tf = new TextField();
    TextArea ta = new TextArea();
    Socket s = null;

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
        connect();
    }

    private class TFListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String str = tf.getText().trim();
            ta.setText(str);
            tf.setText("");
            try {
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                dos.writeUTF(str);
                dos.flush();
                dos.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }

    public void connect() {
        try {
            s = new Socket("192.168.203.1", 8813);
            System.out.println("connected");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ChatClient().launchFrame();
    }
}
