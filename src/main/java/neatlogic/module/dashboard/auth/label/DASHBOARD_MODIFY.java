package neatlogic.module.dashboard.auth.label;

import neatlogic.framework.auth.core.AuthBase;

import java.util.Collections;
import java.util.List;

/** 权限名称与说明使用国际化键，权限标识及校验规则保持不变。 */
public class DASHBOARD_MODIFY extends AuthBase {

    @Override
    public String getAuthDisplayName() {
        return "auth.dashboard_modify.name";
    }

    @Override
    public String getAuthIntroduction() {
        return "auth.dashboard_modify.description";
    }

    @Override
    public String getAuthGroup() {
        return "dashboard";
    }

    @Override
    public Integer getSort() {
        return 2;
    }

    @Override
    public List<Class<? extends AuthBase>> getIncludeAuths() {
        return Collections.singletonList(DASHBOARD_BASE.class);
    }
}
