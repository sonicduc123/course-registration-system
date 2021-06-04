package pojo;

import java.util.Date;

public class User implements java.io.Serializable {
    private int id;
    private String username;
    private String password;
    private String name;
    private String userID;
    private String email;
    private String phone;
    private Date birthday;
    private String type;

    public User() {}
    public User(int id, String username, String password, String name, String userID, String email, String phone, Date birthday, String type) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.userID = userID;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
        this.type = type;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
