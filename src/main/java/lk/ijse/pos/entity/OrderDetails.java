package lk.ijse.pos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class OrderDetails {
    private String orderId;
    private String supplement_id;
    private int qty;
    private double unitPrice;
    private double total;

}
