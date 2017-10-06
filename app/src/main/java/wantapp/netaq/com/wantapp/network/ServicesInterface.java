package wantapp.netaq.com.wantapp.network;


import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Query;
import wantapp.netaq.com.wantapp.models.ResponseSMS;
import wantapp.netaq.com.wantapp.models.SMSRequest;

/**
 * Created by Sabih Ahmed on 15-May-17.
 */
public interface ServicesInterface {


    @POST("https://platform.clickatell.com/messages")
    Call<ResponseSMS> sendOTP(@HeaderMap Map<String, String> headers,
                              @Body SMSRequest smsRequest);


}
