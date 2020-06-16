package com.attendance.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

/**
 * 考勤记录表
 */
public class CwaRecord implements Serializable {
    /** 员工id */
    @Id
    @Column
    private String recStaffId;
    /** 考勤日期 */
    @Id
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="Asia/Shanghai")
    private Date recDate;
    /** 上班打卡时间 */
    @Column
    private Time recStartTime;
    /** 上班打卡地址 */
    @Column
    private String recStartMap;
    /** 上班打卡地址 */
    @Column
    private String recStartAddress;
    /** 上班打卡状态 */
    @Column
    private String recStartFlag;
    /** 下班打卡时间 */
    @Column
    private Time recEndTime;
    /** 下班打卡地址 */
    @Column
    private String recEndMap;
    /** 下班打卡地址 */
    @Column
    private String recEndAddress;
    /** 下班打卡状态 */
    @Column
    private String recEndFlag;

    @Transient
    private CwaStaff staff;

    private static final long serialVersionUID = 1L;

    public CwaRecord() {
    }

    public CwaRecord(String recStaffId) {
        this.recStaffId = recStaffId;
    }

    public CwaRecord(String recStaffId, Date recDate) {
        this.recStaffId = recStaffId;
        this.recDate = recDate;
    }

    public String getRecStaffId() {
        return recStaffId;
    }

    public void setRecStaffId(String recStaffId) {
        this.recStaffId = recStaffId;
    }

    public Date getRecDate() {
        return recDate;
    }

    public void setRecDate(Date recDate) {
        this.recDate = recDate;
    }

    public Time getRecStartTime() {
        return recStartTime;
    }

    public void setRecStartTime(Time recStartTime) {
        this.recStartTime = recStartTime;
    }

    public Time getRecEndTime() {
        return recEndTime;
    }

    public void setRecEndTime(Time recEndTime) {
        this.recEndTime = recEndTime;
    }

    public String getRecStartMap() {
        return recStartMap;
    }

    public void setRecStartMap(String recStartMap) {
        this.recStartMap = recStartMap;
    }

    public String getRecEndMap() {
        return recEndMap;
    }

    public void setRecEndMap(String recEndMap) {
        this.recEndMap = recEndMap;
    }

    public String getRecStartFlag() {
        return recStartFlag;
    }

    public void setRecStartFlag(String recStartFlag) {
        this.recStartFlag = recStartFlag;
    }

    public String getRecEndFlag() {
        return recEndFlag;
    }

    public void setRecEndFlag(String recEndFlag) {
        this.recEndFlag = recEndFlag;
    }

    public CwaStaff getStaff() {
        return staff;
    }

    public void setStaff(CwaStaff staff) {
        this.staff = staff;
    }

    public String getRecStartAddress() {
        return recStartAddress;
    }

    public void setRecStartAddress(String recStartAddress) {
        this.recStartAddress = recStartAddress;
    }

    public String getRecEndAddress() {
        return recEndAddress;
    }

    public void setRecEndAddress(String recEndAddress) {
        this.recEndAddress = recEndAddress;
    }

    @Override
    public String toString() {
        return "CwaRecord{" +
                "recStaffId='" + recStaffId + '\'' +
                ", recDate=" + recDate +
                ", recStartTime=" + recStartTime +
                ", recStartMap='" + recStartMap + '\'' +
                ", recStartAddress='" + recStartAddress + '\'' +
                ", recStartFlag='" + recStartFlag + '\'' +
                ", recEndTime=" + recEndTime +
                ", recEndMap='" + recEndMap + '\'' +
                ", recEndAddress='" + recEndAddress + '\'' +
                ", recEndFlag='" + recEndFlag + '\'' +
                ", staff=" + staff +
                '}';
    }
}
