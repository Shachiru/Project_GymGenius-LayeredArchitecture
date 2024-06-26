package lk.ijse.pos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@Data
@NoArgsConstructor

public class MemberDTO implements Serializable {
    private String id;
    private String name;
    private String address;
    private String mobile;
    private String dob;
    private String gender;

}
