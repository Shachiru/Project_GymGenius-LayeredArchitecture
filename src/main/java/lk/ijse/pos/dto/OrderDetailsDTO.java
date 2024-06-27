package lk.ijse.pos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderDetailsDTO implements Serializable {
    private String orderId;
    private String supplement_id;
    private int qty;
    private double unitPrice;
    private double total;
}
