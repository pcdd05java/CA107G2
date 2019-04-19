package com.renting_house_information.model;

import java.util.List;

public class Renting_House_Information_Service {
	
	private Renting_House_Information_DAO_Interface dao;
	
	public Renting_House_Information_Service() {
		dao = new Renting_House_Information_DAO();
	}

	public Renting_House_Information_VO addRenting_House_Information
	(String rhi_content, int rhi_status, byte[] rhi_p1, byte[] rhi_p2, byte[] rhi_p3, byte[] rhi_p4, byte[] rhi_p5) {
		
		Renting_House_Information_VO renting_house_information_VO = new Renting_House_Information_VO();

		renting_house_information_VO.setRhi_content(rhi_content);
		renting_house_information_VO.setRhi_status(rhi_status);
		renting_house_information_VO.setRhi_p1(rhi_p1);
		renting_house_information_VO.setRhi_p2(rhi_p2);
		renting_house_information_VO.setRhi_p3(rhi_p3);
		renting_house_information_VO.setRhi_p4(rhi_p4);
		renting_house_information_VO.setRhi_p5(rhi_p5);
		dao.insert(renting_house_information_VO);

		return renting_house_information_VO;
	}
	
	public Renting_House_Information_VO updateRenting_House_Information
	(String rhi_no, String rhi_content, int rhi_status, java.sql.Date rhi_date, byte[] rhi_p1, byte[] rhi_p2, byte[] rhi_p3, byte[] rhi_p4, byte[] rhi_p5) {
		
		Renting_House_Information_VO renting_house_information_VO = new Renting_House_Information_VO();
		
		renting_house_information_VO.setRhi_no(rhi_no);
		renting_house_information_VO.setRhi_content(rhi_content);
		renting_house_information_VO.setRhi_status(rhi_status);
		renting_house_information_VO.setRhi_date(rhi_date);
		renting_house_information_VO.setRhi_p1(rhi_p1);
		renting_house_information_VO.setRhi_p2(rhi_p2);
		renting_house_information_VO.setRhi_p3(rhi_p3);
		renting_house_information_VO.setRhi_p4(rhi_p4);
		renting_house_information_VO.setRhi_p5(rhi_p5);
		dao.update(renting_house_information_VO);
		
		return renting_house_information_VO;
	}
	
	public void deleteRenting_House_Information(String rhi_no) {
		dao.delete(rhi_no);
	}

	public Renting_House_Information_VO getOneRenting_House_Information(String rhi_no) {
		return dao.findByPrimaryKey(rhi_no);
	}

	public List<Renting_House_Information_VO> getAll() {
		return dao.getAll();
	}
	
	public Renting_House_Information_VO showPicRenting_House_Information(String rhi_no) {
		return dao.showpic(rhi_no);
	}
}
