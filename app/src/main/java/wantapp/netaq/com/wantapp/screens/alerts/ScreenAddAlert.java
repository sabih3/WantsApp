package wantapp.netaq.com.wantapp.screens.alerts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xiaofeng.flowlayoutmanager.FlowLayoutManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import wantapp.netaq.com.wantapp.R;
import wantapp.netaq.com.wantapp.adapters.AddedAlertsAdapter;

public class ScreenAddAlert extends AppCompatActivity {

    @BindView(R.id.field_alert)EditText fieldAlert;
    @BindView(R.id.btn_add_alert)Button btnAddAlert;
    @BindView(R.id.added_alerts_layout)RecyclerView addedAlertListView;

    private ArrayList<String> addedAlertList;
    private AddedAlertsAdapter addedAlertsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_add_alert);

        ButterKnife.bind(this);
        addedAlertList = new ArrayList<>();
        initViews();
    }

    private void initViews() {
        btnAddAlert.setOnClickListener(new AddAlertsListener());


        setAddedAlertListView();

    }

    private void setAddedAlertListView() {
        FlowLayoutManager flowLayoutManager = new FlowLayoutManager();
        flowLayoutManager.setAutoMeasureEnabled(true);
        addedAlertListView.setLayoutManager(flowLayoutManager);


        addedAlertsAdapter = new AddedAlertsAdapter(addedAlertList);
        addedAlertListView.setAdapter(addedAlertsAdapter);


    }

    private class AddAlertsListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            String inputAlert = fieldAlert.getText().toString();

            addedAlertList.add(inputAlert);
            addedAlertsAdapter.notifyDataSetChanged();

//            View chipsLayout = LayoutInflater.from(ScreenAddAlert.this).inflate(R.layout.chips_layout, null);
//            TextView chipsText = chipsLayout.findViewById(R.id.chip_text);
//            chipsText.setText(inputAlert);

            fieldAlert.setText("");
        }
    }
}
