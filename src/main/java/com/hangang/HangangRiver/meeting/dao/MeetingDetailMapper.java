package com.hangang.HangangRiver.meeting.dao;

import java.util.List;

import com.hangang.HangangRiver.meeting.model.MeetingDetail;
import com.hangang.HangangRiver.meeting.model.MeetingDetailForm;
import org.apache.ibatis.annotations.Param;

public interface MeetingDetailMapper {
	void insert(MeetingDetail meetingDetail);
	MeetingDetail detail(int meeting_seq);
	void update(@Param("meeting_seq") int meeting_seq,
				@Param("meetingDetail") MeetingDetail meetingDetail);
	void delete(int meeting_seq);
	void move(MeetingDetail meetingDetail);
	List<MeetingDetailForm> selectAll(MeetingDetailForm meetingForm);
}