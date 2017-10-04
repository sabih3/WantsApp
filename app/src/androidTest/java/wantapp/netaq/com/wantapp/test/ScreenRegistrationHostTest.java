package wantapp.netaq.com.wantapp.test;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import wantapp.netaq.com.wantapp.screens.register.ScreenRegistrationHost;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import wantapp.netaq.com.wantapp.R;

/**
 * Created by sabih on 03-Oct-17.
 */

@RunWith(AndroidJUnit4.class)
public class ScreenRegistrationHostTest {

    ActivityTestRule<ScreenRegistrationHost> registerHost = new ActivityTestRule<>(ScreenRegistrationHost.class);

    @Test
    public void testIfPagerisShown(){

        registerHost.launchActivity(new Intent());
        onView(withId(R.id.login_pager)).check(matches(isDisplayed()));

    }

    @Test
    public void checkSwipeOfPager(){
        registerHost.launchActivity(new Intent());
        onView(withId(R.id.login_pager)).perform(swipeRight());

    }



}