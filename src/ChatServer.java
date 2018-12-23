import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    public static void main(String[] args) {
        ServerSocket ss = null;
        Socket s = null;
        try {
            ss = new ServerSocket(8888);
        } catch (BindException e) {
            System.out.println("端口使用中...");
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
        } catch (EOFException e) {
            System.out.println("Client closed!");
        } catch (IOException e) {
            e.printStackTrace();
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
