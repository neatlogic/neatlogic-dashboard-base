/*
Copyright(c) 2023 NeatLogic Co., Ltd. All Rights Reserved.

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

import neatlogic.framework.dashboard.constvalue.IDashboardGroupField;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

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
