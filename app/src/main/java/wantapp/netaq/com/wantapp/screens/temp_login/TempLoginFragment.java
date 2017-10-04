package wantapp.netaq.com.wantapp.screens.temp_login;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.quickblox.chat.QBChatService;

import butterknife.BindView;
import butterknife.ButterKnife;
import wantapp.netaq.com.wantapp.R;
import wantapp.netaq.com.wantapp.utils.NavigationController;
import wantapp.netaq.com.wantapp.utils.QBUserHelper;


public class TempLoginFragment extends Fragment implements View.OnClickListener,TempLoginView {

    @BindView(R.id.field_login)EditText fieldLogin;
    @BindView(R.id.field_password)EditText fieldPassword;
    @BindView(R.id.btn_login)Button btnLogin;
    @BindView(R.id.tv_sabih)TextView TVSabih;
    @BindView(R.id.tv_azam)TextView TVAzam;
    @BindView(R.id.progress)ProgressBar progressBar;
    private LoginFragmentPresenter loginPresenter;

    public TempLoginFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        ButterKnife.bind(this,view);

        TVSabih.setOnClickListener(this);
        TVAzam.setOnClickListener(this);

        loginPresenter = new LoginFragmentPresenter(this);



        return view;
    }


    @Override
    public void onClick(View view) {
        loginPresenter.loginQB(view.getId());

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onChatReady() {
        progressBar.setVisibility(View.GONE);
        QBUserHelper.persistUser(QBChatService.getInstance().getUser());
        NavigationController.showConsumerChatListScreen(getContext());
        Toast.makeText(getContext(),"Logged in chat",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onChatFailed() {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getContext(),"Failed to initialize chat",Toast.LENGTH_SHORT).show();
    }
}
