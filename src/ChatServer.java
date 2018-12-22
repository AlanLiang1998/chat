import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    public static void main(String[] args) {
        try {
            boolean started = false;
            ServerSocket ss = new ServerSocket(8888);
            started = true;
            while (started) {
                boolean connected = false;
                Socket s = ss.accept();
                System.out.println("A client connected!");
                connected = true;
                DataInputStream dis = new DataInputStream(s.getInputStream());
                while (connected) {
                    String str = dis.readUTF();
                    System.out.println(str);
                }
                dis.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
