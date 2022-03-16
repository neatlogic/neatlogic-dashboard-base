package codedriver.framework.dashboard.dto;

import codedriver.framework.common.constvalue.ApiParamType;
import codedriver.framework.restful.annotation.EntityField;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

public class DashboardDataVo {
	@EntityField(name = "数据结果list", type = ApiParamType.JSONARRAY)
	private List<DashboardWidgetDataVo> dataList;
	@EntityField(name = "table数据结果list", type = ApiParamType.JSONARRAY)
	private List<Map<String, Object>> tableDataList;
	@EntityField(name = "table 行头list", type = ApiParamType.JSONARRAY)
	private List<DashboardTableColumnTheadVo> tableColumnList;
	@EntityField(name = "table 列头list", type = ApiParamType.JSONARRAY)
	private List<DashboardTableColumnTheadVo> tableTheadList;
	@EntityField(name = "图表配置", type = ApiParamType.JSONOBJECT)
	private JSONObject configObj;
	@EntityField(name = "图表名称", type = ApiParamType.STRING)
	private String name;

	public DashboardDataVo() {
	}

	public DashboardDataVo(List<DashboardWidgetDataVo> dataList) {
		this.dataList = dataList;
	}

	public DashboardDataVo(List<Map<String, Object>> tableDataList, List<DashboardTableColumnTheadVo> tableColumnVoList, List<DashboardTableColumnTheadVo> tableTheadVoList) {
		this.tableDataList = tableDataList;
		this.tableColumnList = tableColumnVoList;
		this.tableTheadList = tableTheadVoList;
	}

	public List<DashboardWidgetDataVo> getDataList() {
		return dataList;
	}

	public void setDataList(List<DashboardWidgetDataVo> dataList) {
		this.dataList = dataList;
	}

	public JSONObject getConfigObj() {
		return configObj;
	}

	public void setConfigObj(JSONObject configObj) {
		this.configObj = configObj;
	}

	public List<Map<String, Object>> getTableDataList() {
		return tableDataList;
	}

	public void setTableDataList(List<Map<String, Object>> tableDataList) {
		this.tableDataList = tableDataList;
	}

	public List<DashboardTableColumnTheadVo> getTableColumnList() {
		return tableColumnList;
	}

	public void setTableColumnList(List<DashboardTableColumnTheadVo> tableColumnList) {
		this.tableColumnList = tableColumnList;
	}

	public List<DashboardTableColumnTheadVo> getTableTheadList() {
		return tableTheadList;
	}

	public void setTableTheadList(List<DashboardTableColumnTheadVo> tableTheadList) {
		this.tableTheadList = tableTheadList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
