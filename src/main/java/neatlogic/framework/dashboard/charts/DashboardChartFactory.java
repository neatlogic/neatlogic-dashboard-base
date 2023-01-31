/*
 * Copyright(c) 2022 TechSure Co., Ltd. All Rights Reserved.
 * 本内容仅限于深圳市赞悦科技有限公司内部传阅，禁止外泄以及用于其他的商业项目。
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
