package project.alpstore;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;

public class RegisterController implements Initializable {

    //Javafx variables initialized
    @FXML
    private ComboBox<String> regDepartments;
    @FXML
    private TextField regFirstName;
    @FXML
    private TextField regLastName;
    @FXML
    private Button regNextButton;
    @FXML
    private TextField regPhone;
    @FXML
    private TextField regUsername;
    @FXML
    private PasswordField regPassword;
    @FXML
    private Label regError;
    public static Label errorMsg;

    //initialize method that runs at the start
    public void initialize(URL url, ResourceBundle resourceBundle) {
        regDepartments.setItems(FXCollections.observableArrayList("Product Management", "Customer Support", "Human Resources"));
        errorMsg = regError;
    }
    //onNextButtonClick method that runs when the next button is clicked
    public void onNextButtonClick(ActionEvent actionEvent){
        //Checks if any fields are left blank and gives an error if there are blank fields
        if (regFirstName.getText().isBlank() || regLastName.getText().isBlank() || regPhone.getText().isBlank() || regUsername.getText().isBlank() || regPassword.getText().isBlank() || regDepartments.getSelectionModel().isEmpty()) {
            regError.setText("Please Enter Credentials for all Fields");
            regError.setVisible(true);
            regDepartments.addEventHandler(ComboBox.ON_SHOWN, event -> regError.setVisible(false));
        }
        //Attempts to register user
        else {
            System.out.println("working");
            regError.setVisible(false);
            //Calls the DBUtils class where user registration is processed
            DBUtils.signupUser(regUsername.getText(), regPassword.getText(), regFirstName.getText(), regLastName.getText(), regPhone.getText(), regDepartments.getSelectionModel().getSelectedItem());
        }
    }
}

