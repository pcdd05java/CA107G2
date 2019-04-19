package com.renting_house_information.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Renting_House_Information_DAO implements Renting_House_Information_DAO_Interface{
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDBCA107G2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	static int picnum =1; 
	
	private static final String INSERT_STMT =
			"Insert into RENTING_HOUSE_INFORMATION (RHI_NO, RHI_CONTENT, RHI_STATUS, RHI_DATE, RHI_P1, RHI_P2, RHI_P3, RHI_P4, RHI_P5) values ('RH'||LPAD(to_char(rhi_no_seq.NEXTVAL), 6, '0'),?, ?, sysdate, ?, ?, ?, ?, ?)";
	private static final String UPDATE = 
			"update RENTING_HOUSE_INFORMATION set RHI_CONTENT=?, RHI_STATUS=?, RHI_DATE=?, RHI_P1=?, RHI_P2=?, RHI_P3=?, RHI_P4=?, RHI_P5=? where RHI_NO = ?";
	private static final String DELETE = 
			"delete from RENTING_HOUSE_INFORMATION where RHI_NO = ?";
	private static final String SHOW_PIC = 
			"select RHI_P1, RHI_P2, RHI_P3, RHI_P4, RHI_P5 from RENTING_HOUSE_INFORMATION where RHI_NO = ?";
	private static final String GET_ONE_STMT = 
			"select RHI_NO, RHI_CONTENT, RHI_STATUS, RHI_DATE, RHI_P1, RHI_P2, RHI_P3, RHI_P4, RHI_P5 from RENTING_HOUSE_INFORMATION where RHI_NO = ?";
	private static final String GET_ALL_STMT = 
			"select RHI_NO, RHI_CONTENT, RHI_STATUS, RHI_DATE, RHI_P1, RHI_P2, RHI_P3, RHI_P4, RHI_P5 from RENTING_HOUSE_INFORMATION order by RHI_NO";
	@Override
	public void insert(Renting_House_Information_VO renting_house_information_VO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, renting_house_information_VO.getRhi_content());
			pstmt.setInt(2, renting_house_information_VO.getRhi_status());
			pstmt.setBytes(3, renting_house_information_VO.getRhi_p1());
			pstmt.setBytes(4, renting_house_information_VO.getRhi_p2());
			pstmt.setBytes(5, renting_house_information_VO.getRhi_p3());
			pstmt.setBytes(6, renting_house_information_VO.getRhi_p4());
			pstmt.setBytes(7, renting_house_information_VO.getRhi_p5());

			
			pstmt.executeUpdate();
			
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}


	@Override
	public void update(Renting_House_Information_VO renting_house_information_VO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, renting_house_information_VO.getRhi_content());
			pstmt.setInt(2, renting_house_information_VO.getRhi_status());
			pstmt.setDate(3, renting_house_information_VO.getRhi_date());
			pstmt.setBytes(4, renting_house_information_VO.getRhi_p1());
			pstmt.setBytes(5, renting_house_information_VO.getRhi_p2());
			pstmt.setBytes(6, renting_house_information_VO.getRhi_p3());
			pstmt.setBytes(7, renting_house_information_VO.getRhi_p4());
			pstmt.setBytes(8, renting_house_information_VO.getRhi_p5());
			pstmt.setString(9, renting_house_information_VO.getRhi_no());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
	
	@Override
	public Renting_House_Information_VO showpic(String rhi_no) {

		Renting_House_Information_VO renting_house_information_VO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(SHOW_PIC);

			pstmt.setString(1, rhi_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				renting_house_information_VO = new Renting_House_Information_VO();
				renting_house_information_VO.setRhi_p1(rs.getBytes("RHI_P1"));
				renting_house_information_VO.setRhi_p2(rs.getBytes("RHI_P2"));
				renting_house_information_VO.setRhi_p3(rs.getBytes("RHI_P3"));
				renting_house_information_VO.setRhi_p4(rs.getBytes("RHI_P4"));
				renting_house_information_VO.setRhi_p5(rs.getBytes("RHI_P5"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return renting_house_information_VO;
	}

	@Override
	public void delete(String rhi_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, rhi_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		
	}

	@Override
	public Renting_House_Information_VO findByPrimaryKey(String rhi_no) {

		Renting_House_Information_VO renting_house_information_VO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, rhi_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				renting_house_information_VO = new Renting_House_Information_VO();
				renting_house_information_VO.setRhi_no(rs.getString("RHI_NO"));
				renting_house_information_VO.setRhi_content(rs.getString("RHI_CONTENT"));
				renting_house_information_VO.setRhi_status(rs.getInt("RHI_STATUS"));
				renting_house_information_VO.setRhi_date(rs.getDate("RHI_DATE"));
				renting_house_information_VO.setRhi_p1(rs.getBytes("RHI_P1"));
				renting_house_information_VO.setRhi_p2(rs.getBytes("RHI_P2"));
				renting_house_information_VO.setRhi_p3(rs.getBytes("RHI_P3"));
				renting_house_information_VO.setRhi_p4(rs.getBytes("RHI_P4"));
				renting_house_information_VO.setRhi_p5(rs.getBytes("RHI_P5"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return renting_house_information_VO;
	}

	@Override
	public List<Renting_House_Information_VO> getAll() {
		List<Renting_House_Information_VO> list = new ArrayList<Renting_House_Information_VO>();
		Renting_House_Information_VO renting_house_information_VO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				renting_house_information_VO = new Renting_House_Information_VO();
				renting_house_information_VO.setRhi_no(rs.getString("RHI_NO"));
				renting_house_information_VO.setRhi_content(rs.getString("RHI_CONTENT"));
				renting_house_information_VO.setRhi_status(rs.getInt("RHI_STATUS"));
				renting_house_information_VO.setRhi_date(rs.getDate("RHI_DATE"));
				renting_house_information_VO.setRhi_p1(rs.getBytes("RHI_P1"));
				renting_house_information_VO.setRhi_p2(rs.getBytes("RHI_P2"));
				renting_house_information_VO.setRhi_p3(rs.getBytes("RHI_P3"));
				renting_house_information_VO.setRhi_p4(rs.getBytes("RHI_P4"));
				renting_house_information_VO.setRhi_p5(rs.getBytes("RHI_P5"));
				
				list.add(renting_house_information_VO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}




}
