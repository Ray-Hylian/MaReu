package com.example.mareu;

public class DeleteMeetingEvent {

    public Meeting meeting;

    public DeleteMeetingEvent(Meeting meeting){
        this.meeting = meeting;
    }
}
