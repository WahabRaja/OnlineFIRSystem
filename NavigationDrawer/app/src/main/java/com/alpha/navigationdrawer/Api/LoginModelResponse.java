package com.alpha.navigationdrawer.Api;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by haseeb on 11/30/2017.
 */

public class LoginModelResponse {
    @SerializedName("UserInfo")
    private ArrayList<LoginModel>response;

    public ArrayList<LoginModel>GetResponse(){return response;}
}
