/*Copyright (C) 2023  深圳极向量科技有限公司 All Rights Reserved.

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

package neatlogic.framework.dashboard.constvalue;

import neatlogic.framework.util.$;

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
        return $.t(text);
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
