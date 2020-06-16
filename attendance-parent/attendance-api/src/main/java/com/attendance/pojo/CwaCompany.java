package com.attendance.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 公司表
 */
public class CwaCompany implements Serializable {
    /** 公司id */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cId;
    /** 公司名 */
    @Column
    private String cName;
    /** 公司地址 */
    @Column
    private String cAddress;
    /** 公司联系方式 */
    @Column
    private String cPhone;
    /** 公司地图上的地址 */
    @Column
    private String cMapAddress;

    public CwaCompany() {
    }

    public CwaCompany(Long cId) {
        this.cId = cId;
    }

    public Long getcId() {
        return cId;
    }

    public void setcId(Long cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcAddress() {
        return cAddress;
    }

    public void setcAddress(String cAddress) {
        this.cAddress = cAddress;
    }

    public String getcPhone() {
        return cPhone;
    }

    public void setcPhone(String cPhone) {
        this.cPhone = cPhone;
    }

    public String getcMapAddress() {
        return cMapAddress;
    }

    public void setcMapAddress(String cMapAddress) {
        this.cMapAddress = cMapAddress;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", cId=").append(cId);
        sb.append(", cName=").append(cName);
        sb.append(", cAddress=").append(cAddress);
        sb.append(", cPhone=").append(cPhone);
        sb.append(", cMapAddress=").append(cMapAddress);
        sb.append("]");
        return sb.toString();
    }
}
