package com.attendance.controller.company;

import com.alibaba.dubbo.config.annotation.Reference;
import com.attendance.Service.CompanyService;
import com.attendance.Service.StaffService;
import com.attendance.pojo.*;
import com.attendance.util.PmsUploadUtil;
import com.attendance.vo.RecordVO;
import com.attendance.vo.RepairVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.rmi.runtime.Log;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Reference
    private CompanyService companyService;
    @Reference
    private StaffService staffService;

    /** 注册公司 */
    @RequestMapping("/register/{userId}")
    public CwaStaff register(@PathVariable("userId") String userId,@RequestBody CwaCompany cwaCompany){
        companyService.registerCompany(cwaCompany,userId);
        CwaStaff staff = staffService.selectStaffById(userId);
        CwaRegulations regulations = new CwaRegulations(staff.getCompany().getcId());
        companyService.initRecode(regulations,userId);
        return staff;
    }

    /** 创建部门 */
    @RequestMapping("/createDepartment")
    public int createDepartment(@RequestBody CwaDepartment department){
        return companyService.createDepartment(department);
    }

    /** 查询部门 */
    @RequestMapping("/selectDepartments")
    public List<CwaDepartment> selectDepartments(@RequestBody CwaDepartment department){
        return companyService.selectDepartments(department);
    }

    /** 查询部门的员工 */
    @RequestMapping("/selectDepartmentStaff")
    public List<CwaStaff> selectDepartmentStaff(@RequestBody CwaDepartmentStaff departmentStaff){
        return companyService.selectDepartmentStaff(departmentStaff);
    }

    /** 退出部门 */
    @RequestMapping("/quitDeparment")
    public int quitDeparment(@RequestBody CwaDepartmentStaff cwaDepartmentStaff){
        return companyService.quitDeparment(cwaDepartmentStaff);
    }

    /** 改名 */
    @RequestMapping("/rename")
    public int rename(@RequestBody CwaStaff staff){
        return companyService.rename(staff);
    }

    /** 升职 */
    @RequestMapping("/promotion/{sid}")
    public int promotion(@PathVariable("sid") String sid,@RequestBody CwaDepartment cwaDepartment){
        return companyService.promotion(sid,cwaDepartment);
    }

    /** 降职 */
    @RequestMapping("/demotion/{sid}")
    public int demotion(@PathVariable("sid") String sid,@RequestBody CwaDepartment cwaDepartment){
        return companyService.demotion(sid,cwaDepartment);
    }

    /** 查询考勤规则 */
    @RequestMapping("/selectRegulations")
    public CwaRegulations selectRegulations(@RequestBody CwaRegulations regulations){
        return companyService.selectRegulations(regulations);
    }

    /** 修改考勤规则 */
    @RequestMapping("/updateRegulations")
    public int updateRegulations(@RequestBody CwaRegulations regulations){
        return companyService.updateRegulations(regulations);
    }

    /** 查询考勤记录 */
    @RequestMapping("/selectRecord")
    public CwaRecord selectRecord(@RequestBody CwaRecord cwaRecord){
        return companyService.selectRecord(cwaRecord);
    }

    /** 打卡 */
    @RequestMapping("/insertReord")
    public CwaRecord insertReord(String tips,@RequestBody RecordVO recordVO){
        return companyService.insertReord(recordVO);
    }

    /** 加入部门 */
    @RequestMapping("/addDepartment/{command}/{userId}")
    public synchronized CwaStaff addDepartment(@PathVariable("command") String command,@PathVariable("userId") String userId){
        int i = companyService.addDepartment(command, userId);
        CwaStaff staff = staffService.selectStaffById(userId);
        if (i == 0){
            return staff;
        }
        System.out.println(staff);
        CwaRegulations regulations = new CwaRegulations(staff.getCompany().getcId(),staff.getDepartment().getdId());
        companyService.initRecode(regulations,userId);
        return staff;
    }

    /** 获取一个月考勤记录 */
    @RequestMapping("/getMonthRecode/{staffId}/{year}/{month}")
    public List<CwaRecord> getMonthRecode(@PathVariable("staffId") String staffId,
                                          @PathVariable("year") int year,
                                          @PathVariable("month") int month){
        return companyService.getMonthRecode(staffId,year,month);
    }

    /** 获取公司的一天的考勤记录 */
    @RequestMapping("/getDateRecode")
    public Map<String,List<CwaRecord>> getDateRecode(@RequestBody RecordVO recordVO){
        return companyService.getDateRecode(recordVO.getCompanyId(),recordVO.getDepartmentId(),recordVO.getDate());
    }

    //实现文件上传的请求
    @RequestMapping("/fileUpload")
    public String upload(@RequestParam("file") MultipartFile mutipartFile) {
        //如果将MultipartFile对象的内容上传到fastdfs分布式文件系统
        return PmsUploadUtil.uploadImage(mutipartFile);
    }

    //添加补卡记录
    @RequestMapping("/addRepair")
    public int addRepair(@RequestBody CwaRepair cwaRepair) {
        return companyService.addRepair(cwaRepair);
    }

    //查看本月补卡次数
    @RequestMapping("/selectReairCount")
    public int selectReairCount(@RequestBody CwaRepair cwaRepair) {
        return companyService.selectReairCount(cwaRepair);
    }

    //查看补卡
    @RequestMapping("/selectReair")
    public CwaRepair selectReair(@RequestBody CwaRepair cwaRepair) {
        return companyService.selectReair(cwaRepair);
    }

    //取消申请补卡信息
    @RequestMapping("/cancelRepair")
    public int cancelRepair(@RequestBody CwaRepair cwaRepair) {
        return companyService.cancelRepair(cwaRepair);
    }

    /** 获取一个月补卡记录 */
    @RequestMapping("/getMonthRepair/{staffId}/{year}/{month}")
    public List<CwaRepair> getMonthRepair(@PathVariable("staffId") String staffId,
                                          @PathVariable("year") int year,
                                          @PathVariable("month") int month){
        return companyService.getMonthRepair(staffId,year,month);
    }

    /** 查看时间范围内的补卡记录 */
    @RequestMapping("/getDateRangeRepair")
    public List<CwaRepair> getDateRangeRepair(@RequestBody RepairVo repairVo){
        return companyService.getDateRangeRepair(repairVo);
    }

    /** 补卡处理 */
    @RequestMapping("/handleRepair")
    public int handleRepair(@RequestBody CwaRepair cwaRepair){
        return companyService.handleRepair(cwaRepair);
    }

    /** 解散公司 */
    @RequestMapping("/dismissCompany/{companyId}")
    public int dismissCompany(@PathVariable("companyId") long companyId){
        return companyService.dismissCompany(companyId);
    }

    /** 获取提醒补卡信息 */
    @RequestMapping("/remindRepair/{sid}")
    public List<CwaRemindRepair> remindRepair(@PathVariable("sid") String sid){
        return companyService.remindRepair(sid);
    }

    /** 确认提醒补卡信息 */
    @RequestMapping("/confirmRemindRepair/{rrids}")
    public int confirmRemindRepair(@PathVariable("rrids") List<Long> rrids){
        return companyService.confirmRemindRepair(rrids);
    }
}
