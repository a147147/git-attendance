package com.attendance.controller.excel;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.attendance.Service.CompanyService;
import com.attendance.excel.RecodExcel;
import com.attendance.pojo.CwaRecord;
import com.attendance.vo.RecordExcelVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@RestController
@RequestMapping("/excel")
public class ExcelController {
    @Reference
    private CompanyService companyService;
    //存储文件路径
    @Value("${excel.filepath}")
    private String filepath;

    //下载
    @RequestMapping("/downloadFile/{filename}")
    public String downloadFile(@PathVariable("filename") String filename, HttpServletResponse res) {
        String exampleFilePath = filepath + filename;
        System.out.println(exampleFilePath);
        File excelFile = new File(exampleFilePath);
        res.setCharacterEncoding("UTF-8");
        res.setHeader("content-type", "application/octet-stream;charset=UTF-8");
        res.setContentType("application/octet-stream;charset=UTF-8");
        //加上设置大小下载下来的.xlsx文件打开时才不会报“Excel 已完成文件级验证和修复。此工作簿的某些部分可能已被修复或丢弃”
        res.addHeader("Content-Length", String.valueOf(excelFile.length()));
        try {
            res.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(filename.trim(), "UTF-8"));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File(exampleFilePath)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "下载失败";
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "下载成功";
    }

    // 导出表
    @RequestMapping("/exportExcel")
    public String exportExcel(@RequestBody RecordExcelVo recordExcelVo) throws Exception{
        String uuid = UUID.randomUUID().toString();
        String fileName=uuid+".xls";

        File file = new File(filepath + fileName);
        file.setWritable(true,false);
        file.setReadable(true,false);
        FileOutputStream out = new FileOutputStream(file);
        ExcelWriter excelWriter = EasyExcel.write(out).build();

        Map<String, List<CwaRecord>> records = companyService.selectRangeRecord(recordExcelVo);

        int index = 0;
        for(Map.Entry<String, List<CwaRecord>> entry : records.entrySet()){
            String date = entry.getKey();
            List<CwaRecord> rs = entry.getValue();

            List<RecodExcel> recods = new ArrayList<>();

            for (CwaRecord r : rs) {
                RecodExcel recod = new RecodExcel(r.getStaff().getsName());

                recod.setStartTime(r.getRecStartTime() == null ? "" : r.getRecStartTime().toString());
                recod.setStartAddress(r.getRecStartAddress());
                String startFlag = r.getRecStartFlag() == null ? "" : r.getRecStartFlag();
                if (startFlag.contains("已打卡")) {
                    recod.setIsStartWork("是");
                }
                if (startFlag.contains("外出考勤")) {
                    recod.setIsStartOut("是");
                }
                if (startFlag.contains("迟到")) {
                    recod.setIsStartLate("是");
                }

                recod.setEndTime(r.getRecEndTime() == null ? "" : r.getRecEndTime().toString());
                recod.setEndAddress(r.getRecEndAddress());
                String endFlag = r.getRecEndFlag() == null ? "" : r.getRecEndFlag();
                if (endFlag.contains("已打卡")) {
                    recod.setIsEndWork("是");
                }
                if (endFlag.contains("外出考勤")) {
                    recod.setIsEndOut("是");
                }
                if (endFlag.contains("早退")) {
                    recod.setIsEndEarly("是");
                }

                recods.add(recod);
            }

            //这里 需要指定写用哪个class去写
            WriteSheet writeSheet = EasyExcel.writerSheet(index++, date).head(RecodExcel.class).build();
            excelWriter.write(recods, writeSheet);
        }

        //千万别忘记finish 会帮忙关闭流
        excelWriter.finish();

        return "http://127.0.0.1:8080/excel/downloadFile/" + uuid + ".xls";
    }

}
