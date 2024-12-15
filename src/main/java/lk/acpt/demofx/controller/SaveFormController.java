package lk.acpt.demofx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lk.acpt.demofx.dto.VehicleDto;
import lk.acpt.demofx.model.VehicleModel;

public class SaveFormController {
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
    void save(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());
        String brand = txtBrand.getText();
        String model = txtModel.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double price = Double.parseDouble(txtPrice.getText());

        boolean b = VehicleModel.saveVehicle(new VehicleDto(id, brand, model, qty, price));

        if(b){
            //alert
        }else{
            //alert
        }
    }

}
