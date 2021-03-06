package com.java110.property.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.java110.property.dao.IPropertyServiceDao;
import com.java110.common.constant.ResponseConstant;
import com.java110.common.exception.DAOException;
import com.java110.common.util.DateUtil;
import com.java110.core.base.dao.BaseServiceDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 物业服务 与数据库交互
 * Created by wuxw on 2017/4/5.
 */
@Service("propertyServiceDaoImpl")
//@Transactional
public class PropertyServiceDaoImpl extends BaseServiceDao implements IPropertyServiceDao {

    private final static Logger logger = LoggerFactory.getLogger(PropertyServiceDaoImpl.class);

    /**
     * 物业信息封装
     * @param businessPropertyInfo 物业信息 封装
     * @throws DAOException
     */
    @Override
    public void saveBusinessPropertyInfo(Map businessPropertyInfo) throws DAOException {
        businessPropertyInfo.put("month", DateUtil.getCurrentMonth());
        // 查询business_user 数据是否已经存在
        logger.debug("保存物业信息 入参 businessPropertyInfo : {}",businessPropertyInfo);
        int saveFlag = sqlSessionTemplate.insert("propertyServiceDaoImpl.saveBusinessPropertyInfo",businessPropertyInfo);

        if(saveFlag < 1){
            throw new DAOException(ResponseConstant.RESULT_PARAM_ERROR,"保存物业数据失败："+ JSONObject.toJSONString(businessPropertyInfo));
        }
    }

    /**
     * 物业属性信息分装
     * @param businessPropertyAttr 物业属性信息封装
     * @throws DAOException
     */
    @Override
    public void saveBusinessPropertyAttr(Map businessPropertyAttr) throws DAOException {
        businessPropertyAttr.put("month", DateUtil.getCurrentMonth());
        // 查询business_user 数据是否已经存在
        logger.debug("保存物业属性信息 入参 businessPropertyAttr : {}",businessPropertyAttr);

        int saveFlag = sqlSessionTemplate.insert("propertyServiceDaoImpl.saveBusinessPropertyAttr",businessPropertyAttr);

        if(saveFlag < 1){
            throw new DAOException(ResponseConstant.RESULT_PARAM_ERROR,"保存物业属性数据失败："+ JSONObject.toJSONString(businessPropertyAttr));
        }
    }

    /**
     * 保存物业照片信息
     * @param businessPropertyPhoto 物业照片
     * @throws DAOException
     */
    @Override
    public void saveBusinessPropertyPhoto(Map businessPropertyPhoto) throws DAOException {
        businessPropertyPhoto.put("month", DateUtil.getCurrentMonth());
        logger.debug("保存物业照片信息 入参 businessPropertyPhoto : {}",businessPropertyPhoto);

        int saveFlag = sqlSessionTemplate.insert("propertyServiceDaoImpl.saveBusinessPropertyPhoto",businessPropertyPhoto);

        if(saveFlag < 1){
            throw new DAOException(ResponseConstant.RESULT_PARAM_ERROR,"保存物业照片数据失败："+ JSONObject.toJSONString(businessPropertyPhoto));
        }
    }

    /**
     * 保存物业证件信息
     * @param businessPropertyCerdentials 物业证件
     * @throws DAOException
     */
    @Override
    public void saveBusinessPropertyCerdentials(Map businessPropertyCerdentials) throws DAOException {
        businessPropertyCerdentials.put("month", DateUtil.getCurrentMonth());
        logger.debug("保存物业证件信息 入参 businessPropertyCerdentials : {}",businessPropertyCerdentials);

        int saveFlag = sqlSessionTemplate.insert("propertyServiceDaoImpl.saveBusinessPropertyCerdentials",businessPropertyCerdentials);

        if(saveFlag < 1){
            throw new DAOException(ResponseConstant.RESULT_PARAM_ERROR,"保存物业证件数据失败："+ JSONObject.toJSONString(businessPropertyCerdentials));
        }
    }

    /**
     * 保存物业用户信息
     * @param info
     * @throws DAOException
     */
    public void saveBusinessPropertyUser(Map info) throws DAOException{
        info.put("month", DateUtil.getCurrentMonth());
        logger.debug("保存物业用户信息入参 info : {}",info);

        int saveFlag = sqlSessionTemplate.insert("propertyServiceDaoImpl.saveBusinessPropertyUser",info);

        if(saveFlag < 1){
            throw new DAOException(ResponseConstant.RESULT_PARAM_ERROR,"保存物业用户信息数据失败："+ JSONObject.toJSONString(info));
        }
    }

    /**
     * 保存物业费用信息
     * @param info
     * @throws DAOException
     */
    public void saveBusinessPropertyFee(Map info) throws DAOException{
        info.put("month", DateUtil.getCurrentMonth());
        logger.debug("保存物业费用信息入参 info : {}",info);

        int saveFlag = sqlSessionTemplate.insert("propertyServiceDaoImpl.saveBusinessPropertyFee",info);

        if(saveFlag < 1){
            throw new DAOException(ResponseConstant.RESULT_PARAM_ERROR,"保存物业费用信息数据失败："+ JSONObject.toJSONString(info));
        }
    }

    /**
     * 保存 住户信息
     * @param businessPropertyHouse 住户信息 封装
     * @throws DAOException 操作数据库异常
     */
    public void saveBusinessPropertyHouse(Map businessPropertyHouse) throws DAOException{
        businessPropertyHouse.put("month", DateUtil.getCurrentMonth());
        // 查询business_user 数据是否已经存在
        logger.debug("保存住户信息 入参 businessPropertyHouse : {}",businessPropertyHouse);
        int saveFlag = sqlSessionTemplate.insert("propertyServiceDaoImpl.saveBusinessPropertyHouse",businessPropertyHouse);

        if(saveFlag < 1){
            throw new DAOException(ResponseConstant.RESULT_PARAM_ERROR,"保存住户数据失败："+ JSONObject.toJSONString(businessPropertyHouse));
        }
    }

    /**
     * 保存住户属性
     * @param businessPropertyHouseAttr 住户信息封装
     * @throws DAOException 操作数据库异常
     */
    public void saveBusinessPropertyHouseAttr(Map businessPropertyHouseAttr) throws DAOException{
        businessPropertyHouseAttr.put("month", DateUtil.getCurrentMonth());
        // 查询business_user 数据是否已经存在
        logger.debug("保存住户属性信息 入参 businessPropertyHouseAttr : {}",businessPropertyHouseAttr);

        int saveFlag = sqlSessionTemplate.insert("propertyServiceDaoImpl.saveBusinessPropertyHouseAttr",businessPropertyHouseAttr);

        if(saveFlag < 1){
            throw new DAOException(ResponseConstant.RESULT_PARAM_ERROR,"保存住户属性数据失败："+ JSONObject.toJSONString(businessPropertyHouseAttr));
        }
    }

    /**
     * 查询物业信息
     * @param info bId 信息
     * @return 物业信息
     * @throws DAOException
     */
    @Override
    public Map getBusinessPropertyInfo(Map info) throws DAOException {

        logger.debug("查询物业信息 入参 info : {}",info);

        List<Map> businessPropertyInfos = sqlSessionTemplate.selectList("propertyServiceDaoImpl.getBusinessPropertyInfo",info);
        if(businessPropertyInfos == null){
            return null;
        }
        if(businessPropertyInfos.size() >1){
            throw new DAOException(ResponseConstant.RESULT_PARAM_ERROR,"根据条件查询有多条数据,数据异常，请检查：businessPropertyInfos，"+ JSONObject.toJSONString(info));
        }

        return businessPropertyInfos.get(0);
    }

    /**
     * 查询物业属性
     * @param info bId 信息
     * @return 物业属性
     * @throws DAOException
     */
    @Override
    public List<Map> getBusinessPropertyAttrs(Map info) throws DAOException {
        logger.debug("查询物业属性信息 入参 info : {}",info);

        List<Map> businessPropertyAttrs = sqlSessionTemplate.selectList("propertyServiceDaoImpl.getBusinessPropertyAttrs",info);

        return businessPropertyAttrs;
    }

    /**
     * 查询住户信息（business过程）
     * 根据bId 查询物业信息
     * @param info bId 信息
     * @return 物业信息
     * @throws DAOException
     */
    public Map getBusinessPropertyHouse(Map info) throws DAOException{
        logger.debug("查询住户信息 入参 info : {}",info);

        List<Map> businessPropertyHouses = sqlSessionTemplate.selectList("propertyServiceDaoImpl.getBusinessPropertyHouse",info);
        if(businessPropertyHouses == null){
            return null;
        }
        if(businessPropertyHouses.size() >1){
            throw new DAOException(ResponseConstant.RESULT_PARAM_ERROR,"根据条件查询有多条数据,数据异常，请检查：businessPropertyHouse，"+ JSONObject.toJSONString(info));
        }

        return businessPropertyHouses.get(0);
    }


    /**
     * 查询住户属性信息（business过程）
     * @param info bId 信息
     * @return 物业属性
     * @throws DAOException
     */
    public List<Map> getBusinessPropertyHouseAttrs(Map info) throws DAOException{
        logger.debug("查询住户属性信息 入参 info : {}",info);

        List<Map> businessPropertyHouseAttrs = sqlSessionTemplate.selectList("propertyServiceDaoImpl.getBusinessPropertyHouseAttrs",info);

        return businessPropertyHouseAttrs;
    }

    /**
     * 查询物业照片
     * @param info bId 信息
     * @return
     * @throws DAOException
     */
    @Override
    public List<Map> getBusinessPropertyPhoto(Map info) throws DAOException {
        logger.debug("查询物业照片信息 入参 info : {}",info);

        List<Map> businessPropertyPhotos = sqlSessionTemplate.selectList("propertyServiceDaoImpl.getBusinessPropertyPhoto",info);

        return businessPropertyPhotos;
    }

    /**
     * 查询物业证件
     * @param info bId 信息
     * @return
     * @throws DAOException
     */
    @Override
    public List<Map> getBusinessPropertyCerdentials(Map info) throws DAOException {
        logger.debug("查询物业证件信息 入参 info : {}",info);

        List<Map> businessPropertyCerdentialses = sqlSessionTemplate.selectList("propertyServiceDaoImpl.getBusinessPropertyCerdentials",info);

        return businessPropertyCerdentialses;
    }

    /**
     * 查询物业用户信息
     * @param info bId 信息
     * @return 物业照片
     * @throws DAOException
     */
    public List<Map> getBusinessPropertyUser(Map info) throws DAOException{
        logger.debug("查询物业用户信息 入参 info : {}",info);

        List<Map> businessPropertyUsers = sqlSessionTemplate.selectList("propertyServiceDaoImpl.getBusinessPropertyUser",info);

        return businessPropertyUsers;
    }

    /**
     * 查询物业费用信息
     * @param info bId 信息
     * @return 物业照片
     * @throws DAOException
     */
    public List<Map> getBusinessPropertyFee(Map info) throws DAOException{
        logger.debug("查询物业用户信息 入参 info : {}",info);

        List<Map> businessPropertyUsers = sqlSessionTemplate.selectList("propertyServiceDaoImpl.getBusinessPropertyFee",info);

        return businessPropertyUsers;
    }


    /**
     * 保存物业信息 到 instance
     * @param info   bId 信息
     * @throws DAOException
     */
    @Override
    public void savePropertyInfoInstance(Map info) throws DAOException {
        logger.debug("保存物业信息Instance 入参 info : {}",info);

        int saveFlag = sqlSessionTemplate.insert("propertyServiceDaoImpl.savePropertyInfoInstance",info);

        if(saveFlag < 1){
            throw new DAOException(ResponseConstant.RESULT_PARAM_ERROR,"保存物业信息Instance数据失败："+ JSONObject.toJSONString(info));
        }
    }

    @Override
    public void savePropertyAttrsInstance(Map info) throws DAOException {
        logger.debug("保存物业属性信息Instance 入参 info : {}",info);

        int saveFlag = sqlSessionTemplate.insert("propertyServiceDaoImpl.savePropertyAttrsInstance",info);

        if(saveFlag < 1){
            throw new DAOException(ResponseConstant.RESULT_PARAM_ERROR,"保存物业属性信息Instance数据失败："+ JSONObject.toJSONString(info));
        }
    }

    /**
     * 保存 住户信息 Business数据到 Instance中
     * @param info
     * @throws DAOException
     */
    public void savePropertyHouseInstance(Map info) throws DAOException{
        logger.debug("保存住户信息Instance 入参 info : {}",info);

        int saveFlag = sqlSessionTemplate.insert("propertyServiceDaoImpl.savePropertyHouseInstance",info);

        if(saveFlag < 1){
            throw new DAOException(ResponseConstant.RESULT_PARAM_ERROR,"保存住户信息Instance数据失败："+ JSONObject.toJSONString(info));
        }
    }


    /**
     * 保存 住户属性信息 Business数据到 Instance中
     * @param info
     * @throws DAOException
     */
    public void savePropertyHouseAttrsInstance(Map info) throws DAOException{
        logger.debug("保存住户属性信息Instance 入参 info : {}",info);

        int saveFlag = sqlSessionTemplate.insert("propertyServiceDaoImpl.savePropertyHouseAttrsInstance",info);

        if(saveFlag < 1){
            throw new DAOException(ResponseConstant.RESULT_PARAM_ERROR,"保存住户属性信息Instance数据失败："+ JSONObject.toJSONString(info));
        }
    }

    @Override
    public void savePropertyPhotoInstance(Map info) throws DAOException {
        logger.debug("保存物业照片信息Instance 入参 info : {}",info);

        int saveFlag = sqlSessionTemplate.insert("propertyServiceDaoImpl.savePropertyPhotoInstance",info);

        if(saveFlag < 1){
            throw new DAOException(ResponseConstant.RESULT_PARAM_ERROR,"保存物业照片信息Instance数据失败："+ JSONObject.toJSONString(info));
        }
    }

    @Override
    public void savePropertyCerdentialsInstance(Map info) throws DAOException {
        logger.debug("保存物业证件信息Instance 入参 info : {}",info);

        int saveFlag = sqlSessionTemplate.insert("propertyServiceDaoImpl.savePropertyCerdentialsInstance",info);

        if(saveFlag < 1){
            throw new DAOException(ResponseConstant.RESULT_PARAM_ERROR,"保存物业证件信息Instance数据失败："+ JSONObject.toJSONString(info));
        }
    }

    /**
     * 保存 物业用户信息 Business数据到 Instance中
     * @param info
     * @throws DAOException
     */
    public void savePropertyUserInstance(Map info) throws DAOException{
        logger.debug("保存物业用户信息Instance 入参 info : {}",info);

        int saveFlag = sqlSessionTemplate.insert("propertyServiceDaoImpl.savePropertyUserInstance",info);

        if(saveFlag < 1){
            throw new DAOException(ResponseConstant.RESULT_PARAM_ERROR,"保存物业用户信息Instance数据失败："+ JSONObject.toJSONString(info));
        }
    }

    /**
     * 保存 物业费用信息 Business数据到 Instance中
     * @param info
     * @throws DAOException
     */
    public void savePropertyFeeInstance(Map info) throws DAOException{
        logger.debug("保存物业费用信息Instance 入参 info : {}",info);

        int saveFlag = sqlSessionTemplate.insert("propertyServiceDaoImpl.savePropertyFeeInstance",info);

        if(saveFlag < 1){
            throw new DAOException(ResponseConstant.RESULT_PARAM_ERROR,"保存物业费用信息Instance数据失败："+ JSONObject.toJSONString(info));
        }
    }


    /**
     * 查询物业信息（instance）
     * @param info bId 信息
     * @return
     * @throws DAOException
     */
    @Override
    public Map getPropertyInfo(Map info) throws DAOException {
        logger.debug("查询物业信息 入参 info : {}",info);

        List<Map> businessPropertyInfos = sqlSessionTemplate.selectList("propertyServiceDaoImpl.getPropertyInfo",info);
        if(businessPropertyInfos == null || businessPropertyInfos.size() == 0){
            return null;
        }
        if(businessPropertyInfos.size() >1){
            throw new DAOException(ResponseConstant.RESULT_PARAM_ERROR,"根据条件查询有多条数据,数据异常，请检查：getPropertyInfo，"+ JSONObject.toJSONString(info));
        }

        return businessPropertyInfos.get(0);
    }

    /**
     * 物业属性查询（instance）
     * @param info bId 信息
     * @return
     * @throws DAOException
     */
    @Override
    public List<Map> getPropertyAttrs(Map info) throws DAOException {
        logger.debug("查询物业属性信息 入参 info : {}",info);

        List<Map> propertyAttrs = sqlSessionTemplate.selectList("propertyServiceDaoImpl.getPropertyAttrs",info);

        return propertyAttrs;
    }

    /**
     * 查询住户信息（instance过程）
     * 根据bId 查询物业信息
     * @param info bId 信息
     * @return 物业信息
     * @throws DAOException
     */
    public Map getPropertyHouse(Map info) throws DAOException{
        logger.debug("查询住户信息 入参 info : {}",info);

        List<Map> businessPropertyInfos = sqlSessionTemplate.selectList("propertyServiceDaoImpl.getPropertyHouse",info);
        if(businessPropertyInfos == null || businessPropertyInfos.size() == 0){
            return null;
        }
        if(businessPropertyInfos.size() >1){
            throw new DAOException(ResponseConstant.RESULT_PARAM_ERROR,"根据条件查询有多条数据,数据异常，请检查：getPropertyHouse，"+ JSONObject.toJSONString(info));
        }

        return businessPropertyInfos.get(0);
    }


    /**
     * 查询住户属性信息（instance过程）
     * @param info bId 信息
     * @return 物业属性
     * @throws DAOException
     */
    public List<Map> getPropertyHouseAttrs(Map info) throws DAOException{
        logger.debug("查询住户属性信息 入参 info : {}",info);

        List<Map> propertyAttrs = sqlSessionTemplate.selectList("propertyServiceDaoImpl.getPropertyHouseAttrs",info);

        return propertyAttrs;
    }

    /**
     * 物业照片查询（instance）
     * @param info bId 信息
     * @return
     * @throws DAOException
     */
    @Override
    public List<Map> getPropertyPhoto(Map info) throws DAOException {
        logger.debug("查询物业照片信息 入参 info : {}",info);

        List<Map> propertyPhotos = sqlSessionTemplate.selectList("propertyServiceDaoImpl.getPropertyPhoto",info);

        return propertyPhotos;
    }

    /**
     * 物业证件查询（instance）
     * @param info bId 信息
     * @return
     * @throws DAOException
     */
    @Override
    public List<Map> getPropertyCerdentials(Map info) throws DAOException {
        logger.debug("查询物业证件信息 入参 info : {}",info);

        List<Map> propertyCerdentialses = sqlSessionTemplate.selectList("propertyServiceDaoImpl.getPropertyCerdentials",info);

        return propertyCerdentialses;
    }

    /**
     * 查询物业用户信息（instance 过程）
     * @param info bId 信息
     * @return 物业照片
     * @throws DAOException
     */
    public List<Map> getPropertyUser(Map info) throws DAOException{
        logger.debug("查询物业用户信息 入参 info : {}",info);

        List<Map> propertyUsers = sqlSessionTemplate.selectList("propertyServiceDaoImpl.getPropertyUser",info);

        return propertyUsers;
    }

    public List<Map> getPropertyFee(Map info) throws DAOException{
        logger.debug("查询物业费用信息 入参 info : {}",info);
        List<Map> propertyFees = sqlSessionTemplate.selectList("propertyServiceDaoImpl.getPropertyFee",info);
        return propertyFees;
    }

    /**
     * 修改物业信息
     * @param info 修改信息
     * @throws DAOException
     */
    @Override
    public void updatePropertyInfoInstance(Map info) throws DAOException {
        logger.debug("修改物业信息Instance 入参 info : {}",info);

        int saveFlag = sqlSessionTemplate.update("propertyServiceDaoImpl.updatePropertyInfoInstance",info);

        if(saveFlag < 1){
            throw new DAOException(ResponseConstant.RESULT_PARAM_ERROR,"修改物业信息Instance数据失败："+ JSONObject.toJSONString(info));
        }
    }

    /**
     * 修改物业属性信息（instance）
     * @param info 修改信息
     * @throws DAOException
     */
    @Override
    public void updatePropertyAttrInstance(Map info) throws DAOException {
        logger.debug("修改物业属性信息Instance 入参 info : {}",info);

        int saveFlag = sqlSessionTemplate.update("propertyServiceDaoImpl.updatePropertyAttrInstance",info);

        if(saveFlag < 1){
            throw new DAOException(ResponseConstant.RESULT_PARAM_ERROR,"修改物业属性信息Instance数据失败："+ JSONObject.toJSONString(info));
        }
    }

    /**
     * 修改住户信息
     * @param info 修改信息
     * @throws DAOException
     */
    public void updatePropertyHouseInstance(Map info) throws DAOException{
        logger.debug("修改住户信息Instance 入参 info : {}",info);

        int saveFlag = sqlSessionTemplate.update("propertyServiceDaoImpl.updatePropertyHouseInstance",info);

        if(saveFlag < 1){
            throw new DAOException(ResponseConstant.RESULT_PARAM_ERROR,"修改住户信息Instance数据失败："+ JSONObject.toJSONString(info));
        }
    }


    /**
     * 修改住户属性信息
     * @param info 修改信息
     * @throws DAOException
     */
    public void updatePropertyHouseAttrInstance(Map info) throws DAOException{
        logger.debug("修改住户属性信息Instance 入参 info : {}",info);

        int saveFlag = sqlSessionTemplate.update("propertyServiceDaoImpl.updatePropertyHouseAttrInstance",info);

        if(saveFlag < 1){
            throw new DAOException(ResponseConstant.RESULT_PARAM_ERROR,"修改住户属性信息Instance数据失败："+ JSONObject.toJSONString(info));
        }
    }

    /**
     * 修改 物业照片信息
     * @param info 修改信息
     * @throws DAOException
     */
    @Override
    public void updatePropertyPhotoInstance(Map info) throws DAOException {
        logger.debug("修改物业照片信息Instance 入参 info : {}",info);

        int saveFlag = sqlSessionTemplate.update("propertyServiceDaoImpl.updatePropertyPhotoInstance",info);

        if(saveFlag < 1){
            throw new DAOException(ResponseConstant.RESULT_PARAM_ERROR,"修改物业照片信息Instance数据失败："+ JSONObject.toJSONString(info));
        }
    }

    /**
     * 修改物业证件信息
     * @param info 修改信息
     * @throws DAOException
     */
    @Override
    public void updatePropertyCerdentailsInstance(Map info) throws DAOException {
        logger.debug("修改物业证件信息Instance 入参 info : {}",info);

        int saveFlag = sqlSessionTemplate.update("propertyServiceDaoImpl.updatePropertyCerdentailsInstance",info);

        if(saveFlag < 1){
            throw new DAOException(ResponseConstant.RESULT_PARAM_ERROR,"修改物业证件信息Instance数据失败："+ JSONObject.toJSONString(info));
        }
    }

    /**
     * 修改物业用户信息
     * @param info 修改信息
     * @throws DAOException
     */
    public void updatePropertyUserInstance(Map info) throws DAOException{
        logger.debug("修改物业用户信息Instance 入参 info : {}",info);

        int saveFlag = sqlSessionTemplate.update("propertyServiceDaoImpl.updatePropertyUserInstance",info);

        if(saveFlag < 1){
            throw new DAOException(ResponseConstant.RESULT_PARAM_ERROR,"修改物业用户信息Instance数据失败："+ JSONObject.toJSONString(info));
        }
    }



    /**
     * 修改物业费用信息
     * @param info 修改信息
     * @throws DAOException
     */
    public void updatePropertyFeeInstance(Map info) throws DAOException{
        logger.debug("修改物业费用信息Instance 入参 info : {}",info);

        int saveFlag = sqlSessionTemplate.update("propertyServiceDaoImpl.updatePropertyFeeInstance",info);

        if(saveFlag < 1){
            throw new DAOException(ResponseConstant.RESULT_PARAM_ERROR,"修改物业费用信息Instance数据失败："+ JSONObject.toJSONString(info));
        }
    }

}
