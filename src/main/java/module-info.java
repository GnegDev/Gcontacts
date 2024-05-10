module com.gnegdev.gcontacts {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.gnegdev.gcontacts to javafx.fxml;
    exports com.gnegdev.gcontacts;
}