package neatlogic.framework.dashboard.dto;

/**
 * @Title: DashboardDataSubGroupVo
 * @Package: neatlogic.module.report.dto
 * @Description: TODO
 * @Author: 89770
 * @Date: 2021/3/9 17:48
 **/
public class DashboardWidgetGroupDefineVo {
    private String primaryKey;
    private String handleName;
    private String proName;
    private String proTitle;

    public DashboardWidgetGroupDefineVo() {
    }

    public DashboardWidgetGroupDefineVo(String primaryKey, String handleName, String proName) {
        this.primaryKey = primaryKey;
        this.handleName = handleName;
        this.proName = proName;
    }

    public DashboardWidgetGroupDefineVo(String primaryKey, String handleName, String proName, String proTitle) {
        this.primaryKey = primaryKey;
        this.handleName = handleName;
        this.proName = proName;
        this.proTitle = proTitle;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getHandleName() {
        return handleName;
    }

    public void setHandleName(String handleName) {
        this.handleName = handleName;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProTitle() {
        return proTitle;
    }

    public void setProTitle(String proTitle) {
        this.proTitle = proTitle;
    }
}
