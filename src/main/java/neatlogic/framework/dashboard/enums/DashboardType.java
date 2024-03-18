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

package neatlogic.framework.dashboard.enums;

import neatlogic.framework.util.$;

public enum DashboardType {
    SYSTEM("system", "系统分类"), CUSTOM("custom", "自定义");
    private final String value;
    private final String name;

    DashboardType(String _value, String _name) {
        this.value = _value;
        this.name = _name;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return $.t(name);
    }

    public static String getValue(String _value) {
        for (DashboardType s : DashboardType.values()) {
            if (s.getValue().equals(_value)) {
                return s.getValue();
            }
        }
        return null;
    }

    public static String getName(String _value) {
        for (DashboardType s : DashboardType.values()) {
            if (s.getValue().equals(_value)) {
                return s.getName();
            }
        }
        return "";
    }

}
