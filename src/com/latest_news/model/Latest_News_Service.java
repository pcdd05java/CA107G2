package com.latest_news.model;

import java.util.List;

public class Latest_News_Service {
	
	private Latest_News_DAO_Interface dao;
	
	public Latest_News_Service() {
		dao = new Latest_News_DAO();
	}

	public Latest_News_VO addLatest_News(String ln_content) {
		
		Latest_News_VO latest_news_VO = new Latest_News_VO();
		
		latest_news_VO.setLn_content(ln_content);
		
		return latest_news_VO;
	}
	
	public Latest_News_VO updateLatest_News(String ln_no, String ln_content, java.sql.Date ln_date) {
		
		Latest_News_VO latest_news_VO = new Latest_News_VO();
		
		latest_news_VO.setLn_no(ln_no);
		latest_news_VO.setLn_content(ln_content);
		latest_news_VO.setLn_date(ln_date);
		dao.update(latest_news_VO);
		
		return latest_news_VO;
	}
	
	public void deleteLatest_News(String ln_no) {
		dao.delete(ln_no);
	}

	public Latest_News_VO getOneLatest_News(String ln_no) {
		return dao.findByPrimaryKey(ln_no);
	}

	public List<Latest_News_VO> getAll() {
		return dao.getAll();
	}
}
