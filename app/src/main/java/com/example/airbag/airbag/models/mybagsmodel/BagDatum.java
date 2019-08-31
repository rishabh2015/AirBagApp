package com.example.airbag.airbag.models.mybagsmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by risha on 4/2/2017.
 */

public class BagDatum {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("journey_status")
    @Expose
    private Integer journey_status;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("qr_code")
    @Expose
    private String qr_code;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("trip")
    @Expose
    private Integer trip;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getJourney_status() {
        return journey_status;
    }

    public void setJourney_status(Integer journey_status) {
        this.journey_status = journey_status;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getQr_code() {
        return qr_code;
    }

    public void setQr_code(String qr_code) {
        this.qr_code = qr_code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTrip() {
        return trip;
    }

    public void setTrip(int trip) {
        this.trip = trip;
    }
}
