/*
 * Copyright (c)  2022 TechSure Co.,Ltd.  All Rights Reserved.
 * 本内容仅限于深圳市赞悦科技有限公司内部传阅，禁止外泄以及用于其他的商业项目。
 */

package codedriver.framework.dashboard.config;

import codedriver.framework.common.constvalue.dashboard.DashboardShowConfig;
import codedriver.framework.dashboard.dto.DashboardShowConfigVo;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Objects;

public abstract class DashboardWidgetShowConfigBase implements IDashboardWidgetShowConfig {
    @Override
    public JSONArray getShowConfig(JSONObject showConfigJson){
        JSONArray processTaskShowChartConfigArray = new JSONArray();
        for (DashboardShowConfig gs : DashboardShowConfig.values()) {
            JSONObject showConfig = showConfigJson.getJSONObject(gs.getValue());
            if(showConfig == null){
                continue;
            }
            DashboardShowConfigVo showConfigVo = showConfig.toJavaObject(DashboardShowConfigVo.class);
            if (Objects.equals(gs.getValue(), DashboardShowConfig.GROUPFIELD.getValue())) {
                showConfigVo.getDataList().addAll(getGroupFieldOptionListConfig());
            } else if (Objects.equals(gs.getValue(), DashboardShowConfig.SUBGROUPFIELD.getValue())) {
                showConfigVo.getDataList().addAll(getSubGroupFieldOptionListConfig());
            } else if (Objects.equals(gs.getValue(), DashboardShowConfig.AGGREGATE.getValue())) {
                showConfigVo.getDataList().addAll(getStatisticsOptionList());
            }
            processTaskShowChartConfigArray.add(showConfigVo);
        }
        return processTaskShowChartConfigArray;
    }

    @Override
    public JSONArray getStatisticsOptionList() {
        return new JSONArray();
    }
}
