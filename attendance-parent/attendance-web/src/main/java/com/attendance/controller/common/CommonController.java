package com.attendance.controller.common;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.attendance.Service.StaffService;
import com.attendance.cons.RoleCon;
import com.attendance.pojo.CwaStaff;
import com.attendance.util.HttpClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common")
public class CommonController {
    @Reference
    private StaffService staffService;

    /** 登录 */
    @RequestMapping("/login/{code}/{name}")
    public CwaStaff openId(@PathVariable("code") String code,@PathVariable("name") String name){
        //获取微信小程序授权后的openid
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add("appid","wx25741c9497298b55");
        map.add("secret","a1c80917bf24ff64e9c717575919a3f5");
        map.add("js_code",code);
        map.add("grant_type","authorization_code");
        //返回后的数据{"session_key":"dZJrxj0mQ+Vce22prKe2qw==","openid":"oT4JJ5N5RpUCetRG2NoaPpK3tkG0"}
        String opendIdJson = HttpClient.sendPostRequest("https://api.weixin.qq.com/sns/jscode2session", map);
        JSONObject jsonObject = JSONObject.parseObject(opendIdJson);
        //微信对应的唯一id
        String openid = (String) jsonObject.get("openid");

        CwaStaff cwaStaff = staffService.selectStaffById(openid);
        if (cwaStaff == null){
            cwaStaff = new CwaStaff();
            cwaStaff.setsId(openid);
            cwaStaff.setsName(name);
            cwaStaff.setsRoleId(RoleCon.USER);
            staffService.insertStaff(cwaStaff);
            cwaStaff = staffService.selectStaffById(openid);
        }
        System.out.println(cwaStaff);
        return cwaStaff;
    }

    /** 登录 */
    @RequestMapping("/login2/{id}")
    public CwaStaff login2(@PathVariable("id") String id){
        CwaStaff cwaStaff = staffService.selectStaffById(id);
        if (cwaStaff == null){
            cwaStaff = new CwaStaff();
            cwaStaff.setsId(id);
            cwaStaff.setsName("张三");
            cwaStaff.setsRoleId(RoleCon.USER);
            staffService.insertStaff(cwaStaff);
            cwaStaff = staffService.selectStaffById(id);
        }
        System.out.println(cwaStaff);
        return cwaStaff;
    }

    /** 修改用户信息 */
    @RequestMapping("/updateStaff")
    public CwaStaff register(@RequestBody CwaStaff cwaStaff){
        staffService.updateUser(cwaStaff);
        CwaStaff staff = staffService.selectStaffById(cwaStaff.getsId());
        return staff;
    }
}
