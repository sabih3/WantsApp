package wantapp.netaq.com.wantapp.models;

/**
 * Created by sabih on 18-Sep-17.
 */

public class Want {

    private String want;
    private String created_at;

    public Want(String want, String created_at) {
        this.want = want;
        this.created_at = created_at;
    }


    public String getWant() {
        return want;
    }

    public String getCreated_at() {
        return created_at;
    }
}

