package com.example.airbag.airbag.rest;

import com.example.airbag.airbag.Urls.Urls;
import com.example.airbag.airbag.activities.AccountDetails;
import com.example.airbag.airbag.classes.Bag;
import com.example.airbag.airbag.models.accountdetailsmodel.AccountDetailsModel;
import com.example.airbag.airbag.models.authsigninmodel.AuthoritySignInModel;
import com.example.airbag.airbag.models.bagdetailspostmodel.BagDetailsPostModel;
import com.example.airbag.airbag.models.getotpmodel.GetOtpModel;
import com.example.airbag.airbag.models.mybagsmodel.MyBagsModel;
import com.example.airbag.airbag.models.mytripsmodel.MyTripsModel;
import com.example.airbag.airbag.models.reportlostmodel.ReportLostModel;
import com.example.airbag.airbag.models.statusupdatemodel.StatusUpdateModel;
import com.example.airbag.airbag.models.verifyotpmodel.VerifyOtpModel;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST(Urls.GET_OTP)
    Call<GetOtpModel> getOTP(@Field("mobile") String mobileNumber);

    @FormUrlEncoded
    @POST(Urls.Verify_OTP)
    Call<VerifyOtpModel> verifyOTP(@Field("mobile") String mobile, @Field("otp") String otp);
    
    @FormUrlEncoded
    @POST(Urls.Update_Profile)
    Call<AccountDetailsModel> updateProfile(@Field("username") String username, @Field("password") String password,@Field("email") String email,@Field("mobile") String mobile,
                                            @Field("token_key") String token_key,@Field("reg_id") String reg_id);
    //@GET("/RetrofitExample/bags.json")
    //public void getBags(Callback<List<Bag>> response);

    @FormUrlEncoded
    @POST(Urls.Auth_SignIn)
    Call<AuthoritySignInModel> authSignIn(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST(Urls.Post_BagDetails)
    Call<BagDetailsPostModel> postBagDetails(@Field("ticket_number") String TicketNumber, @Field("mobile") String mobile, @Field("items") JSONObject[] json);

    @FormUrlEncoded
    @POST(Urls.FCM_POST)
    Call<VerifyOtpModel> fcmPost(@Field("dev_id") String dev_id, @Field("reg_id") String reg_id,@Field("name") String name);

    @FormUrlEncoded
    @POST(Urls.STATUS_UPDATE)
    Call<StatusUpdateModel> statusUpdate(@Field("qr_code") String qr_code,@Field("checkpoint") int checkpoint,@Field("token_key") String token_key);

    @FormUrlEncoded
    @POST(Urls.Lost_Item)
    Call<ReportLostModel> reportLost(@Field("qr_code") String qr_code, @Field("username") String username);

    @FormUrlEncoded
    @POST(Urls.LIST_TRIP)
    Call<MyTripsModel> listTrip(@Field("token_key") String token_key, @Field("mobile") String mobile);

    @FormUrlEncoded
    @POST(Urls.LIST_ITEM)
    Call<MyBagsModel> listBags(@Field("token_key") String token_key, @Field("mobile") String mobile, @Field("ticket_number") String ticket_number);

}
