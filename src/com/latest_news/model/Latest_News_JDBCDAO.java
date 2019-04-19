package com.latest_news.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Latest_News_JDBCDAO implements Latest_News_DAO_Interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA107";
	String passwd = "123456";
	
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
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, latest_news_VO.getLn_content());
			
			pstmt.executeUpdate();
			
			
		} catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, latest_news_VO.getLn_content());
			pstmt.setDate(2, latest_news_VO.getLn_date());
			pstmt.setString(3, latest_news_VO.getLn_no());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, ln_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

	
	public static void main(String[] args) {
		
		Latest_News_JDBCDAO dao = new Latest_News_JDBCDAO();
		
		//�s�W
		Latest_News_VO latest_news_VO1 = new Latest_News_VO();
		latest_news_VO1.setLn_content("�����ߦ���34�~�A�b���ѻP�U����T�޳N�����V�ǭ��W�L��U�H���A�ӱ���Java�����޳N���V�����V�ǭ��N�W�L3432�H���C���h��J�b������T�������V�O�u�@�����Z�Ǫ��j�A�w�g����Java�Z�ǭ��N�~�ɱj�Ӧ��O����ޡC");
		dao.insert(latest_news_VO1);
		
		//�ק�
		Latest_News_VO latest_news_VO2 = new Latest_News_VO();
		latest_news_VO2.setLn_no("N000001");
		latest_news_VO2.setLn_content("��70��  2019/04/16��2019/09/27   �� ���Z�u�ѳ̫�X�ӦW�B�A�d��n�֡I�֬��߶��Ѯv(0800-022282 ����217)�A�γ��W�ҵ{�����|�A�ѳ�I�� ");
		latest_news_VO2.setLn_date(java.sql.Date.valueOf("2019-03-31"));
		dao.update(latest_news_VO2);
		
		// �R��
		dao.delete("N000003");
		
		//�d�ߤ@��
		Latest_News_VO latest_news_VO3 = dao.findByPrimaryKey("N000001");
		System.out.println(latest_news_VO3.getLn_no() + ",");
		System.out.println(latest_news_VO3.getLn_content() + ",");
		System.out.println(latest_news_VO3.getLn_date() + ",");
		System.out.println("---------------------");
		
		//�d�ߥ���
		List<Latest_News_VO> list = dao.getAll();
		for (Latest_News_VO aLatest_News : list) {
		System.out.print(aLatest_News.getLn_no() + ",");
		System.out.print(aLatest_News.getLn_content() + ",");
		System.out.print(aLatest_News.getLn_date() + ",");
		System.out.println();
		}
	}
}
