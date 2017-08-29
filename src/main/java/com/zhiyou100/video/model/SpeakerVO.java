package com.zhiyou100.video.model;

public class SpeakerVO {

	private Speaker s;
	private String speakerName;
	private String speakerJob;
	private int currentPage;
	public Speaker getS() {
		return s;
	}
	public void setS(Speaker s) {
		this.s = s;
	}
	public String getSpeakerName() {
		return speakerName;
	}
	public void setSpeakerName(String speakerName) {
		this.speakerName = speakerName;
	}
	public String getSpeakerJob() {
		return speakerJob;
	}
	public void setSpeakerJob(String speakerJob) {
		this.speakerJob = speakerJob;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	@Override
	public String toString() {
		return "SpeakerVO [s=" + s + ", speakerName=" + speakerName + ", speakerJob=" + speakerJob + ", currentPage="
				+ currentPage + "]";
	}
	
	
	
	
	
}
