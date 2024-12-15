package lk.acpt.demofx.model;

import lk.acpt.demofx.db.DBConnection;
import lk.acpt.demofx.dto.OrderDetailDto;
import lk.acpt.demofx.dto.OrderDto;

import java.sql.*;

public class OrderModel {

    public static boolean placeOrder(OrderDto orderDto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDBConnection().getConnection();

        //disable auto commit feature
        connection.setAutoCommit(false);

        //insert data to order table
        PreparedStatement stm1 = connection.prepareStatement("insert into orders(order_date,amount)value(?,?)", Statement.RETURN_GENERATED_KEYS);
        stm1.setObject(1, orderDto.getOrderDate());
        stm1.setObject(2, orderDto.getSubTotal());

        int orderSave = stm1.executeUpdate();

        if (orderSave > 0) {
            //get order id from generated keys
            ResultSet generatedKeys = stm1.getGeneratedKeys();

            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);

                //save order details
                for (OrderDetailDto dto : orderDto.getOrderDetailDtos()) {
                    PreparedStatement stm2 = connection.prepareStatement("insert into order_details(oid,vid,qty,price)values(?,?,?,?)");
                    stm2.setObject(1, id);
                    stm2.setObject(2, dto.getVehicleId());
                    stm2.setObject(3, dto.getQty());
                    stm2.setObject(4, dto.getPrice());

                    int orderDetailSave = stm2.executeUpdate();

                    if (orderDetailSave > 0) {
                        PreparedStatement stm3 = connection.prepareStatement("update vehicle set qty=qty-? where id=?");
                        stm3.setObject(1, dto.getQty());
                        stm3.setObject(2, dto.getVehicleId());

                        int vehicleQtyUpdated = stm3.executeUpdate();

                        if (vehicleQtyUpdated <= 0) {
                            connection.rollback();
                            connection.setAutoCommit(true);
                            return false;
                        }

                    } else {
                        connection.rollback();
                        connection.setAutoCommit(true);
                        return false;
                    }
                }
            }
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } else {
            //cache remove
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }
    }
}
