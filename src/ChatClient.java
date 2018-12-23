
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatClient extends Frame {
    TextField tf = new TextField();
    TextArea ta = new TextArea();
    Socket s = null;
    DataOutputStream dos = null;
    DataInputStream dis = null;
    boolean connected = false;

    public void launchFrame() {
        setTitle("chat");
        setLocation(400, 300);
        setSize(300, 300);
        add(tf, BorderLayout.SOUTH);
        add(ta, BorderLayout.NORTH);
        pack();
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                disconnect();
                System.exit(0);
            }
        });
        tf.addActionListener(new TFListener());
        setVisible(true);
        connect();
        new Thread(new ReceiveThread()).start();
    }

    private class TFListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String str = tf.getText().trim();
            //ta.setText(str);
            tf.setText("");
            try {
                dos.writeUTF(str);
                dos.flush();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }

    public void connect() {
        try {
            s = new Socket("192.168.203.1", 8888);
            System.out.println("connected");
            connected = true;
            dos = new DataOutputStream(s.getOutputStream());
            dis = new DataInputStream(s.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            dos.close();
            dis.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ReceiveThread implements Runnable {

        public void run() {
            try {
                while (connected) {
                    String str = dis.readUTF();
                    ta.setText(ta.getText() + str + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new ChatClient().launchFrame();
    }
}
