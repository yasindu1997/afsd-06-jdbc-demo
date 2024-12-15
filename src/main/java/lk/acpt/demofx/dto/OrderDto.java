package lk.acpt.demofx.dto;

import java.util.ArrayList;
import java.util.Date;

public class OrderDto {
    private String orderDate;
    private double subTotal;
    private ArrayList<OrderDetailDto> orderDetailDtos;

    public OrderDto(String orderDate, double subTotal, ArrayList<OrderDetailDto> orderDetailDtos) {
        this.orderDate = orderDate;
        this.subTotal = subTotal;
        this.orderDetailDtos = orderDetailDtos;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public ArrayList<OrderDetailDto> getOrderDetailDtos() {
        return orderDetailDtos;
    }

    public void setOrderDetailDtos(ArrayList<OrderDetailDto> orderDetailDtos) {
        this.orderDetailDtos = orderDetailDtos;
    }
}
