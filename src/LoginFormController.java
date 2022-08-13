import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    @FXML
    public JFXTextField txtUserName;
    public static String userName;
    public AnchorPane root;


    public void btnLogInOnAction(ActionEvent actionEvent) throws IOException {
        userName= txtUserName.getText();
        if (userName==userName){
            Parent parent= FXMLLoader.load(this.getClass().getResource("UserForm.fxml"));
            Scene scene=new Scene(parent);

            Stage primaryStage=(Stage)this.root.getScene().getWindow();

            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
        }

    }
}
