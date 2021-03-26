package com.howei.controller;

import com.alibaba.fastjson.JSONObject;
import com.howei.pojo.Knowledge;
import com.howei.pojo.Users;
import com.howei.service.KnowledgeService;
import com.howei.util.JsonResult;
import com.howei.util.Result;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/exam/knowledge")
public class KnowledgeController {


    @Autowired
    private KnowledgeService knowledgeService;

    @GetMapping("/toKnowledge")
    public String knowledgePage() {
        return "knowledge";
    }

    @GetMapping("/all")
    @ResponseBody
    public Result getAllKnowledge(
            @RequestParam int type
    ) {
        Users users = (Users) SecurityUtils.getSubject().getPrincipal();
        if (users == null) {
            return Result.fail();
        }
        int employeeId = users.getEmployeeId();
        Map<String, Object> map = new HashMap<>();
        // map.put("employeeId", employeeId);
        map.put("type", type);

        List<Knowledge> knowledgeList = knowledgeService.getByMap(map);
        return Result.success(knowledgeList, knowledgeList.size());
    }

    @GetMapping("/get")
    @ResponseBody
    public Result getKnowledge(@RequestParam Integer id) {
        Users users = (Users) SecurityUtils.getSubject().getPrincipal();
        if (users == null) {
            return Result.fail("用户失效");
        }
        Knowledge knowledge = knowledgeService.getById(id);
        return Result.success(knowledge, 1);

    }

    @GetMapping("/delete")
    @ResponseBody
    public Result deleteKnowledge(@RequestParam Integer id) {
        Integer flag = knowledgeService.deleteById(id);
        if (flag > 0) {
            return Result.success();
        } else {
            return Result.fail();
        }
    }

    @PostMapping("/save")
    @ResponseBody
    public Result save(@RequestBody Knowledge knowledge) {


        Users users = (Users) SecurityUtils.getSubject().getPrincipal();
        if (users == null) {
            return Result.fail("用户失效");
        }
        knowledge.setEmployeeId(users.getEmployeeId());
        knowledge.setEmployeeName(users.getUserName());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(System.currentTimeMillis());
        Integer id = knowledge.getId();
        int flag;
        if (id == null || id == 0) {

            knowledge.setCreateTime(currentTime);
            flag = knowledgeService.insert(knowledge);
        } else {
            knowledge.setUpdateTime(currentTime);
            flag = knowledgeService.updateById(knowledge);
        }
        if (flag > 0) {
            return Result.success();
        } else {
            return Result.fail();
        }

    }

    @GetMapping("/check")
    @ResponseBody
    public Result checkKnowlege(@RequestParam Integer id) {
        Users users = (Users) SecurityUtils.getSubject().getPrincipal();
        if (users == null) {
            return Result.fail("用户失效");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("knowledgeId", id);
        int employeeId = users.getEmployeeId();
        map.put("employeeId", employeeId);
        Map<String, Object> checkEmployeeMap = knowledgeService.getCheckEmployeeByMap(map);
        if (checkEmployeeMap != null && !checkEmployeeMap.isEmpty()) {
            return Result.fail("你已经审核过");
        }

        String userName = users.getUserName();
        map.put("employeeName", userName);
        map.put("createTime", new Date());
        int flag = knowledgeService.insertCheckEmployee(map);


        Knowledge knowledge = knowledgeService.getById(id);
        System.out.println("check_konwolege"+knowledge);
        List<Map> checkedEmployeeList = knowledge.getCheckedEmployee();
        knowledge.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
        int size = checkedEmployeeList.size();
        System.out.println("size:::"+size);
        if (size > 0 && size < 5) {
            knowledge.setStatus(1);
        }
        if (size >= 5) {
            knowledge.setStatus(2);
            knowledge.setType(1);
            knowledge.setHeat(0);
        }
        knowledgeService.updateById(knowledge);
        return Result.success();
    }


}
