package lk.ijse.pos.dto;

import lk.ijse.pos.entity.Order;
import lk.ijse.pos.entity.OrderDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class PlaceOrderDTO implements Serializable {
    private OrderDTO order;
    private List<OrderDetailsDTO> orderDetail;
}
