package com.example.mareu.DI;

import com.example.mareu.service.MeetingApiService;
import com.example.mareu.service.MeetingApiServiceClass;

public class DI {

    private static MeetingApiService services = new MeetingApiServiceClass();

    public static MeetingApiService getMeetingApiService(){return services;}
}
