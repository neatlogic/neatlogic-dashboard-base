package codedriver.framework.dashboard.dto;

import codedriver.framework.common.constvalue.ApiParamType;
import codedriver.framework.restful.annotation.EntityField;

/**
 * @Title: DashboardDataVo
 * @Package: codedriver.module.report.dto
 * @Description: TODO
 * @Author: 89770
 * @Date: 2021/3/9 17:47
 * Copyright(c) 2021 TechSure Co., Ltd. All Rights Reserved.
 * 本内容仅限于深圳市赞悦科技有限公司内部传阅，禁止外泄以及用于其他的商业项目。
 **/
public class DashboardWidgetDataGroupVo {
    @EntityField(name = "分组数据vo", type = ApiParamType.STRING)
    private DashboardDataGroupVo dataGroupVo;
    @EntityField(name = "二级分组数据vo", type = ApiParamType.STRING)
    private DashboardDataSubGroupVo dataSubGroupVo;
    @EntityField(name = "配置", type = ApiParamType.STRING)
    private DashboardWidgetChartConfigVo chartConfigVo;

    public DashboardDataGroupVo getDataGroupVo() {
        return dataGroupVo;
    }

    public void setDataGroupVo(DashboardDataGroupVo dataGroupVo) {
        this.dataGroupVo = dataGroupVo;
    }

    public DashboardDataSubGroupVo getDataSubGroupVo() {
        return dataSubGroupVo;
    }

    public void setDataSubGroupVo(DashboardDataSubGroupVo dataSubGroupVo) {
        this.dataSubGroupVo = dataSubGroupVo;
    }

    public DashboardWidgetChartConfigVo getChartConfigVo() {
        return chartConfigVo;
    }

    public void setChartConfigVo(DashboardWidgetChartConfigVo chartConfigVo) {
        this.chartConfigVo = chartConfigVo;
    }
}
