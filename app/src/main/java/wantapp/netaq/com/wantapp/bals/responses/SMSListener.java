package wantapp.netaq.com.wantapp.bals.responses;

/**
 * Created by sabih on 5/17/2017.
 */

public interface SMSListener extends NetworkListener{

    void onSmsSent();
    void onSmsSentFailure();
}
