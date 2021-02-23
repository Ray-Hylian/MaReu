package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MeetingGenerator {

    public static List<Meeting> generateMeeting = Arrays.asList(
            //    public Meeting(String subject, String date, String hour, String room, String guests) {
            new Meeting ("MaReu presentation", "01/03/2021", "09:00", "Mario", "desmond.humes@lamzone.com, kate.austen@lamzone.com, juliet.burke@lamzone.com, pierre.chang@lamzone.com"),
            new Meeting ("research and development", "15/03/2021", "15:00", "Yoshi", "jack.shephard@lamzone.com, sayid.jarrah@lamzone.com"),
            new Meeting ("meeting for fun", "28/03/2021", "13:30", "Peach", "richard.alpert@lamzone.com, pierre.chang@lamzone.com, james.ford@lamzone.com")
            );

    public static List<Meeting> generateMeeting() {
        return new ArrayList<>(generateMeeting);
    }
}
