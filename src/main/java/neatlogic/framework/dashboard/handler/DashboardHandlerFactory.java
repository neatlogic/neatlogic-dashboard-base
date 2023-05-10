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

import neatlogic.framework.applicationlistener.core.ModuleInitializedListenerBase;
import neatlogic.framework.bootstrap.NeatLogicWebApplicationContext;
import neatlogic.framework.common.RootComponent;
import neatlogic.framework.dashboard.dto.DashboardHandlerVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:chenqiwei
 * @Time:Jun 7, 2020
 * @ClassName: DashboardHandlerFactory
 * @Description: 生产仪表板数据源处理类
 */
@RootComponent
public class DashboardHandlerFactory extends ModuleInitializedListenerBase {
	private static Map<String, IDashboardHandler> componentMap = new HashMap<>();
	private static List<DashboardHandlerVo> dashboardHandlerList = new ArrayList<>();

	public static IDashboardHandler getHandler(String handler) {
		return componentMap.get(handler);
	}

	public static List<DashboardHandlerVo> getDashboardHandlerList() {
		return dashboardHandlerList;
	}

	@Override
	public void onInitialized(NeatLogicWebApplicationContext context) {
		Map<String, IDashboardHandler> myMap = context.getBeansOfType(IDashboardHandler.class);
		for (Map.Entry<String, IDashboardHandler> entry : myMap.entrySet()) {
			IDashboardHandler component = entry.getValue();
			if (component.getClassName() != null) {
				componentMap.put(component.getClassName(), component);

				DashboardHandlerVo dashboardHandlerVo = new DashboardHandlerVo();
				dashboardHandlerVo.setHandler(component.getClassName());
				dashboardHandlerVo.setName(component.getName());
				dashboardHandlerVo.setDisplayName(component.getDisplayName());
				dashboardHandlerVo.setType(component.getType());
				dashboardHandlerVo.setModuleId(context.getId());
				dashboardHandlerVo.setIcon(component.getIcon());
				dashboardHandlerList.add(dashboardHandlerVo);
			}
		}
	}

	@Override
	protected void myInit() {
		// TODO Auto-generated method stub
		
	}
}
