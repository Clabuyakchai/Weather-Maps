package com.example.clabuyakchai.weather.data.model.belrosstrakh;

public class OfficeInfo {
    private Integer id;
    private String address;
    private Double latitude;
    private Double longitude;
    private String city;

    public OfficeInfo(Integer id, String address, Double latitude, Double longitude, String city) {
        this.id = id;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
