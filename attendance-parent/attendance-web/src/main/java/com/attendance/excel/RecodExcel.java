package com.attendance.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;

@HeadRowHeight(20)
@ColumnWidth(20)
public class RecodExcel {
    @ExcelProperty({"姓名"})
    private String name;
    @ExcelProperty({"上班打卡时间"})
    private String startTime;
    @ExcelProperty({"上班打卡地址"})
    private String startAddress;
    @ExcelProperty({"是否上班打卡"})
    private String isStartWork;
    @ExcelProperty({"是否上班外勤"})
    private String isStartOut;
    @ExcelProperty({"是否上班迟到"})
    private String isStartLate;

    @ExcelProperty({"下班打卡时间"})
    private String endTime;
    @ExcelProperty({"下班打卡地址"})
    private String endAddress;
    @ExcelProperty({"是否下班打卡"})
    private String isEndWork;
    @ExcelProperty({"是否下班外勤"})
    private String isEndOut;
    @ExcelProperty({"是否下班早退"})
    private String isEndEarly;

    public RecodExcel() {
    }

    public RecodExcel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public String getIsStartWork() {
        return isStartWork;
    }

    public void setIsStartWork(String isStartWork) {
        this.isStartWork = isStartWork;
    }

    public String getIsStartOut() {
        return isStartOut;
    }

    public void setIsStartOut(String isStartOut) {
        this.isStartOut = isStartOut;
    }

    public String getIsStartLate() {
        return isStartLate;
    }

    public void setIsStartLate(String isStartLate) {
        this.isStartLate = isStartLate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }

    public String getIsEndWork() {
        return isEndWork;
    }

    public void setIsEndWork(String isEndWork) {
        this.isEndWork = isEndWork;
    }

    public String getIsEndOut() {
        return isEndOut;
    }

    public void setIsEndOut(String isEndOut) {
        this.isEndOut = isEndOut;
    }

    public String getIsEndEarly() {
        return isEndEarly;
    }

    public void setIsEndEarly(String isEndEarly) {
        this.isEndEarly = isEndEarly;
    }
}
