package com.example.mareu.DI;

import com.example.mareu.service.MeetingApiService;
import com.example.mareu.service.MeetingApiServiceClass;
import com.example.mareu.service.MeetingGenerator;

public class DI {

    private static MeetingApiService services = new MeetingApiServiceClass();

    public static MeetingApiService getMeetingApiService(){return services;}

    public static MeetingApiService getNewInstanceApiService() {
        return new MeetingApiServiceClass();
    }

    public static void clearApiService() {
        services.getMeeting().clear();
        services.getMeeting().addAll(MeetingGenerator.generateMeeting());
    }
}
