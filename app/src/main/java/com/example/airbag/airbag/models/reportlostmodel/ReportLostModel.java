package com.example.airbag.airbag.models.reportlostmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by risha on 4/1/2017.
 */

public class ReportLostModel {

    @SerializedName("status")
    @Expose
    private Integer status;

    @SerializedName("shelf_number")
    @Expose
    private Integer shelf_number;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getShelf_Number()
    {
        return shelf_number;
    }
    public Integer setShelf_Number(Integer shelf_number)
    {
        return this.shelf_number=shelf_number;
    }
}
