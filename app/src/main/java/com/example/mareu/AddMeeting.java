package com.example.mareu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.Layout;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.example.mareu.databinding.ActivityAddMeetingBinding;
import com.example.mareu.databinding.ActivityListMeetingBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class AddMeeting extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    ActivityAddMeetingBinding binding;
    private Meeting meeting;
    private Date date;
    private Date StartingHour;
    private Date EndingHour;
    int t1Hour, t1Minute, t2Hour, t2Minute;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initDatePicker();
        //initTimePicker();
        initSpinner();
        initValidateMeetingBtn();
    }

    private void initView() {
        binding = ActivityAddMeetingBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
    }

    private void initDatePicker() {
        binding.meetingDate.setFocusable(false);
        ;
        binding.meetingDate.setOnClickListener(v -> {
            DialogFragment datePicker = new DatePickerFragment();
        });
    }

    /*
    private void initTimePicker() {
        binding.startingHour.setOnClickListener(v -> {
            DialogFragment timePicker = new TimePickerFragment();
        });
        binding.endingHour.setOnClickListener(v -> {
            DialogFragment timePicker = new TimePickerFragment();
        });
    }
     */

    private void initSpinner() {
        Spinner spinner = binding.roomSpinner;
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.rooms, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        binding.roomSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
    }

    private void initValidateMeetingBtn() {
        binding.validateMeetingBtn.setOnClickListener(v -> {
            String meetingSubject = binding.meetingSubjectTxt.getText().toString();
            String meetingDate = binding.meetingDate.getText().toString();
            String meetingGuests = binding.meetingGuests.getText().toString();
        });
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        date = c.getTime();
        String dateString = DisplayFormatter.formatDateToString(date);
        binding.meetingDate.setText(dateString);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        //Initialize hour and minute
        t1Hour = hourOfDay;
        t1Minute = minute;
        //Initialize calendar
        Calendar calendar = Calendar.getInstance();
        //Set hour and minute
        calendar.set(0, 0, 0, t1Hour, t1Minute);
        //Set selected time on text view
        binding.startingHour.setText(DateFormat.format("hh:mm aa", calendar));
        binding.endingHour.setText(DateFormat.format("hh:mm aa", calendar));
    }
}
