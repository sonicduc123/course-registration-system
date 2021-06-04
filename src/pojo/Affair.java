package pojo;

import java.util.Date;

public class Affair implements java.io.Serializable {
    private int id;
    private String username;
    private String password;
    private String name;
    private int affairID;
    private String email;
    private String phone;
    private Date birthday;

    public Affair() {}
    public Affair(int id, String username, String password, String name, int affairID, String email, String phone, Date birthday) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.affairID = affairID;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAffairID() { return affairID; }

    public void setAffairID(int affairID) {
        this.affairID = affairID;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}