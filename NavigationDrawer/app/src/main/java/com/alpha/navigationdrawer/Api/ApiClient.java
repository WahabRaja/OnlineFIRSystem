package com.alpha.navigationdrawer.Api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by haseeb on 10/6/2017.
 */

public class ApiClient {

    private  static final String BaseUri="http://www.dibukhanmathematician.com/OnlineFIRSystem/";
    private static Retrofit retrofit;

    public static Retrofit getApiClient()
    {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        if(retrofit==null)
        {
            retrofit= new Retrofit.Builder().baseUrl(BaseUri).
                    addConverterFactory(GsonConverterFactory.create()).build();
        }
            return retrofit;
    }
}
