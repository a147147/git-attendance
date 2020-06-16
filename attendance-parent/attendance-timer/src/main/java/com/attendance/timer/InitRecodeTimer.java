package com.attendance.timer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.attendance.Service.StaffService;
import com.attendance.dao.RecordDao;
import com.attendance.dao.RegulationsDao;
import com.attendance.dao.StaffDao;
import com.attendance.pojo.CwaRecord;
import com.attendance.pojo.CwaRegulations;
import com.attendance.pojo.CwaStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * 每天凌晨定时器
 * 初始化考勤表
 */
@Component
public class InitRecodeTimer {
    @Reference
    private StaffService staffService;
    @Autowired
    private StaffDao staffDao;
    @Autowired
    private RegulationsDao regulationsDao;
    @Autowired
    private RecordDao recordDao;
    //每天0：0:03执行
    @Scheduled(cron = "03 00 00 ? * *")
    public void initRecode() {
        //查询全部考勤规则
        List<CwaRegulations> regulations = regulationsDao.selectAll();
        // 公司id-考勤列表
        Map<Long,List<CwaRegulations>> regMap = new HashMap<>();
        for (CwaRegulations r : regulations) {
            List<CwaRegulations> r1 = regMap.putIfAbsent(r.getRegCompanyId(), new ArrayList<>());
            if (r1 == null) {
                regMap.get(r.getRegCompanyId()).add(r);
                continue;
            }
            r1.add(r);
        }

        //查询全部要考勤的员工
        Example estaff = new Example(CwaStaff.class);
        Example.Criteria cstaff = estaff.createCriteria();
        cstaff.andNotEqualTo("sRoleId",1);
        List<CwaStaff> staffs = staffDao.selectByExample(estaff);
        System.out.println(staffs);
        for (CwaStaff s : staffs) {
            CwaStaff staff = staffService.selectStaffById(s.getsId());
            List<CwaRegulations> reg = regMap.get(staff.getCompany().getcId());
            if (reg.size() == 1){   //只有一个考勤规则
                inserRecode(reg.get(0),staff.getsId());
                continue;
            }
            // 员工是否加入到考勤
            boolean hasInsert = false;
            for (CwaRegulations r : reg) {
                if (staff.getDepartment() == null && r.getRegDepartmentId() == null){     //老板
                    inserRecode(r,staff.getsId());
                    hasInsert = true;
                    break;
                }

                Long did = r.getRegDepartmentId();
                if (staff.getDepartment() != null && staff.getDepartment().getdId().equals(did)){    //员工
                    inserRecode(r,staff.getsId());
                    hasInsert = true;
                    break;
                }
            }
            // 员工没有对应的考勤规则
            if (!hasInsert){
                reg.forEach(r-> {
                    if (r.getRegDepartmentId() == null){
                        inserRecode(r,staff.getsId());
                    }
                });
            }
        }
    }

    // 插入考勤记录
    private void inserRecode(CwaRegulations regulation,String sid){
        if (isWork(regulation)) {
            Date date = new Date();
            date.setHours(0);
            date.setMinutes(0);
            date.setSeconds(0);
            CwaRecord record = new CwaRecord(sid,new java.sql.Date(date.getTime()));
            recordDao.insert(record);
        }
    }

    //判断是否要上班
    private boolean isWork(CwaRegulations regulation){
        Calendar calendar = Calendar.getInstance();
        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1 == 0 ? 7 : calendar.get(Calendar.DAY_OF_WEEK) - 1;
        switch (week){
            case 1:
                return regulation.getRegMon();
            case 2:
                return regulation.getRegTues();
            case 3:
                return regulation.getRegWed();
            case 4:
                return regulation.getRegThur();
            case 5:
                return regulation.getRegFri();
            case 6:
                return regulation.getRegSat();
            case 7:
                return regulation.getRegSun();
            default:
                return false;
        }
    }

}
