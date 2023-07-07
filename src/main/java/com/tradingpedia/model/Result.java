
package com.tradingpedia.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Result {

    @SerializedName("RESULT")
    @Expose
    private String result;
    @SerializedName("ID")
    @Expose
    private String id;
    @SerializedName("NUMBER")
    @Expose
    private String number;
    @SerializedName("AREACODE")
    @Expose
    private String areacode;
    @SerializedName("AMOUNT")
    @Expose
    private String amount;
    @SerializedName("REPEAT")
    @Expose
    private String repeat;
    @SerializedName("TIME")
    @Expose
    private String time;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
