package com.attendance.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.attendance.Service.StaffService;
import com.attendance.dao.*;
import com.attendance.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
@org.springframework.stereotype.Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffDao staffDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private DepartmentStaffDao departmentStaffDao;

    @Override
    public CwaStaff selectStaffById(String id) {
        CwaStaff staff = staffDao.selectOne(new CwaStaff(id));
        // 员工不存在
        if (staff == null){
            return null;
        }
        staff.setRole(roleDao.selectOne(new CwaRole(staff.getsRoleId())));

        CwaDepartmentStaff departmentStaff = new CwaDepartmentStaff();
        departmentStaff.setDsStaffId(staff.getsId());
        CwaDepartmentStaff ds = departmentStaffDao.selectOne(departmentStaff);
        // 还没加入部门或公司
        if (ds == null){
            return staff;
        }

        if (ds.getDsCompanyId() == null){
            return staff;
        }
        CwaCompany company = companyDao.selectOne(new CwaCompany(ds.getDsCompanyId()));
        staff.setCompany(company);

        if (ds.getDsDepartmentId() == null){
            return staff;
        }
        CwaDepartment department = departmentDao.selectOne(new CwaDepartment(ds.getDsDepartmentId()));
        staff.setDepartment(department);

        return staff;
    }

    @Override
    @Transactional
    public int insertStaff(CwaStaff staff) {
        return staffDao.insert(staff);
    }

    @Override
    @Transactional
    public int updateUser(CwaStaff staff) {
        return staffDao.updateByPrimaryKeySelective(staff);
    }
}
