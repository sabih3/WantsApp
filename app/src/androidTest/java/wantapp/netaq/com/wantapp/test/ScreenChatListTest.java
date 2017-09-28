package wantapp.netaq.com.wantapp.test;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import wantapp.netaq.com.wantapp.R;
import org.junit.Test;
import org.junit.runner.RunWith;

import wantapp.netaq.com.wantapp.screens.chat_list.ScreenChatList;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Created by sabih on 25-Sep-17.
 */

@RunWith(AndroidJUnit4.class)
public class ScreenChatListTest {

    ActivityTestRule<ScreenChatList> chatListScreenRule = new ActivityTestRule<ScreenChatList>(ScreenChatList.class);
    @Test
    public void checkIfChatDialogListIsDisplayed(){

        chatListScreenRule.launchActivity(new Intent());
        onView(withId(R.id.chat_list_consumer)).check(matches(isDisplayed()));
    }


    @Test
    public void checkIfDialogAppearsOnMenuItemClick(){
        chatListScreenRule.launchActivity(new Intent());
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        onView(withText("Create Chat")).perform(click());

        //onView(withId(R.layout.input_dialog_view)).check(matches(isDisplayed()));

    }

}