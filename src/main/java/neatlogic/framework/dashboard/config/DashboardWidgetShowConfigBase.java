/*Copyright (C) 2023  深圳极向量科技有限公司 All Rights Reserved.

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.*/

package neatlogic.framework.dashboard.config;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import neatlogic.framework.common.constvalue.dashboard.DashboardShowConfig;
import neatlogic.framework.dashboard.dto.DashboardShowConfigVo;

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
