package com.example.airbag.airbag.models.mytripsmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TripDatum {

    @SerializedName("ticket_number")
    @Expose
    private String ticketNumber;
    @SerializedName("user")
    @Expose
    private Integer user;
    @SerializedName("trip_status")
    @Expose
    private Integer tripStatus;

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Integer getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(Integer tripStatus) {
        this.tripStatus = tripStatus;
    }

}