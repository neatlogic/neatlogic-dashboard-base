package codedriver.framework.dashboard.dto;

import java.util.List;
import java.util.Map;

/**
 * @Title: DashboardDataSubGroupVo
 * @Package: codedriver.module.report.dto
 * @Description: TODO
 * @Author: 89770
 * @Date: 2021/3/9 17:48
 * Copyright(c) 2021 TechSure Co., Ltd. All Rights Reserved.
 * 本内容仅限于深圳市赞悦科技有限公司内部传阅，禁止外泄以及用于其他的商业项目。
 **/
public class DashboardDataSubGroupVo {
    private String primaryKey;
    private String handleName;
    private String proName;
    private String proTitle;
    private List<Map<String,String>> dataList;

    public DashboardDataSubGroupVo() {
    }

    public DashboardDataSubGroupVo(String primaryKey, String handleName, String proName) {
        this.primaryKey = primaryKey;
        this.handleName = handleName;
        this.proName = proName;
    }

    public DashboardDataSubGroupVo(String primaryKey, String handleName, String proName,String proTitle) {
        this.primaryKey = primaryKey;
        this.handleName = handleName;
        this.proName = proName;
        this.proTitle = proTitle;
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

    public List<Map<String, String>> getDataList() {
        return dataList;
    }

    public void setDataList(List<Map<String, String>> dataList) {
        this.dataList = dataList;
    }

    public String getProTitle() {
        return proTitle;
    }

    public void setProTitle(String proTitle) {
        this.proTitle = proTitle;
    }
}
