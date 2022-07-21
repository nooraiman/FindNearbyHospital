package com.example.findnearbyhospital;

import java.util.ArrayList;

public class User {
    String name, email, phone;
    Double curLat,curLong;
//    ArrayList<Nearby> nearbyPlace = new ArrayList<Nearby>(); KIV

    public User() {}

    public User(String n, String e, String p) {
        this.name = n;
        this.email = e;
        this.phone = p;
        curLat = 0.0;
        curLong = 0.0;
    }

    public void setName(String n) { this.name = n; }
    public void setEmail(String e) { this.email = e; }
    public void setPhone(String p) { this.phone = p; }
    public void setLocation(Double l, Double l2) { this.curLat = l; this.curLong = l2; }
    public void setCurLat(Double l) { this.curLat = l; }
    public void setCurLong(Double l) { this.curLong = l; }

    // KIV
//    public void setNearbyPlace(String p, String a, Double l1, Double l2) {
//        Nearby n = new Nearby();
//        n.setNearby(p, a, l1, l2);
//        nearbyPlace.add(n);
//    }
//    public ArrayList getNearbyPlace() { return this.nearbyPlace; }

    public String getName() { return  this.name; }
    public String getEmail() { return  this.email; }
    public String getPhone() { return  this.phone; }
    public Double getCurLat() { return  this.curLat; }
    public Double getCurLong() { return  this.curLong; }
}
