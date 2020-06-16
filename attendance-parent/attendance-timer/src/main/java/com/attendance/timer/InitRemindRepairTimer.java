package com.attendance.timer;

import com.attendance.cons.RecordCon;
import com.attendance.dao.RecordDao;
import com.attendance.dao.RemindRepairDao;
import com.attendance.pojo.CwaRecord;
import com.attendance.pojo.CwaRemindRepair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 每天凌晨定时器
 * 提醒补卡
 */
@Component
public class InitRemindRepairTimer {
    @Autowired
    private RecordDao recordDao;
    @Autowired
    private RemindRepairDao remindRepairDao;

    //每天0：0:03执行
    @Scheduled(cron = "03 00 00 ? * *")
    public void initRecode() {
        CwaRecord record = new CwaRecord();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1); //得到前一天
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        Date date = calendar.getTime();
        record.setRecDate(new java.sql.Date(date.getTime()));
        List<CwaRecord> records = recordDao.select(record);

        System.out.println(date);
        System.out.println(records);
        for (CwaRecord recode : records) {
            String detail = "";
            String sf = recode.getRecStartFlag();
            if (sf == null){
                detail += "上午没打卡 ";
                recode.setRecStartFlag(RecordCon.MISSING);
                recordDao.updateByPrimaryKeySelective(recode);
            }else if (sf.contains("迟到")){
                detail += "上午迟到 ";
            }

            String ef = recode.getRecEndFlag();
            if (ef == null){
                detail += "下午没打卡 ";
                recode.setRecEndFlag(RecordCon.MISSING);
                recordDao.updateByPrimaryKeySelective(recode);
            }else if (ef.contains("早退")) {
                detail += "下午早退 ";
            }

            if (!detail.equals("")) {
                CwaRemindRepair remindRepair = new CwaRemindRepair();
                remindRepair.setRrStaffId(recode.getRecStaffId());
                remindRepair.setRrDate(recode.getRecDate());
                remindRepair.setRrDetail(detail);
                remindRepairDao.insertSelective(remindRepair);
            }
        }

    }

}
