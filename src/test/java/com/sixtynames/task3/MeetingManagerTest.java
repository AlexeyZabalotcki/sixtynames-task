package com.sixtynames.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MeetingManagerTest {

    private MeetingManager meetingManager;

    @BeforeEach
    public void setUp() {
        meetingManager = new MeetingManager();
    }

    @Test
    public void checkAddMeetingShouldAddNewMeeting() {
        Meeting meeting = new Meeting();
        meeting.setTitle("Meeting 1");
        meeting.setStartTime(LocalDateTime.of(2023, 8, 17, 10, 0));
        meeting.setEndTime(LocalDateTime.of(2023, 8, 17, 11, 0));
        meeting.setReminderTime(LocalDateTime.of(2023, 8, 17, 9, 45));

        meetingManager.addMeeting(meeting);

        List<Meeting> meetings = meetingManager.getMeetings();
        assertEquals(1, meetings.size());
        assertEquals("Meeting 1", meetings.get(0).getTitle());
    }

    @Test
    public void checkAddOverlappingMeetingShouldReturnIllegalArgumentException() {
        Meeting meeting1 = new Meeting();
        meeting1.setTitle("Meeting 1");
        meeting1.setStartTime(LocalDateTime.of(2023, 8, 17, 10, 0));
        meeting1.setEndTime(LocalDateTime.of(2023, 8, 17, 11, 0));
        meeting1.setReminderTime(LocalDateTime.of(2023, 8, 17, 9, 45));

        Meeting meeting2 = new Meeting();
        meeting2.setTitle("Meeting 2");
        meeting2.setStartTime(LocalDateTime.of(2023, 8, 17, 10, 30));
        meeting2.setEndTime(LocalDateTime.of(2023, 8, 17, 11, 30));
        meeting2.setReminderTime(LocalDateTime.of(2023, 8, 17, 10, 15));

        meetingManager.addMeeting(meeting1);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            meetingManager.addMeeting(meeting2);
        });

        String expectedMessage = "Meetings cannot overlap.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void checkRemoveMeetingShouldRemoveMeeting() {
        Meeting meeting = new Meeting();
        meeting.setTitle("Meeting 1");
        meeting.setStartTime(LocalDateTime.of(2023, 8, 17, 10, 0));
        meeting.setEndTime(LocalDateTime.of(2023, 8, 17, 11, 0));
        meeting.setReminderTime(LocalDateTime.of(2023, 8, 17, 9, 45));

        meetingManager.addMeeting(meeting);
        meetingManager.removeMeeting(meeting);

        List<Meeting> meetings = meetingManager.getMeetings();
        assertEquals(0, meetings.size());
    }

    @Test
    public void checkUpdateMeetingShouldUpdateMeeting() {
        Meeting oldMeeting = new Meeting();
        oldMeeting.setTitle("Meeting 1");
        oldMeeting.setStartTime(LocalDateTime.of(2023, 8, 17, 10, 0));
        oldMeeting.setEndTime(LocalDateTime.of(2023, 8, 17, 11, 0));
        oldMeeting.setReminderTime(LocalDateTime.of(2023, 8, 17, 9, 45));

        Meeting newMeeting = new Meeting();
        newMeeting.setTitle("Updated Meeting 1");
        newMeeting.setStartTime(LocalDateTime.of(2023, 8, 17, 11, 0));
        newMeeting.setEndTime(LocalDateTime.of(2023, 8, 17, 12, 0));
        newMeeting.setReminderTime(LocalDateTime.of(2023, 8, 17, 10, 45));

        meetingManager.addMeeting(oldMeeting);
        meetingManager.updateMeeting(oldMeeting, newMeeting);

        List<Meeting> meetings = meetingManager.getMeetings();
        assertEquals(1, meetings.size());
        assertEquals("Updated Meeting 1", meetings.get(0).getTitle());
    }

    @Test
    public void checkNotifyUpcomingMeetingsShouldWorkCorrect() {
        Meeting meeting = new Meeting();
        meeting.setTitle("Meeting 1");
        meeting.setStartTime(LocalDateTime.now().plusMinutes(30));
        meeting.setEndTime(LocalDateTime.now().plusHours(1));
        meeting.setReminderTime(LocalDateTime.now().minusMinutes(5));

        meetingManager.addMeeting(meeting);

        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        meetingManager.notifyUpcomingMeetings();

        String expectedOutput = "Reminder: Meeting Meeting 1 starts at " + meeting.getStartTime();
        String actualOutput = outContent.toString().trim();

        assertTrue(actualOutput.contains(expectedOutput));
    }
}