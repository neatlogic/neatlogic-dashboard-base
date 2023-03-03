/*
Copyright(c) $today.year NeatLogic Co., Ltd. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
 */

package neatlogic.framework.dashboard.config;

import neatlogic.framework.common.constvalue.dashboard.DashboardShowConfig;
import neatlogic.framework.dashboard.dto.DashboardShowConfigVo;
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
