package com.example.findnearbyhospital;

public class Nearby{

    String place, address;
    Double lat,lon;

    public Nearby() {}

    public void setNearby(String p,String a,Double l1, Double l2) {
        this.place = p;
        this.address = a;
        this.lat = l1;
        this.lon = l2;
    }

    public void setPlace(String p) { this.place = p; }
    public void setLatitude(Double l) { this.lat = l; }
    public void setLongitude(Double l) { this.lon = l; }

    public String getPlace() { return this.place; }
    public Double getLatitude() { return this.lat; }
    public Double getLongitude() { return this.lon; }

}
