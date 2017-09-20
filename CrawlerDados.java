package com.br.desafio.idwall;

public class CrawlerDados {

	private String upvotes;
	private String subreddit;
	private String titThread;
	private String linkThread;
	private String linkComent;
	
	public String getUpvotes() {
		return upvotes;
	}
	public void setUpvotes(String upvotes) {
		this.upvotes = upvotes;
	}
	public String getSubreddit() {
		return subreddit;
	}
	public void setSubreddit(String subreddit) {
		this.subreddit = subreddit;
	}
	public String getTitThread() {
		return titThread;
	}
	public void setTitThread(String titThread) {
		this.titThread = titThread;
	}
	public String getLinkThread() {
		return linkThread;
	}
	public void setLinkThread(String linkThread) {
		this.linkThread = linkThread;
	}
	public String getLinkComent() {
		return linkComent;
	}
	public void setLinkComent(String linkComent) {
		this.linkComent = linkComent;
	}
}
