package lk.ijse.pos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor

public class Member {
    private String id;
    private String name;
    private String address;
    private String mobile;
    private String dob;
    private String gender;
}
