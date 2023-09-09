package org.example.controller;

import com.mysql.cj.jdbc.CallableStatementWrapper;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.example.db.DbConnection;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerFormController {

    public TextField customerId;
    public TextField phnId;
    public TextField customerAddressId;
    public TextField customerNameId;
    public void clickOnSaveCustomer(ActionEvent actionEvent) {
        String id = customerId.getText();
        String name = customerNameId.getText();
        String address = customerAddressId.getText();
        String phoneNo = phnId.getText();

        try {
            Connection con = DbConnection.getInstance().getConnection();

            String sql = "INSERT INTO customer VALUES(?, ?, ?, ?)";
            PreparedStatement pstm = con.prepareStatement(sql);

            pstm.setString(1, id);
            pstm.setString(2, name);
            pstm.setString(3, phoneNo);
            pstm.setString(4, address);

            boolean isSaved = pstm.executeUpdate() > 0;

            if(isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void clearFields() {
        customerId.setText("");
        customerNameId.setText("");
        customerAddressId.setText("");
        phnId.setText("");
    }

    public void serchCustomer(MouseEvent mouseEvent) {
        String id = customerId.getText();
        if(id == null){
            return;
        }

        try {
            Connection con = DbConnection.getInstance().getConnection();

            String sql = "SELECT * FROM customer WHERE id=?";

            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, id);

            ResultSet resultSet = pstm.executeQuery();

            if(resultSet.next()) {
                String txtId = resultSet.getString(1);
                String txtName = resultSet.getString(2);
                String txtAddress = resultSet.getString(3);
                String txtTel = resultSet.getString(4);

                setFields(txtId, txtName, txtAddress, txtTel);
            } else {
                new Alert(Alert.AlertType.WARNING, "customer not found!").show();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    private void setFields(String txtId, String txtName, String txtAddress, String txtTel) {
        this.customerId.setText(txtId);
        this.customerNameId.setText(txtName);
        this.customerAddressId.setText(txtAddress);
        this.phnId.setText(txtTel);
    }

    public void clickonReset(ActionEvent actionEvent) {
        this.clearFields();
    }

    public void clickOnUpdate(ActionEvent actionEvent) throws SQLException {
        String id = customerId.getText();
        String name = customerNameId.getText();
        String address = customerAddressId.getText();
        String phoneNo = phnId.getText();

        try {
            Connection con = DbConnection.getInstance().getConnection();

            String sql = "UPDATE customer SET name = ?, address = ?, phoneNo = ? WHERE id = ?";

            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, name);
            pstm.setString(2, address);
            pstm.setString(3, phoneNo);
            pstm.setString(4, id);

            boolean isUpdated = pstm.executeUpdate() > 0;
            if(isUpdated) {
                clearFields();
                new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void deleteCustomer(ActionEvent actionEvent) {
        String id = customerId.getText();
        try {
            Connection con = DbConnection.getInstance().getConnection();

            String sql = "delete from customer WHERE id = ?";

            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, id);

            boolean isDeleted = pstm.executeUpdate() > 0;
            if(isDeleted) {
                clearFields();
                new Alert(Alert.AlertType.CONFIRMATION, "customer deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
