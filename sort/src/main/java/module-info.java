module log121.amande {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens log121.amande to javafx.fxml;

    exports log121.amande;
}
