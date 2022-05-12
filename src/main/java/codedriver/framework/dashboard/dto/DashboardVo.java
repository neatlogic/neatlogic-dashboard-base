/*
 * Copyright(c) 2022 TechSure Co., Ltd. All Rights Reserved.
 * 本内容仅限于深圳市赞悦科技有限公司内部传阅，禁止外泄以及用于其他的商业项目。
 */

package codedriver.framework.dashboard.dto;

import codedriver.framework.auth.core.AuthActionChecker;
import codedriver.framework.common.constvalue.ApiParamType;
import codedriver.framework.common.constvalue.GroupSearch;
import codedriver.framework.common.dto.BaseEditorVo;
import codedriver.framework.dto.AuthorityVo;
import codedriver.framework.restful.annotation.EntityField;
import codedriver.framework.util.SnowflakeUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

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

    public boolean isAdmin() {
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
        if (CollectionUtils.isEmpty(authList)) {
            authList = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(authorityList)) {
                for (AuthorityVo authorityVo : this.authorityList) {
                    if (authorityVo.getType().equals(GroupSearch.ROLE.getValue())) {
                        authList.add(GroupSearch.ROLE.getValuePlugin() + authorityVo.getUuid());
                    } else if (authorityVo.getType().equals(GroupSearch.USER.getValue())) {
                        authList.add(GroupSearch.USER.getValuePlugin() + authorityVo.getUuid());
                    } else if (authorityVo.getType().equals(GroupSearch.TEAM.getValue())) {
                        authList.add(GroupSearch.TEAM.getValuePlugin() + authorityVo.getUuid());
                    }
                }
            }
        }
        return authList;
    }

    public void setAuthList(List<String> authList) {
        this.authList = authList;
    }


    public List<AuthorityVo> getAuthorityList() {
        if (CollectionUtils.isEmpty(authorityList) && CollectionUtils.isNotEmpty(authList)) {
            authorityList = new ArrayList<>();
            for (String value : authList) {
                AuthorityVo authorityVo = new AuthorityVo();
                if (value.startsWith(GroupSearch.ROLE.getValuePlugin())) {
                    authorityVo.setType(GroupSearch.ROLE.getValue());
                    authorityVo.setUuid(value.replaceAll(GroupSearch.ROLE.getValuePlugin(), StringUtils.EMPTY));
                } else if (value.startsWith(GroupSearch.USER.getValuePlugin())) {
                    authorityVo.setType(GroupSearch.USER.getValue());
                    authorityVo.setUuid(value.replaceAll(GroupSearch.USER.getValuePlugin(), StringUtils.EMPTY));
                } else if (value.startsWith(GroupSearch.TEAM.getValuePlugin())) {
                    authorityVo.setType(GroupSearch.TEAM.getValue());
                    authorityVo.setUuid(value.replaceAll(GroupSearch.TEAM.getValuePlugin(), StringUtils.EMPTY));
                }
                authorityList.add(authorityVo);
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


}
