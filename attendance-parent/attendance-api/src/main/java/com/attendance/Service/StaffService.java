package com.attendance.Service;

import com.attendance.pojo.CwaStaff;

/**
 * 员工Service
 */
public interface StaffService {
    /** 通过id获取员工信息 */
    CwaStaff selectStaffById(String id);
    /** 员工注册 */
    int insertStaff(CwaStaff staff);
    /** 修改员工信息 */
    int updateUser(CwaStaff staff);
}
