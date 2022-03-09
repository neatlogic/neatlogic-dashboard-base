/*
 * Copyright (c)  2022 TechSure Co.,Ltd.  All Rights Reserved.
 * 本内容仅限于深圳市赞悦科技有限公司内部传阅，禁止外泄以及用于其他的商业项目。
 */

package codedriver.framework.dashboard.handler;

import com.alibaba.fastjson.JSONObject;

import codedriver.framework.dashboard.dto.ChartDataVo;
import codedriver.framework.dashboard.dto.DashboardWidgetVo;

public abstract class DashboardHandlerBase implements IDashboardHandler {
	public final ChartDataVo getData(DashboardWidgetVo widgetVo) {
		ChartDataVo chartDataVo = new ChartDataVo();
		JSONObject dataJson = myGetData(widgetVo);
		chartDataVo.setData(dataJson);
		chartDataVo.setConfigObj(dataJson.getJSONObject("configObj"));
		return chartDataVo;
	}

	protected abstract JSONObject myGetData(DashboardWidgetVo widgetVo);
	
	public final JSONObject getConfig(DashboardWidgetVo widgetVo) {
		return myGetConfig(widgetVo);
	}
	
	protected abstract JSONObject myGetConfig(DashboardWidgetVo widgetVo);

	public String getDistinctCountColumnSql(){
		return null;
	}
}
