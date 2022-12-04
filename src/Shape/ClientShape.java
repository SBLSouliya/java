package Shape;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientShape {
    public final static String SERVER_IP = "127.0.0.1"; //"10.10.10.59";
    public final static int SERVER_PORT = 999;
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[]args) throws IOException, InterruptedException{
        Socket socket = null;
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT); // Connect to server
            System.out.println("Connected: " + socket);
            DataInputStream is = new DataInputStream(socket.getInputStream());
            DataOutputStream os = new DataOutputStream(socket.getOutputStream());
            System.out.println("nhap");
            String str = scanner.nextLine();
            System.out.println(str);
            os.writeUTF(str);
            String toServer = is.readUTF();
            System.out.println(toServer);
        } catch (IOException ie) {
            System.out.println("Can't connect to server");
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}