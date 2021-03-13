
package com.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class City {

    @SerializedName("id")
    @Expose
    public double id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("coord")
    @Expose
    public Coord coord;
    @SerializedName("country")
    @Expose
    public String country;
    @SerializedName("population")
    @Expose
    public double population;
    @SerializedName("timezone")
    @Expose
    public double timezone;
    @SerializedName("sunrise")
    @Expose
    public double sunrise;
    @SerializedName("sunset")
    @Expose
    public double sunset;

}
