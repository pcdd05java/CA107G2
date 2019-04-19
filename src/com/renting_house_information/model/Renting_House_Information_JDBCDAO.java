package com.renting_house_information.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Renting_House_Information_JDBCDAO implements Renting_House_Information_DAO_Interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA107G2";
	String passwd = "123456";
	static int picnum =1; 
	
	private static final String INSERT_STMT =
			"Insert into RENTING_HOUSE_INFORMATION (RHI_NO, RHI_CONTENT, RHI_STATUS, RHI_DATE, RHI_P1, RHI_P2, RHI_P3, RHI_P4, RHI_P5) values ('RH'||LPAD(to_char(rhi_no_seq.NEXTVAL), 6, '0'),?, ?, sysdate, ?, ?, ?, ?, ?)";
	private static final String UPDATE = 
			"update RENTING_HOUSE_INFORMATION set RHI_CONTENT=?, RHI_STATUS=?, RHI_DATE=?, RHI_P1=?, RHI_P2=?, RHI_P3=?, RHI_P4=?, RHI_P5=? where RHI_NO = ?";
	private static final String DELETE = 
			"delete from RENTING_HOUSE_INFORMATION where RHI_NO = ?";
	private static final String GET_ONE_STMT = 
			"select RHI_NO, RHI_CONTENT, RHI_STATUS, RHI_DATE, RHI_P1, RHI_P2, RHI_P3, RHI_P4, RHI_P5 from RENTING_HOUSE_INFORMATION where RHI_NO = ?";
	private static final String GET_ALL_STMT = 
			"select RHI_NO, RHI_CONTENT, RHI_STATUS, RHI_DATE, RHI_P1, RHI_P2, RHI_P3, RHI_P4, RHI_P5 from RENTING_HOUSE_INFORMATION order by RHI_NO";
	private static final String SHOW_PIC = 
			"select RHI_P1, RHI_P2, RHI_P3, RHI_P4, RHI_P5 from RENTING_HOUSE_INFORMATION where RHI_NO = ?";
	@Override
	public void insert(Renting_House_Information_VO renting_house_information_VO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, renting_house_information_VO.getRhi_content());
			pstmt.setInt(2, renting_house_information_VO.getRhi_status());
			pstmt.setBytes(3, renting_house_information_VO.getRhi_p1());
			pstmt.setBytes(4, renting_house_information_VO.getRhi_p2());
			pstmt.setBytes(5, renting_house_information_VO.getRhi_p3());
			pstmt.setBytes(6, renting_house_information_VO.getRhi_p4());
			pstmt.setBytes(7, renting_house_information_VO.getRhi_p5());

			
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
	public void update(Renting_House_Information_VO renting_house_information_VO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void delete(String rhi_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, rhi_no);

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
	public Renting_House_Information_VO findByPrimaryKey(String rhi_no) {

		Renting_House_Information_VO renting_house_information_VO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

	
	public static void main(String[] args) throws IOException {
		
		Renting_House_Information_JDBCDAO dao = new Renting_House_Information_JDBCDAO();
		
		//�s�W
		Renting_House_Information_VO renting_house_information_VO1 = new Renting_House_Information_VO();
		renting_house_information_VO1.setRhi_content("�� �����j�ǰ����]������U�^�� �p���覡�G�аȥ��H�q�ܥ��s�� 0968608014 �^���� �� �a�z��m�G�� �a�}�G��饫���c�Ϥ��v���������� ��s�Υ�y�D�BNOVA 3C ����B���o�BSOGO�B���c�]���B���s��|���� ���s���t�����A�s�Υ�y�D�U�A���s�Τ�V�G�@�B�Ĥ@�Ӭ���O�k��]����i�ӡ^�����@�ʤ��ثᥪ��ܤ������F�G�B�ΦܲĤG�Ӭ���O���u�T�����v���f�k��A�����@�ʦh���ثᥪ��ܤ�����");
		renting_house_information_VO1.setRhi_status(0);
		byte[] pic1 = getPictureByteArray("webcontent/RentingHouseInformation/images/1.jpg");
		renting_house_information_VO1.setRhi_p1(pic1);
		byte[] pic2 = getPictureByteArray("webcontent/RentingHouseInformation/images/2.jpg");
		renting_house_information_VO1.setRhi_p2(pic2);
		byte[] pic3 = getPictureByteArray("webcontent/RentingHouseInformation/images/3.jpg");
		renting_house_information_VO1.setRhi_p3(pic3);
		byte[] pic4 = getPictureByteArray("webcontent/RentingHouseInformation/images/4.jpg");
		renting_house_information_VO1.setRhi_p4(pic4);
		byte[] pic5 = getPictureByteArray("webcontent/RentingHouseInformation/images/5.jpg");
		renting_house_information_VO1.setRhi_p5(pic5);
	    dao.insert(renting_house_information_VO1);
		
		//�ק�
		Renting_House_Information_VO renting_house_information_VO2 = new Renting_House_Information_VO();
		renting_house_information_VO2.setRhi_no("RH000001");
		renting_house_information_VO2.setRhi_content("�� ���ڨ�H�H�W�a�x�ӯ��]�M�Э����H�^�� �i�H�i���w�d���]�����󭭨�^�� �w��ǥͤΥ�¾�W�Z�کӯ��� ����ñ�q�ɯ��U����ݥX�ܨ������ҩ���󥿥��Ѩ����T�{ ");
		renting_house_information_VO2.setRhi_status(1);
		renting_house_information_VO2.setRhi_date(java.sql.Date.valueOf("2019-03-31"));
		//�u��p1�A��l����
		byte[] picA = getPictureByteArray("webcontent/RentingHouseInformation/images/a.jpg");
		renting_house_information_VO2.setRhi_p1(picA);
		renting_house_information_VO2.setRhi_p2(renting_house_information_VO2.getRhi_p2());
		renting_house_information_VO2.setRhi_p3(renting_house_information_VO2.getRhi_p3());
		renting_house_information_VO2.setRhi_p4(renting_house_information_VO2.getRhi_p4());
		renting_house_information_VO2.setRhi_p5(renting_house_information_VO2.getRhi_p5());
		dao.update(renting_house_information_VO2);
		
		// �R��
		dao.delete("RH000003");
		
		//�d�ߤ@��
		Renting_House_Information_VO renting_house_information_VO3 = dao.findByPrimaryKey("RH000001");
		System.out.println(renting_house_information_VO3.getRhi_no() + ",");
		System.out.println(renting_house_information_VO3.getRhi_content() + ",");
		System.out.println(renting_house_information_VO3.getRhi_status() + ",");
		System.out.println(renting_house_information_VO3.getRhi_date() + ",");
		readPicture(renting_house_information_VO3.getRhi_p1());
		readPicture(renting_house_information_VO3.getRhi_p2());
		readPicture(renting_house_information_VO3.getRhi_p3());
		readPicture(renting_house_information_VO3.getRhi_p4());
		readPicture(renting_house_information_VO3.getRhi_p5());
		System.out.println("---------------------");
		
		//�d�ߥ���
		List<Renting_House_Information_VO> list = dao.getAll();
		for (Renting_House_Information_VO aRenting_House_Info : list) {
		System.out.print(aRenting_House_Info.getRhi_no() + ",");
		System.out.println(aRenting_House_Info.getRhi_content() + ",");
		System.out.println(aRenting_House_Info.getRhi_status() + ",");
		System.out.println(aRenting_House_Info.getRhi_date() + ",");
		readPicture(aRenting_House_Info.getRhi_p1());
		readPicture(aRenting_House_Info.getRhi_p2());
		readPicture(aRenting_House_Info.getRhi_p3());
		readPicture(aRenting_House_Info.getRhi_p4());
		readPicture(aRenting_House_Info.getRhi_p5());
		System.out.println();
		}
	}
	
	
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[fis.available()];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}
	public static void readPicture(byte[] bytes) throws IOException{
		if(bytes != null) {
			File directory = new File("webcontent/RentingHouseInformation/outputs");
			if(!directory.exists()) {
				directory.mkdirs();
			}
		FileOutputStream fos = new FileOutputStream("webcontent/RentingHouseInformation/outputs/"+ picnum +".jpg");
		fos.write(bytes);
		picnum++;
		fos.flush();
		fos.close();}
	}


	@Override
	public Renting_House_Information_VO showpic(String rhi_no) {

		Renting_House_Information_VO renting_house_information_VO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
}
