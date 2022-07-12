package project.alpstore;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController {

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
    protected void onNextButtonClick() throws IOException {

        //checks if both username and password fields are not left
        // empty fields result in an error
        if (txtUsername.getText().isBlank() == true || txtPassword.getText().isBlank() == true){
            errorMsg.setText("Please Enter Credentials for both Fields");
            errorMsg.setVisible(true);
        }
        else {
            ((Stage) nextButton.getScene().getWindow()).close();

        }
    }
    //onRegistrationButtonClick method to send the user to the registration page
    @FXML
    protected void onRegistrationButtonCLick() throws IOException {
        ((Stage) nextButton.getScene().getWindow()).close();
        openRegistration(new Stage());
    }

    //Loads the employee registration window
    public static void openRegistration(Stage stage) throws IOException {
        FXMLLoader registerWindow = new FXMLLoader(WebstoreAdminApplication.class.getResource("fxml/register-view.fxml"));
        Scene registerScene = new Scene(registerWindow.load(), 480, 600);
        stage.setTitle("Employee Registration");
        Image icon = new Image("file:src/main/resources/project/alpstore/Imgs/logo.jpg");
        stage.getIcons().add(icon);
        stage.setScene(registerScene);
        stage.show();

    }
}