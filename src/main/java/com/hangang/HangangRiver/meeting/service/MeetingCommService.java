package com.hangang.HangangRiver.meeting.service;

import com.hangang.HangangRiver.exceptions.AlreadyContactedMeetingException;
import com.hangang.HangangRiver.exceptions.InvalidMatchingInfoException;
import com.hangang.HangangRiver.exceptions.InvalidMeetingException;
import com.hangang.HangangRiver.meeting.model.Comment;
import com.hangang.HangangRiver.meeting.model.ContactedMeeting;
import com.hangang.HangangRiver.meeting.model.JoinDetail;
import com.hangang.HangangRiver.meeting.model.MeetingDetail;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MeetingCommService extends MeetingBaseService{
    private String CONTACT_MSG = "같이 놀강~ 난누굴강 완료모임을 확인해주세요.";
    private String ADD_COMMENT_MSG = "누가 내가 만든 모임에 관심을 보여요.";

    public Comment createComment(Comment comment) throws InvalidMeetingException, IOException {
        // 1. Check the target meeting to be valid
        int meeting_seq = comment.getMeeting_seq();
        MeetingDetail meetingDetail = meetingDetailMapper.detail(meeting_seq);

        if (meetingDetail == null) {
            throw new InvalidMeetingException();
        }

        commentMapper.insert(comment);

        if(comment.getUser_id().compareTo(meetingDetail.getUser_id()) != 0){
            pushMessage(meetingDetail.getUser_id(), ADD_COMMENT_MSG);
        }

        return comment;
    }

    public List<Comment> getCommentsByMeeting(int meeting_seq){
        return commentMapper.selectCommentList(meeting_seq);
    }

    public void removeComment(int comment_seq){
        commentMapper.delete(comment_seq);
    }

    public ContactedMeeting match(ContactedMeeting meeting)
            throws InvalidMatchingInfoException, AlreadyContactedMeetingException, IOException {

        int meeting_seq = meeting.getMeeting_seq();
        int application_seq = meeting.getApplication_seq();
        JoinDetail joinDetail = joinDetailMapper.getJoinDetail(application_seq);

        if(meetingDetailMapper.isExistMeetingDetail(meeting_seq) == false
                || joinDetail == null
                || joinDetail.getMeeting_seq() != meeting_seq){
            throw new InvalidMatchingInfoException();
        }

        if(matchingMapper.isContactedMeeting(meeting_seq)){
            throw new AlreadyContactedMeetingException();
        }

        MeetingDetail meetingInfo = meetingDetailMapper.detail(meeting.getMeeting_seq());
        meeting.setHost_user_id(meetingInfo.getUser_id());
        JoinDetail joinDetailInfo = joinDetailMapper.getJoinDetail(meeting.getApplication_seq());
        meeting.setGuest_user_id(joinDetailInfo.getUser_id());
        matchingMapper.insert(meeting);

        pushMessage(meeting.getHost_user_id(), CONTACT_MSG);
        pushMessage(meeting.getGuest_user_id(), CONTACT_MSG);

        return meeting;
    }

    public ContactedMeeting getContactedMeetingById(int contact_seq){
        return matchingMapper.detail(contact_seq);
    }

    public ContactedMeeting getContactedMeetingByMatchingInfo(int meeting_seq, int application_seq){
        return matchingMapper.detailByMatchingInfo(meeting_seq, application_seq);
    }

    public void unmatch(int contact_seq){
        matchingMapper.delete(contact_seq);
    }
}
