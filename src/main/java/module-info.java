module project.alpstore {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens project.alpstore to javafx.fxml;
    exports project.alpstore;
}