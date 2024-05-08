import java.io.*;
import java.net.*;

public class Client {
    public static void main(String args[]) {
        try {
            Socket s = new Socket("127.0.0.1", 10087);

            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the file name:");
            String filename = sc.nextLine();
            dos.writeUTF(filename);

            DataInputStream dis = new DataInputStream(s.getInputStream());
            FileOutputStream fos = new FileOutputStream("received_" + filename);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = dis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }

            bos.flush();
            System.out.println("File received from server.");

            bos.close();
            dis.close();
            s.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
