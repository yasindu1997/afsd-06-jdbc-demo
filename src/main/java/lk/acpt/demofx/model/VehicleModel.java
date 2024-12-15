package lk.acpt.demofx.model;

import lk.acpt.demofx.db.DBConnection;
import lk.acpt.demofx.dto.VehicleDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class VehicleModel {

    public static boolean saveVehicle(VehicleDto vehicleDto) {

        try {
            Connection connection = DBConnection.getDBConnection().getConnection();

            Connection connection2 = DBConnection.getDBConnection().getConnection();

            //write sql query
            PreparedStatement preparedStatement = connection.prepareStatement("insert into vehicle values(?,?,?,?,?)");
            preparedStatement.setObject(1, vehicleDto.getId());
            preparedStatement.setObject(2, vehicleDto.getBrand());
            preparedStatement.setObject(3, vehicleDto.getModel());
            preparedStatement.setObject(4, vehicleDto.getQty());
            preparedStatement.setObject(5, vehicleDto.getPrice());

            //execute query
            int i = preparedStatement.executeUpdate();

            return i > 0;

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteVehicle(int id) {

    }

    public static void updateVehicle(VehicleDto vehicleDto) {

    }

    public static List<VehicleDto> getAllVehicle() {
        return null;
    }

    public static VehicleDto searchVehicle(int id) {
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();

            //write sql query
            PreparedStatement preparedStatement = connection.prepareStatement("select * from vehicle where id = ?");
            preparedStatement.setObject(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            VehicleDto vehicleDto = new VehicleDto();

            if (resultSet.next()) {
                vehicleDto.setBrand(resultSet.getString(2));
                vehicleDto.setModel(resultSet.getString(3));
                vehicleDto.setQty(Integer.parseInt(String.valueOf(resultSet.getInt(4))));
                vehicleDto.setPrice(Double.parseDouble(String.valueOf(resultSet.getDouble(5))));
            }

            return vehicleDto;

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
