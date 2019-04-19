package com.renting_house_information.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.renting_house_information.model.Renting_House_Information_Service;
import com.renting_house_information.model.Renting_House_Information_VO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class Renting_House_Information_Servlet extends HttpServlet {
	

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("getOne_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("rhi_no");
				String rhi_noReg = "RH[0-9]{6}";
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入租屋資訊編號");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/RentingHouseInformation/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				String rhi_no = null;
				rhi_no = str;
				if (!rhi_no.trim().matches(rhi_noReg)) {
					errorMsgs.add("租屋資訊編號：一定以是「RH」開頭，後面接6個「0~9」的數字");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/RentingHouseInformation/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
		
				/***************************2.開始查詢資料*****************************************/
				Renting_House_Information_Service rhiSvc = new Renting_House_Information_Service();
				Renting_House_Information_VO rhiVO = rhiSvc.getOneRenting_House_Information(rhi_no);
				if (rhiVO == null) {
					errorMsgs.add("查無資料");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/RentingHouseInformation/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("rhiVO", rhiVO);
				String url = "/RentingHouseInformation/listOneRhi.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料：" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher( "/RentingHouseInformation/select_page.jsp");
						failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorsMsgs", errorMsgs);
		
		try {
			/***************************1.接收請求參數****************************************/
			String rhi_no = req.getParameter("rhi_no");
			
			/***************************2.開始查詢資料****************************************/
			Renting_House_Information_Service rhiSvc = new Renting_House_Information_Service();
			Renting_House_Information_VO rhiVO = rhiSvc.getOneRenting_House_Information(rhi_no);
			
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
			req.setAttribute("rhiVO", rhiVO);
			String url ="/RentingHouseInformation/update_rhi_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			/***************************其他可能的錯誤處理**********************************/
		} catch (Exception e) {
			errorMsgs.add("無法取得要修改的資料：" + e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/RentingHouseInformation/listAllRhi.jsp");
			failureView.forward(req, res);
		}
	}
		
	if ("update".equals(action)) {
		/***************************1.接收請求參數***************************************/

		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
	
		try {
			
			String rhi_no = req.getParameter("rhi_no").trim();
			
			String rhi_content = req.getParameter("rhi_content");
			if(rhi_content == null || rhi_content.trim().length() ==0 ) {
				errorMsgs.add("租屋資訊內容：請勿空白");
			}
			
			String str = req.getParameter("rhi_status");
			Integer rhi_status =  new Integer(str);
		
			
			java.sql.Date rhi_date = null;
			try {
				rhi_date = java.sql.Date.valueOf(req.getParameter("rhi_date").trim());
			}catch (IllegalArgumentException e) {
				rhi_date = new java.sql.Date (System.currentTimeMillis());
				errorMsgs.add("請輸入日期！");
			}
			
		Renting_House_Information_Service rhiSvcpic = new Renting_House_Information_Service();
		Renting_House_Information_VO rhiVOpic = rhiSvcpic.showPicRenting_House_Information(rhi_no);
		    byte[] rhi_p1 = rhiVOpic.getRhi_p1();
			byte[] rhi_p2 = rhiVOpic.getRhi_p2();
			byte[] rhi_p3 = rhiVOpic.getRhi_p3();
			byte[] rhi_p4 = rhiVOpic.getRhi_p4();
			byte[] rhi_p5 = rhiVOpic.getRhi_p5();
			
			Part part1 = req.getPart("rhi_p1");
			if(part1.getSize() != 0) {
				InputStream in = part1.getInputStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[in.available()];
				int i;
				while ((i = in.read(buffer)) != -1) {
					baos.write(buffer, 0, i);
				}
				rhi_p1 = baos.toByteArray();
				baos.close();
				in.close();
			}			
		
			Part part2 = req.getPart("rhi_p2");
			if(part2.getSize() != 0) {
				InputStream in = part2.getInputStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[in.available()];
				int i;
				while ((i = in.read(buffer)) != -1) {
					baos.write(buffer, 0, i);
				}
				rhi_p2 = baos.toByteArray();
				baos.close();
				in.close();
			}
			
			Part part3 = req.getPart("rhi_p3");
			if(part3.getSize() != 0) {
				InputStream in = part3.getInputStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[in.available()];
				int i;
				while ((i = in.read(buffer)) != -1) {
					baos.write(buffer, 0, i);
				}
				rhi_p3 = baos.toByteArray();
				baos.close();
				in.close();
			}
			
			Part part4 = req.getPart("rhi_p4");
			if(part4.getSize() != 0) {
				InputStream in = part4.getInputStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[in.available()];
				int i;
				while ((i = in.read(buffer)) != -1) {
					baos.write(buffer, 0, i);
				}
				rhi_p4 = baos.toByteArray();
				baos.close();
				in.close();
			}
			
			Part part5 = req.getPart("rhi_p5");
			if(part5.getSize() != 0) {
				InputStream in = part5.getInputStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[in.available()];
				int i;
				while ((i = in.read(buffer)) != -1) {
					baos.write(buffer, 0, i);
				}
				rhi_p5 = baos.toByteArray();
				baos.close();
				in.close();
			}
			
			Renting_House_Information_VO rhiVO = new Renting_House_Information_VO();
			rhiVO.setRhi_no(rhi_no);
			rhiVO.setRhi_content(rhi_content);
			rhiVO.setRhi_status(rhi_status);
			rhiVO.setRhi_date(rhi_date);
			rhiVO.setRhi_p1(rhi_p1);
			rhiVO.setRhi_p2(rhi_p2);
			rhiVO.setRhi_p3(rhi_p3);
			rhiVO.setRhi_p4(rhi_p5);
			rhiVO.setRhi_p5(rhi_p5);
			
			if(!errorMsgs.isEmpty()) {
				req.setAttribute("rhiVO", rhiVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/RentingHouseInformation/update_rhi_input.jsp");
				failureView.forward(req, res);
				return;
					
			}
			
			/***************************2.開始修改資料*****************************************/
			Renting_House_Information_Service rhiSvc = new Renting_House_Information_Service();
			rhiVO = rhiSvc.updateRenting_House_Information(rhi_no, rhi_content, rhi_status, rhi_date, rhi_p1, rhi_p2, rhi_p3, rhi_p4, rhi_p5);
			
			/***************************3.修改完成,準備轉交(Send the Success view)*************/
			req.setAttribute("rhiVO", rhiVO);
			String url = "/RentingHouseInformation/listOneRhi.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req,  res);
			
			/***************************其他可能的錯誤處理*************************************/
		} catch (Exception e) {
			errorMsgs.add("修改資料失敗：" + e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/RentingHouseInformation/update_rhi_input.jsp");
					failureView.forward(req, res);
		}
	}
	
	if ("insert".equals(action)) {
		/***************************1.接收請求參數***************************************/

		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
	
		try {
			
			String rhi_content = req.getParameter("rhi_content");
			if(rhi_content == null || rhi_content.trim().length() ==0 ) {
				errorMsgs.add("租屋資訊內容：請勿空白");
			}
			
			String str = req.getParameter("rhi_status");
			Integer rhi_status =  new Integer(str);			
			
		    byte[] rhi_p1 = null;
			byte[] rhi_p2 = null;
			byte[] rhi_p3 = null;
			byte[] rhi_p4 = null;
			byte[] rhi_p5 = null;
			
			Part part1 = req.getPart("rhi_p1");
			if(part1.getSize() != 0) {
				InputStream in = part1.getInputStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[in.available()];
				int i;
				while ((i = in.read(buffer)) != -1) {
					baos.write(buffer, 0, i);
				}
				rhi_p1 = baos.toByteArray();
				baos.close();
				in.close();
			}
			
		
			Part part2 = req.getPart("rhi_p2");
			if(part2.getSize() != 0) {
				InputStream in = part2.getInputStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[in.available()];
				int i;
				while ((i = in.read(buffer)) != -1) {
					baos.write(buffer, 0, i);
				}
				rhi_p2 = baos.toByteArray();
				baos.close();
				in.close();
			}
			
			Part part3 = req.getPart("rhi_p3");
			if(part3.getSize() != 0) {
				InputStream in = part3.getInputStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[in.available()];
				int i;
				while ((i = in.read(buffer)) != -1) {
					baos.write(buffer, 0, i);
				}
				rhi_p3 = baos.toByteArray();
				baos.close();
				in.close();
			}
			
			Part part4 = req.getPart("rhi_p4");
			if(part4.getSize() != 0) {
				InputStream in = part4.getInputStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[in.available()];
				int i;
				while ((i = in.read(buffer)) != -1) {
					baos.write(buffer, 0, i);
				}
				rhi_p4 = baos.toByteArray();
				baos.close();
				in.close();
			}
			
			Part part5 = req.getPart("rhi_p5");
			if(part5.getSize() != 0) {
				InputStream in = part5.getInputStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[in.available()];
				int i;
				while ((i = in.read(buffer)) != -1) {
					baos.write(buffer, 0, i);
				}
				rhi_p5 = baos.toByteArray();
				baos.close();
				in.close();
			}
			
			Renting_House_Information_VO rhiVO = new Renting_House_Information_VO();
			rhiVO.setRhi_content(rhi_content);
			rhiVO.setRhi_status(rhi_status);
			rhiVO.setRhi_p1(rhi_p1);
			rhiVO.setRhi_p2(rhi_p2);
			rhiVO.setRhi_p3(rhi_p3);
			rhiVO.setRhi_p4(rhi_p5);
			rhiVO.setRhi_p5(rhi_p5);
			
			if(!errorMsgs.isEmpty()) {
				req.setAttribute("rhiVO", rhiVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/RentingHouseInformation/update_rhi_input.jsp");
				failureView.forward(req, res);
				return;
					
			}
			/***************************2.開始新增資料***************************************/
			System.out.println("1");
			Renting_House_Information_Service rhiSvc = new Renting_House_Information_Service();
			rhiVO = rhiSvc.addRenting_House_Information(rhi_content, rhi_status, rhi_p1, rhi_p2, rhi_p3, rhi_p4, rhi_p5);
			System.out.println("2");
			/***************************3.新增完成,準備轉交(Send the Success view)***********/
			String url = "/RentingHouseInformation/listAllRhi.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			/***************************其他可能的錯誤處理**********************************/
		} catch (Exception e) {
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/RentingHouseInformation/addRhi.jsp");
			failureView.forward(req, res);
		}
	}
	
	if ("delete".equals(action)) {
		
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
	
	
	try {
		/***************************1.接收請求參數***************************************/
		String rhi_no = req.getParameter("rhi_no");
		
		/***************************2.開始刪除資料***************************************/
		Renting_House_Information_Service rhiSvc = new Renting_House_Information_Service();
		rhiSvc.deleteRenting_House_Information(rhi_no);
		
		/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
		String url = "/RentingHouseInformation/listAllRhi.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url);
		successView.forward(req, res);
		
		/***************************其他可能的錯誤處理**********************************/
	} catch (Exception e) {
		errorMsgs.add("刪除資料失敗：" + e.getMessage());
		RequestDispatcher failureView = req.getRequestDispatcher("/RentingHouseInformation/listAllRhi.jsp");
		failureView.forward(req, res);
	}
	}
}
}
