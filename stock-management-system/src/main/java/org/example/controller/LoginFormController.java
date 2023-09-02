package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginFormController {

    public AnchorPane loginAnc;

    @FXML
    private TextField userNameId;

    @FXML
    private TextField passwordId;
    String userName = "Admin";
    String password ="123";

    @FXML
    void clickOnLoginButton(ActionEvent event) throws IOException {
        String userName = userNameId.getText();
        String password = passwordId.getText();
       if(Objects.equals(userName, "Admin") && Objects.equals(password, "123")){
           Parent root = FXMLLoader.load(this.getClass().getResource("/view/main_form.fxml"));
           Stage primaryStage = (Stage) this.loginAnc.getScene().getWindow();
           Scene scene = new Scene(root);
           primaryStage.setScene(scene);

           primaryStage.show();;
           primaryStage.setTitle("Dashboard");
       }else {
           // Display an alert for incorrect credentials
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Login Error");
           alert.setHeaderText("Incorrect Username or Password");
           alert.setContentText("Please enter a valid username and password.");
           alert.showAndWait();
       }
    }

}
