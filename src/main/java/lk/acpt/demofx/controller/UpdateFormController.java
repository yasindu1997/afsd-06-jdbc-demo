package lk.acpt.demofx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.*;

public class UpdateFormController {
    @FXML
    private TextField txtBrand;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;

    @FXML
    void cancle(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void search(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());

        try {
            //load Driver class to ram
            Class.forName("com.mysql.cj.jdbc.Driver");

            //establish a connection with db
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/afsd_06", "root", "acpt");

            //write sql query
            PreparedStatement preparedStatement = connection.prepareStatement("select * from vehicle where id = ?");
            preparedStatement.setObject(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
               txtBrand.setText(resultSet.getString(2));
               txtModel.setText(resultSet.getString(3));
               txtQty.setText(String.valueOf(resultSet.getInt(4)));
               txtPrice.setText(String.valueOf(resultSet.getDouble(5)));
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void update(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());
        String brand = txtBrand.getText();
        String model = txtModel.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double price = Double.parseDouble(txtPrice.getText());

        try {
            //load Driver class to ram
            Class.forName("com.mysql.cj.jdbc.Driver");

            //establish a connection with db
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/afsd_06", "root", "acpt");

            //write sql query
            PreparedStatement preparedStatement = connection.prepareStatement("update vehicle set brand=?, model=?, qty=?, price = ? where id =?");
            preparedStatement.setObject(1,brand);
            preparedStatement.setObject(2,model);
            preparedStatement.setObject(3,qty);
            preparedStatement.setObject(4,price);
            preparedStatement.setObject(5,id);

            //execute query
            int i = preparedStatement.executeUpdate();

            if(i>0){
                System.out.println("Data Updated !");
            }else{
                System.out.println("Failed !");
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
