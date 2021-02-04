package com.example.mareu;

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
}
