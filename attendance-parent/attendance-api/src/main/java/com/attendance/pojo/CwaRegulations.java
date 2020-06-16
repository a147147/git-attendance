package com.attendance.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Time;

/**
 * 考勤规定表
 */
public class CwaRegulations implements Serializable {
    /** 考勤规定id */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long regId;
    /** 上班时间 */
    @Column
    private Time regStartTime;
    /** 下班时间  */
    @Column
    private Time regEndTime;
    /** 公司id  */
    @Column
    private Long regCompanyId;
    /**  部门id */
    @Column
    private Long regDepartmentId;
    /** 考勤地图上的地址 */
    @Column
    private String regMapAddress;
    /** 考勤范围 */
    @Column
    private Integer regScope;
    /** 补卡次数 */
    @Column
    private Integer regRepairCount;
    /** 周一(1：上班，0：放假) */
    @Column
    private Boolean regMon;
    /** 周二(1：上班，0：放假) */
    @Column
    private Boolean regTues;
    /** 周三(1：上班，0：放假) */
    @Column
    private Boolean regWed;
    /** 周四(1：上班，0：放假) */
    @Column
    private Boolean regThur;
    /** 周五(1：上班，0：放假) */
    @Column
    private Boolean regFri;
    /** 周六(1：上班，0：放假) */
    @Column
    private Boolean regSat;
    /** 周日(1：上班，0：放假) */
    @Column
    private Boolean regSun;

    private static final long serialVersionUID = 1L;

    public CwaRegulations() {
    }

    public CwaRegulations(Long regCompanyId) {
        this.regCompanyId = regCompanyId;
    }

    public CwaRegulations(Long regCompanyId, Long regDepartmentId) {
        this.regCompanyId = regCompanyId;
        this.regDepartmentId = regDepartmentId;
    }

    public Long getRegId() {
        return regId;
    }

    public void setRegId(Long regId) {
        this.regId = regId;
    }

    public Time getRegStartTime() {
        return regStartTime;
    }

    public void setRegStartTime(Time regStartTime) {
        this.regStartTime = regStartTime;
    }

    public Time getRegEndTime() {
        return regEndTime;
    }

    public void setRegEndTime(Time regEndTime) {
        this.regEndTime = regEndTime;
    }

    public Long getRegCompanyId() {
        return regCompanyId;
    }

    public void setRegCompanyId(Long regCompanyId) {
        this.regCompanyId = regCompanyId;
    }

    public Long getRegDepartmentId() {
        return regDepartmentId;
    }

    public void setRegDepartmentId(Long regDepartmentId) {
        this.regDepartmentId = regDepartmentId;
    }

    public String getRegMapAddress() {
        return regMapAddress;
    }

    public void setRegMapAddress(String regMapAddress) {
        this.regMapAddress = regMapAddress;
    }

    public Integer getRegScope() {
        return regScope;
    }

    public void setRegScope(Integer regScope) {
        this.regScope = regScope;
    }

    public Integer getRegRepairCount() {
        return regRepairCount;
    }

    public void setRegRepairCount(Integer regRepairCount) {
        this.regRepairCount = regRepairCount;
    }

    public Boolean getRegMon() {
        return regMon;
    }

    public void setRegMon(Boolean regMon) {
        this.regMon = regMon;
    }

    public Boolean getRegTues() {
        return regTues;
    }

    public void setRegTues(Boolean regTues) {
        this.regTues = regTues;
    }

    public Boolean getRegWed() {
        return regWed;
    }

    public void setRegWed(Boolean regWed) {
        this.regWed = regWed;
    }

    public Boolean getRegThur() {
        return regThur;
    }

    public void setRegThur(Boolean regThur) {
        this.regThur = regThur;
    }

    public Boolean getRegFri() {
        return regFri;
    }

    public void setRegFri(Boolean regFri) {
        this.regFri = regFri;
    }

    public Boolean getRegSat() {
        return regSat;
    }

    public void setRegSat(Boolean regSat) {
        this.regSat = regSat;
    }

    public Boolean getRegSun() {
        return regSun;
    }

    public void setRegSun(Boolean regSun) {
        this.regSun = regSun;
    }

    @Override
    public String toString() {
        return "CwaRegulations{" +
                "regId=" + regId +
                ", regStartTime=" + regStartTime +
                ", regEndTime=" + regEndTime +
                ", regCompanyId=" + regCompanyId +
                ", regDepartmentId=" + regDepartmentId +
                ", regMapAddress='" + regMapAddress + '\'' +
                ", regScope=" + regScope +
                ", regRepairCount=" + regRepairCount +
                ", regMon=" + regMon +
                ", regTues=" + regTues +
                ", regWed=" + regWed +
                ", regThur=" + regThur +
                ", regFri=" + regFri +
                ", regSat=" + regSat +
                ", regSun=" + regSun +
                '}';
    }
}
