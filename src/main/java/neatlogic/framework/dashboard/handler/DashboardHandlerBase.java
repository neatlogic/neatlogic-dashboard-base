/*
 * Copyright (c)  2022 TechSure Co.,Ltd.  All Rights Reserved.
 * 本内容仅限于深圳市赞悦科技有限公司内部传阅，禁止外泄以及用于其他的商业项目。
 */

package neatlogic.framework.dashboard.handler;

import com.alibaba.fastjson.JSONObject;

import neatlogic.framework.dashboard.dto.DashboardDataVo;
import neatlogic.framework.dashboard.dto.DashboardWidgetVo;

public abstract class DashboardHandlerBase implements IDashboardHandler {
	public final DashboardDataVo getData(DashboardWidgetVo widgetVo) {
		return myGetData(widgetVo);
	}

	protected abstract DashboardDataVo myGetData(DashboardWidgetVo widgetVo);
	
	public final JSONObject getConfig(DashboardWidgetVo widgetVo) {
		return myGetConfig(widgetVo);
	}
	
	protected abstract JSONObject myGetConfig(DashboardWidgetVo widgetVo);

	public String getDistinctCountColumnSql(){
		return null;
	}
}
