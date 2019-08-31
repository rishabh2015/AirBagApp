
package com.example.airbag.airbag.models.authsigninmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthoritySignInModel {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("token_key")
    @Expose
    private String tokenKey;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

}
