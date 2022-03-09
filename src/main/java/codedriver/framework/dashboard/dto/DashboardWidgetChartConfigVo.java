package codedriver.framework.dashboard.dto;

import codedriver.framework.common.constvalue.ApiParamType;
import codedriver.framework.restful.annotation.EntityField;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * @Title: DashboardConfigVo
 * @Package: codedriver.framework.dashboard.dto
 * @Description: TODO
 * @Author: 89770
 * @Date: 2021/3/12 12:06
 * Copyright(c) 2021 TechSure Co., Ltd. All Rights Reserved.
 * 本内容仅限于深圳市赞悦科技有限公司内部传阅，禁止外泄以及用于其他的商业项目。
 **/
public class DashboardWidgetChartConfigVo implements Serializable {
    private static final long serialVersionUID = 2751480431370469835L;
    @EntityField(name = "分类", type = ApiParamType.STRING)
    private String type;
    @EntityField(name = "分组", type = ApiParamType.STRING)
    private String group;
    @EntityField(name = "分组名", type = ApiParamType.STRING)
    private String groupName;
    @EntityField(name = "二级分组", type = ApiParamType.STRING)
    private String subGroup;
    @EntityField(name = "二级分组名", type = ApiParamType.STRING)
    private String subGroupName;
    @EntityField(name = "聚合方式", type = ApiParamType.STRING)
    private String statisticsType;
    @EntityField(name = "最大分组数量", type = ApiParamType.INTEGER)
    private Integer limitNum;
    @EntityField(name = "刷新时间", type = ApiParamType.INTEGER)
    private Integer refreshTime;
    @EntityField(name = "分组二次过滤", type = ApiParamType.JSONARRAY)
    private JSONArray configList;
    @EntityField(name = "将数据集提取map, 用于过滤，并按list 权重排序", type = ApiParamType.JSONOBJECT)
    private LinkedHashMap<String,Object> groupDataCountMap;
    @EntityField(name = "子sql，用于'累积总和（group_sum）'", type = ApiParamType.STRING)
    @JSONField(serialize = false)
    private String subSql;

    public DashboardWidgetChartConfigVo(){}
    public DashboardWidgetChartConfigVo(JSONObject configChart) {
        this.type = configChart.getString("type");
        this.group = configChart.getString("groupfield");
        this.subGroup = configChart.getString("subgroupfield");
        this.statisticsType = configChart.getString("aggregate");
        this.limitNum = configChart.getInteger("maxgroup");
        this.refreshTime = configChart.getInteger("refreshtime");
        this.configList = configChart.getJSONArray("configlist");
    }

    //TODO 临时转换，后续待前端修改后删除
    public JSONObject getConfig(){
        JSONObject config = new JSONObject();
        config.put("type",type);
        config.put("groupfield",group);
        config.put("groupfieldText",groupName);
        config.put("subgroupfield",subGroup);
        config.put("subgroupfieldText",subGroupName);
        config.put("aggregate",statisticsType);
        config.put("maxgroup",limitNum);
        config.put("refreshtime",refreshTime);
        config.put("configlist",configList);
        return config;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getSubGroup() {
        return subGroup;
    }

    public void setSubGroup(String subGroup) {
        this.subGroup = subGroup;
    }

    public LinkedHashMap<String, Object> getGroupDataCountMap() {
        return groupDataCountMap;
    }

    public void setGroupDataCountMap(LinkedHashMap<String, Object> groupDataCountMap) {
        this.groupDataCountMap = groupDataCountMap;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getSubGroupName() {
        return subGroupName;
    }

    public void setSubGroupName(String subGroupName) {
        this.subGroupName = subGroupName;
    }

    public String getStatisticsType() {
        return statisticsType;
    }

    public void setStatisticsType(String statisticsType) {
        this.statisticsType = statisticsType;
    }

    public Integer getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(Integer limitNum) {
        this.limitNum = limitNum;
    }

    public Integer getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(Integer refreshTime) {
        this.refreshTime = refreshTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JSONArray getConfigList() {
        return configList;
    }

    public void setConfigList(JSONArray configList) {
        this.configList = configList;
    }

    public String getSubSql() {
        return subSql;
    }

    public void setSubSql(String subSql) {
        this.subSql = subSql;
    }

}
