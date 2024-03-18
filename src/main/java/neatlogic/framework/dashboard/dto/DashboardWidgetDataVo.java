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

package neatlogic.framework.dashboard.dto;

import neatlogic.framework.common.constvalue.ApiParamType;
import neatlogic.framework.restful.annotation.EntityField;
import org.apache.commons.lang3.StringUtils;

public class DashboardWidgetDataVo {
    @EntityField(name = "总数", type = ApiParamType.STRING)
    private Object total;

    @EntityField(name = "分组值", type = ApiParamType.STRING)
    private Object value;

    @EntityField(name = "分组名", type = ApiParamType.STRING)
    private String column;

    @EntityField(name = "分组名 提示", type = ApiParamType.STRING)
    private String columnTitle = StringUtils.EMPTY;

    @EntityField(name = "类型", type = ApiParamType.STRING)
    private String type;

    @EntityField(name = "类型 提示", type = ApiParamType.STRING)
    private String typeTitle;

    public Object getTotal() {
        return total;
    }

    public void setTotal(Object total) {
        this.total = total;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColumnTitle() {
        return columnTitle;
    }

    public void setColumnTitle(String columnTitle) {
        this.columnTitle = columnTitle;
    }

    public String getTypeTitle() {
        return typeTitle;
    }

    public void setTypeTitle(String typeTitle) {
        this.typeTitle = typeTitle;
    }
}
