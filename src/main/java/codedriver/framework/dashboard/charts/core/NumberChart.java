package codedriver.framework.dashboard.charts.core;

import codedriver.framework.common.constvalue.dashboard.ChartType;
import codedriver.framework.common.constvalue.dashboard.DashboardShowConfig;
import codedriver.framework.dashboard.charts.DashboardChartBase;
import codedriver.framework.dashboard.dto.DashboardShowConfigVo;
import codedriver.framework.dashboard.dto.DashboardWidgetDataGroupVo;
import codedriver.framework.dashboard.dto.DashboardWidgetDataVo;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class NumberChart extends DashboardChartBase {

	@Override
	public String[] getSupportChart() {
		return new String[] {ChartType.NUMBERCHART.getValue()};
	}

	@Override
	public JSONObject getData(DashboardWidgetDataGroupVo dashboardDataVo) {
		JSONObject dataJson = new JSONObject();
		List<DashboardWidgetDataVo> resultDataList = getDefaultData(dashboardDataVo);
		//多值图补充总数
		String type = dashboardDataVo.getChartConfigVo().getType();
		if(StringUtils.isNotBlank(type) && type.equals("many")) {
			int total = 0;
			for (DashboardWidgetDataVo widgetDataVo : resultDataList) {
						total += Long.parseLong(widgetDataVo.getTotal());
			}
			DashboardWidgetDataVo widgetTotalDataVo = new DashboardWidgetDataVo();
			widgetTotalDataVo.setTotal(Integer.toString(total));
			widgetTotalDataVo.setColumn("总数");
			widgetTotalDataVo.setValue(Integer.toString(total));
			resultDataList.add(0, widgetTotalDataVo);
		}
		dataJson.put("dataList", resultDataList);
		return dataJson;
	}

	@Override
	public JSONObject getChartConfig() {
		JSONObject charConfig = new JSONObject();
		JSONObject showConfig = new JSONObject();
		showConfig.put(DashboardShowConfig.TYPE.getValue(),new DashboardShowConfigVo(DashboardShowConfig.TYPE,JSONArray.parseArray("[{'value':'single','text':'单值','isDefault':1},{'value':'many','text':'多值'}]")));
		showConfig.put(DashboardShowConfig.AGGREGATE.getValue(),new DashboardShowConfigVo(DashboardShowConfig.AGGREGATE,JSONArray.parseArray("[{'value':'count','text':'计数','isDefault':1}]")));
		showConfig.put(DashboardShowConfig.GROUPFIELD.getValue(),new DashboardShowConfigVo(DashboardShowConfig.GROUPFIELD,new JSONArray()));
		showConfig.put(DashboardShowConfig.MAXGROUP.getValue(),new DashboardShowConfigVo(DashboardShowConfig.MAXGROUP,JSONArray.parseArray("[{'value':'10','text':'10','isDefault':1},{'value':'20','text':'20'}]")));
		showConfig.put(DashboardShowConfig.REFRESHTIME.getValue(),new DashboardShowConfigVo(DashboardShowConfig.REFRESHTIME,JSONArray.parseArray("[{'value':'-1','text':'不刷新','isDefault':1},{'value':'30','text':'30'}]")));
		charConfig.put("showConfig", showConfig);
		return charConfig;
	}
}
