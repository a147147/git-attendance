package com.attendance.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 部门员工表
 */
public class CwaDepartmentStaff implements Serializable {
    /** 部门员工表id */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dsId;
    /** 公司id */
    @Column
    private Long dsCompanyId;
    /** 部门id */
    @Column
    private Long dsDepartmentId;
    /** 员工id */
    @Column
    private String dsStaffId;

    public CwaDepartmentStaff() {
    }

    public CwaDepartmentStaff(Long dsCompanyId) {
        this.dsCompanyId = dsCompanyId;
    }

    public CwaDepartmentStaff(Long dsCompanyId, Long dsDepartmentId) {
        this.dsCompanyId = dsCompanyId;
        this.dsDepartmentId = dsDepartmentId;
    }

    public CwaDepartmentStaff(Long dsCompanyId, Long dsDepartmentId, String dsStaffId) {
        this.dsCompanyId = dsCompanyId;
        this.dsDepartmentId = dsDepartmentId;
        this.dsStaffId = dsStaffId;
    }

    public Integer getDsId() {
        return dsId;
    }

    public void setDsId(Integer dsId) {
        this.dsId = dsId;
    }

    public Long getDsCompanyId() {
        return dsCompanyId;
    }

    public void setDsCompanyId(Long dsCompanyId) {
        this.dsCompanyId = dsCompanyId;
    }

    public Long getDsDepartmentId() {
        return dsDepartmentId;
    }

    public void setDsDepartmentId(Long dsDepartmentId) {
        this.dsDepartmentId = dsDepartmentId;
    }

    public String getDsStaffId() {
        return dsStaffId;
    }

    public void setDsStaffId(String dsStaffId) {
        this.dsStaffId = dsStaffId;
    }

    @Override
    public String toString() {
        return "CwaDepartmentStaff{" +
                "dsId=" + dsId +
                ", dsCompanyId=" + dsCompanyId +
                ", dsDepartmentId=" + dsDepartmentId +
                ", dsStaffId=" + dsStaffId +
                '}';
    }
}
