package com.latest_news.model;

import java.util.*;

public interface Latest_News_DAO_Interface {
    public void insert(Latest_News_VO latest_news_VO);
    public void update(Latest_News_VO latest_news_VO);
    public void delete(String ln_no);
    public Latest_News_VO findByPrimaryKey(String ln_no);
    public List<Latest_News_VO> getAll();
    //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//  public List<Latest_News_VO> getAll(Map<String, String[]> map); 
}
