package com.alpha.navigationdrawer.Api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by haseeb on 11/21/2017.
 */

public class LoginModel {
    @SerializedName("cnic")
    String cnic;

    @SerializedName("password")
    String password;

    @SerializedName("response")
    String Response;

    public String getResponse()
    {
        return Response;
    }

}
