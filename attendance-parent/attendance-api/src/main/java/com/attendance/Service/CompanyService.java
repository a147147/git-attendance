package com.attendance.Service;

import com.attendance.pojo.*;
import com.attendance.vo.RecordExcelVo;
import com.attendance.vo.RecordVO;
import com.attendance.vo.RepairVo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 公司Service
 */
public interface CompanyService {
    /** 注册公司 */
    int registerCompany(CwaCompany cwaCompany,String userId);
    /** 创建部门 */
    int createDepartment(CwaDepartment department);
    /** 查询部门 */
    List<CwaDepartment> selectDepartments(CwaDepartment department);
    /** 查询部门的员工 */
    List<CwaStaff> selectDepartmentStaff(CwaDepartmentStaff departmentStaff);
    /** 退出部门 */
    int quitDeparment(CwaDepartmentStaff cwaDepartmentStaff);
    /** 改名 */
    int rename(CwaStaff staff);
    /** 升职 */
    int promotion(String sid,CwaDepartment cwaDepartment);
    /** 降职 */
    int demotion(String sid,CwaDepartment cwaDepartment);
    /** 查询考勤规则 */
    CwaRegulations selectRegulations(CwaRegulations regulations);
    /** 修改考勤规则 */
    int updateRegulations(CwaRegulations regulations);
    /** 查询考勤记录 */
    CwaRecord selectRecord(CwaRecord cwaRecord);
    /** 打卡 */
    CwaRecord insertReord(RecordVO recordVO);
    /** 加入部门 */
    int addDepartment(String command,String userId);
    /** 获取一个月考勤记录 */
    List<CwaRecord> getMonthRecode(String staffId,int year,int month);
    /** 初始化考勤表 */
    int initRecode(CwaRegulations regulations,String staffId);
    /** 获取公司的一天的考勤记录 */
    Map<String, List<CwaRecord>> getDateRecode(Long cId,Long dId, Date date);
    /** 查询考勤范围内的考勤记录 */
    Map<String,List<CwaRecord>> selectRangeRecord(RecordExcelVo recordExcelVo);
    /** 添加补卡记录 */
    int addRepair(CwaRepair cwaRepair);
    /** 查看本月补卡次数 */
    int selectReairCount(CwaRepair cwaRepair);
    /** 查看本月补卡 */
    CwaRepair selectReair(CwaRepair cwaRepair);
    /** 查看本月补卡 */
    int cancelRepair(CwaRepair cwaRepair);
    /** 获取一个月补卡记录 */
    List<CwaRepair> getMonthRepair(String staffId,int year,int month);
    /** 获取一段时间内的补卡记录 */
    List<CwaRepair> getDateRangeRepair(RepairVo repairVo);
    /** 补卡处理 */
    int handleRepair(CwaRepair cwaRepair);
    /** 解散公司 */
    int dismissCompany(long companyId);
    /** 获取提醒补卡列表 */
    List<CwaRemindRepair> remindRepair(String sid);
    /** 确认提醒补卡 */
    int confirmRemindRepair(List<Long> rrids);
}
