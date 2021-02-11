package com.example.mareu.ui.meeting_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.mareu.DI.DI;
import com.example.mareu.R;
import com.example.mareu.databinding.ActivityAddMeetingBinding;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.MeetingApiService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddMeeting extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    ActivityAddMeetingBinding binding;
    private Meeting meeting;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private TimePicker timePicker;
    private TimePicker startTimePicker;
    private TimePicker endTimePicker;
    private Calendar calendar;
    private MeetingApiService meetingApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        meetingApiService = DI.getMeetingApiService();
        initClickListener();
        onDatePickerSet();
        onTimePickerSet();
        initSpinner();
    }

    /**
     * View initialization between the list and the details
     */
    private void initView() {
        binding = ActivityAddMeetingBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
    }

    /**
     * Create new meeting if he user fill the date and the hour
     */
    private void initClickListener(){
        binding.validateMeetingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.meetingDate.getText().toString().isEmpty() || binding.meetingHour.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), getString(R.string.empty_infos), Toast.LENGTH_SHORT).show();
                }
                else{
                    String meetingSubject = binding.meetingSubject.getText().toString();
                    String meetingDate = binding.meetingDate.getText().toString();
                    String meetingHour = binding.meetingHour.getText().toString();
                    String meetingLocation = binding.meetingLocation.getSelectedItem().toString();
                    String meetingGuests = binding.meetingGuests.getText().toString();

                    Meeting meeting = new Meeting(meetingSubject, meetingDate, meetingHour, meetingLocation, meetingGuests);
                    meetingApiService.createMeeting(meeting);
                    finish();
                }
            }
        });
    }

    /**
     * Date configuration
     */
    private void onDatePickerSet() {
        Calendar c = Calendar.getInstance();
        calendar = Calendar.getInstance();
        calendar.clear();
        datePickerDialog = new DatePickerDialog(this, AddMeeting.this,
        c.get(Calendar.YEAR),
        c.get(Calendar.MONTH),
        c.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
        binding.meetingDate.setOnClickListener(v -> { datePickerDialog.show();
        });
    }
    private void setDate(DatePicker date){
        calendar.set(date.getYear(), date.getMonth(), date.getDayOfMonth());
        SimpleDateFormat displayDateFormater = new SimpleDateFormat(getString(R.string.display_date_format), Locale.FRANCE);
        String output = displayDateFormater.format(calendar.getTime());
        binding.meetingDate.setText(output);
    }
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        setDate(view);
    }

    /**
     * Starting and ending time configuration
     */
    private void onTimePickerSet(){
        timePickerDialog = new TimePickerDialog(this, AddMeeting.this, getResources().getInteger(R.integer.default_hour), getResources().getInteger(R.integer.default_minute), true);
        binding.meetingHour.setOnClickListener(v -> {
            timePickerDialog.show();
        });
    }
    private void setTime(int hour, int minute) {
        calendar.set(Calendar.HOUR, hour);
        calendar.set(Calendar.MINUTE, minute);
        SimpleDateFormat displayTimeFormater = new SimpleDateFormat(getString(R.string.display_hour_format), Locale.FRANCE);
        String output = displayTimeFormater.format(calendar.getTime());
        binding.meetingHour.setText(output);
    }
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        timePicker = view;
        setTime(view.getCurrentHour(), view.getCurrentMinute());
    }

    /**
     * Room spinner configuration
     */
    private void initSpinner() {
        Spinner spinner = binding.meetingLocation;
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.rooms, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }
}
