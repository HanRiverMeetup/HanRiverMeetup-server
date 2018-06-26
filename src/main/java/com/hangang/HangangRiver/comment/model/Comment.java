package com.hangang.HangangRiver.comment.model;


import java.util.Date;



public class Comment {
	private int comment_seq;
	private int meeting_seq;
	private String user_id;
	private String comment;
	private Date creation_time;

	public int getComment_seq() {
		return comment_seq;
	}

	public void setComment_seq(int comment_seq) {
		this.comment_seq = comment_seq;
	}

	public int getMeeting_seq() {
		return meeting_seq;
	}

	public void setMeeting_seq(int meeting_seq) {
		this.meeting_seq = meeting_seq;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCreation_time() {
		return creation_time;
	}

	public void setCreation_time(Date creation_time) {
		this.creation_time = creation_time;
	}

	@Override
	public String toString() {
		return "Comment [comment_seq=" + comment_seq + ", meeting_seq=" + meeting_seq + ", user_id=" + user_id
				+ ", comment=" + comment + ", creation_time=" + creation_time + "]";
	}

}