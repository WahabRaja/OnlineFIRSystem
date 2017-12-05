package com.alpha.navigationdrawer.Api;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by haseeb on 12/1/2017.
 */

public class StatusViewModel {

    @SerializedName("response")
    ArrayList<StatusViewDetail> Response;

    public  ArrayList<StatusViewDetail> getResponse()
    {
        return Response;
    }
}
