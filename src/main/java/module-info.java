module com.open.terminal.openterminal {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    requires java.desktop;
    requires javafx.swing;
    requires org.slf4j;
    requires jsch;
    requires annotations;

    opens com.open.terminal.openterminal to javafx.fxml;
    exports com.open.terminal.openterminal;
    exports com.open.terminal.openterminal.util;
}
