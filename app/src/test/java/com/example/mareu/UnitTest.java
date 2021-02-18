package com.example.mareu;

import com.example.mareu.DI.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.MeetingApiService;
import com.example.mareu.service.MeetingGenerator;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class UnitTest {

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
        Meeting neighbourToDelete = meetingApiService.getMeeting().get(0);
        meetingApiService.deleteMeeting(neighbourToDelete);
        assertFalse(meetingApiService.getMeeting().contains(neighbourToDelete));
    }

}