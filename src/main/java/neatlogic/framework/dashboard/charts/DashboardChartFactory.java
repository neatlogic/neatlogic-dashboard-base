/*Copyright (C) 2024  深圳极向量科技有限公司 All Rights Reserved.

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
