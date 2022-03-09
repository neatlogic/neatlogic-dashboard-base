/*
 * Copyright (c)  2022 TechSure Co.,Ltd.  All Rights Reserved.
 * 本内容仅限于深圳市赞悦科技有限公司内部传阅，禁止外泄以及用于其他的商业项目。
 */

package codedriver.framework.dashboard.handler;

import codedriver.framework.applicationlistener.core.ModuleInitializedListenerBase;
import codedriver.framework.bootstrap.CodedriverWebApplicationContext;
import codedriver.framework.common.RootComponent;
import codedriver.framework.dashboard.dto.DashboardHandlerVo;

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
	public void onInitialized(CodedriverWebApplicationContext context) {
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
