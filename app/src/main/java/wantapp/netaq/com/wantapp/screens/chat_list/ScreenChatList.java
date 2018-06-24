package wantapp.netaq.com.wantapp.screens.chat_list;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.quickblox.chat.model.QBChatDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wantapp.netaq.com.wantapp.R;
import wantapp.netaq.com.wantapp.adapters.ConsumerChatListAdapter;
import wantapp.netaq.com.wantapp.db.managers.DialogDataManager;
import wantapp.netaq.com.wantapp.db.models.Dialog;
import wantapp.netaq.com.wantapp.eventbus.ActiveChatEvent;
import wantapp.netaq.com.wantapp.eventbus.ChatDialogCreatedEvent;
import wantapp.netaq.com.wantapp.screens.chat_screen.ScreenChat;
import wantapp.netaq.com.wantapp.utils.NavigationController;
import wantapp.netaq.com.wantapp.utils.UIUtils;

public class ScreenChatList extends AppCompatActivity implements ChatListView, ConsumerChatListAdapter.ConsumerDialogListener {


    @BindView(R.id.chat_list_consumer)RecyclerView chatListConsumer;
    @BindView(R.id.toolbar_with_logo)Toolbar toolbar;

    @BindView(R.id.chat_list_parent)CoordinatorLayout rootView;
    private ChatListPresenter chatListPresenter;
    private List<Dialog> mDialogList;
    private ConsumerChatListAdapter consumerChatListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_chat_list);

        ButterKnife.bind(this);

        chatListPresenter = new ChatListPresenter(this);
        chatListPresenter.checkUserHasPastChats();

        EventBus.getDefault().register(this);

        setToolbar();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    private void setToolbar() {
        setSupportActionBar(UIUtils.adjustToolbar(this,toolbar,""));
    }
    @Override
    public void showProgressView() {
        UIUtils.showProgress(rootView);
    }

    @Override
    public void initializeChatList(List<Dialog> dialogsList) {
        UIUtils.hideProgress(rootView);
        this.mDialogList = dialogsList;


        showChatsInList(mDialogList);
    }


    @Override
    public void showNoChatListView() {
        UIUtils.hideProgress(rootView);
    }


    //ChatSessionManager.createChat
    //this is triggered when app user creates chat with other user
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void ChatDialogCreated(ChatDialogCreatedEvent event){
        //do this after receiving event
        DialogDataManager.persistNewCreatedDialog(event.getChatDialog());
        chatListPresenter.checkUserHasPastChats();
    }

    @Subscribe
    public void onChatMessageReceived(ActiveChatEvent event){

        for(Dialog dialog : mDialogList){
            if(dialog.getDialogId().equals(event.getDialogID())){

                dialog.setLastMessage(event.getQbChatMessage().getBody()) ;
                consumerChatListAdapter.notify();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_chat_list,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.create_Chat){

            final View alertLayout = LayoutInflater.from(ScreenChatList.this).inflate(R.layout.input_dialog_view, null);
            AlertDialog.Builder alert = new AlertDialog.Builder(this);

            alert.setTitle("Provide User Id");
            // this is set the view from XML inside AlertDialog
            alert.setView(alertLayout);
            alert.setCancelable(false);

            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            alert.setPositiveButton("Login", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    EditText field_user_id = alertLayout.findViewById(R.id.editTextDialogUserInput);
                    String userid = field_user_id.getText().toString().trim();

                    int userID = Integer.parseInt(userid);

                    chatListPresenter.createChatWithUserID(userID);

                }
            });
            AlertDialog dialog = alert.create();
            dialog.show();
        }

        return true;
    }





    private void showChatsInList(List<Dialog> dialogsList) {

        ConsumerChatListAdapter consumerChatListAdapter = new ConsumerChatListAdapter(dialogsList);
        chatListConsumer.setLayoutManager(new LinearLayoutManager(this));
        chatListConsumer.setAdapter(consumerChatListAdapter);

        consumerChatListAdapter.setDialogListener(this);
    }


    @Override
    public void onChatDialogClick(QBChatDialog chatDialog) {
        Intent intent = new Intent(this, ScreenChat.class);
        intent.putExtra(NavigationController.EXTRA_DIALOG, chatDialog);
        startActivity(intent);
    }
}
