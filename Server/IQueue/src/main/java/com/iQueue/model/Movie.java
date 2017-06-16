package com.iQueue.model;

/* 病人体征信息 */
public class Movie {
	
	public String Ticket;  // 剩余票
	public String MovieName;  // 名字
	public void setMovieName(String name) {
		this.MovieName = name;
	}
	public String getMovieName() {
		return MovieName;
	}
	
	public void setTicket(String ticket) {
		this.Ticket = ticket;
	}
	
	public String getTicket() {
		return Ticket;
	}
}
