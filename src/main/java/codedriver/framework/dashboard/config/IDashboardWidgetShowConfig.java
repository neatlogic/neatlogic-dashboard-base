/*
 * Copyright (c)  2022 TechSure Co.,Ltd.  All Rights Reserved.
 * 本内容仅限于深圳市赞悦科技有限公司内部传阅，禁止外泄以及用于其他的商业项目。
 */

package codedriver.framework.dashboard.config;

import codedriver.framework.dashboard.constvalue.IDashboardGroupField;
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
