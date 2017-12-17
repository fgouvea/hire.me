package com.shortener.vo;

public class StatisticsVO {
	private long time_taken;
	
	public StatisticsVO() { }
	
	public StatisticsVO(long timeTaken) {
		this.time_taken = timeTaken;
	}
	
	public long getTime_taken() {
		return time_taken;
	}
	
	public void setTime_taken(long timeTaken) {
		this.time_taken = timeTaken;
	}
}
