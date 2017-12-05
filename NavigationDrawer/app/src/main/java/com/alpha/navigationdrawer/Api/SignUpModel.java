package com.alpha.navigationdrawer.Api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by haseeb on 11/21/2017.
 */

public class SignUpModel {
    @SerializedName("name")
    String name;

    @SerializedName("cnic")
    String cnic;

    @SerializedName("email")
    String email;

    @SerializedName("mothername")
    String mothername;

    @SerializedName("mobile")
    String mobileNo;

    @SerializedName("policestation")
    String PoliceStation;

    @SerializedName("password")
    String password;

    @SerializedName("Action")
    private int Action;

    @SerializedName("response")
    private String Response;

    public String getResponse()
    {
        return Response;
    }
}
