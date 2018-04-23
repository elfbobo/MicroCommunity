package com.java110.service.dao;

import com.java110.entity.service.ServiceSql;

import java.util.List;
import java.util.Map;

/**
 * 查询数据交互接口
 * Created by wuxw on 2018/4/20.
 */
public interface IQueryServiceDAO {

    /**
     * 执行sql
     * @param sql
     * @return
     */
    public List<Map> executeSql(String sql);

    public int updateSql(String sql);

    /**
     * 执行存储过程
     * @param paramsInfo
     * @return
     */
    public String executeProc(Map<String,Object> paramsInfo);


    public String updateProc(Map<String,Object> paramsInfo);

    public List<ServiceSql> qureyServiceSqlAll();
}