package com.yyyu.ssh.dao.bean;

import java.util.List;

/**
 * 功能：
 *
 * @author yu
 * @date 2017/10/24.
 */
public class RoleDataTablesReturn {

    private Integer recordsTotal;
    private Integer recordsFiltered;
    private Integer draw;
    private List<RoleReturn> data;

    public RoleDataTablesReturn() {
    }

    public Integer getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Integer recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Integer getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Integer recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public List<RoleReturn> getData() {
        return data;
    }

    public void setData(List<RoleReturn> data) {
        this.data = data;
    }
}
