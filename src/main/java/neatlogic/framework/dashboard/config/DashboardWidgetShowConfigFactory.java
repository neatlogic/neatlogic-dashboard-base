/*Copyright (C) $today.year  深圳极向量科技有限公司 All Rights Reserved.

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.*/

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
