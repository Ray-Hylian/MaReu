package com.example.mareu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.databinding.ActivityListMeetingBinding;
import com.example.mareu.databinding.ListItemsBinding;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.MeetingViewHolder> {

    private final List<Meeting> meetings;
    private Context context;

    public MeetingAdapter(List<Meeting> items, Context context){
        meetings = items;
        this.context = context;
    }

    @NonNull
    @Override
    public MeetingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new MeetingViewHolder(ListItemsBinding.inflate(layoutInflater));
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingAdapter.MeetingViewHolder holder, int position) {
        Meeting meeting = meetings.get(position);
        holder.binding.subject.setText(((meeting.getSubject())));
        holder.binding.date.setText(((meeting.getDate())));
        holder.binding.hour.setText(((meeting.getHour())));
        holder.binding.room.setText(((meeting.getRoom())));
        holder.binding.guests.setText(((meeting.getGuests())));
        holder.binding.deleteMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteMeetingEvent((meeting)));
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
