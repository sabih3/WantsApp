package wantapp.netaq.com.wantapp.models;


/**
 * Created by sabih on 5/16/2017.
 */

public class SMSRequest {

    public String content;
    public String[] to;

    public SMSRequest(String text, String[] to) {
        this.content = text;
        this.to = to;
    }



}
