package lk.acpt.demofx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void save(ActionEvent event) {
        String userName = txtUsername.getText();
        String password = txtPassword.getText();

        //login logic
    }

    public void saveData() {

    }

}
