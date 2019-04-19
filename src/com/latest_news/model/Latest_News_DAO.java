package com.latest_news.model;

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

public class Latest_News_DAO implements Latest_News_DAO_Interface{
	
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
	
	private static final String INSERT_STMT =
			"Insert into LATEST_NEWS (LN_NO,LN_CONTENT,LN_DATE) values ('N'||LPAD(to_char(ln_no_seq.NEXTVAL), 6, '0'),?, sysdate)";
	private static final String UPDATE = 
			"update LATEST_NEWS set LN_CONTENT=?, LN_DATE=? where LN_NO = ?";
	private static final String DELETE = 
			"delete from LATEST_NEWS where LN_NO = ?";
	private static final String GET_ONE_STMT = 
			"select LN_NO, LN_CONTENT, LN_DATE from LATEST_NEWS where LN_NO = ?";
	private static final String GET_ALL_STMT = 
			"select LN_NO, LN_CONTENT, LN_DATE  from LATEST_NEWS order by LN_NO";
	
	
	@Override
	public void insert(Latest_News_VO latest_news_VO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, latest_news_VO.getLn_content());
			
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
	public void update(Latest_News_VO latest_news_VO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, latest_news_VO.getLn_content());
			pstmt.setDate(2, latest_news_VO.getLn_date());
			pstmt.setString(3, latest_news_VO.getLn_no());
			
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
	public void delete(String ln_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, ln_no);

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
	public Latest_News_VO findByPrimaryKey(String ln_no) {

		Latest_News_VO latest_news_VO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, ln_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				latest_news_VO = new Latest_News_VO();
				latest_news_VO.setLn_no(rs.getString("LN_NO"));
				latest_news_VO.setLn_content(rs.getString("LN_CONTENT"));
				latest_news_VO.setLn_date(rs.getDate("LN_DATE"));
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
		return latest_news_VO;
	}

	@Override
	public List<Latest_News_VO> getAll() {
		List<Latest_News_VO> list = new ArrayList<Latest_News_VO>();
		Latest_News_VO latest_news_VO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				latest_news_VO = new Latest_News_VO();
				latest_news_VO.setLn_no(rs.getString("LN_NO"));
				latest_news_VO.setLn_content(rs.getString("LN_CONTENT"));
				latest_news_VO.setLn_date(rs.getDate("LN_DATE"));
				
				list.add(latest_news_VO); // Store the row in the list
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
