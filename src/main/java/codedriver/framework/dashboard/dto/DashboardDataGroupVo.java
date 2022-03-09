package codedriver.framework.dashboard.dto;

import codedriver.framework.common.constvalue.ApiParamType;
import codedriver.framework.restful.annotation.EntityField;

import java.util.List;
import java.util.Map;

/**
 * @Title: DashboardDataGroupVo
 * @Package: codedriver.module.report.dto
 * @Description: TODO
 * @Author: 89770
 * @Date: 2021/3/9 17:48
 * Copyright(c) 2021 TechSure Co., Ltd. All Rights Reserved.
 * 本内容仅限于深圳市赞悦科技有限公司内部传阅，禁止外泄以及用于其他的商业项目。
 **/
public class DashboardDataGroupVo {
    @EntityField(name = "主键key", type = ApiParamType.STRING)
    private String primaryKey;
    @EntityField(name = "组件名", type = ApiParamType.STRING)
    private String handleName;
    @EntityField(name = "属性名", type = ApiParamType.STRING)
    private String proName;
    @EntityField(name = "属性 提示", type = ApiParamType.STRING)
    private String proTitle;
    private List<Map<String, Object>> dataList;
    private Map<String, Object> dataCountMap;

    public DashboardDataGroupVo() {
    }

    public DashboardDataGroupVo(String primaryKey, String handleName, String proName, Map<String, Object> dataCountMap) {
        this.primaryKey = primaryKey;
        this.handleName = handleName;
        this.proName = proName;
        this.dataCountMap = dataCountMap;
    }

    public DashboardDataGroupVo(String primaryKey, String handleName, String proName,String proTitle, Map<String, Object> dataCountMap) {
        this.primaryKey = primaryKey;
        this.handleName = handleName;
        this.proName = proName;
        this.proTitle = proTitle;
        this.dataCountMap = dataCountMap;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getHandleName() {
        return handleName;
    }

    public void setHandleName(String handleName) {
        this.handleName = handleName;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public List<Map<String, Object>> getDataList() {
        return dataList;
    }

    public void setDataList(List<Map<String, Object>> dataList) {
        this.dataList = dataList;
    }

    public Map<String, Object> getDataCountMap() {
        return dataCountMap;
    }

    public String getProTitle() {
        return proTitle;
    }

    public void setProTitle(String proTitle) {
        this.proTitle = proTitle;
    }

    public void setDataCountMap(Map<String, Object> dataCountMap) {
        this.dataCountMap = dataCountMap;
    }
}
