package codedriver.framework.dashboard.dto;

import com.alibaba.fastjson.JSONObject;

import codedriver.framework.common.constvalue.ApiParamType;
import codedriver.framework.restful.annotation.EntityField;

public class ChartDataVo {
	@EntityField(name = "数据集", type = ApiParamType.JSONARRAY)
	private JSONObject data;
	@EntityField(name = "图表配置", type = ApiParamType.JSONOBJECT)
	private JSONObject configObj;

	public JSONObject getData() {
		return data;
	}

	public void setData(JSONObject data) {
		this.data = data;
	}

	public JSONObject getConfigObj() {
		return configObj;
	}

	public void setConfigObj(JSONObject configObj) {
		this.configObj = configObj;
	}
	
}
