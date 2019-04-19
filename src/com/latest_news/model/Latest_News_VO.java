package com.latest_news.model;



public class Latest_News_VO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 387416074958683470L;
	private String ln_no;
	private String ln_content;
	private java.sql.Date ln_date;
	
	public String getLn_no() {
		return ln_no;
	}
	public void setLn_no(String ln_no) {
		this.ln_no = ln_no;
	}
	public String getLn_content() {
		return ln_content;
	}
	public void setLn_content(String ln_content) {
		this.ln_content = ln_content;
	}
	public java.sql.Date getLn_date() {
		return ln_date;
	}
	public void setLn_date(java.sql.Date ln_date) {
		this.ln_date = ln_date;
	}
}
