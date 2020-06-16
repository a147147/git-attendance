package com.attendance.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 部门管理员表
 */
public class CwaDepartmentAdmin implements Serializable {
    /** 部门员工表id */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer daId;
    /** 公司id */
    @Column
    private Long daCompanyId;
    /** 部门id */
    @Column
    private Long daDepartmentId;
    /** 员工id */
    @Column
    private String daAdminId;

    public CwaDepartmentAdmin() {
    }

    public CwaDepartmentAdmin(Long daCompanyId) {
        this.daCompanyId = daCompanyId;
    }

    public CwaDepartmentAdmin(Long daCompanyId, Long daDepartmentId) {
        this.daCompanyId = daCompanyId;
        this.daDepartmentId = daDepartmentId;
    }

    public Integer getDaId() {
        return daId;
    }

    public void setDaId(Integer daId) {
        this.daId = daId;
    }

    public Long getDaCompanyId() {
        return daCompanyId;
    }

    public void setDaCompanyId(Long daCompanyId) {
        this.daCompanyId = daCompanyId;
    }

    public Long getDaDepartmentId() {
        return daDepartmentId;
    }

    public void setDaDepartmentId(Long daDepartmentId) {
        this.daDepartmentId = daDepartmentId;
    }

    public String getDaAdminId() {
        return daAdminId;
    }

    public void setDaAdminId(String daAdminId) {
        this.daAdminId = daAdminId;
    }

    @Override
    public String toString() {
        return "CwaDepartmentAdmin{" +
                "daId=" + daId +
                ", daCompanyId=" + daCompanyId +
                ", daDepartmentId=" + daDepartmentId +
                ", daAdminId='" + daAdminId + '\'' +
                '}';
    }
}
