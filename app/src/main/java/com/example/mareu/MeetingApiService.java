package com.example.mareu;

import java.util.List;

public interface MeetingApiService {

    List<Meeting> getMeeting();

    void createMeeting(Meeting meeting);

    void deleteMeeting(Meeting meeting);

}
