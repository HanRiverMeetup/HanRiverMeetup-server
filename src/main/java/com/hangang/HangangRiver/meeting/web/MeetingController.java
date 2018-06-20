package com.hangang.HangangRiver.meeting.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hangang.HangangRiver.meeting.model.Meeting;
import com.hangang.HangangRiver.meeting.service.MeetingService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/meeting")
public class MeetingController {

	@Autowired
	MeetingService meetingService;

	@PostMapping("/save")
	private void saveMeeting(HttpServletRequest request, @RequestBody Meeting meeting){
		try {
			meetingService.save(meeting);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@GetMapping("/detail/{meeting_seq}") 
	private Meeting meetingDetail(@PathVariable int meeting_seq) throws Exception{
		return meetingService.showDetail(meeting_seq);
	}

	@PostMapping("/modify")
	private void modifyMeeting(@RequestBody Meeting meeting){
		try {
			meetingService.modify(meeting);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@GetMapping("/remove/{meeting_seq}") 
	private void remove(@PathVariable int meeting_seq) throws Exception{
		meetingService.remove(meeting_seq);
	}
}
