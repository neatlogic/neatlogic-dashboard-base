package neatlogic.framework.dashboard.charts.core;

import neatlogic.framework.common.constvalue.dashboard.ChartType;
import neatlogic.framework.common.constvalue.dashboard.DashboardShowConfig;
import neatlogic.framework.dashboard.charts.DashboardChartBase;
import neatlogic.framework.dashboard.dto.*;
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
    public DashboardDataVo getMyData(DashboardWidgetAllGroupDefineVo dashboardDataVo, List<Map<String, Object>> dbDataMapList) {
        List<Map<String, Object>> dataList = new ArrayList<>();
        Map<String, Object> columnTypeMap = new HashMap<>();
        List<DashboardTableColumnTheadVo> tableTheadVoList = new ArrayList<>();
        List<DashboardTableColumnTheadVo> tableColumnVoList = new ArrayList<>();
        DashboardWidgetGroupDefineVo dataGroupDefineVo = dashboardDataVo.getGroupDefineVo();
        DashboardWidgetGroupDefineVo dataSubGroupDefineVo = dashboardDataVo.getSubGroupDefineVo();
        if (CollectionUtils.isNotEmpty(dbDataMapList)) {
            for (Map<String, Object> dataMap : dbDataMapList) {
                if(!dataMap.containsKey(dataGroupDefineVo.getPrimaryKey())){
                    continue;
                }
                String column = dataMap.get(dataGroupDefineVo.getPrimaryKey()).toString();
                if (StringUtils.isNotBlank(column)) {
                    //columnList
                    String columnValue = dataMap.get(dataGroupDefineVo.getProName()).toString();
                    if (tableColumnVoList.stream().noneMatch(o->Objects.equals(o.getDisplayName(),columnValue))) {
                        tableColumnVoList.add(new DashboardTableColumnTheadVo(column,columnValue));
                    }
                    String displayName;
                    String name;
                    if (dataSubGroupDefineVo != null) {
                        //拼接map column_type->value
                        if(!dataMap.containsKey(dataSubGroupDefineVo.getPrimaryKey())){
                            continue;
                        }
                        String type = dataMap.get(dataSubGroupDefineVo.getPrimaryKey()).toString();
                        if (StringUtils.isNotBlank(type)) {
                            columnTypeMap.put(column + "_" + type, dataMap.get("count"));
                        }
                        //theadList
                        displayName = dataMap.get(dataSubGroupDefineVo.getProName()).toString();
                        name = dataMap.get(dataSubGroupDefineVo.getPrimaryKey()).toString();
                    } else {
                        displayName = "总数";
                        name = "totalCount";
                        columnTypeMap.put(column, dataMap.get("count"));
                    }
                    if (tableTheadVoList.stream().noneMatch(o->Objects.equals(o.getDisplayName(),displayName))) {
                        tableTheadVoList.add(new DashboardTableColumnTheadVo(name,displayName));
                    }
                }
            }
        }
        //返回dataList
        if (CollectionUtils.isNotEmpty(tableColumnVoList)) {
            for (DashboardTableColumnTheadVo columnVo : tableColumnVoList) {
                String column = columnVo.getName();
                Map<String, Object> dataResultMap = new HashMap<>();
                if (dataSubGroupDefineVo != null) {
                    for (DashboardTableColumnTheadVo theadVo : tableTheadVoList) {
                        String type = theadVo.getName();
                        String count = String.valueOf(columnTypeMap.get(column + "_" + type) == null ? "0" : columnTypeMap.get(column + "_" + type));
                        if (StringUtils.isNotBlank(count)) {
                            dataResultMap.put(type, count);
                        }
                    }
				} else {
                    dataResultMap.put("totalCount", columnTypeMap.get(column));
				}
				dataList.add(dataResultMap);
			}
        }
        return  new DashboardDataVo(dataList,tableColumnVoList,tableTheadVoList);
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
