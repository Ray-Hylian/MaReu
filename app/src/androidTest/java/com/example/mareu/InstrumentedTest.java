package com.example.mareu;

import android.content.Context;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.mareu.DI.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.ui.meeting_list.ListMeeting;
import com.example.mareu.utils.DeleteViewAction;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;



@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {

    private ListMeeting listMeeting;

    @Rule
    public ActivityTestRule<ListMeeting> activityRule =
            new ActivityTestRule(ListMeeting.class);


    @Test
    public void myMeetingList_shouldNotBeEmpty() {
        onView(withId(R.id.meetingList)).check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void myMeetingList_deleteAction_shouldRemoveItem() {
    }

    @Test
    public void myMeetingList_addButton_shouldAddActivity(){
    }


    public void myMeetingList_shouldFilterByRoom() {
    }

    public void myMeetingList_shouldFilterByDate() {
    }
}

