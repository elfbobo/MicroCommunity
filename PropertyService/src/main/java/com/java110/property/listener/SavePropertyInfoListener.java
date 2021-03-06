package com.java110.property.listener;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.java110.common.constant.BusinessTypeConstant;
import com.java110.common.constant.StatusConstant;
import com.java110.common.util.Assert;
import com.java110.core.annotation.Java110Listener;
import com.java110.core.context.DataFlowContext;
import com.java110.core.factory.GenerateCodeFactory;
import com.java110.entity.center.Business;
import com.java110.property.dao.IPropertyServiceDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 保存 物业信息 侦听
 * Created by wuxw on 2018/5/18.
 */
@Java110Listener("savePropertyInfoListener")
@Transactional
public class SavePropertyInfoListener extends AbstractPropertyBusinessServiceDataFlowListener {

    private final static Logger logger = LoggerFactory.getLogger(SavePropertyInfoListener.class);

    @Autowired
    IPropertyServiceDao propertyServiceDaoImpl;

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public String getBusinessTypeCd() {
        return BusinessTypeConstant.BUSINESS_TYPE_SAVE_PROPERTY_INFO;
    }

    /**
     * 保存物业信息 business 表中
     * @param dataFlowContext 数据对象
     * @param business 当前业务对象
     */
    @Override
    protected void doSaveBusiness(DataFlowContext dataFlowContext, Business business) {
        JSONObject data = business.getDatas();
        Assert.notEmpty(data,"没有datas 节点，或没有子节点需要处理");

        //处理 businessProperty 节点
        if(data.containsKey("businessProperty")){
            JSONObject businessProperty = data.getJSONObject("businessProperty");
            doBusinessProperty(business,businessProperty);
            dataFlowContext.addParamOut("propertyId",businessProperty.getString("propertyId"));
        }

        if(data.containsKey("businessPropertyAttr")){
            JSONArray businessPropertyAttrs = data.getJSONArray("businessPropertyAttr");
            doSaveBusinessPropertyAttrs(business,businessPropertyAttrs);
        }
    }

    /**
     * business 数据转移到 instance
     * @param dataFlowContext 数据对象
     * @param business 当前业务对象
     */
    @Override
    protected void doBusinessToInstance(DataFlowContext dataFlowContext, Business business) {
        JSONObject data = business.getDatas();

        Map info = new HashMap();
        info.put("bId",business.getbId());
        info.put("operate",StatusConstant.OPERATE_ADD);

        //物业信息
        Map businessPropertyInfo = propertyServiceDaoImpl.getBusinessPropertyInfo(info);
        if( businessPropertyInfo != null && !businessPropertyInfo.isEmpty()) {
            propertyServiceDaoImpl.savePropertyInfoInstance(info);
            dataFlowContext.addParamOut("propertyId",businessPropertyInfo.get("property_id"));
        }
        //物业属性
        List<Map> businessPropertyAttrs = propertyServiceDaoImpl.getBusinessPropertyAttrs(info);
        if(businessPropertyAttrs != null && businessPropertyAttrs.size() > 0) {
            propertyServiceDaoImpl.savePropertyAttrsInstance(info);
        }
    }

    /**
     * 撤单
     * @param dataFlowContext 数据对象
     * @param business 当前业务对象
     */
    @Override
    protected void doRecover(DataFlowContext dataFlowContext, Business business) {
        String bId = business.getbId();
        //Assert.hasLength(bId,"请求报文中没有包含 bId");
        Map info = new HashMap();
        info.put("bId",bId);
        info.put("statusCd",StatusConstant.STATUS_CD_VALID);
        Map paramIn = new HashMap();
        paramIn.put("bId",bId);
        paramIn.put("statusCd",StatusConstant.STATUS_CD_INVALID);
        //物业信息
        Map propertyInfo = propertyServiceDaoImpl.getPropertyInfo(info);
        if(propertyInfo != null && !propertyInfo.isEmpty()){
            paramIn.put("propertyId",propertyInfo.get("property_id").toString());
            propertyServiceDaoImpl.updatePropertyInfoInstance(paramIn);
            dataFlowContext.addParamOut("propertyId",propertyInfo.get("property_id"));
        }

        //物业属性
        List<Map> propertyAttrs = propertyServiceDaoImpl.getPropertyAttrs(info);
        if(propertyAttrs != null && propertyAttrs.size()>0){
            propertyServiceDaoImpl.updatePropertyAttrInstance(paramIn);
        }
    }



    /**
     * 处理 businessProperty 节点
     * @param business 总的数据节点
     * @param businessProperty 物业节点
     */
    private void doBusinessProperty(Business business,JSONObject businessProperty){

        Assert.jsonObjectHaveKey(businessProperty,"propertyId","businessProperty 节点下没有包含 propertyId 节点");

        if(businessProperty.getString("propertyId").startsWith("-")){
            //刷新缓存
            flushPropertyId(business.getDatas());
        }

        businessProperty.put("bId",business.getbId());
        businessProperty.put("operate", StatusConstant.OPERATE_ADD);
        //保存物业信息
        propertyServiceDaoImpl.saveBusinessPropertyInfo(businessProperty);

    }



    /**
     * 保存物业属性信息
     * @param business 当前业务
     * @param businessPropertyAttrs 物业属性
     */
    private void doSaveBusinessPropertyAttrs(Business business,JSONArray businessPropertyAttrs){
        JSONObject data = business.getDatas();
        JSONObject businessProperty = data.getJSONObject("businessProperty");
        for(int propertyAttrIndex = 0 ; propertyAttrIndex < businessPropertyAttrs.size();propertyAttrIndex ++){
            JSONObject propertyAttr = businessPropertyAttrs.getJSONObject(propertyAttrIndex);
            Assert.jsonObjectHaveKey(propertyAttr,"attrId","businessPropertyAttr 节点下没有包含 attrId 节点");

            if(propertyAttr.getString("attrId").startsWith("-")){
                String attrId = GenerateCodeFactory.getAttrId();
                propertyAttr.put("attrId",attrId);
            }

            propertyAttr.put("bId",business.getbId());
            propertyAttr.put("propertyId",businessProperty.getString("propertyId"));
            propertyAttr.put("operate", StatusConstant.OPERATE_ADD);

            propertyServiceDaoImpl.saveBusinessPropertyAttr(propertyAttr);
        }
    }



    /**
     * 刷新 物业ID
     * @param data
     */
    private void flushPropertyId(JSONObject data) {

        String propertyId = GenerateCodeFactory.getPropertyId();
        JSONObject businessProperty = data.getJSONObject("businessProperty");
        businessProperty.put("propertyId",propertyId);
        //刷物业属性
        if(data.containsKey("businessPropertyAttr")) {
            JSONArray businessPropertyAttrs = data.getJSONArray("businessPropertyAttr");
            for(int businessPropertyAttrIndex = 0;businessPropertyAttrIndex < businessPropertyAttrs.size();businessPropertyAttrIndex++) {
                JSONObject businessPropertyAttr = businessPropertyAttrs.getJSONObject(businessPropertyAttrIndex);
                businessPropertyAttr.put("propertyId", propertyId);
            }
        }
//        //刷 是物业照片 的 propertyId
//        if(data.containsKey("businessPropertyPhoto")) {
//            JSONArray businessPropertyPhotos = data.getJSONArray("businessPropertyPhoto");
//            for(int businessPropertyPhotoIndex = 0;businessPropertyPhotoIndex < businessPropertyPhotos.size();businessPropertyPhotoIndex++) {
//                JSONObject businessPropertyPhoto = businessPropertyPhotos.getJSONObject(businessPropertyPhotoIndex);
//                businessPropertyPhoto.put("propertyId", propertyId);
//            }
//        }
//        //刷 物业证件 的propertyId
//        if(data.containsKey("businessPropertyCerdentials")) {
//            JSONArray businessPropertyCerdentialses = data.getJSONArray("businessPropertyCerdentials");
//            for(int businessPropertyCerdentialsIndex = 0;businessPropertyCerdentialsIndex < businessPropertyCerdentialses.size();businessPropertyCerdentialsIndex++) {
//                JSONObject businessPropertyCerdentials = businessPropertyCerdentialses.getJSONObject(businessPropertyCerdentialsIndex);
//                businessPropertyCerdentials.put("propertyId", propertyId);
//            }
//        }
    }


    public IPropertyServiceDao getPropertyServiceDaoImpl() {
        return propertyServiceDaoImpl;
    }

    public void setPropertyServiceDaoImpl(IPropertyServiceDao propertyServiceDaoImpl) {
        this.propertyServiceDaoImpl = propertyServiceDaoImpl;
    }
}
