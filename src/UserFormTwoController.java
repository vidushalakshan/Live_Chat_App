import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.*;
import java.net.Socket;

public class UserFormTwoController {
    public static String userNameTwo;
    public AnchorPane root;
    public TextField txtUserWriteMassageTwo;
    public TextArea vboxShowMassageTwo;
    public Text userName;
    public JFXButton btnSend;
    String messageIn = "";
    static DataOutputStream dataOutputStream;
    static DataInputStream dataInputStream;
    PrintWriter printWriter;


    Socket socket = null;

    public void initialize() throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket("localhost",1000);
                    dataInputStream = new DataInputStream(socket.getInputStream());
                    dataOutputStream=new DataOutputStream(socket.getOutputStream());
                    while (!messageIn.equals("end")) {
                        messageIn = dataInputStream.readUTF();
                        vboxShowMassageTwo.appendText("\nClient1 : " + messageIn.trim() + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void btnSendOnActionTwo(ActionEvent actionEvent) throws IOException {
        String text = txtUserWriteMassageTwo.getText();
        dataOutputStream.writeUTF(text);
        vboxShowMassageTwo.appendText("\nMe :" +text.trim() +"\n");
        txtUserWriteMassageTwo.clear();
    }
}
