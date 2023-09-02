package org.example.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class DashboardFormController {

    public AnchorPane rootNode;

    public AnchorPane dashboardContent;
    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        //open the customer manage form
        Parent root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/customer_form.fxml")));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("Customer Manage");
        stage.show();

    }
    @FXML
    public void btnItemOnAction(ActionEvent actionEvent) throws IOException {
        //open the Item Manage Form to the Dashboard stage
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/item_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage primaryStage = (Stage) this.rootNode.getScene().getWindow();

        primaryStage.setScene(scene);
        primaryStage.setTitle("Item Manage");
    }

    @FXML
    void clickOnOrderManage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/order_form.fxml"));

        this.dashboardContent.getChildren().clear();
        this.dashboardContent.getChildren().add(root);
    }
}
