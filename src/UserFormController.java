import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;



public class UserFormController {

    public TextField txtUserWriteMassage;

    public TextArea vboxShowMassage;
    Socket accept = null;

    public void initialize(){
        new Thread(()->{
            try {
                ServerSocket serverSocket = new ServerSocket(8000);
                System.out.println("Server Started!");
                accept = serverSocket.accept();
                System.out.println("Client Connected!");
                InputStreamReader inputStreamReader = new InputStreamReader(accept.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String record = bufferedReader.readLine();
                System.out.println(record);

            } catch (Exception e){
                e.printStackTrace();
            }
        }).start();
    }

    public void btnSendOnAction(ActionEvent actionEvent) throws IOException {
        PrintWriter printWriter = new PrintWriter(accept.getOutputStream());
        printWriter.println(txtUserWriteMassage.getText());
        printWriter.flush();
    }

}
