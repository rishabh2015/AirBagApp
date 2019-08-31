package com.example.airbag.airbag.models.mybagsmodel;

import com.example.airbag.airbag.models.mytripsmodel.TripDatum;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by risha on 4/2/2017.
 */

public class MyBagsModel {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("item_data")
    @Expose
    private List<BagDatum> bagData = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<BagDatum> getBagData() {
        return bagData;
    }

    public void setBagData(List<BagDatum> bagData) {
        this.bagData = bagData;
    }


}
