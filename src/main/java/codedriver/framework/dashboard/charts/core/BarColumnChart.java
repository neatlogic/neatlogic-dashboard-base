package codedriver.framework.dashboard.charts.core;

import codedriver.framework.common.constvalue.dashboard.ChartType;
import codedriver.framework.common.constvalue.dashboard.DashboardShowConfig;
import codedriver.framework.dashboard.constvalue.DashboardStatistics;
import codedriver.framework.dashboard.charts.DashboardChartBase;
import codedriver.framework.dashboard.dto.DashboardShowConfigVo;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class BarColumnChart extends DashboardChartBase {

    @Override
    public String[] getSupportChart() {
        return new String[]{ChartType.BARCHART.getValue(), ChartType.STACKBARCHART.getValue(), ChartType.COLUMNCHART.getValue(), ChartType.STACKCOLUMNCHART.getValue()};
    }


    @Override
    public JSONObject getChartConfig() {
        JSONObject charConfig = new JSONObject();
        JSONObject showConfig = new JSONObject();
        showConfig.put(DashboardShowConfig.TYPE.getValue(), new DashboardShowConfigVo(DashboardShowConfig.TYPE, JSONArray.parseArray("[{'value':'bar','text':'横向'},{'value':'column','text':'纵向','isDefault':1}]")));
        showConfig.put(DashboardShowConfig.AGGREGATE.getValue(), new DashboardShowConfigVo(DashboardShowConfig.AGGREGATE, JSONArray.parseArray(
                String.format("[{'value':'%s','text':'%s','isDefault':1,'unit':''}]", DashboardStatistics.COUNT.getValue(), DashboardStatistics.COUNT.getText())
        )));
        showConfig.put(DashboardShowConfig.GROUPFIELD.getValue(), new DashboardShowConfigVo(DashboardShowConfig.GROUPFIELD, new JSONArray()));
        showConfig.put(DashboardShowConfig.SUBGROUPFIELD.getValue(), new DashboardShowConfigVo(DashboardShowConfig.SUBGROUPFIELD, new JSONArray()));
        showConfig.put(DashboardShowConfig.MAXGROUP.getValue(), new DashboardShowConfigVo(DashboardShowConfig.MAXGROUP, JSONArray.parseArray("[{'value':'10','text':'10','isDefault':1},{'value':'20','text':'20'}]")));
        showConfig.put(DashboardShowConfig.REFRESHTIME.getValue(), new DashboardShowConfigVo(DashboardShowConfig.REFRESHTIME, JSONArray.parseArray("[{'value':'-1','text':'不刷新','isDefault':1},{'value':'30','text':'30'}]")));
        charConfig.put("showConfig", showConfig);
        return charConfig;
    }
}
