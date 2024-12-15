package lk.acpt.demofx.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.acpt.demofx.dto.OrderDetailDto;
import lk.acpt.demofx.dto.OrderDto;
import lk.acpt.demofx.dto.VehicleDto;
import lk.acpt.demofx.model.OrderModel;
import lk.acpt.demofx.model.VehicleModel;
import lk.acpt.demofx.tm.ItemTM;

import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class OrderController implements Initializable {
    @FXML
    private Label lblTotal;

    @FXML
    private TableView<ItemTM> tblItems;

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
    private TextField txtQtyOnHand;

    private List<ItemTM> itemTMS;

    private ArrayList<OrderDetailDto> orderDetailDtos;

    private double subTotal = 0.0;

    @FXML
    void addToCart(ActionEvent event) {
        int qty = Integer.parseInt(txtQty.getText());
        String brand = txtBrand.getText();
        String model = txtModel.getText();
        double unitPrice = Double.parseDouble(txtPrice.getText());
        double total = unitPrice * qty;

        subTotal+=total;
        lblTotal.setText(String.valueOf(subTotal));

        itemTMS.add(new ItemTM(brand,model,qty,unitPrice,total));

        int id = Integer.parseInt(txtId.getText());
        orderDetailDtos.add(new OrderDetailDto(id,qty,total));

        tblItems.setItems(FXCollections.observableArrayList(itemTMS));
    }

    @FXML
    void placeOrder(ActionEvent event) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String format = dateFormat.format(date);

        try {
            boolean orderPlaced = OrderModel.placeOrder(new OrderDto(format, subTotal, orderDetailDtos));
            if(orderPlaced){
                System.out.println("Order Placed Successfully !");
            }else{
                System.out.println("Place Order Failed !");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void search(ActionEvent event) {
        Integer id = Integer.valueOf(txtId.getText());

        VehicleDto vehicleDto = VehicleModel.searchVehicle(id);
        txtBrand.setText(vehicleDto.getBrand());
        txtModel.setText(vehicleDto.getModel());
        txtQtyOnHand.setText(String.valueOf(vehicleDto.getQty()));
        txtPrice.setText(String.valueOf(vehicleDto.getPrice()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblItems.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("brand"));
        tblItems.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("model"));
        tblItems.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblItems.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblItems.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("total"));

        itemTMS = new ArrayList<>();

        orderDetailDtos = new ArrayList<>();
    }
}
