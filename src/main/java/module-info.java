module project.alpstore {
    requires javafx.controls;
    requires javafx.fxml;


    opens project.alpstore to javafx.fxml;
    exports project.alpstore;
}