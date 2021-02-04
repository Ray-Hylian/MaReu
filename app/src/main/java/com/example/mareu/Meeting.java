package com.example.mareu;

public class Meeting {

    private String subject;
    private String date;
    private String hour;
    private String room;
    private String guests;

    public Meeting(String subject, String date, String hour, String location, String guests) {
        this.subject = subject;
        this.date = date;
        this.hour = hour;
        this.room = room;
        this.guests = guests;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getGuests() {
        return guests;
    }

    public void setGuests(String guests) {
        this.guests = guests;
    }
}
