package com.alpha.navigationdrawer.Api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by haseeb on 11/29/2017.
 */

public class FIrLaunch {

    @SerializedName("Description")
    String Description;

    @SerializedName("DateTime")
    String DateTime;

    @SerializedName("PoliceStation")
    String PoliceStation;

    @SerializedName("CrimeType")
    String CrimeType;

    @SerializedName("UserID")
    int UserID;

    @SerializedName("response")
    private String Response;

    public String getResponse()
    {
        return Response;
    }
}
