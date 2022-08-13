import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class UserFormTwoController {
    public AnchorPane root;
    public TextField txtUserWriteMassageTwo;
    public TextArea vboxShowMassageTwo;

    Socket socket = null;


    public void initialize() throws IOException {
        new Thread(()->{
            try {
                socket = new Socket("localhost",8000);
                InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String record = bufferedReader.readLine();
                System.out.println(record);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }


    public void btnSendOnActionTwo(ActionEvent actionEvent) throws IOException {
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        printWriter.println(txtUserWriteMassageTwo.getText());
        printWriter.flush();
    }
}
