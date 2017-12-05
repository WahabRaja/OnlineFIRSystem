package com.alpha.navigationdrawer.Api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by haseeb on 11/30/2017.
 */

public class StatusViewDetail  {


    @SerializedName("PoliceStation")
    String PoliceStation;

    @SerializedName("Status")
    String Status;

    @SerializedName("id")
    String id;

    @SerializedName("response")
    String response;

    public String getResponse()
    {
        return response;
    }
}
