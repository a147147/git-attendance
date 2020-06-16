package com.attendance.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 员工表
 */
public class CwaStaff implements Serializable {
    /** 员工id */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String sId;
    /** 员工姓名 */
    @Column
    private String sName;
    /** 员工联系方式 */
    @Column
    private String sPhone;
    /** 员工角色 */
    @Column
    private Long sRoleId;
    /** 员工密码 */
    @Column
    private String sAddress;

    @Transient
    private CwaRole role;
    @Transient
    private CwaCompany company;
    @Transient
    private CwaDepartment department;

    public CwaStaff() {
    }

    public CwaStaff(String sId) {
        this.sId = sId;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsPhone() {
        return sPhone;
    }

    public void setsPhone(String sPhone) {
        this.sPhone = sPhone;
    }

    public Long getsRoleId() {
        return sRoleId;
    }

    public void setsRoleId(Long sRoleId) {
        this.sRoleId = sRoleId;
    }

    public String getsAddress() {
        return sAddress;
    }

    public void setsAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    public CwaRole getRole() {
        return role;
    }

    public void setRole(CwaRole role) {
        this.role = role;
    }

    public CwaCompany getCompany() {
        return company;
    }

    public void setCompany(CwaCompany company) {
        this.company = company;
    }

    public CwaDepartment getDepartment() {
        return department;
    }

    public void setDepartment(CwaDepartment department) {
        this.department = department;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sId=").append(sId);
        sb.append(", sName=").append(sName);
        sb.append(", sPhone=").append(sPhone);
        sb.append(", sRoleId=").append(sRoleId);
        sb.append(", sAddress=").append(sAddress);
        sb.append("]");
        return sb.toString();
    }
}
