package com.example.mareu.ui.meeting_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.example.mareu.DI.DI;
import com.example.mareu.R;
import com.example.mareu.databinding.ActivityListMeetingBinding;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.MeetingApiService;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


public class ListMeeting extends AppCompatActivity {

    ActivityListMeetingBinding binding;
    MeetingAdapter meetingAdapter;
    private MeetingApiService meetingApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        meetingApiService = DI.getMeetingApiService();

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
                Intent intent = new Intent(ListMeeting.this, AddMeeting.class);
                startActivity(intent);
            }
        }));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(R.id.filterRoom){
            filterByRoom(); return true;
        }
        if(R.id.filterDate){
            filterByDate(); return true;
        }
        if(R.id.cancel){
            initList();
        }
    }

     */

    /*
    private void filterByRoom(){
        Spinner spinner = findViewById(R.id.spinnerMenu);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.rooms, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    private void filterByDate(){

    }

     */

}