import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.sql.SQLException;


public class ServerFormController {

    public static String userNameOneOn;
    static DataInputStream dataInputStream;
    static DataOutputStream dataOutputStream;
    public TextField txtUserWriteMassage;
    public TextArea vboxShowMassage;
    public Text userNameOne;
    Socket accept = null;
    DataOutputStream dos;
    String messageIn = "";
    PrintWriter printWriter;

    BufferedReader reader;

    public void initialize() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerSocket serverSocket = new ServerSocket(17000);
                    System.out.println("Server Started!");
                    accept = serverSocket.accept();
                    System.out.println("Client Connected!");
                    dataInputStream = new DataInputStream(accept.getInputStream());
                    dataOutputStream = new DataOutputStream(accept.getOutputStream());

                    while (!messageIn.equals("end")) {
                        messageIn = dataInputStream.readUTF();
                        vboxShowMassage.appendText("\nClient2: " + messageIn.trim() + "\n");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void btnSendOnAction(ActionEvent actionEvent) throws IOException {
        dataOutputStream.writeUTF(txtUserWriteMassage.getText().trim());
        vboxShowMassage.appendText("\nMe :" + txtUserWriteMassage.getText().trim() + "\n");
        txtUserWriteMassage.clear();
    }

    public void ImageOnMouseClicked(MouseEvent mouseEvent) {

    }

    /*public static class GreetingServer extends Thread {
        Socket server;
        private final ServerSocket serverSocket;

        public GreetingServer(int port) throws Exception {
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(180000);
        }

        public static void main(String[] args) throws Exception {
            Thread t = new GreetingServer(6066);
            t.start();
        }

        public void run() {
            while (true) {
                try {
                    server = serverSocket.accept();
                    BufferedImage img = ImageIO.read(ImageIO.createImageInputStream(server.getInputStream()));
                    JFrame frame = new JFrame();
                    frame.getContentPane().add(new JLabel(new ImageIcon(img)));
                    frame.pack();
                    frame.setVisible(true);
                } catch (SocketTimeoutException st) {
                    System.out.println("Socket timed out!");
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }
    }*/
}
