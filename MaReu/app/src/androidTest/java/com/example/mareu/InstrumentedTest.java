package com.example.mareu;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.DatePicker;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.mareu.DI.DI;
import com.example.mareu.service.MeetingApiService;
import com.example.mareu.ui.meeting_list.ListMeeting;
import com.example.mareu.utils.DeleteViewAction;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;


@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {

    private static int ITEMS_COUNT = 3;
    MeetingApiService meetingApiService = DI.getMeetingApiService();

    @Rule
    public ActivityTestRule<ListMeeting> mActivityTestRule = new ActivityTestRule<>(ListMeeting.class);


    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    public static void setDate(int year, int monthOfYear, int dayOfMonth) {
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(year, monthOfYear, dayOfMonth));
        onView(withId(android.R.id.button1)).perform(click());
    }

    @Test
    public void myMeetingList_shouldNotBeEmpty() {
        onView(withId(R.id.meetingList)).check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void myMeetingList_deleteButton_shouldRemoveItem() {
        ITEMS_COUNT = meetingApiService.getMeeting().size();
        onView(Matchers.allOf(ViewMatchers.withId(R.id.meetingList), isDisplayed())).check(withItemCount(ITEMS_COUNT));
        onView(Matchers.allOf(ViewMatchers.withId(R.id.meetingList), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        onView(Matchers.allOf(ViewMatchers.withId(R.id.meetingList), isDisplayed())).check(withItemCount(ITEMS_COUNT - 1));
    }

    @Test
    public void myMeetingList_addButton_shouldAddItem() {
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.addMeetingFab), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 1), isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.meetingSubject), childAtPosition(childAtPosition(withId(R.id.addMeetingLayout), 0), 0), isDisplayed()));
        appCompatEditText.perform(replaceText("test"), closeSoftKeyboard());

        ViewInteraction materialTextView = onView(
                allOf(withId(R.id.meetingDate), childAtPosition(childAtPosition(withId(R.id.addMeetingLayout), 0), 3), isDisplayed()));
        materialTextView.perform(click());

        ViewInteraction materialButton = onView(
                allOf(withId(android.R.id.button1), withText("OK"), childAtPosition(childAtPosition(withClassName(is("android.widget.ScrollView")), 0), 3)));
        materialButton.perform(scrollTo(), click());

        ViewInteraction materialTextView2 = onView(
                allOf(withId(R.id.meetingHour), childAtPosition(childAtPosition(withId(R.id.addMeetingLayout), 0), 4), isDisplayed()));
        materialTextView2.perform(click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK"), childAtPosition(childAtPosition(withClassName(is("android.widget.ScrollView")), 0), 3)));
        materialButton2.perform(scrollTo(), click());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.validateMeetingBtn), withText("ok"), childAtPosition(childAtPosition(withId(R.id.addMeetingLayout), 0), 6), isDisplayed()));
        materialButton3.perform(click());
    }

    @Test
    public void myMeetingList_shouldFilterByRoom() {
        ViewInteraction overflowMenuButton = onView(
                allOf(withContentDescription("More options"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()));
        overflowMenuButton.perform(click());

        ViewInteraction materialTextView = onView(
                allOf(withId(R.id.title), withText("filter by room"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        materialTextView.perform(click());

        ViewInteraction materialTextView2 = onView(
                allOf(withId(R.id.title), withText("Mario"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        materialTextView2.perform(click());
    }

    @Test
    public void myMeetingList_shouldFilterByDate() {

        //open menu
        ViewInteraction overflowMenuButton = onView(
                allOf(withContentDescription("More options"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()));
        overflowMenuButton.perform(click());

        //click on date filter choice
        ViewInteraction materialTextView = onView(
                allOf(withId(R.id.title), withText("filter by date"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        materialTextView.perform(click());

        //select a date from the calendar and validate
        setDate(2021, 3, 1);
        //update the recycler view
        onView(Matchers.allOf(ViewMatchers.withId(R.id.meetingList), isDisplayed())).check(withItemCount(ITEMS_COUNT - 2));
    }
}



