package com.example.mareu;

public class DI {

    private static MeetingApiService services = new MeetingApiServiceClass();

    public static MeetingApiService getMeetingApiService(){return services;}

    public static MeetingApiService getInstanceApiService(){
        services = new MeetingApiServiceClass();
        return services;
    }
}
