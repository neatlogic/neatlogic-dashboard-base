/*
 *
 * Copyright (C) 2025  TechSure Co., Ltd.  All Rights Reserved.
 * This file is part of the NeatLogic software.
 * Licensed under the NeatLogic Sustainable Use License (NSUL), Version 4.x – 2025.
 * You may use this file only in compliance with the License.
 * See the LICENSE file distributed with this work for the full license text.
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 */

package neatlogic.module.dashboard.auth.label;

import neatlogic.framework.auth.core.AuthBase;
import neatlogic.framework.auth.core.AuthModule;
import neatlogic.framework.auth.label.DATA_WAREHOUSE_BASE;

import java.util.Collections;
import java.util.List;

/** 权限名称与说明使用国际化键，权限标识及校验规则保持不变。 */
@AuthModule("dashboard")
public class DASHBOARD_BASE extends AuthBase {

    @Override
    public String getAuthDisplayName() {
        return "auth.dashboard_base.name";
    }

    @Override
    public String getAuthIntroduction() {
        return "auth.dashboard_base.description";
    }

    @Override
    public String getAuthGroup() {
        return "dashboard";
    }

    @Override
    public Integer getSort() {
        return 1;
    }

    @Override
    public List<Class<? extends AuthBase>> getIncludeAuths() {
        return Collections.singletonList(DATA_WAREHOUSE_BASE.class);
    }
}
