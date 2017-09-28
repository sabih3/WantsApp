package wantapp.netaq.com.wantapp.screens.chat_list;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

import wantapp.netaq.com.wantapp.chat.ChatSessionManager;

import static org.junit.Assert.*;

/**
 * Created by sabih on 24-Sep-17.
 */
public class ChatListPresenterTest {

    @Test
    public void TestRetrievingChatDialogs(){
        ChatListPresenter chatListPresenter = (ChatListPresenter) Mockito.mockingDetails(ChatListPresenter.class);

        Assert.assertTrue(chatListPresenter.setChatList());
    }

    @Test
    public void TestIfUserHasPastChats(){
        ChatListPresenter chatListPresenter = (ChatListPresenter) Mockito.mockingDetails(ChatListPresenter.class);
    }



}