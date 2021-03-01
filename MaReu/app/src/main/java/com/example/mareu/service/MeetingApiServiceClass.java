package com.example.mareu.service;

import com.example.mareu.model.Meeting;
import com.example.mareu.service.MeetingApiService;
import com.example.mareu.service.MeetingGenerator;

import java.util.ArrayList;
import java.util.List;

public class MeetingApiServiceClass implements MeetingApiService {

    private List<Meeting> meetings = MeetingGenerator.generateMeeting();

    @Override
    public List<Meeting> getMeeting() {
        return meetings;
    }

    @Override
    public void createMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    @Override
    public List<Meeting> getMeetingByRoom(String room) {
        List<Meeting> meetingByRoom = new ArrayList<>();
        for (int i = 0; i < meetings.size(); i++) {
            if (meetings.get(i).getRoom().equals(room)) {
                meetingByRoom.add(meetings.get(i));
            }
        }
        return meetingByRoom;
    }

    @Override
    public List<Meeting> getMeetingByDate(String date) {
        List<Meeting> meetingList = getMeeting();
        List<Meeting> meetingByDate = new ArrayList<>();
        for (int i = 0; i < meetingList.size(); i++) {
            if (meetingList.get(i).getDate().contains(date)) {
                meetingByDate.add(meetingList.get(i));
            }
        }
        return meetingByDate;
    }
}
