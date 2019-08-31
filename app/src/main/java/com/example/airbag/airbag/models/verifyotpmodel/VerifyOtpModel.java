package com.example.airbag.airbag.models.verifyotpmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by rishabh jain on 3/26/2017.
 */

public class VerifyOtpModel {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("is_active")
    @Expose
    private Integer is_active;
    @SerializedName("token_key")
    @Expose
    private String tokenKey;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getis_active() {
        return is_active;
    }

    public void setis_active(Integer is_active) {
        this.is_active = is_active;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

}
