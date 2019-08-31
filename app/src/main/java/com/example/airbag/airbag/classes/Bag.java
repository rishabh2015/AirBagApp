package com.example.airbag.airbag.classes;

import android.graphics.Bitmap;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by risha on 3/29/2017.
 */

public class Bag {
    public Integer Bagstatus;
    public String BagColor;
    public String BagType;
    public String BagName;
    public String BagBrand;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public Bag(Integer bagstatus, String bagColor, String bagType, String bagName, String bagBrand, Bitmap bitmap) {
        Bagstatus = bagstatus;
        BagColor = bagColor;
        BagType = bagType;
        BagName = bagName;
        BagBrand = bagBrand;
        this.bitmap = bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }


    public Bitmap bitmap;

    public Bag(String BagColor, String BagType, String BagName, String BagBrand) {
        this.BagBrand = BagBrand;
        this.BagColor = BagColor;
        this.BagType = BagType;
        this.BagName = BagName;


    }


    public Integer getBagstatus() {
        return Bagstatus;
    }

    public void setBagstatus(Integer bagstatus) {
        Bagstatus = bagstatus;
    }

    public String getBagColor() {
        return BagColor;
    }

    public void setBagColor(String bagColor) {
        BagColor = bagColor;
    }

    public String getBagType() {
        return BagType;
    }



    public void setBagType(String bagType) {
        BagType = bagType;
    }

    public String getBagName() {
        return BagName;
    }

    public void setBagName(String bagName) {
        BagName = bagName;
    }

    public String getBagBrand() {
        return BagBrand;
    }

    public void setBagBrand(String bagBrand) {
        BagBrand = bagBrand;
    }

    public JSONObject toJSON() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("BagName", getBagName());
            jsonObject.put("BagBrand", getBagBrand());
            jsonObject.put("BagType", getBagType());
            jsonObject.put("BagColor", getBagColor());

            return jsonObject;
        } catch (JSONException e) {

            e.printStackTrace();
            return null;
        }
    }
}
