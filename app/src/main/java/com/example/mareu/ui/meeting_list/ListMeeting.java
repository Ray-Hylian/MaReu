package com.example.mareu.ui.meeting_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mareu.DI.DI;
import com.example.mareu.databinding.ActivityListMeetingBinding;


public class ListMeeting extends AppCompatActivity {

    ActivityListMeetingBinding binding;
    MeetingAdapter meetingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initList();
        addMeeting();
    }

    private void initView(){
        binding = ActivityListMeetingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void initList(){
        meetingAdapter = new MeetingAdapter(DI.getMeetingApiService().getMeeting(), getApplicationContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager((this));
        binding.meetingList.setLayoutManager(layoutManager);
        binding.meetingList.setHasFixedSize(true);
        binding.meetingList.setAdapter(meetingAdapter);
    }

    private void addMeeting(){
        binding.addMeetingFab.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddMeeting.class);
                startActivity(intent);
            }
        }));
    }

    @Override
    protected void onResume() {
        super.onResume();
        initList();
    }
}