import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class UserFormThreeController {

    public TextField txtUserWriteMassageThree;
    public JFXButton btnSend;
    public TextArea vboxShowMassageThree;
    public Text userName;

    static DataInputStream dataInputStream;
    static DataOutputStream dataOutputStream;
    String messageIn = "";
    Socket three = null;

    public void initialize() throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    three = new Socket("localhost",13000);
                    dataInputStream = new DataInputStream(three.getInputStream());
                    dataOutputStream=new DataOutputStream(three.getOutputStream());
                    while (!messageIn.equals("end")) {
                        messageIn = dataInputStream.readUTF();
                        vboxShowMassageThree.appendText("\nClient3 : " + messageIn.trim() + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void btnSendOnActionThree(ActionEvent actionEvent) throws IOException {
        String text = txtUserWriteMassageThree.getText();
        dataOutputStream.writeUTF(text);
        vboxShowMassageThree.appendText("\nMe :" +text.trim() +"\n");
        txtUserWriteMassageThree.clear();
    }

    public void ImageOnMouseClicked(MouseEvent mouseEvent) {

    }
}
