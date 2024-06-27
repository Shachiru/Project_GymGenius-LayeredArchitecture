package lk.ijse.pos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class EmployeeDTO implements Serializable {
    private String id;
    private String name;
    private String address;
    private String mobile;
    private String empRole;
    private String userId;
}
