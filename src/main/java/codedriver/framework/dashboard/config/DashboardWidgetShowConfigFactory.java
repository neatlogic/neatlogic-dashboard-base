/*
 * Copyright (c)  2022 TechSure Co.,Ltd.  All Rights Reserved.
 * 本内容仅限于深圳市赞悦科技有限公司内部传阅，禁止外泄以及用于其他的商业项目。
 */

package codedriver.framework.dashboard.config;

import codedriver.framework.applicationlistener.core.ModuleInitializedListenerBase;
import codedriver.framework.bootstrap.CodedriverWebApplicationContext;
import codedriver.framework.common.RootComponent;

import java.util.HashMap;
import java.util.Map;

@RootComponent
public class DashboardWidgetShowConfigFactory extends ModuleInitializedListenerBase {
    private static final Map<String, IDashboardWidgetShowConfig> chartMap = new HashMap<>();

    public static IDashboardWidgetShowConfig getChart(String chartType, String module, String chartDataSourceName) {
        return chartMap.get(chartType + "_" + module + "_" + chartDataSourceName);
    }

    @Override
    protected void onInitialized(CodedriverWebApplicationContext context) {
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
