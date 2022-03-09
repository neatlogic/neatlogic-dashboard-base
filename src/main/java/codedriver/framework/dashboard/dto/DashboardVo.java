/*
 * Copyright(c) 2021 TechSure Co., Ltd. All Rights Reserved.
 * 本内容仅限于深圳市赞悦科技有限公司内部传阅，禁止外泄以及用于其他的商业项目。
 */

package codedriver.framework.dashboard.dto;

import codedriver.framework.common.constvalue.ApiParamType;
import codedriver.framework.common.constvalue.GroupSearch;
import codedriver.framework.common.dto.BaseEditorVo;
import codedriver.framework.common.dto.BasePageVo;
import codedriver.framework.dto.AuthorityVo;
import codedriver.framework.restful.annotation.EntityField;
import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class DashboardVo extends BaseEditorVo {
    @EntityField(name = "仪表板uuid", type = ApiParamType.STRING)
    private String uuid;
    @EntityField(name = "仪表板名称", type = ApiParamType.STRING)
    private String name;
    @EntityField(name = "是否激活", type = ApiParamType.INTEGER)
    private int isActive;
    @EntityField(name = "是否系统默认面板", type = ApiParamType.INTEGER)
    private int isSystemDefault;
    @EntityField(name = "是否个人默认面板", type = ApiParamType.INTEGER)
    private int isCustomDefault;
    @EntityField(name = "描述", type = ApiParamType.STRING)
    private String description;
    @EntityField(name = "仪表板组件列表", type = ApiParamType.JSONOBJECT)
    private List<DashboardWidgetVo> widgetList;
    @EntityField(name = "system：系统分类  custom：自定义分类", type = ApiParamType.STRING)
    private String type = "custom";
    @EntityField(name = "授权列表", type = ApiParamType.STRING)
    private List<String> valueList;
    @EntityField(name = "默认用户", type = ApiParamType.STRING)
    private String defaultUser;
    @EntityField(name = "默认用户", type = ApiParamType.STRING)
    private String defaultType;
    @EntityField(name = "是否拥有编辑权限", type = ApiParamType.JSONARRAY)
    private Integer isCanEdit;
    @EntityField(name = "是否拥有授权权限", type = ApiParamType.JSONARRAY)
    private Integer isCanRole;
    @JSONField(serialize = false)
    private List<AuthorityVo> authorityList;

    //params
    private String userUuid;
    private List<String> teamUuidList;
    private List<String> roleUuidList;
    private Integer isMine;

    public String getUuid() {
        if (StringUtils.isBlank(uuid)) {
            uuid = UUID.randomUUID().toString().replace("-", "");
        }
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public List<DashboardWidgetVo> getWidgetList() {
        return widgetList;
    }

    public void setWidgetList(List<DashboardWidgetVo> widgetList) {
        this.widgetList = widgetList;
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

    public int getIsSystemDefault() {
        return isSystemDefault;
    }

    public void setIsSystemDefault(int isSystemDefault) {
        this.isSystemDefault = isSystemDefault;
    }

    public int getIsCustomDefault() {
        return isCustomDefault;
    }

    public void setIsCustomDefault(int isCustomDefault) {
        this.isCustomDefault = isCustomDefault;
    }

    public List<String> getValueList() {
        if (CollectionUtils.isEmpty(valueList)) {
            valueList = new ArrayList<String>();
            if (CollectionUtils.isNotEmpty(authorityList)) {
                for (AuthorityVo authorityVo : this.authorityList) {
                    if (authorityVo.getType().equals(GroupSearch.ROLE.getValue())) {
                        valueList.add(GroupSearch.ROLE.getValuePlugin() + authorityVo.getUuid());
                    } else if (authorityVo.getType().equals(GroupSearch.USER.getValue())) {
                        valueList.add(GroupSearch.USER.getValuePlugin() + authorityVo.getUuid());
                    } else if (authorityVo.getType().equals(GroupSearch.TEAM.getValue())) {
                        valueList.add(GroupSearch.TEAM.getValuePlugin() + authorityVo.getUuid());
                    }
                }
            }
        }
        return valueList;
    }

    public void setValueList(List<String> valueList) {
        this.valueList = valueList;
    }

    public String getDefaultUser() {
        return defaultUser;
    }

    public void setDefaultUser(String defaultUser) {
        this.defaultUser = defaultUser;
    }

    public String getDefaultType() {
        return defaultType;
    }

    public void setDefaultType(String defaultType) {
        this.defaultType = defaultType;
    }

    public Integer getIsCanEdit() {
        return isCanEdit;
    }

    public void setIsCanEdit(Integer isCanEdit) {
        this.isCanEdit = isCanEdit;
    }

    public Integer getIsCanRole() {
        return isCanRole;
    }

    public void setIsCanRole(Integer isCanRole) {
        this.isCanRole = isCanRole;
    }

    public List<AuthorityVo> getAuthorityList() {
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
        return isMine;
    }

    public void setIsMine(Integer isMine) {
        this.isMine = isMine;
    }


    public enum DashBoardType {
        SYSTEM("system", "系统分类"), CUSTOM("custom", "自定义");
        private String value;
        private String name;

        private DashBoardType(String _value, String _name) {
            this.value = _value;
            this.name = _name;
        }

        public String getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

        public static String getValue(String _value) {
            for (DashBoardType s : DashBoardType.values()) {
                if (s.getValue().equals(_value)) {
                    return s.getValue();
                }
            }
            return null;
        }

        public static String getName(String _value) {
            for (DashBoardType s : DashBoardType.values()) {
                if (s.getValue().equals(_value)) {
                    return s.getName();
                }
            }
            return "";
        }

    }
}
