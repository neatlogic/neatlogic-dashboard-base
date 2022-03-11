package codedriver.framework.dashboard.charts;

import codedriver.framework.dashboard.dto.DashboardDataVo;
import codedriver.framework.dashboard.dto.DashboardWidgetAllGroupDefineVo;
import codedriver.framework.dashboard.dto.DashboardWidgetDataVo;
import codedriver.framework.dashboard.dto.DashboardWidgetGroupDefineVo;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class DashboardChartBase {
    /**
     * @param @return
     * @return String
     * @Author: chenqiwei
     * @Time:Mar 20, 2020
     * @Description: 要和dashboard_widget表chart_type字段枚举值一致
     */
    public abstract String[] getSupportChart();

    /**
     * @param @return
     * @return JSONObject
     * @Description: 返回数据
     */
    public DashboardDataVo getData(DashboardWidgetAllGroupDefineVo dashboardWidgetAllGroupDefineVo, List<Map<String, Object>> dbDataMapList) {
        DashboardDataVo dashboardDataVo = getMyData(dashboardWidgetAllGroupDefineVo,dbDataMapList);
        dashboardDataVo.setConfigObj(dashboardWidgetAllGroupDefineVo.getChartConfigVo().getConfig());
        return dashboardDataVo;
    }

    public DashboardDataVo getMyData(DashboardWidgetAllGroupDefineVo dashboardWidgetAllGroupDefineVo,List<Map<String, Object>> dbDataMapList) {
        return new DashboardDataVo(getDefaultData(dashboardWidgetAllGroupDefineVo,dbDataMapList));
    }

    /**
     *
     * @param dashboardWidgetAllGroupDefineVo dashboard 一级分组和二级分组的映射声明
     * @param dbDataMapList 从数据库查回来的数据
     * @return
     */
    protected List<DashboardWidgetDataVo> getDefaultData(DashboardWidgetAllGroupDefineVo dashboardWidgetAllGroupDefineVo,List<Map<String, Object>> dbDataMapList) {
        List<DashboardWidgetDataVo> resultDataList = new ArrayList<>();
        DashboardWidgetGroupDefineVo dataGroupDefineVo = dashboardWidgetAllGroupDefineVo.getGroupDefineVo();
        DashboardWidgetGroupDefineVo dataSubGroupDefinedVo = dashboardWidgetAllGroupDefineVo.getSubGroupDefineVo();
        if (CollectionUtils.isNotEmpty(dbDataMapList)) {
            Map<String, Object> groupDataCountMap = dashboardWidgetAllGroupDefineVo.getDbExchangeGroupDataMap();
            //循环获取需要的字段数据
            for (Map<String, Object> dataMap : dbDataMapList) {
                Iterator<Map.Entry<String, Object>> iterator = dataMap.entrySet().iterator();
                DashboardWidgetDataVo dashboardWidgetDataVo = new DashboardWidgetDataVo();
                //如果不包含primaryKey 或 存在值为null 的列，则废弃该数据
                if (!dataMap.containsKey(dataGroupDefineVo.getPrimaryKey()) || dataMap.containsValue(null)) {
                    continue;
                }
                while (iterator.hasNext()) {
                    Map.Entry<String, Object> entry = iterator.next();
                    String key = entry.getKey();
                    String value = String.valueOf(entry.getValue());
                    //如果是分组
                    if (dataGroupDefineVo.getPrimaryKey().equals(key)) {
                        if (dataSubGroupDefinedVo != null) {
                            dashboardWidgetDataVo.setTotal(groupDataCountMap.get(value).toString());
                        } else {
                            dashboardWidgetDataVo.setTotal(dataMap.get("count").toString());
                        }
                    }
                    if (dataGroupDefineVo.getProName().equals(key)) {
                        dashboardWidgetDataVo.setColumn(value);
                    }

                    if (StringUtils.isNotBlank(dataGroupDefineVo.getProTitle()) && dataGroupDefineVo.getProTitle().equals(key)) {
                        dashboardWidgetDataVo.setColumnTitle(value);
                    }
                    //如果是子分组
                    if (dataSubGroupDefinedVo != null && dataSubGroupDefinedVo.getProName().equals(key)) {
                        dashboardWidgetDataVo.setType(value);
                    }
                    if (dataSubGroupDefinedVo != null && StringUtils.isNotBlank(dataSubGroupDefinedVo.getProTitle()) && dataSubGroupDefinedVo.getProTitle().equals(key)) {
                        dashboardWidgetDataVo.setTypeTitle(value);
                    }

                    if ("count".equals(key)) {
                        dashboardWidgetDataVo.setValue(dataMap.get("count").toString());
                    }
                }
                resultDataList.add(dashboardWidgetDataVo);
            }
        }
        return resultDataList;
    }

    /**
     * @param @return
     * @return JSONObject
     * @Description: 回显规则
     */
    public abstract JSONObject getChartConfig();


}
