package lk.ijse.pos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Supplement {
    private String id;
    private String productName;
    private String unitPrice;
    private String qty;
}
