package lk.ijse.pos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SupplementDTO implements Serializable {
    private String id;
    private String productName;
    private String unitPrice;
    private String qty;
}
