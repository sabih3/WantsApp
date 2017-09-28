package wantapp.netaq.com.wantapp.screens;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import wantapp.netaq.com.wantapp.R;
import wantapp.netaq.com.wantapp.models.Want;
import wantapp.netaq.com.wantapp.utils.WantsContainer;

public class ScreenNewWant extends AppCompatActivity {

    @BindView(R.id.btn_post_want)Button btnWantPost;
    @BindView(R.id.field_want)EditText fieldWant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_new_want);

        ButterKnife.bind(this);

        initViews();
    }

    private void initViews() {
        btnWantPost.setOnClickListener(new WantPostListener());
        fieldWant.setSingleLine();
        fieldWant.setOnEditorActionListener(new WantFieldDoneButtonListener());
    }

//    @Override
//    public void onBackPressed() {
//        //super.onBackPressed();
//        //Todo:Handle for discarding the note
//
//    }

    private class WantPostListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            String wantedItem = fieldWant.getText().toString();
            processWantedItem(wantedItem);




        }
    }

    private void processWantedItem(String wantedItem) {
        if(wantedItem.isEmpty()){

        }else{
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String currentDate = dateFormat.format(new Date());
            Want want = new Want(wantedItem,currentDate);
            WantsContainer.wantsList.add(want);

            Intent intent = new Intent(ScreenNewWant.this, MainActivity.class);
            startActivity(intent);
        }
    }

    private class WantFieldDoneButtonListener implements TextView.OnEditorActionListener {
        @Override
        public boolean onEditorAction(TextView textView, int imeCode, KeyEvent keyEvent) {
            if(imeCode == EditorInfo.IME_ACTION_DONE){

                String wantedItem = fieldWant.getText().toString();
                processWantedItem(wantedItem);

            }
            return true;
        }
    }
}
