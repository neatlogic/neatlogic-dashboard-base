/*
 * Copyright (c)  2022 TechSure Co.,Ltd.  All Rights Reserved.
 * 本内容仅限于深圳市赞悦科技有限公司内部传阅，禁止外泄以及用于其他的商业项目。
 */

package codedriver.framework.dashboard.handler;

import codedriver.framework.dashboard.dto.ChartDataVo;
import codedriver.framework.dashboard.dto.DashboardWidgetVo;
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
	 ChartDataVo getData(DashboardWidgetVo widgetVo);

	/**
	 * @date Mar 2, 2020
	 * @description TODO 获取图表视图配置数据
	 * @param widgetVo widget配置
	 * @return JSONObject
	 */
	 JSONObject getConfig(DashboardWidgetVo widgetVo);

	 String getDistinctCountColumnSql();
	
}
