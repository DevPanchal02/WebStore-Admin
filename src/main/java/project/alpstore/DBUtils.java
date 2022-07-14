package project.alpstore;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.*;
import static project.alpstore.LoginController.loginErrMsg;
import static project.alpstore.RegisterController.errorMsg;

//DBUtils class where all database related processes are executed
public class DBUtils {

    //Signup method where User Registration is processed with the given values
    public static void signupUser(String userName, String password, String firstName, String lastName, String number, String department) {
        Connection connection = null;
        PreparedStatement psInset = null;
        PreparedStatement psCheckUserExists = null;
        PreparedStatement psCheckPhoneExists = null;
        ResultSet resultSet = null;
        ResultSet resultSet1 = null;

        //Establishes a connection and sets values for SQL Connection
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp-admin", "root", "St20020120!");
            //Attempts to Check if username exists
            psCheckUserExists = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            psCheckUserExists.setString(1, userName);
            resultSet = psCheckUserExists.executeQuery();
            //Attempts to check if phone number used exists
            psCheckPhoneExists = connection.prepareStatement("SELECT  * FROM users WHERE phone = ?");
            psCheckPhoneExists.setString(1, number);
            resultSet1 = psCheckPhoneExists.executeQuery();

            //Displays error if username already exists
            if (resultSet.isBeforeFirst()) {
                errorMsg.setVisible(true);
                errorMsg.setText("Username already exists");
            }
            //Displays error if phone number already exists
            else if (resultSet1.isBeforeFirst()){
                errorMsg.setVisible(true);
                errorMsg.setText("Phone Number is already in-use");
            }

            //Inserts registration values into SQL Table
            else {
                psInset = connection.prepareStatement("INSERT INTO users(username, password, firstname, lastname, phone, department) VALUES (?, ?, ?, ?, ?, ?)");
                psInset.setString(1, userName);
                psInset.setString(2, password);
                psInset.setString(3, firstName);
                psInset.setString(4, lastName);
                psInset.setString(5, number);
                psInset.setString(6, department);
                psInset.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();

        //'Finally' statement used to close any all SQL Connections
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultSet1 != null) {
                try {
                    resultSet1.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckPhoneExists != null) {
                try {
                    psCheckPhoneExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psInset != null) {
                try {
                    psInset.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //Login method that attempts to log in existing users with the provided values
    public static void loginUser (String userName, String password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            //Establishes a connection and sets values for SQL Connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp-admin", "root", "St20020120!");
            //Prepared statement to find the stored password of the inputted username within the database
            preparedStatement = connection.prepareStatement("SELECT password FROM users WHERE username = ?");
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();

            //If no password is found it displays an error saying the user does not exist
            if (!resultSet.isBeforeFirst()){
                System.out.println("User not found");
                loginErrMsg.setVisible(true);
                loginErrMsg.setText("User not found");
            }
            //Checks if the provided password is the same as the retrieved password from the database
            else {
                while (resultSet.next()){
                    //Stores retrieved password from database in a variable and compares it to the inputted password
                    String retrievedPassword = resultSet.getString("password");
                    //if passwords are the same, the user is logged in
                    if (retrievedPassword.equals(password)){
                        System.out.println("Logged In ");
                    }
                    //if passwords are different, an error message is displayed
                    else {
                        System.out.println("Incorrect Password");
                        loginErrMsg.setVisible(true);
                        loginErrMsg.setText("Invalid Credentials");
                    }
                }
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        //'Finally' statement used to close all remaining SQL Connections
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
