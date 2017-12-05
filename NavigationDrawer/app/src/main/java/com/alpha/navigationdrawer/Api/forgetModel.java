package com.alpha.navigationdrawer.Api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by haseeb on 12/4/2017.
 */

public class forgetModel {

    @SerializedName("cnic")
    String cnic;

    @SerializedName("Action")
    String Action;

    @SerializedName("response")
    String response;

    public String getResponse()
    {
        return response;
    }


}
