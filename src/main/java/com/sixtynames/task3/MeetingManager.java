package com.sixtynames.task3;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MeetingManager {
    private List<Meeting> meetings = new ArrayList<>();

    public void addMeeting(Meeting meeting) {
        for (Meeting m : meetings) {
            if (m.getStartTime().isBefore(meeting.getEndTime()) && meeting.getStartTime().isBefore(m.getEndTime())) {
                throw new IllegalArgumentException("Meetings cannot overlap.");
            }
        }
        meetings.add(meeting);
    }

    public void removeMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    public void updateMeeting(Meeting oldMeeting, Meeting newMeeting) {
        removeMeeting(oldMeeting);
        addMeeting(newMeeting);
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void notifyUpcomingMeetings() {
        LocalDateTime now = LocalDateTime.now();
        for (Meeting meeting : meetings) {
            if (meeting.getReminderTime().isBefore(now) && meeting.getStartTime().isAfter(now)) {
                System.out.println("Reminder: Meeting " + meeting.getTitle() + " starts at " + meeting.getStartTime());
            }
        }
    }
}
