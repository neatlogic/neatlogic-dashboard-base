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
