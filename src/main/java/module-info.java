module sparadrap.sparadrap_javafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires javafx.base;
    requires com.dlsc.formsfx;
    requires java.sql;
    requires java.desktop;

    opens classMetier.sante to javafx.base;

    opens sparadrap.sparadrap_javafx to javafx.fxml;
    exports sparadrap.sparadrap_javafx;
}