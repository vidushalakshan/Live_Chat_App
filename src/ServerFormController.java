import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerFormController {

    public TextField txtUserWriteMassage;

    public TextArea vboxShowMassage;
    public Text userNameOne;
    Socket accept = null;
    public static String userNameOneOn;
    DataOutputStream dos;
    String messageIn = "";
    static DataInputStream dataInputStream;
    static DataOutputStream dataOutputStream;

    PrintWriter printWriter;

    BufferedReader reader;

    public void initialize() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerSocket serverSocket = new ServerSocket(1000);
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
        vboxShowMassage.appendText("\nMe :" +txtUserWriteMassage.getText().trim()+"\n");
        txtUserWriteMassage.clear();
    }
}
