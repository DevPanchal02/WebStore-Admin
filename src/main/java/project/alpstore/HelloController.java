package project.alpstore;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HelloController {

    //JavaFX Variables for each element in the login page
    @FXML
    private Button nextButton;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label errorMsg;

    //onNextButtonClick method to validate the users Credentials
    @FXML
    protected void onNextButtonClick() {

        if (txtUsername.getText().isBlank() == true || txtPassword.getText().isBlank() == true){
            errorMsg.setText("Please Enter Credentials for both Fields");
            errorMsg.setVisible(true);
        }
        else {
            ((Stage) nextButton.getScene().getWindow()).close();
        }
    }

}