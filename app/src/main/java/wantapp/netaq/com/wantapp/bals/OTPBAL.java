package wantapp.netaq.com.wantapp.bals;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wantapp.netaq.com.wantapp.bals.responses.SMSListener;
import wantapp.netaq.com.wantapp.models.ResponseSMS;
import wantapp.netaq.com.wantapp.models.SMSRequest;
import wantapp.netaq.com.wantapp.network.RestClient;

/**
 * Created by sabih on 05-Oct-17.
 */

public class OTPBAL {

    public static void sendOTP(int otp, String mobileNumber) {

    }

    public static HashMap<String, String> getClickATellHeaders(){

        HashMap<String, String> headerMap = new HashMap<>();

        headerMap.put("Authorization","LAL0g4lpR5WJS6Avb0buZw==");
        headerMap.put("Content-Type","application/json");
        headerMap.put("Accept","application/json");
        //headerMap.put("X-Version","1");

        return headerMap;


    }


    public static void sendOTP(String recipient, int OTP, final SMSListener smsListener){
        int otp = OTP;

        String[] to = new String[1];

        to[0]= recipient;

        SMSRequest request = new SMSRequest("Welcome to iWants. Your Verification code is :"+otp, to);

        Call<ResponseSMS> call = RestClient.getAdapter().sendOTP(OTPBAL.getClickATellHeaders(), request);

        call.enqueue(new Callback<ResponseSMS>() {
            @Override
            public void onResponse(Call<ResponseSMS> call, Response<ResponseSMS> response) {

                if(response.body()!=null){
                    smsListener.onSmsSent();
                }

                if(response.errorBody()!=null){
                    smsListener.onSmsSentFailure();
                }

            }

            @Override
            public void onFailure(Call<ResponseSMS> call, Throwable t) {

                smsListener.onRequestFailed();
            }
        });
    }
}
