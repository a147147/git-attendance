package com.attendance.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 部门表
 */
public class CwaDepartment implements Serializable {
    /** 部门id */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dId;
    /** 公司id */
    @Column
    private Long dCompanyId;
    /** 部门名 */
    @Column
    private String dName;

    public CwaDepartment() {
    }

    public CwaDepartment(Long dId,Long dCompanyId) {
        this.dId = dId;
        this.dCompanyId = dCompanyId;
    }

    public CwaDepartment(Long dId) {
        this.dId = dId;
    }

    public Long getdId() {
        return dId;
    }

    public void setdId(Long dId) {
        this.dId = dId;
    }

    public Long getdCompanyId() {
        return dCompanyId;
    }

    public void setdCompanyId(Long dCompanyId) {
        this.dCompanyId = dCompanyId;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", dId=").append(dId);
        sb.append(", dCompanyId=").append(dCompanyId);
        sb.append(", dName=").append(dName);
        sb.append("]");
        return sb.toString();
    }
}
