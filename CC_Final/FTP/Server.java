import java.io.*;
import java.net.*;

public class Server {
    public static void main(String args[]) {
        try {
            ServerSocket ss = new ServerSocket(10087);
            System.out.println("Waiting for connection...");
            Socket s = ss.accept();
            System.out.println("Connected.");

            DataInputStream dis = new DataInputStream(s.getInputStream());
            String filename = dis.readUTF();
            FileInputStream fis = new FileInputStream(filename);
            BufferedInputStream bis = new BufferedInputStream(fis);

            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            BufferedOutputStream bos = new BufferedOutputStream(dos);

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }

            bos.flush();
            System.out.println("File sent to client.");

            bos.close();
            bis.close();
            s.close();
            ss.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
