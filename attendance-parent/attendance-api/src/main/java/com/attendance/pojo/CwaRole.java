package com.attendance.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 角色表
 */
public class CwaRole implements Serializable {
    /** 角色id */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rId;
    /** 角色名 */
    @Column
    private String rName;

    public CwaRole() {
    }

    public CwaRole(Long rId) {
        this.rId = rId;
    }

    public Long getrId() {
        return rId;
    }

    public void setrId(Long rId) {
        this.rId = rId;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", rId=").append(rId);
        sb.append(", rName=").append(rName);
        sb.append("]");
        return sb.toString();
    }
}
