package com.springboot.its.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="All details about the Interviews.")
@Entity
@Table(name="interview")
public class Interview {

	// define fields
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	
	@Column(name="interviewer_name")
	@Size(min=5,max=30, message="Name should have atleast 5 characters and max of 30")
	@ApiModelProperty(notes="Name should have atleast 5 characters and max of 30")
	private String interviewerName;
	
	@Column(name="interview_name")
	@Size(min=3,max=30, message="Name should have atleast 3 characters and max of 30")
	@ApiModelProperty(notes="Name should have atleast 3 characters and max of 30")
	private String interviewName;
	
	@Column(name="userskills")
	@Size(min=5,max=30, message="Skills should have atleast 5 characters and max of 30")
	@ApiModelProperty(notes="skills should have atleast 5 characters and max of 30")
	private String userSkills;
	
	@Column(name="time")
	@Temporal(TemporalType.TIME)
	private Date time;	
	
	@Column(name="date")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name="interview_status")
	@Size(min=5,max=100, message="Status should have atleast 5 characters and max of 100")
	@ApiModelProperty(notes="Status should have atleast 5 characters and max of 100")
	private String interviewStatus;
	
	@Column(name="remarks")
	@Size(min=5,max=100, message="Name should have atleast 5 characters and max of 100")
	@ApiModelProperty(notes="Name should have atleast 5 characters and max of 100")	
	private String remarks;
	// define constructors
	
	/*
	 * @ManyToOne(fetch=FetchType.LAZY)
	 * 
	 * @JsonIgnore private User user;
	 */
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false,cascade=CascadeType.ALL)
    @JoinColumn(name = "userid")
	@JsonIgnore

    private User user;
	
	public Interview() {
		
	}

	public Interview(String interviewerName, String interviewName, String userSkills, Date time, Date date,
			String interviewStatus, String remarks) {
		super();
		this.interviewerName = interviewerName;
		this.interviewName = interviewName;
		this.userSkills = userSkills;
		this.time = time;
		this.date = date;
		this.interviewStatus = interviewStatus;
		this.remarks = remarks;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInterviewerName() {
		return interviewerName;
	}

	public void setInterviewerName(String interviewerName) {
		this.interviewerName = interviewerName;
	}

	public String getInterviewName() {
		return interviewName;
	}

	public void setInterviewName(String interviewName) {
		this.interviewName = interviewName;
	}

	public String getUserSkills() {
		return userSkills;
	}

	public void setUserSkills(String userSkills) {
		this.userSkills = userSkills;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getInterviewStatus() {
		return interviewStatus;
	}

	public void setInterviewStatus(String interviewStatus) {
		this.interviewStatus = interviewStatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Interview [id=" + id + ", interviewerName=" + interviewerName + ", interviewName=" + interviewName
				+ ", userSkills=" + userSkills + ", time=" + time + ", date=" + date + ", interviewStatus="
				+ interviewStatus + ", remarks=" + remarks + "]";
	}
		
		
}











