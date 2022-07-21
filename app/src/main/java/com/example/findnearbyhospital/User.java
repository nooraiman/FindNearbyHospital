package com.example.findnearbyhospital;

import java.util.ArrayList;

public class User {
    String UID, name, email, phone;

    public User() {}

    public User(String n, String e, String p) {
        this.UID = null;
        this.name = n;
        this.email = e;
        this.phone = p;
    }

    public void setUID(String id) { this.UID = id; }
    public void setName(String n) { this.name = n; }
    public void setEmail(String e) { this.email = e; }
    public void setPhone(String p) { this.phone = p; }


    public String getName() { return  this.name; }
    public String getEmail() { return  this.email; }
    public String getPhone() { return  this.phone; }
}
