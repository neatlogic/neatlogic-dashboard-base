package codedriver.framework.dashboard.charts;

import codedriver.framework.dashboard.dto.DashboardDataGroupVo;
import codedriver.framework.dashboard.dto.DashboardDataSubGroupVo;
import codedriver.framework.dashboard.dto.DashboardWidgetDataGroupVo;
import codedriver.framework.dashboard.dto.DashboardWidgetDataVo;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

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
    public JSONObject getData(DashboardWidgetDataGroupVo dashboardDataVo) {
        return getMyData(dashboardDataVo);
    }

    public JSONObject getMyData(DashboardWidgetDataGroupVo dashboardDataVo) {
        JSONObject dataJson = new JSONObject();
        dataJson.put("dataList", getDefaultData(dashboardDataVo));
        return dataJson;
    }

    /**
     * @Description: 支持 普遍chart的数据处理
     * @Author: 89770
     * @Date: 2021/3/12 15:01
     * @Params: [dashboardDataVo]
     * @Returns: com.alibaba.fastjson.JSONObject
     **/
    protected List<DashboardWidgetDataVo> getDefaultData(DashboardWidgetDataGroupVo dashboardDataGroupVo) {
        List<DashboardWidgetDataVo> resultDataList = new ArrayList<>();
        DashboardDataGroupVo dataGroupVo = dashboardDataGroupVo.getDataGroupVo();
        DashboardDataSubGroupVo dataSubGroupVo = dashboardDataGroupVo.getDataSubGroupVo();
        if (CollectionUtils.isNotEmpty(dataGroupVo.getDataList())) {
            Map<String, Object> groupDataCountMap = dataGroupVo.getDataCountMap();
            //循环获取需要的字段数据
            for (Map<String, Object> dataMap : dataGroupVo.getDataList()) {
                Iterator<Map.Entry<String, Object>> iterator = dataMap.entrySet().iterator();
                DashboardWidgetDataVo dashboardWidgetDataVo = new DashboardWidgetDataVo();
                //如果不包含primaryKey 或 存在值为null 的列，则废弃该数据
                if (!dataMap.containsKey(dataGroupVo.getPrimaryKey()) || dataMap.containsValue(null)) {
                    continue;
                }
                while (iterator.hasNext()) {
                    Map.Entry<String, Object> entry = iterator.next();
                    String key = entry.getKey();
                    String value = String.valueOf(entry.getValue());
                    //如果是分组
                    if (dataGroupVo.getPrimaryKey().equals(key)) {
                        if (dataSubGroupVo != null) {
                            dashboardWidgetDataVo.setTotal(groupDataCountMap.get(value).toString());
                        } else {
                            dashboardWidgetDataVo.setTotal(dataMap.get("count").toString());
                        }
                    }
                    if (dataGroupVo.getProName().equals(key)) {
                        dashboardWidgetDataVo.setColumn(value);
                    }

                    if (StringUtils.isNotBlank(dataGroupVo.getProTitle()) && dataGroupVo.getProTitle().equals(key)) {
                        dashboardWidgetDataVo.setColumnTitle(value);
                    }
                    //如果是子分组
                    if (dataSubGroupVo != null && dataSubGroupVo.getProName().equals(key)) {
                        dashboardWidgetDataVo.setType(value);
                    }
                    if (dataSubGroupVo != null && StringUtils.isNotBlank(dataSubGroupVo.getProTitle()) && dataSubGroupVo.getProTitle().equals(key)) {
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
