package com.alpha.navigationdrawer.Api;



import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by haseeb on 10/6/2017.
 */

public interface ApiInterface {

    @FormUrlEncoded
    @POST("Upload.php")
    retrofit2.Call<SignUpModel> RegisterUser(@Field("name") String name ,@Field("cnic") String cnic,@Field("email") String email,@Field("mothername") String mothername,@Field("mobile") String moblie,@Field("policestation") String policestation ,@Field("password") String password, @Field("Action") int Action);

    @FormUrlEncoded
    @POST("Upload.php")
    retrofit2.Call<LoginModel> LoginUser(@Field("cnic") String cnic, @Field("password") String password, @Field("Action") int Action);

    @FormUrlEncoded
    @POST("RegisterFIR.php")
    retrofit2.Call<FIrLaunch> FirLaunch(@Field("Description") String des, @Field("DateTime") String DateTime, @Field("PoliceStation") String PolicStation, @Field("CrimeType") String CrimeType, @Field("UserID") int UserID);

    @FormUrlEncoded
    @POST("Upload.php")
    retrofit2.Call<EditInfoModel> UpdateInfo(@Field("Contact") String contct,@Field("Email") String Email,@Field("PoliceStation") String PoliceStation,@Field("cnic") String cnic,@Field("Action") int Action);

    @FormUrlEncoded
    @POST("StatusShow.php")
    Call<StatusViewDetail> Status(@Field("PoliceStation") String PoliceStation);

    @FormUrlEncoded
    @POST("PoliceLogin.php")
    Call<PloginModel> LoginPolice(@Field("ID") String ID, @Field("Password") String Password, @Field("Action") int Action);

    @FormUrlEncoded
    @POST("Upload.php")
    retrofit2.Call<forgetModel> ForgetUser(@Field("cnic") String cnic, @Field("Action") int Action);

    @FormUrlEncoded
    @POST("StatusPost.php")
    Call<StatusPost> PostStatus(@Field("Status") String Status,@Field("PoliceStation") String PoliceStation);

}
