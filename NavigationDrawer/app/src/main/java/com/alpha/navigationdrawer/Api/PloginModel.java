package com.alpha.navigationdrawer.Api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by haseeb on 12/1/2017.
 */

public class PloginModel {
    @SerializedName("ID")
    String ID;

    @SerializedName("Password")
    String Password;

    @SerializedName("response")
    String response;

    @SerializedName("Action")
    String Action;

    public String getResponse()
    {
        return response;
    }
}
