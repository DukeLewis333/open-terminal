package com.open.terminal.openterminal;


import javafx.fxml.FXML;
/**
 * @description:
 * @author：dukelewis
 * @date: 2025/12/28
 * @Copyright： https://github.com/DukeLewis
 */
public class WelcomeController {

    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void handleCreateConnection() {
        if (mainController != null) {
            mainController.handleNewConnection();
        }
    }
}
