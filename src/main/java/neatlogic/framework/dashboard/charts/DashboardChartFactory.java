/*
 * Copyright(c) 2023 NeatLogic Co., Ltd. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package neatlogic.framework.dashboard.charts;

import org.reflections.Reflections;

import java.util.*;

public class DashboardChartFactory {
    private static final Map<String, DashboardChartBase> chartMap = new HashMap<>();
    private static final List<DashboardChartBase> charList = new ArrayList<>();

    static {
        Reflections reflections = new Reflections("neatlogic.framework.dashboard.charts.core");
        Set<Class<? extends DashboardChartBase>> modules = reflections.getSubTypesOf(DashboardChartBase.class);
        for (Class<? extends DashboardChartBase> c : modules) {
            DashboardChartBase chart;
            try {
                chart = c.newInstance();
                for (String ch : chart.getSupportChart()) {
                    chartMap.put(ch, chart);
                    charList.add(chart);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public static DashboardChartBase getChart(String type) {
		return chartMap.get(type);
	}
	
	public static List<DashboardChartBase> getDashboardChartList() {
		return charList;
	}
}
