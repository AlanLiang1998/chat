import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    public static void main(String[] args) {
        ServerSocket ss = null;
        Socket s = null;
        try {
            ss = new ServerSocket(8888);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DataInputStream dis = null;
        try {
            boolean started = false;
            started = true;
            while (started) {
                boolean connected = false;
                s = ss.accept();
                System.out.println("A client connected!");
                connected = true;
                dis = new DataInputStream(s.getInputStream());
                while (connected) {
                    String str = dis.readUTF();
                    System.out.println(str);
                }
                //dis.close();
            }
        } catch (IOException e) {
            System.out.println("Client closed!");
            //e.printStackTrace();
        } finally {
            try {
                dis.close();
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
