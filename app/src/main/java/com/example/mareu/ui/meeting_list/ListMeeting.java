package com.example.mareu.ui.meeting_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.mareu.events.DeleteMeetingEvent;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.MeetingApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class ListMeeting extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    ActivityListMeetingBinding binding;
    private MeetingApiService meetingApiService;
    private DatePickerDialog datePickerDialog;
    private Calendar calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        meetingApiService = DI.getMeetingApiService();

        initView();
        addMeeting();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initList(meetingApiService.getMeeting());
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void initView(){
        binding = ActivityListMeetingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void initList(List<Meeting> meetings){
        MeetingAdapter adapter = new MeetingAdapter(meetings);
        binding.meetingList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.meetingList.setHasFixedSize(true);
        binding.meetingList.setAdapter(adapter);
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

    @Subscribe
    public void onDeleteMeeting(DeleteMeetingEvent event){
        meetingApiService.deleteMeeting(event.meeting);
        initList(meetingApiService.getMeeting());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * filters configuration
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filterRoom:
                return true;
            case R.id.filterDate:
                onDatePickerSet();
                return true;
            case R.id.cancel:
                // no filters
                initList(meetingApiService.getMeeting());
                return true;
            default:
                initList(meetingApiService.getMeetingByRoom(item.getTitle().toString()));
                return true;
        }
    }

    /*
    private void filterByRoom(){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(ListMeeting.this);
        View v = getLayoutInflater().inflate(R.layout.spinner_menu, null);
        mBuilder.setTitle(getString(R.string.room_filter));

        Spinner spinner = v.findViewById(R.id.spinnerMenu);
        ArrayAdapter<String> roomAdapter = new ArrayAdapter<>(this, R.layout.spinner_text, getResources().getStringArray(R.array.rooms));
        roomAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(roomAdapter);

        mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                initList(meetingApiService.getMeetingByRoom(spinner.getSelectedItem().toString()));
            }
        });
        mBuilder.setView(v);
        AlertDialog dialog = mBuilder.create();
        dialog.show();
    }
     */

    private void onDatePickerSet() {
        calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, ListMeeting.this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        calendar.set(year, month, dayOfMonth);
        SimpleDateFormat displayDateFormater = new SimpleDateFormat(getString(R.string.display_date_format), Locale.FRANCE);
        String output = displayDateFormater.format(calendar.getTime());
        initList(meetingApiService.getMeetingByDate(output));
    }

}