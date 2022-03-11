package codedriver.framework.dashboard.dto;

import codedriver.framework.common.constvalue.ApiParamType;
import codedriver.framework.restful.annotation.EntityField;

import java.util.LinkedHashMap;

/**
 * @Title: DashboardDataVo
 * @Package: codedriver.module.report.dto
 * @Description: TODO
 * @Author: 89770
 * @Date: 2021/3/9 17:47
 * Copyright(c) 2021 TechSure Co., Ltd. All Rights Reserved.
 * 本内容仅限于深圳市赞悦科技有限公司内部传阅，禁止外泄以及用于其他的商业项目。
 **/
public class DashboardWidgetAllGroupDefineVo {
    @EntityField(name = "分组数据vo", type = ApiParamType.JSONOBJECT)
    private DashboardWidgetGroupDefineVo groupDefineVo;
    @EntityField(name = "二级分组数据vo", type = ApiParamType.JSONOBJECT)
    private DashboardWidgetGroupDefineVo subGroupDefineVo;
    @EntityField(name = "配置", type = ApiParamType.JSONOBJECT)
    private DashboardWidgetChartConfigVo chartConfigVo;
    @EntityField(name = "一级分组搜索结果转换后的map，用于获取total ", type = ApiParamType.STRING)
    private LinkedHashMap<String, Object> dbExchangeGroupDataMap;

    public DashboardWidgetGroupDefineVo getGroupDefineVo() {
        return groupDefineVo;
    }

    public void setGroupDefineVo(DashboardWidgetGroupDefineVo groupDefineVo) {
        this.groupDefineVo = groupDefineVo;
    }

    public DashboardWidgetGroupDefineVo getSubGroupDefineVo() {
        return subGroupDefineVo;
    }

    public void setSubGroupDefineVo(DashboardWidgetGroupDefineVo subGroupDefineVo) {
        this.subGroupDefineVo = subGroupDefineVo;
    }

    public DashboardWidgetChartConfigVo getChartConfigVo() {
        return chartConfigVo;
    }

    public void setChartConfigVo(DashboardWidgetChartConfigVo chartConfigVo) {
        this.chartConfigVo = chartConfigVo;
    }

    public LinkedHashMap<String, Object> getDbExchangeGroupDataMap() {
        return dbExchangeGroupDataMap;
    }

    public void setDbExchangeGroupDataMap(LinkedHashMap<String, Object> dbExchangeGroupDataMap) {
        this.dbExchangeGroupDataMap = dbExchangeGroupDataMap;
    }
}
