package codedriver.framework.dashboard.charts.core;

import codedriver.framework.common.constvalue.dashboard.ChartType;
import codedriver.framework.common.constvalue.dashboard.DashboardShowConfig;
import codedriver.framework.dashboard.charts.DashboardChartBase;
import codedriver.framework.dashboard.dto.DashboardDataVo;
import codedriver.framework.dashboard.dto.DashboardShowConfigVo;
import codedriver.framework.dashboard.dto.DashboardWidgetAllGroupDefineVo;
import codedriver.framework.dashboard.dto.DashboardWidgetDataVo;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

public class NumberChart extends DashboardChartBase {

    @Override
    public String[] getSupportChart() {
        return new String[]{ChartType.NUMBERCHART.getValue()};
    }

    @Override
    public DashboardDataVo getData(DashboardWidgetAllGroupDefineVo dashboardWidgetAllGroupDefineVo, List<Map<String, Object>> dbDataMapList) {
        List<DashboardWidgetDataVo> resultDataList = getDefaultData(dashboardWidgetAllGroupDefineVo, dbDataMapList);
        //多值图补充总数
        String type = dashboardWidgetAllGroupDefineVo.getChartConfigVo().getType();
        if (StringUtils.isNotBlank(type) && type.equals("many")) {
            int total = 0;
            for (DashboardWidgetDataVo widgetDataVo : resultDataList) {
                total += Integer.parseInt(widgetDataVo.getTotal().toString());
            }
            DashboardWidgetDataVo widgetTotalDataVo = new DashboardWidgetDataVo();
            widgetTotalDataVo.setTotal(total);
            widgetTotalDataVo.setColumn("总数");
            widgetTotalDataVo.setValue(total);
            resultDataList.add(0, widgetTotalDataVo);
        }
        return new DashboardDataVo(resultDataList);
    }

    @Override
    public JSONObject getChartConfig() {
        JSONObject charConfig = new JSONObject();
        JSONObject showConfig = new JSONObject();
        showConfig.put(DashboardShowConfig.TYPE.getValue(), new DashboardShowConfigVo(DashboardShowConfig.TYPE, JSONArray.parseArray("[{'value':'single','text':'单值','isDefault':1},{'value':'many','text':'多值'}]")));
        showConfig.put(DashboardShowConfig.AGGREGATE.getValue(), new DashboardShowConfigVo(DashboardShowConfig.AGGREGATE, JSONArray.parseArray("[{'value':'count','text':'计数','isDefault':1}]")));
        showConfig.put(DashboardShowConfig.GROUPFIELD.getValue(), new DashboardShowConfigVo(DashboardShowConfig.GROUPFIELD, new JSONArray()));
        showConfig.put(DashboardShowConfig.MAXGROUP.getValue(), new DashboardShowConfigVo(DashboardShowConfig.MAXGROUP, JSONArray.parseArray("[{'value':'10','text':'10','isDefault':1},{'value':'20','text':'20'}]")));
        showConfig.put(DashboardShowConfig.REFRESHTIME.getValue(), new DashboardShowConfigVo(DashboardShowConfig.REFRESHTIME, JSONArray.parseArray("[{'value':'-1','text':'不刷新','isDefault':1},{'value':'30','text':'30'}]")));
        charConfig.put("showConfig", showConfig);
        return charConfig;
    }
}
