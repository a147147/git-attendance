package com.attendance.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

/**
 * 补卡表
 */
public class CwaRepair implements Serializable {
    /** 补卡id */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long repId;
    /** 员工id */
    @Column
    private String repStaffId;
    /** 部门id */
    @Column
    private Long repDepartmentId;
    /** 补卡日期 */
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="Asia/Shanghai")
    private Date repDate;
    /** 上班时间 */
    @Column
    private Time repTime;
    /** 补卡理由 */
    @Column
    private String repReason;
    /** 补卡状态 */
    @Column
    private String repFlag;
    /** 上班还是下班补卡 */
    @Column
    private String repState;
    /** 照片urls */
    @Column
    private String regImages;
    /** 领导意见 */
    @Column
    private String regView;

    @Transient
    private String staffName;

    public CwaRepair() {
    }

    public CwaRepair(String repStaffId) {
        this.repStaffId = repStaffId;
    }

    public Long getRepDateTime() {
        return repDate.getTime();
    }

    public Long getRepId() {
        return repId;
    }

    public void setRepId(Long repId) {
        this.repId = repId;
    }

    public String getRepStaffId() {
        return repStaffId;
    }

    public void setRepStaffId(String repStaffId) {
        this.repStaffId = repStaffId;
    }

    public Long getRepDepartmentId() {
        return repDepartmentId;
    }

    public void setRepDepartmentId(Long repDepartmentId) {
        this.repDepartmentId = repDepartmentId;
    }

    public Date getRepDate() {
        return repDate;
    }

    public void setRepDate(Date repDate) {
        this.repDate = repDate;
    }

    public Time getRepTime() {
        return repTime;
    }

    public void setRepTime(Time repTime) {
        this.repTime = repTime;
    }

    public String getRepReason() {
        return repReason;
    }

    public void setRepReason(String repReason) {
        this.repReason = repReason;
    }

    public String getRepFlag() {
        return repFlag;
    }

    public void setRepFlag(String repFlag) {
        this.repFlag = repFlag;
    }

    public String getRepState() {
        return repState;
    }

    public void setRepState(String repState) {
        this.repState = repState;
    }

    public String getRegImages() {
        return regImages;
    }

    public void setRegImages(String regImages) {
        this.regImages = regImages;
    }

    public String getRegView() {
        return regView;
    }

    public void setRegView(String regView) {
        this.regView = regView;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    @Override
    public String toString() {
        return "CwaRepair{" +
                "repId=" + repId +
                ", repStaffId='" + repStaffId + '\'' +
                ", repDepartmentId=" + repDepartmentId +
                ", repDate=" + repDate +
                ", repTime=" + repTime +
                ", repReason='" + repReason + '\'' +
                ", repFlag='" + repFlag + '\'' +
                ", repState='" + repState + '\'' +
                ", regImages='" + regImages + '\'' +
                ", regView='" + regView + '\'' +
                ", staffName='" + staffName + '\'' +
                '}';
    }
}
