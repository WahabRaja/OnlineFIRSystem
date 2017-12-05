package com.alpha.navigationdrawer.Api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by haseeb on 11/30/2017.
 */

public class EditInfoModel {
    @SerializedName("Contact")
    String Contact;

    @SerializedName("Email")
    String Email;

    @SerializedName("PoliceStation")
    String PoliceStation;

    @SerializedName("cnic")
    String cnic;

    @SerializedName("Action")
    int Action;

    @SerializedName("response")
    String Response;

    public String getResponse()
    {
        return Response;
    }
}
