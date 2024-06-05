module com.main.tugas6 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.main.tugas6 to javafx.fxml;
    exports com.main.tugas6;
}