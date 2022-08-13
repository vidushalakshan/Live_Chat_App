import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;


public class UserFormController implements Initializable {
    @FXML
    public TextField txtUserWriteMassage;

    @FXML
    public TextArea vboxShowMassage;
    Socket accept = null;

    public void btnSendOnAction(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
