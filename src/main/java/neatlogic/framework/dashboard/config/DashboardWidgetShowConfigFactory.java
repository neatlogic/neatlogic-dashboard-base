/*
 *
 * Copyright (C) 2025  TechSure Co., Ltd.  All Rights Reserved.
 * This file is part of the NeatLogic software.
 * Licensed under the NeatLogic Sustainable Use License (NSUL), Version 4.x – 2025.
 * You may use this file only in compliance with the License.
 * See the LICENSE file distributed with this work for the full license text.
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 */

package neatlogic.framework.dashboard.config;

import neatlogic.framework.applicationlistener.core.ModuleInitializedListenerBase;
import neatlogic.framework.bootstrap.NeatLogicWebApplicationContext;
import neatlogic.framework.common.RootComponent;

import java.util.HashMap;
import java.util.Map;

@RootComponent
public class DashboardWidgetShowConfigFactory extends ModuleInitializedListenerBase {
    private static final Map<String, IDashboardWidgetShowConfig> chartMap = new HashMap<>();

    public static IDashboardWidgetShowConfig getChart(String chartType, String module, String chartDataSourceName) {
        return chartMap.get(chartType + "_" + module + "_" + chartDataSourceName);
    }

    @Override
    protected void onInitialized(NeatLogicWebApplicationContext context) {
        Map<String, IDashboardWidgetShowConfig> myMap = context.getBeansOfType(IDashboardWidgetShowConfig.class);
        for (Map.Entry<String, IDashboardWidgetShowConfig> entry : myMap.entrySet()) {
            IDashboardWidgetShowConfig chartCustom = entry.getValue();
            String[] chartTypes = chartCustom.getSupportChart();
            for (String chartType : chartTypes) {
                chartMap.put(chartType + "_" + chartCustom.getModule() + "_" + chartCustom.getName(), chartCustom);
            }
        }
    }

    @Override
    protected void myInit() {

    }
}
