package lk.ijse.pos.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class EmployeeTM {
    private String id;
    private String name;
    private String address;
    private String mobile;
    private String empRole;
    private String userId;
}
