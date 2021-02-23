package com.example.mareu;

import com.example.mareu.DI.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.MeetingApiService;
import com.example.mareu.service.MeetingGenerator;

import junit.framework.TestCase;

import org.hamcrest.MatcherAssert;
import org.hamcrest.beans.HasPropertyWithValue;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.hamcrest.core.Every;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LocalUnitTest {

    private MeetingApiService meetingApiService;


    @Before
    public void setup(){meetingApiService = DI.getNewInstanceApiService();}

    @Test
    public void getMeetingWithSuccess() {
        List<Meeting> meetings = meetingApiService.getMeeting();
        List<Meeting> expectedMeeting = MeetingGenerator.generateMeeting;
        assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeeting.toArray()));
    }

    @Test
    public void deleteMeetingWithSuccess() {
        Meeting meetingToDelete = meetingApiService.getMeeting().get(0);
        meetingApiService.deleteMeeting(meetingToDelete);
        assertFalse(meetingApiService.getMeeting().contains(meetingToDelete));
    }

    @Test
    public void createMeetingWithSuccess(){
        Meeting meetingToCreate = meetingApiService.getMeeting().get(0);
        meetingApiService.createMeeting(meetingToCreate);
        assertTrue(meetingApiService.getMeeting().contains(meetingToCreate));
    }

    @Test
    public void filterMeetingByRoomWithSuccess(){
        Meeting meetingFilteredByRoom = meetingApiService.getMeeting().get(0);
        List<Meeting> filteredListByRoom = meetingApiService.getMeetingByRoom("meetingFilteredByRoom");
        assertThat(filteredListByRoom, Every.everyItem(HasPropertyWithValue.hasProperty("Mario", Is.is(meetingFilteredByRoom))));
    }

    @Test
    public void filterMeetingByDateWithSuccess(){
        Meeting meetingFilteredByDate = meetingApiService.getMeeting().get(0);
        List<Meeting> filteredListByDate = meetingApiService.getMeetingByDate("meetingFilteredByDate");
        assertThat(filteredListByDate, Every.everyItem(HasPropertyWithValue.hasProperty("date", Is.is(meetingFilteredByDate))));
    }


}