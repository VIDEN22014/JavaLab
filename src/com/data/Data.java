
package com.data;

import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("cod")
    @Expose
    public String cod;
    @SerializedName("message")
    @Expose
    public double message;
    @SerializedName("cnt")
    @Expose
    public double cnt;
    @SerializedName("list")
    @Expose
    public java.util.List<com.data.List> list = new ArrayList<com.data.List>();
    @SerializedName("city")
    @Expose
    public City city;

}
