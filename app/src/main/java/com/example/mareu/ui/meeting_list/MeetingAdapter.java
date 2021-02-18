package com.example.mareu.ui.meeting_list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mareu.databinding.ListItemsBinding;
import com.example.mareu.events.DeleteMeetingEvent;
import com.example.mareu.model.Meeting;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Random;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.MeetingViewHolder> {

    private final List<Meeting> meetings;


    public MeetingAdapter(List<Meeting> meetings){
        this.meetings = meetings;

    }

    @NonNull
    @Override
    public MeetingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new MeetingViewHolder(ListItemsBinding.inflate(layoutInflater, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MeetingAdapter.MeetingViewHolder holder, int position) {
        Meeting meeting = meetings.get(position);
        holder.binding.meetingInfos.setText(meeting.getSubject() +" "+ meeting.getHour() +" "+ meeting.getRoom());
        holder.binding.guests.setText(((meeting.getGuests())));

        Random random = new Random();
        int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        holder.binding.avatar.setColorFilter(color);
        
        holder.binding.deleteMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteMeetingEvent(meeting));
            }
        });
    }

    @Override
    public int getItemCount() {
        return meetings.size();
    }

    public class MeetingViewHolder extends RecyclerView.ViewHolder{

        ListItemsBinding binding;

        public MeetingViewHolder(ListItemsBinding b){
            super(b.getRoot());
            binding = b;
        }
    }
}
