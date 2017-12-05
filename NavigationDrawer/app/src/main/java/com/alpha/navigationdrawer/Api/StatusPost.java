package com.alpha.navigationdrawer.Api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by haseeb on 12/5/2017.
 */

public class StatusPost {
    @SerializedName("PoliceStation")
    String PoliceStation;

    @SerializedName("Status")
    String Status;

    @SerializedName("response")
    String response;

    public String getResponse()
    {
        return response;
    }
}
