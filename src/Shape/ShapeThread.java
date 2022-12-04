package Shape;
import java.io.*;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

public class ShapeThread extends Thread {
    Socket socket;

    public ShapeThread(Socket socket) {
        this.socket = socket;
    }

    public String acceptFromClient() throws IOException {
        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        PrintWriter out = null;
        String ip = null;
        try {
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(os)));
            String line = "";
            line = br.readLine(); // Receive data from client
            System.out.println("From " + socket.getInetAddress().getHostAddress() + ">" + line);
            ip = socket.getInetAddress().getHostAddress();
            out.println("Request accepted:>>" + line);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            br.close();
            out.close();
            socket.close();
        }

        return ip;
    }

    public void sendRequestToServer(String ip) throws IOException {
        Socket sk = null;
        DataOutputStream ot = null;
        BufferedReader b = null;
        try {
            System.out.println("Send request to:" + ip + " ...");
            sk = new Socket(ip, 999);
            System.out.println("Connected: " + sk);
            ot = new DataOutputStream(sk.getOutputStream());
            int v1 = ThreadLocalRandom.current().nextInt(2, 30);
            ot.writeUTF("VUONG " + v1);
            System.out.println("Request: VUONG " + v1);
            b = new BufferedReader(new InputStreamReader(sk.getInputStream()));
            String l;
            while (!(l = b.readLine()).equalsIgnoreCase("q")) {
                System.out.println(l);
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int height = ThreadLocalRandom.current().nextInt(2, 30);
            int width = ThreadLocalRandom.current().nextInt(2, 30);
            ot.writeUTF("CHUNHAT " + height + " " + width);
            System.out.println("Request: CHUNHAT " + height + " " + width);
            while (!(l = b.readLine()).equalsIgnoreCase("q")) {
                System.out.println(l);
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int radius = ThreadLocalRandom.current().nextInt(2, 30);
            ot.writeUTF("TRON " + radius);
            System.out.println("Request: TRON " + radius);
            while (!(l = b.readLine()).equalsIgnoreCase("q")) {
                System.out.println(l);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Connection closed:" + ip);
            if (b != null) {
                b.close();
                b = null;
            }
            if (ot != null) {
                ot.close();
                ot = null;
            }
            if (sk != null) {
                sk.close();
                sk = null;
            }
        }
    }
    @Override
    public void run () {
        try {
            String ip = acceptFromClient();
            sendRequestToServer(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}