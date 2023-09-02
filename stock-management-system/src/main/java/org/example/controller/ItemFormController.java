package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ItemFormController {
    public AnchorPane itemFormId;
    @FXML
    public void clickOnItemBack(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/main_form.fxml"));

        Scene scene = new Scene(root);

        Stage stage = (Stage) this.itemFormId.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dashboard");
    }
}
