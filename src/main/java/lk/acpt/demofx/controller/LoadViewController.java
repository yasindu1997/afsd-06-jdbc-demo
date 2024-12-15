package lk.acpt.demofx.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.acpt.demofx.tm.VehicleTM;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoadViewController implements Initializable {
    @FXML
    private TableView<VehicleTM> tblVehicle;

    @FXML
    void load(ActionEvent event) {
        try {
            //load Driver class to ram
            Class.forName("com.mysql.cj.jdbc.Driver");

            //establish a connection with db
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/afsd_06", "root", "acpt");

            //write sql query
            PreparedStatement preparedStatement = connection.prepareStatement("select * from vehicle");

            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<VehicleTM> tms = new ArrayList<>();

            while (resultSet.next()) {
                VehicleTM tm = new VehicleTM(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getInt(4),
                        resultSet.getDouble(5));

                tms.add(tm);
            }

            //configure fx table
            tblVehicle.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
            tblVehicle.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("brand"));
            tblVehicle.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("model"));
            tblVehicle.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));
            tblVehicle.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("price"));

            tblVehicle.setItems(FXCollections.observableArrayList(tms));

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            //load Driver class to ram
            Class.forName("com.mysql.cj.jdbc.Driver");

            //establish a connection with db
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/afsd_06", "root", "acpt");

            //write sql query
            PreparedStatement preparedStatement = connection.prepareStatement("select * from vehicle");

            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<VehicleTM> tms = new ArrayList<>();

            while (resultSet.next()) {
                VehicleTM tm = new VehicleTM(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getInt(4),
                        resultSet.getDouble(5));

                tms.add(tm);
            }

            //configure fx table
            tblVehicle.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
            tblVehicle.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("brand"));
            tblVehicle.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("model"));
            tblVehicle.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));
            tblVehicle.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("price"));

            tblVehicle.setItems(FXCollections.observableArrayList(tms));

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
