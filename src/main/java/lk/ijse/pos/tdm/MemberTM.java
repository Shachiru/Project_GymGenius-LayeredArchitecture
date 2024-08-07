package lk.ijse.pos.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor

public class MemberTM {
    private String id;
    private String name;
    private String address;
    private String mobile;
    private String dob;
    private String gender;
}
