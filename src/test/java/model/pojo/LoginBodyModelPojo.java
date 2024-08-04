package model.pojo;

public class LoginBodyModelPojo {
    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    String email, password;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


