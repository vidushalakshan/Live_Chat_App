import javafx.event.ActionEvent;
import javafx.geometry.NodeOrientation;
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
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(6000);
                System.out.println("Server Started!");
                accept = serverSocket.accept();
                System.out.println("Client Connected!");
                InputStreamReader inputStreamReader = new InputStreamReader(accept.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String record = bufferedReader.readLine();
                System.out.println(record);
                while (!messageIn.equals("end")) {
                    messageIn = dataInputStream.readUTF();
                    txtUserWriteMassage.appendText("\nServer: " + messageIn.trim() + "\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void btnSendOnAction(ActionEvent actionEvent) throws IOException {
        String text = txtUserWriteMassage.getText();
        vboxShowMassage.appendText("\tServer :" +text.trim()+"\n");
        dataOutputStream.writeUTF(text);
        txtUserWriteMassage.setText("");
    }
}
