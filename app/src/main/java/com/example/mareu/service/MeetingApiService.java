package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.List;

public interface MeetingApiService {

    List<Meeting> getMeeting();

    void createMeeting(Meeting meeting);

    void deleteMeeting(Meeting meeting);

    List<Meeting> getMeetingByRoom(String room);

    List<Meeting> getMeetingByDate(String date);


}
