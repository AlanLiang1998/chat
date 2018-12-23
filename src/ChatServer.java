import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    ServerSocket ss = null;
    boolean started = false;

    public void start() {
        try {
            ss = new ServerSocket(8888);
            started = true;
        } catch (BindException e) {
            System.out.println("端口使用中...");
            System.out.println("请关闭相关程序并关闭服务器！");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            while (started) {
                Socket s = ss.accept();
                Client c = new Client(s);
                new Thread(c).start();
            }
        } catch (IOException e) {
            try {
                ss.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new ChatServer().start();
    }

    private class Client implements Runnable {
        Socket s = null;
        DataInputStream dis = null;
        boolean connected = false;

        public Client(Socket s) {
            this.s = s;
            connected = true;
            try {
                dis = new DataInputStream(s.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            try {
                while (connected) {
                    String str = dis.readUTF();
                    System.out.println(str);
                }
                //dis.close();
            } catch (EOFException e) {
                System.out.println("A client closed!");
            } catch (IOException e) {
                e.printStackTrace();
                //e.printStackTrace();
            } finally {
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
