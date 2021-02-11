package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MeetingGenerator {

    public static List<Meeting> generateMeeting = Arrays.asList();

    static List<Meeting> generateMeeting() {
        return new ArrayList<>(generateMeeting);
    }
}
