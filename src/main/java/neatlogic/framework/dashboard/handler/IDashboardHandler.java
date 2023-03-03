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

package neatlogic.framework.dashboard.handler;

import neatlogic.framework.dashboard.dto.DashboardDataVo;
import neatlogic.framework.dashboard.dto.DashboardWidgetVo;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.ClassUtils;

public interface IDashboardHandler {

	 String getType();

	 default String getClassName() {
		return ClassUtils.getUserClass(this.getClass()).getName();
	}

	/**
	 * @date Mar 2, 2020
	 * @description 获取唯一名
	 * @return String
	 */
	 String getName();
	
	/**
	* @author chenqiwei
	* @date Mar 20, 2020
	* @Description: 获取图标 
	* @return String
	 */
	 String getIcon();
	
	/**
	* @author chenqiwei
	* @date Mar 20, 2020
	* @Description: 获取显示名 
	* @return String
	 */
	 String getDisplayName();

	/**
	 * @date Mar 2, 2020
	 * @description 获取数据
	 * @param widgetVo widget配置
	 * @return ChartDataVo
	 */
	 DashboardDataVo getData(DashboardWidgetVo widgetVo);

	/**
	 * @date Mar 2, 2020
	 * @description TODO 获取图表视图配置数据
	 * @param widgetVo widget配置
	 * @return JSONObject
	 */
	 JSONObject getConfig(DashboardWidgetVo widgetVo);

	 String getDistinctCountColumnSql();
	
}
