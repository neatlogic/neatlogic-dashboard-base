package codedriver.framework.dashboard.charts.core;

import codedriver.framework.common.constvalue.dashboard.ChartType;
import codedriver.framework.common.constvalue.dashboard.DashboardShowConfig;
import codedriver.framework.dashboard.charts.DashboardChartBase;
import codedriver.framework.dashboard.dto.DashboardDataGroupVo;
import codedriver.framework.dashboard.dto.DashboardDataSubGroupVo;
import codedriver.framework.dashboard.dto.DashboardWidgetDataGroupVo;
import codedriver.framework.dashboard.dto.DashboardShowConfigVo;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class TableChart extends DashboardChartBase {

    @Override
    public String[] getSupportChart() {
        return new String[]{ChartType.TABLECHART.getValue()};
    }

    @Override
    public JSONObject getData(DashboardWidgetDataGroupVo dashboardDataVo) {
        JSONObject dataJson = new JSONObject();
        List<Map<String, Object>> dataList = new ArrayList<>();
        List<Map<String, String>> theadList = new ArrayList<>();
        List<Map<String, String>> columnList = new ArrayList<>();
        Map<String, Object> columnTypeMap = new HashMap<>();
        DashboardDataGroupVo dataGroupVo = dashboardDataVo.getDataGroupVo();
        DashboardDataSubGroupVo dataSubGroupVo = dashboardDataVo.getDataSubGroupVo();
        if (CollectionUtils.isNotEmpty(dataGroupVo.getDataList())) {
            List<String> theads = new ArrayList<>();
            List<String> columns = new ArrayList<>();
            for (Map<String, Object> dataMap : dataGroupVo.getDataList()) {
                Map<String, String> theadMap = new LinkedHashMap<>();
                Map<String, String> columnMap = new LinkedHashMap<>();
                if(!dataMap.containsKey(dataGroupVo.getPrimaryKey())){
                    continue;
                }
                String column = dataMap.get(dataGroupVo.getPrimaryKey()).toString();
                if (StringUtils.isNotBlank(column)) {
                    //columnList
                    String columnValue = dataMap.get(dataGroupVo.getProName()).toString();
                    if (!columns.contains(columnValue)) {
                        columnMap.put("displayName", columnValue);
                        columnMap.put("name", column);
                        columns.add(columnValue);
                        columnList.add(columnMap);
                    }
                    String displayName = StringUtils.EMPTY;
                    String name = StringUtils.EMPTY;
                    if (dataSubGroupVo != null) {
                        //拼接map column_type->value
                        if(!dataMap.containsKey(dataSubGroupVo.getPrimaryKey())){
                            continue;
                        }
                        String type = dataMap.get(dataSubGroupVo.getPrimaryKey()).toString();
                        if (StringUtils.isNotBlank(type)) {
                            columnTypeMap.put(column + "_" + type, dataMap.get("count"));
                        }
                        //theadList
                        String theadValue = dataMap.get(dataSubGroupVo.getProName()).toString();
                        if (!theads.contains(theadValue)) {
                            displayName = theadValue;
                            name = dataMap.get(dataSubGroupVo.getPrimaryKey()).toString();
                        }
                    } else {
                        displayName = name = "总数";
                        columnTypeMap.put(column, dataMap.get("count"));
                    }
                    if (StringUtils.isNotBlank(displayName) && !theads.contains(displayName)) {
                        theadMap.put("displayName", displayName);
                        theadMap.put("name", name);
                        theads.add(displayName);
                        theadList.add(theadMap);
                    }
                }
            }
        }
        //返回dataList
        if (CollectionUtils.isNotEmpty(columnList)) {
            for (Map<String, String> columnMap : columnList) {
                String column = columnMap.get("name");
                Map<String, Object> dataMap = new HashMap<>();
                if (dataSubGroupVo != null) {
                    for (Map<String, String> theadMap : theadList) {
                        String type = theadMap.get("name");
                        String count = String.valueOf(columnTypeMap.get(column + "_" + type) == null ? "0" : columnTypeMap.get(column + "_" + type));
                        if (StringUtils.isNotBlank(count)) {
                            dataMap.put(type, count);
                        }
                    }
				} else {
                    dataMap.put("总数", columnTypeMap.get(column));
				}
				dataList.add(dataMap);
			}
        }
        dataJson.put("dataList", dataList);
        dataJson.put("columnList", columnList);
        dataJson.put("theadList", theadList);
        return dataJson;
    }

    @Override
    public JSONObject getChartConfig() {
        JSONObject charConfig = new JSONObject();
        JSONObject showConfig = new JSONObject();
        showConfig.put(DashboardShowConfig.AGGREGATE.getValue(), new DashboardShowConfigVo(DashboardShowConfig.AGGREGATE, JSONArray.parseArray("[{'value':'count','text':'计数','isDefault':1}]")));
        showConfig.put(DashboardShowConfig.GROUPFIELD.getValue(), new DashboardShowConfigVo(DashboardShowConfig.GROUPFIELD, new JSONArray()));
        showConfig.put(DashboardShowConfig.SUBGROUPFIELD.getValue(), new DashboardShowConfigVo(DashboardShowConfig.SUBGROUPFIELD, new JSONArray()));
        showConfig.put(DashboardShowConfig.MAXGROUP.getValue(), new DashboardShowConfigVo(DashboardShowConfig.MAXGROUP, JSONArray.parseArray("[{'value':'10','text':'10','isDefault':1},{'value':'20','text':'20'}]")));
        showConfig.put(DashboardShowConfig.REFRESHTIME.getValue(), new DashboardShowConfigVo(DashboardShowConfig.REFRESHTIME, JSONArray.parseArray("[{'value':'-1','text':'不刷新','isDefault':1},{'value':'30','text':'30'}]")));
        charConfig.put("showConfig", showConfig);
        return charConfig;
    }
}
