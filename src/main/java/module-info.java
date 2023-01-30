module com.example.supplychain19novm {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.supplychain19novm to javafx.fxml;
    exports com.example.supplychain19novm;
}