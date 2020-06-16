package com.attendance.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Date;

public class RecordVO implements Serializable {
    /** 员工id */
    private String staffId;
    /** 公司id */
    private Long companyId;
    /** 部门id */
    private Long departmentId;
    /** 考勤日期 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="Asia/Shanghai")
    private Date date;
    /** 考勤地址 */
    private String map;
    /** 考勤地址 */
    private String address;
    /** 是否上班还是下班 */
    private String startOrEnd;
    /** 是否外出考勤 */
    private String outWork;
    /** 是否迟到或早退 */
    private String lateOrEarly;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getStartOrEnd() {
        return startOrEnd;
    }

    public void setStartOrEnd(String startOrEnd) {
        this.startOrEnd = startOrEnd;
    }

    public String getOutWork() {
        return outWork;
    }

    public void setOutWork(String outWork) {
        this.outWork = outWork;
    }

    public String getLateOrEarly() {
        return lateOrEarly;
    }

    public void setLateOrEarly(String lateOrEarly) {
        this.lateOrEarly = lateOrEarly;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "RecordVO{" +
                "staffId='" + staffId + '\'' +
                ", companyId=" + companyId +
                ", departmentId=" + departmentId +
                ", date=" + date +
                ", map='" + map + '\'' +
                ", address='" + address + '\'' +
                ", startOrEnd='" + startOrEnd + '\'' +
                ", outWork='" + outWork + '\'' +
                ", lateOrEarly='" + lateOrEarly + '\'' +
                '}';
    }
}
