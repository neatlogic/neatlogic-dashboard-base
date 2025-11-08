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
