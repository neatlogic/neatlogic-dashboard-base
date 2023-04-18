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

package neatlogic.framework.dashboard.dto;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.annotation.JSONField;
import neatlogic.framework.auth.core.AuthActionChecker;
import neatlogic.framework.common.constvalue.ApiParamType;
import neatlogic.framework.common.dto.BaseEditorVo;
import neatlogic.framework.dto.AuthorityVo;
import neatlogic.framework.restful.annotation.EntityField;
import neatlogic.framework.util.SnowflakeUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DashboardVo extends BaseEditorVo {
    @EntityField(name = "id", type = ApiParamType.LONG)
    private Long id;
    @EntityField(name = "仪表板名称", type = ApiParamType.STRING)
    private String name;
    @EntityField(name = "是否激活", type = ApiParamType.INTEGER)
    private int isActive;
    @EntityField(name = "描述", type = ApiParamType.STRING)
    private String description;
    @EntityField(name = "仪表板组件列表", type = ApiParamType.JSONARRAY)
    private JSONArray widgetList;
    @JSONField(serialize = false)
    private String widgetListStr;
    @EntityField(name = "system：系统分类  custom：自定义分类", type = ApiParamType.STRING)
    private String type = "custom";
    @EntityField(name = "授权列表", type = ApiParamType.STRING)
    private List<String> authList;
    @JSONField(serialize = false)
    private List<AuthorityVo> authorityList;
    @JSONField(serialize = false)
    private String searchType;//搜索类型,all或mine
    @JSONField(serialize = false)
    private boolean isAdmin;//是否管理员

    //params
    private String userUuid;
    private List<String> teamUuidList;
    private List<String> roleUuidList;
    private Integer isMine;
    private JSONArray datasourceInfoList;// 数据源（包含字段、条件字段）的id名称信息，结构形如：[{"conditionList":[{"name":"stepUserUuid","id":625917067845634}],"name":"processTaskStepUserDaySum","id":625917067845632,"fieldList":[{"name":"count","id":625917067845636},{"name":"stepEveryday","id":625918846230754}],"widgetUuid":"8a292f264d7f4a3d996ad7d392469157"}]


    public Long getId() {
        if (id == null) {
            id = SnowflakeUtil.uniqueLong();
        }
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsAdmin() {
        return AuthActionChecker.check("DASHBOARD_MODIFY");
    }


    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public List<String> getAuthList() {
        if (CollectionUtils.isEmpty(authList) && CollectionUtils.isNotEmpty(authorityList)) {
            authList = authorityList.stream().map(a -> a.getType() + "#" + a.getUuid()).collect(Collectors.toList());
        }
        return authList;
    }

    public void setAuthList(List<String> authList) {
        this.authList = authList;
    }


    public List<AuthorityVo> getAuthorityList() {
        if (CollectionUtils.isEmpty(authorityList) && CollectionUtils.isNotEmpty(authList)) {
            authorityList = new ArrayList<>();
            for (String authorityStr : authList) {
                String[] authorityArray = authorityStr.split("#");
                authorityList.add(new AuthorityVo(authorityArray[0], authorityArray[1]));
            }
        }
        return authorityList;
    }

    public void setAuthorityList(List<AuthorityVo> authorityList) {
        this.authorityList = authorityList;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public List<String> getTeamUuidList() {
        return teamUuidList;
    }

    public void setTeamUuidList(List<String> teamUuidList) {
        this.teamUuidList = teamUuidList;
    }

    public List<String> getRoleUuidList() {
        return roleUuidList;
    }

    public void setRoleUuidList(List<String> roleUuidList) {
        this.roleUuidList = roleUuidList;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public Integer getIsMine() {
        if (StringUtils.isNotBlank(searchType)) {
            if (searchType.equalsIgnoreCase("all")) {
                return 0;
            } else if (searchType.equalsIgnoreCase("mine")) {
                return 1;
            }
        }
        return 0;
    }


    public JSONArray getWidgetList() {
        if (widgetList == null && StringUtils.isNotBlank(widgetListStr)) {
            try {
                widgetList = JSONArray.parseArray(widgetListStr);
            } catch (Exception ignored) {

            }
        }
        return widgetList;
    }

    public void setWidgetList(JSONArray widgetList) {
        this.widgetList = widgetList;
    }

    public String getWidgetListStr() {
        if (widgetList != null) {
            widgetListStr = widgetList.toString();
        }
        return widgetListStr;
    }

    public void setWidgetListStr(String widgetListStr) {
        this.widgetListStr = widgetListStr;
    }

    public JSONArray getDatasourceInfoList() {
        return datasourceInfoList;
    }

    public void setDatasourceInfoList(JSONArray datasourceInfoList) {
        this.datasourceInfoList = datasourceInfoList;
    }
}
