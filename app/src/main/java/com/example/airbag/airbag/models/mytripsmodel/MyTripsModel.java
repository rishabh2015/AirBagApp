package com.example.airbag.airbag.models.mytripsmodel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyTripsModel {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("trip_data")
    @Expose
    private List<TripDatum> tripData = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<TripDatum> getTripData() {
        return tripData;
    }

    public void setTripData(List<TripDatum> tripData) {
        this.tripData = tripData;
    }

}