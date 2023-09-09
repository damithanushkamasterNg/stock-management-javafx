package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.db.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemFormController {
    public AnchorPane itemFormId;

    public TextField itemNo;
    public TextField qtyId;
    public TextField itemNameid;
    public TextField descriptionid;

    @FXML
    public void clickOnItemBack(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/main_form.fxml"));

        Scene scene = new Scene(root);

        Stage stage = (Stage) this.itemFormId.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dashboard");
    }

    public void clickSaveItem(ActionEvent actionEvent) {
        String code = itemNo.getText();
        String name = itemNameid.getText();
        String qty_on_hand = qtyId.getText();
        String description = descriptionid.getText();

        try {
            Connection con = DbConnection.getInstance().getConnection();

            String sql = "INSERT INTO item VALUES(?, ?, ?, ?)";
            PreparedStatement pstm = con.prepareStatement(sql);

            pstm.setString(1, code);
            pstm.setString(2, description);
            pstm.setString(3, name);
            pstm.setString(4, qty_on_hand);

            boolean isSaved = pstm.executeUpdate() > 0;

            if(isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "item saved!").show();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void clickDeleteItem(ActionEvent actionEvent) {
        String id = itemNo.getText();
        try {
            Connection con = DbConnection.getInstance().getConnection();

            String sql = "delete from item WHERE code = ?";

            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, id);

            boolean isDeleted = pstm.executeUpdate() > 0;
            if(isDeleted) {
                clearFields();
                new Alert(Alert.AlertType.CONFIRMATION, "item deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void clearFields() {
        itemNameid.setText("");
        qtyId.setText("");
        itemNo.setText("");
        descriptionid.setText("");
    }

    public void clickUpdateItem(ActionEvent actionEvent) {
        String code = itemNo.getText();
        String name = itemNameid.getText();
        String qty_on_hand = qtyId.getText();
        String description = descriptionid.getText();

        try {
            Connection con = DbConnection.getInstance().getConnection();

            String sql = "UPDATE item SET code = ?, name = ?, qty_on_hand = ? WHERE description = ?";

            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, code);
            pstm.setString(2, name);
            pstm.setString(3, qty_on_hand);
            pstm.setString(4, description);

            boolean isUpdated = pstm.executeUpdate() > 0;
            if(isUpdated) {
                clearFields();
                new Alert(Alert.AlertType.CONFIRMATION, "item updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void clickResetItem(ActionEvent actionEvent) {
        clearFields();
    }

    public void searchItem(KeyEvent keyEvent) {
        String itemCodefromField = itemNo.getText();
        if(itemCodefromField == null){
            return;
        }

        try {
            Connection con = DbConnection.getInstance().getConnection();

            String sql = "SELECT * FROM item WHERE code=?";

            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, itemCodefromField);

            ResultSet resultSet = pstm.executeQuery();

            if(resultSet.next()) {
                String itemCode = resultSet.getString(1);
                String itemName = resultSet.getString(2);
                String description = resultSet.getString(3);
                String qty = resultSet.getString(4);

                setFields(itemCode, itemName, description, qty);
            } else {
                new Alert(Alert.AlertType.WARNING, "item not found!").show();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setFields(String itemCode, String itemName, String description, String qty) {
        this.itemNo.setText(itemCode);
        this.itemNameid.setText(itemName);
        this.descriptionid.setText(description);
        this.qtyId.setText(qty);
    }

    //cache initial status of the form

    public void initialize(){
        System.out.println("initialized the item form");
    }
}
