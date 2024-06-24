package lk.ijse.pos.dto;

import java.io.Serializable;

public class MemberDTO implements Serializable {
    private String id;
    private String name;
    private String address;
    private String mobile;
    private String dob;
    private String gender;

    public MemberDTO() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public MemberDTO(String id, String name, String address, String mobile, String dob, String gender) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.mobile = mobile;
        this.dob = dob;
        this.gender = gender;
    }
}
