package com.example.airbag.airbag.classes;

/**
 * Created by risha on 3/29/2017.
 */

public class Trip {
    public String TicketNumber;

    public String getTripstatus() {
        return Tripstatus;
    }

    public void setTripstatus(String tripstatus) {
        Tripstatus = tripstatus;
    }

    public String Tripstatus;

    public String getTicketNumber() {
        return TicketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        TicketNumber = ticketNumber;
    }

    public Trip(String TicketNumber, String Tripstatus)
    {

        this.TicketNumber=TicketNumber;
        this.Tripstatus=Tripstatus;
    }

}
