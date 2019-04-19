package com.renting_house_information.model;

import java.util.*;

public interface Renting_House_Information_DAO_Interface {
    public void insert(Renting_House_Information_VO renting_house_information_VO);
    public void update(Renting_House_Information_VO renting_house_information_VO);
    public void delete(String rhi_no);
    public Renting_House_Information_VO showpic(String rhi_no);
    public Renting_House_Information_VO findByPrimaryKey(String rhi_no);
    public List<Renting_House_Information_VO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<Latest_News_VO> getAll(Map<String, String[]> map); 
}
