package com.hangang.HangangRiver.meeting.service;

import com.hangang.HangangRiver.exceptions.ExistJoinDetailException;
import com.hangang.HangangRiver.exceptions.InvalidMeetingException;
import com.hangang.HangangRiver.exceptions.OverCountJoinDetailException;
import com.hangang.HangangRiver.meeting.model.JoinDetail;
import com.hangang.HangangRiver.meeting.model.MeetingDetail;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingGuestService extends MeetingBaseService{
    public JoinDetail join(JoinDetail joinDetail)
            throws ExistJoinDetailException, InvalidMeetingException, OverCountJoinDetailException {

        int meeting_seq = joinDetail.getMeeting_seq();
        String user_id = joinDetail.getUser_id();

        if(joinDetailMapper.isExistJoinDetails(meeting_seq, user_id)) {
            throw new ExistJoinDetailException();
        }

        if (meetingDetailMapper.detail(meeting_seq) == null) {
            throw new InvalidMeetingException();
        }

        if (joinDetailMapper.getJoinDeatilCount(meeting_seq)>20) {
            throw new OverCountJoinDetailException();
        }

        joinDetailMapper.insert(joinDetail);
        return joinDetail;
    }

    public void cancleJoin(int application_seq){
        joinDetailMapper.delete(application_seq);
    }

    public List<MeetingDetail> getApplicationsDetailById(String user_id){
        return joinDetailMapper.selectMyApplications(user_id);
    }
}
