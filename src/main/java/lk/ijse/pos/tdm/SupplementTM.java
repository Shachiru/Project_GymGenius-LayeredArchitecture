package lk.ijse.pos.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor

public class SupplementTM {
    private String id;
    private String productName;
    private String unitPrice;
    private String qty;
}
