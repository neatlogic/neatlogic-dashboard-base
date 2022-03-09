/*
 * Copyright (c)  2022 TechSure Co.,Ltd.  All Rights Reserved.
 * 本内容仅限于深圳市赞悦科技有限公司内部传阅，禁止外泄以及用于其他的商业项目。
 */

package codedriver.framework.dashboard.constvalue;

public enum DashboardStatistics implements IDashboardGroupField{
    COUNT("count","计数"),
    SUM("sum","总数");

    private final String value;
    private final String text;

    DashboardStatistics(String _value, String _text){
        value = _value;
        text = _text;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getText() {
        return text;
    }

    public static String getValue(String _value) {
        for (DashboardStatistics s : DashboardStatistics.values()) {
            if (s.getValue().equals(_value)) {
                return s.getValue();
            }
        }
        return null;
    }
}
