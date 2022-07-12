package project.alpstore;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.sql.*;

public class RegisterController implements Initializable {

    @FXML
    private ComboBox<String> departments;
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

    public static void signUp(ActionEvent event, String regFirstName, String regLastName, String regPhone, String regUsername, String regPassword, ComboBox<String> departments){
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;

        try{
            connection = DriverManager.getConnection("#DataSourceSettings#\n" +
                    "#LocalDataSource: @localhost\n" +
                    "#BEGIN#\n" +
                    "<data-source source=\"LOCAL\" name=\"@localhost\" uuid=\"0007bf6e-3f89-4238-8ff2-e652882a09e8\"><database-info product=\"MySQL\" version=\"8.0.29\" jdbc-version=\"4.2\" driver-name=\"MySQL Connector/J\" driver-version=\"mysql-connector-java-8.0.25 (Revision: 08be9e9b4cba6aa115f9b27b215887af40b159e0)\" dbms=\"MYSQL\" exact-version=\"8.0.29\" exact-driver-version=\"8.0\"><extra-name-characters>#@</extra-name-characters><identifier-quote-string>`</identifier-quote-string></database-info><case-sensitivity plain-identifiers=\"lower\" quoted-identifiers=\"lower\"/><driver-ref>mysql.8</driver-ref><synchronize>true</synchronize><jdbc-driver>com.mysql.cj.jdbc.Driver</jdbc-driver><jdbc-url>jdbc:mysql://localhost:3306</jdbc-url><secret-storage>master_key</secret-storage><user-name>root</user-name><schema-mapping><introspection-scope><node kind=\"schema\" qname=\"@\"/></introspection-scope></schema-mapping><working-dir>$ProjectFileDir$</working-dir></data-source>\n" +
                    "#END#\n" +
                    "\n");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM users WHERE regUsername = ? ");


        }
        catch(SQLException e) {
            System.out.println("Error");
        }

    }



    public void initialize(URL url, ResourceBundle resourceBundle){
        departments.setItems(FXCollections.observableArrayList("Product Management", "Customer Support", "Human Resources"));
    }

    public void onNextButtonClick(ActionEvent actionEvent) {
    }

}
