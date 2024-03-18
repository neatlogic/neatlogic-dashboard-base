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
import neatlogic.framework.dashboard.constvalue.IDashboardGroupField;

import java.util.List;

public interface IDashboardWidgetShowConfig {

    String getName();
    /**
     * 获取支持的chart类型
     * @return chart类型
     */
    String[] getSupportChart();

    /**
     * 获取分组选项字段
     * @return 分组选项字段
     */
    JSONArray getStatisticsOptionList();

    /**
     * 获取分组选项字段
     * @return 分组选项字段
     */
    List<IDashboardGroupField> getGroupFieldOptionList();

    /**
     * 获取分组选项配置，用于前端渲染分组
     * @return 分组选项配置
     */
    JSONArray getGroupFieldOptionListConfig();

    /**
     * 获取二级分组选项字段
     * @return 二级分组选项字段
     */
    List<IDashboardGroupField> getSubGroupFieldOptionList();

    /**
     * 获取二级分组选项配置，用于前端渲染二级分组
     * @return 二级分组选项配置
     */
    JSONArray getSubGroupFieldOptionListConfig();

    /**
     * 获取最终前端渲染配置
     * @param showConfig 默认前端渲染配置
     * @return 最终前端渲染配置
     */
    JSONArray getShowConfig(JSONObject showConfig);

    /**
     * 获取模块
     * @return 模块
     */
    String getModule();
}
