package com.attendance.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * 提醒补卡表
 */
public class CwaRemindRepair implements Serializable {
    /** 提醒补卡表id */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rrId;
    /** 员工id */
    @Column
    private String rrStaffId;
    /** 补卡日期 */
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="Asia/Shanghai")
    private Date rrDate;
    /** 补卡详细信息 */
    @Column
    private String rrDetail;
    /** 是否查看(1是，0不是) */
    @Column
    private Integer rrSee;

    public Long getRrId() {
        return rrId;
    }

    public void setRrId(Long rrId) {
        this.rrId = rrId;
    }

    public String getRrStaffId() {
        return rrStaffId;
    }

    public void setRrStaffId(String rrStaffId) {
        this.rrStaffId = rrStaffId;
    }

    public Date getRrDate() {
        return rrDate;
    }

    public void setRrDate(Date rrDate) {
        this.rrDate = rrDate;
    }

    public String getRrDetail() {
        return rrDetail;
    }

    public void setRrDetail(String rrDetail) {
        this.rrDetail = rrDetail;
    }

    public Integer getRrSee() {
        return rrSee;
    }

    public void setRrSee(Integer rrSee) {
        this.rrSee = rrSee;
    }

    @Override
    public String toString() {
        return "CwaRemindRepair{" +
                "rrId=" + rrId +
                ", rrStaffId='" + rrStaffId + '\'' +
                ", rrDate=" + rrDate +
                ", rrDetail='" + rrDetail + '\'' +
                ", rrSee=" + rrSee +
                '}';
    }
}
