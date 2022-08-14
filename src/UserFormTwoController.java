import com.sun.security.ntlm.Server;
import javafx.event.ActionEvent;
import javafx.geometry.NodeOrientation;
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
    String messageIn = "";
    static DataOutputStream dataOutputStream;
    static DataInputStream dataInputStream;
    PrintWriter printWriter;


    Socket socket = null;

    public void initialize() throws IOException {
        new Thread(()->{
            try {
                socket = new Socket("localhost",4000);
                InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String record = bufferedReader.readLine();
                System.out.println(record);
                while (!messageIn.equals("end")) {
                    messageIn = dataInputStream.readUTF();
                    txtUserWriteMassageTwo.appendText("\nServer: " + messageIn.trim() + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }

    public void btnSendOnActionTwo(ActionEvent actionEvent) throws IOException {
        String text = txtUserWriteMassageTwo.getText();
        vboxShowMassageTwo.appendText("\tServer :" +text.trim() +"\n");
        dataOutputStream.writeUTF(text);
        txtUserWriteMassageTwo.setText("");
    }
}
