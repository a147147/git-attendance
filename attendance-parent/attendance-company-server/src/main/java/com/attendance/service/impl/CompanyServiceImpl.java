package com.attendance.service.impl;

import com.attendance.Service.CompanyService;
import com.attendance.cons.RepairCon;
import com.attendance.cons.RoleCon;
import com.attendance.dao.*;
import com.attendance.pojo.*;
import com.attendance.vo.RecordExcelVo;
import com.attendance.vo.RecordVO;
import com.attendance.vo.RepairVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@com.alibaba.dubbo.config.annotation.Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private DepartmentStaffDao departmentStaffDao;
    @Autowired
    private DepartmentAdminDao departmentAdminDao;
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private StaffDao staffDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RegulationsDao regulationsDao;
    @Autowired
    private RecordDao recordDao;
    @Autowired
    private RepairDao repairDao;
    @Autowired
    private RemindRepairDao remindRepairDao;

    @Override
    @Transactional
    public synchronized int registerCompany(CwaCompany cwaCompany,String userId) {
        List<CwaCompany> companies = companyDao.select(cwaCompany);
        if (!companies.isEmpty())
            return 0;
        //添加公司
        companyDao.insert(cwaCompany);
        //添加员工和公司关系
        CwaDepartmentStaff ds = new CwaDepartmentStaff();
        ds.setDsCompanyId(cwaCompany.getcId());
        ds.setDsStaffId(userId);
        departmentStaffDao.insert(ds);
        // 增加考勤规则
        CwaRegulations regulations = new CwaRegulations();
        regulations.setRegCompanyId(cwaCompany.getcId());
        regulations.setRegMapAddress(cwaCompany.getcMapAddress());
        regulationsDao.insertSelective(regulations);
        //修改员工角色为老板
        CwaStaff staff = new CwaStaff(userId);
        staff.setsRoleId(RoleCon.BOSS);
        staffDao.updateByPrimaryKeySelective(staff);
        return 1;
    }

    @Override
    @Transactional
    public int createDepartment(CwaDepartment department) {
        return departmentDao.insert(department);
    }

    @Override
    public List<CwaDepartment> selectDepartments(CwaDepartment department){
        return departmentDao.select(department);
    }

    @Override
    public List<CwaStaff> selectDepartmentStaff(CwaDepartmentStaff departmentStaff){
        List<CwaDepartmentStaff> ds = departmentStaffDao.select(departmentStaff);
        List<CwaStaff> staffs = new ArrayList<>();
        for (CwaDepartmentStaff d : ds) {
            CwaStaff staff = staffDao.selectOne(new CwaStaff(d.getDsStaffId()));
            staff.setRole(roleDao.selectOne(new CwaRole(staff.getsRoleId())));
            staffs.add(staff);
        }
        return staffs;
    }

    @Override
    @Transactional
    public int quitDeparment(CwaDepartmentStaff cwaDepartmentStaff) {
        String sid = cwaDepartmentStaff.getDsStaffId();
        CwaStaff staff = new CwaStaff(sid);
        staff.setsRoleId(RoleCon.USER);
        staffDao.updateByPrimaryKeySelective(staff);
        recordDao.delete(new CwaRecord(sid));
        repairDao.delete(new CwaRepair(sid));
        return departmentStaffDao.delete(cwaDepartmentStaff);
    }

    @Override
    @Transactional
    public int rename(CwaStaff staff) {
        return staffDao.updateByPrimaryKeySelective(staff);
    }

    @Override
    @Transactional
    public int promotion(String sid,CwaDepartment cwaDepartment) {
        // 增加部门管理员
        CwaDepartmentAdmin departmentAdmin = new CwaDepartmentAdmin();
        departmentAdmin.setDaCompanyId(cwaDepartment.getdCompanyId());
        departmentAdmin.setDaDepartmentId(cwaDepartment.getdId());
        departmentAdmin.setDaAdminId(sid);
        departmentAdminDao.insert(departmentAdmin);
        // 将员工角色修改为管理员
        CwaStaff cwaStaff = new CwaStaff(sid);
        cwaStaff.setsRoleId(RoleCon.DEPARTMENT);
        staffDao.updateByPrimaryKeySelective(cwaStaff);
        return 1;
    }

    @Override
    @Transactional
    public int demotion(String sid,CwaDepartment cwaDepartment) {
        // 增加部门管理员
        CwaDepartmentAdmin departmentAdmin = new CwaDepartmentAdmin();
        departmentAdmin.setDaCompanyId(cwaDepartment.getdCompanyId());
        departmentAdmin.setDaDepartmentId(cwaDepartment.getdId());
        departmentAdmin.setDaAdminId(sid);
        departmentAdminDao.delete(departmentAdmin);
        // 将员工角色修改为管理员
        CwaStaff cwaStaff = new CwaStaff(sid);
        cwaStaff.setsRoleId(RoleCon.STAFF);
        staffDao.updateByPrimaryKeySelective(cwaStaff);
        return 1;
    }

    @Override
    public int updateRegulations(CwaRegulations regulations) {
        if(regulations.getRegDepartmentId() == null){
            // 老板修改考勤规则
            return regulationsDao.updateByPrimaryKeySelective(regulations);
        }else{
            // 部门修改考勤规则
            CwaRegulations sr = new CwaRegulations(regulations.getRegCompanyId());
            CwaRegulations sregulations = selectRegulations(sr);

            if (sregulations.getRegId().equals(regulations.getRegId())){
                // 公司考勤，没有部门考勤
                regulations.setRegId(null);
                return regulationsDao.insertSelective(regulations);
            }else{
                //部门考勤
                return regulationsDao.updateByPrimaryKeySelective(regulations);
            }
        }
    }

    @Override
    public CwaRegulations selectRegulations(CwaRegulations cwaRegulations) {
        List<CwaRegulations> regulations = regulationsDao.select(cwaRegulations);

        // 只有一个的时候（可能是部门规则，也可能是只有一个公司规则）
        if (regulations.size() == 1){
            return regulations.get(0);
        }
        //没有部门规则
        if (regulations.size() == 0){
            regulations = regulationsDao.select(new CwaRegulations(cwaRegulations.getRegCompanyId()));
        }
        //有多个的时候（查询公司规则，顺便把部门规则查询出来）
        for (CwaRegulations regulation : regulations) {
            if (regulation.getRegDepartmentId() == null || "".equals(regulation.getRegDepartmentId())){
                return regulation;
            }
        }
        return null;
    }

    @Override
    public CwaRecord selectRecord(CwaRecord cwaRecord) {
        return recordDao.selectOne(cwaRecord);
    }

    @Override
    public CwaRecord insertReord(RecordVO recordVO) {
        // 是否存在考勤记录
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        java.sql.Date datesql = new java.sql.Date(calendar.getTimeInMillis());

        CwaRecord record = new CwaRecord(recordVO.getStaffId(),datesql);
        CwaRecord isRecord = recordDao.selectOne(record);
        String flag = "已打卡" + "\n";
        if (recordVO.getOutWork() != null)
            flag +=  recordVO.getOutWork() + "\n";
        if (recordVO.getLateOrEarly() != null)
            flag += recordVO.getLateOrEarly() + "\n";

        if ("上班打卡".equals(recordVO.getStartOrEnd())){
            record.setRecStartTime(new Time(System.currentTimeMillis()));
            record.setRecStartMap(recordVO.getMap());
            record.setRecStartFlag(flag);
            record.setRecStartAddress(recordVO.getAddress());
        }else if ("下班打卡".equals(recordVO.getStartOrEnd())){
            record.setRecEndTime(new Time(System.currentTimeMillis()));
            record.setRecEndMap(recordVO.getMap());
            record.setRecEndFlag(flag);
            record.setRecEndAddress(recordVO.getAddress());
        }else{
            return new CwaRecord();
        }

        if (isRecord == null){
            recordDao.insert(record);
        }else{
            recordDao.updateByPrimaryKeySelective(record);
        }
        return recordDao.selectByPrimaryKey(record);
    }

    @Override
    @Transactional
    public synchronized int addDepartment(String command,String userId) {
        if (command == null || command.equals(""))
            return 0;
        if (!command.startsWith("@@@") || !command.endsWith("@@@"))
            return 0;

        String substring = command.substring(3, command.length() - 3);
        String[] split = substring.split(":");
        if (split.length != 2)
            return 0;

        try{
            long companyId = Long.parseLong(split[0]);
            long departmentId = Long.parseLong(split[1]);
            CwaDepartment department = departmentDao.selectOne(new CwaDepartment(departmentId, companyId));
            if (department == null){
                return 0;
            }

            CwaDepartmentStaff ds = departmentStaffDao.selectOne(new CwaDepartmentStaff(companyId, departmentId, userId));
            if (ds == null){
                departmentStaffDao.insert(new CwaDepartmentStaff(companyId,departmentId,userId));
            }

            CwaStaff staff = new CwaStaff(userId);
            staff.setsRoleId(RoleCon.STAFF);
            staffDao.updateByPrimaryKeySelective(staff);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public List<CwaRecord> getMonthRecode(String staffId,int year, int month) {
        Calendar firstDate = Calendar.getInstance();
        firstDate.set(year,month-1,1);

        Calendar lastDate = Calendar.getInstance();
        lastDate.set(year,month-1,1);
        lastDate.set(Calendar.DAY_OF_MONTH, lastDate.getActualMaximum(Calendar.DAY_OF_MONTH));

        Example example = new Example(CwaRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andBetween("recDate",firstDate.getTime(),lastDate.getTime());
        criteria.andEqualTo("recStaffId",staffId);
        return recordDao.selectByExample(example);
    }

    @Override
    public int initRecode(CwaRegulations regulations,String staffId) {
        CwaRegulations selectRegulations = this.selectRegulations(regulations);
        if (isWork(selectRegulations)) {
            Date date = new Date();
            date.setHours(0);
            date.setMinutes(0);
            date.setSeconds(0);
            CwaRecord record = new CwaRecord(staffId,new java.sql.Date(date.getTime()));
            recordDao.insert(record);
        }
        return 1;
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

    @Override
    public Map<String, List<CwaRecord>> getDateRecode(Long cId, Long dId, Date date) {
        Map<String, List<CwaRecord>> records = new HashMap<>();

        //公司下的全部部门
        CwaDepartment department = new CwaDepartment(dId);
        department.setdCompanyId(cId);
        List<CwaDepartment> departments = departmentDao.select(department);
        Map<Long, String> dMap = new HashMap<>();
        departments.forEach(d -> dMap.put(d.getdId(),d.getdName()));
        // 公司下的全部员工
        List<CwaDepartmentStaff> staffs = departmentStaffDao.select(new CwaDepartmentStaff(cId,dId));
        Map<Long, List<CwaDepartmentStaff>> dsMap = staffs.stream()
                .collect(Collectors.groupingBy(s -> s.getDsDepartmentId() == null ? 0 :s.getDsDepartmentId()));
        for (Map.Entry<Long, List<CwaDepartmentStaff>> dsm : dsMap.entrySet()) {
            Long did = dsm.getKey();
            List<CwaDepartmentStaff> departmentStaffs = dsm.getValue();
            // 部门名
            String name = dMap.getOrDefault(did, "");
            List<CwaRecord> recordsList= new ArrayList<>();
            for (CwaDepartmentStaff ds : departmentStaffs) {
                CwaRecord record = new CwaRecord();
                record.setRecDate(new java.sql.Date(date.getTime()));
                record.setRecStaffId(ds.getDsStaffId());
                CwaRecord r = recordDao.selectOne(record);
                if (r != null){
                    CwaStaff staff = staffDao.selectOne(new CwaStaff(ds.getDsStaffId()));
                    r.setStaff(staff);
                    recordsList.add(r);
                }
            }
            records.put(name+","+did,recordsList);
        }
        departments.forEach(t -> records.putIfAbsent(t.getdName()+","+t.getdId(),new ArrayList<>()));
        return records;
    }

    @Override
    public Map<String,List<CwaRecord>> selectRangeRecord(RecordExcelVo recordExcelVo) {
        Map<String,List<CwaRecord>> recordMap = new HashMap<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        CwaDepartmentStaff ds = new CwaDepartmentStaff(recordExcelVo.getCompanyId(),recordExcelVo.getDepartmentId());
        List<CwaStaff> staffs = this.selectDepartmentStaff(ds);
        for (CwaStaff staff : staffs) {
            Example example = new Example(CwaRecord.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andBetween("recDate",recordExcelVo.getStartDate(),recordExcelVo.getEndDate());
            criteria.andEqualTo("recStaffId",staff.getsId());

            List<CwaRecord> records = recordDao.selectByExample(example);
            for (CwaRecord record : records) {
                record.setStaff(staff);
                String date = format.format(record.getRecDate());
                recordMap.computeIfAbsent(date,t->new ArrayList<>()).add(record);
            }
        }

        return recordMap;
    }

    @Override
    public int addRepair(CwaRepair cwaRepair) {
        cwaRepair.setRepFlag(RepairCon.APPLYING);
        return repairDao.insert(cwaRepair);
    }

    @Override
    public int selectReairCount(CwaRepair cwaRepair) {
        java.sql.Date date = cwaRepair.getRepDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);

        Calendar firstDate = Calendar.getInstance();
        firstDate.set(year,month,1);

        Calendar lastDate = Calendar.getInstance();
        lastDate.set(year,month,1);
        lastDate.set(Calendar.DAY_OF_MONTH, lastDate.getActualMaximum(Calendar.DAY_OF_MONTH));

        Example example = new Example(CwaRepair.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andBetween("repDate",firstDate.getTime(),lastDate.getTime());
        criteria.andEqualTo("repStaffId",cwaRepair.getRepStaffId());
        criteria.andNotEqualTo("repFlag",RepairCon.REFUSE);
        return repairDao.selectCountByExample(example);
    }

    @Override
    public CwaRepair selectReair(CwaRepair cwaRepair) {
        // 是否存在考勤记录
        return repairDao.selectOne(cwaRepair);
    }

    @Override
    public int cancelRepair(CwaRepair cwaRepair) {
        return repairDao.deleteByPrimaryKey(cwaRepair);
    }

    @Override
    public List<CwaRepair> getMonthRepair(String staffId, int year, int month) {
        Calendar firstDate = Calendar.getInstance();
        firstDate.set(year,month-1,1);

        Calendar lastDate = Calendar.getInstance();
        lastDate.set(year,month-1,1);
        lastDate.set(Calendar.DAY_OF_MONTH, lastDate.getActualMaximum(Calendar.DAY_OF_MONTH));

        Example example = new Example(CwaRepair.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andBetween("repDate",firstDate.getTime(),lastDate.getTime());
        criteria.andEqualTo("repStaffId",staffId);
        return repairDao.selectByExample(example);
    }

    @Override
    public List<CwaRepair> getDateRangeRepair(RepairVo repairVo) {
        List<String> staffId = new ArrayList<>();
        List<CwaRepair> repairs = new ArrayList<>();

        if (repairVo.getDepartmentId() != null){    // 管理员
            List<CwaDepartmentStaff> ds = departmentStaffDao.select(new CwaDepartmentStaff(repairVo.getCompanyId(), repairVo.getDepartmentId()));
            ds.forEach(s -> staffId.add(s.getDsStaffId()));
        }else{  //老板
            List<CwaDepartmentAdmin> da = departmentAdminDao.select(new CwaDepartmentAdmin(repairVo.getCompanyId(), repairVo.getDepartmentId()));
            da.forEach(a -> staffId.add(a.getDaAdminId()));
            List<CwaDepartmentStaff> staffs = departmentStaffDao.select(new CwaDepartmentStaff(repairVo.getCompanyId(), repairVo.getDepartmentId()));
            staffId.add(staffs.get(0).getDsStaffId());
        }
        System.out.println(staffId);
        for (String sid : staffId) {
            CwaStaff staff = staffDao.selectOne(new CwaStaff(sid));
            System.out.println(staff);
            if (repairVo.getDepartmentId() != null && staff.getsRoleId() == RoleCon.DEPARTMENT)
                continue;

            Example example = new Example(CwaRepair.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andBetween("repDate",repairVo.getStartDate(),repairVo.getEndDate());
            criteria.andEqualTo("repStaffId",sid);
            List<CwaRepair> repairList = repairDao.selectByExample(example);
            System.out.println(repairList);
            for (CwaRepair cwaRepair : repairList) {
                cwaRepair.setStaffName(staff.getsName());
            }
            repairs.addAll(repairList);
        }
        return repairs.stream().sorted(Comparator.comparingLong(CwaRepair::getRepDateTime).reversed()).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public int handleRepair(CwaRepair cwaRepair) {
        repairDao.updateByPrimaryKeySelective(cwaRepair);
        CwaRepair repair = repairDao.selectOne(cwaRepair);
        String flag = repair.getRepFlag();
        if (RepairCon.AGREE.equals(flag)){
            CwaRecord record = recordDao.selectOne(new CwaRecord(repair.getRepStaffId(),new java.sql.Date(repair.getRepDate().getTime())));
            String state = repair.getRepState();
            if (state.equals("上班")){
                String startFlag = record.getRecStartFlag();
                if (startFlag.contains("外出考勤")){
                    record.setRecStartFlag("已补卡\n外出考勤");
                }else{
                    record.setRecStartFlag("已补卡");
                }
            }else{
                String endFlag = record.getRecEndFlag();
                if (endFlag.contains("外出考勤")){
                    record.setRecEndFlag("已补卡\n外出考勤");
                }else{
                    record.setRecEndFlag("已补卡");
                }
            }
            recordDao.updateByPrimaryKeySelective(record);
        }

        return 1;
    }

    @Override
    @Transactional
    public int dismissCompany(long companyId) {
        List<CwaDepartmentStaff> departmentStaffs = departmentStaffDao.select(new CwaDepartmentStaff(companyId));
        //删除公司
        companyDao.deleteByPrimaryKey(new CwaCompany(companyId));
        //删除部门
        departmentDao.delete(new CwaDepartment(null,companyId));
        //删除部门管理员
        departmentAdminDao.delete(new CwaDepartmentAdmin(companyId));
        // 删除部门员工
        departmentStaffDao.delete(new CwaDepartmentStaff(companyId));
        // 删除考勤规则
        regulationsDao.delete(new CwaRegulations(companyId));

        for (CwaDepartmentStaff departmentStaff : departmentStaffs) {
            String sid = departmentStaff.getDsStaffId();
            //删除考勤记录
            recordDao.delete(new CwaRecord(sid));
            //删除补卡记录
            repairDao.delete(new CwaRepair(sid));
            //修改员工角色id
            CwaStaff staff = new CwaStaff(sid);
            staff.setsRoleId(RoleCon.USER);
            staffDao.updateByPrimaryKeySelective(staff);
        }

        return 1;
    }

    @Override
    public List<CwaRemindRepair> remindRepair(String sid) {
        CwaRemindRepair remindRepair = new CwaRemindRepair();
        remindRepair.setRrStaffId(sid);
        remindRepair.setRrSee(0);
        return remindRepairDao.select(remindRepair);
    }

    @Override
    public int confirmRemindRepair(List<Long> rrids) {
        for (Long rrid : rrids) {
            CwaRemindRepair remindRepair = new CwaRemindRepair();
            remindRepair.setRrId(rrid);
            remindRepair.setRrSee(1);
            remindRepairDao.updateByPrimaryKeySelective(remindRepair);
        }
        return 1;
    }
}
