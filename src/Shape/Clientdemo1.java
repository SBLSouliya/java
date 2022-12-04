package Shape;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Clientdemo1 {
    public final static String SERVER_IP = "127.0.0.1"; //"10.10.10.59";
    public final static int SERVER_PORT = 1000;
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[]args) throws IOException, InterruptedException{
        Socket socket = null;
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT); // Connect to server
            System.out.println("Connected: " + socket);
            DataInputStream is = new DataInputStream(socket.getInputStream());
            DataOutputStream os = new DataOutputStream(socket.getOutputStream());
            System.out.println("Huong dan\nLam hinh tron la them mot so nguyen (tron 8)");
            System.out.println("Lam hinh vuong la them mot so nguyen (vuong 8)");
            System.out.println("Lam hinh chunhat la them hai so nguyen (chunhat 8 6)");
            System.out.println("nhap:");
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