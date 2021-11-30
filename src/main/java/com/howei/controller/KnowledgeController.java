package com.howei.controller;

import com.alibaba.fastjson.JSONObject;
import com.howei.pojo.Knowledge;
import com.howei.pojo.KnowledgeCheckRecord;
import com.howei.pojo.KnowledgeKeyword;
import com.howei.pojo.Users;
import com.howei.service.KnowledgeService;
import com.howei.util.JsonResult;
import com.howei.util.Result;
import com.howei.util.ResultEnum;
import com.sun.org.apache.xpath.internal.compiler.Keywords;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;


@Controller
@RequestMapping("/exam/knowledge")
@CrossOrigin(origins = "*", allowCredentials = "true")
public class KnowledgeController {


    @Autowired
    private KnowledgeService knowledgeService;

    @GetMapping("/toKnowledge")
    public String knowledgePage() {
        return "knowledge";
    }

    @GetMapping("/toDocument")
    public String documentPage() {
        return "documentLibrary";
    }

    /**
     * 查询所有知识库
     *
     * @param type       0编辑库,1文档库
     * @param searchWord 关键词
     */
    @GetMapping("/all")
    @ResponseBody
    public Result getAllKnowledge(
            @RequestParam(required = false) int type,
            @RequestParam(required = false) String searchWord
    ) {
        Users users = (Users) SecurityUtils.getSubject().getPrincipal();
        if (users == null) {
            return Result.fail(ResultEnum.NO_USER);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("type", type);
        if (searchWord != null && !"".equals(searchWord.trim())) {
            map.put("searchWord", "%" + searchWord + "%");
        }
        List<Knowledge> knowledgeList = knowledgeService.getByMap(map);
        return Result.success(knowledgeList, knowledgeList.size());
    }

    /**
     * 根据id获取文档库
     *
     * @param id 文档库id
     */
    @GetMapping("/get")
    @ResponseBody
    public Result getKnowledge(@RequestParam Integer id) {
        Users users = (Users) SecurityUtils.getSubject().getPrincipal();
        if (users == null) {
            return Result.fail(ResultEnum.NO_USER);
        }
        Knowledge knowledge = knowledgeService.getById(id);
        Integer heat = knowledge.getHeat();
        knowledge.setHeat(heat + 1);
        knowledgeService.updateById(knowledge);
        return Result.success(knowledge, 1);

    }


    /**
     * 删除文档库
     *
     * @param id
     * @return
     */
    @GetMapping("/delete")
    @ResponseBody
    public Result deleteKnowledge(@RequestParam Integer id) {
        Users users = (Users) SecurityUtils.getSubject().getPrincipal();
        if (users == null) {
            return Result.fail(ResultEnum.NO_USER);
        }
        knowledgeService.deleteById(id);
        return Result.success();
    }

    /**
     * 将知识库回退为文档库
     *
     * @param id
     * @return
     */
    @GetMapping("/back")
    @ResponseBody
    public Result backKnowledge(@RequestParam Integer id) {
        Users users = (Users) SecurityUtils.getSubject().getPrincipal();
        if (users == null) {
            return Result.fail(ResultEnum.NO_USER);
        }
        Knowledge knowledge = knowledgeService.getById(id);
        knowledge.setType(0);
        knowledge.setStatus(0);
        knowledgeService.updateById(knowledge);
        knowledgeService.deleteKcrByKid(id);
        return Result.success();

    }

    /**
     * 添加或修改知识库
     */
    @PostMapping("/save")
    @ResponseBody
    @Transactional
    public Result save(@RequestBody Knowledge knowledge) {

        Users users = (Users) SecurityUtils.getSubject().getPrincipal();
        if (users == null) {
            return Result.fail(ResultEnum.NO_USER);
        }
        knowledge.setEmployeeId(users.getEmployeeId());
        knowledge.setEmployeeName(users.getUserName());
        LocalDateTime currentTime = LocalDateTime.now();
        Integer id = knowledge.getId();
        if (id == null || id == 0) {
            knowledge.setCreateTime(currentTime);
            knowledgeService.insert(knowledge);
            System.out.println("IIIII:" + knowledge.getId());
            id = knowledge.getId();
        } else {
            knowledge.setUpdateTime(currentTime);
            knowledgeService.updateById(knowledge);
        }
        //关键字
        String keyword = knowledge.getKeyword();
        List<String> newKeywordList = Arrays.asList(keyword.split(" "));
        List<KnowledgeKeyword> knowledgeKeywordList = new ArrayList<>();
        for (String newKeyword : newKeywordList) {
            if (!"".equals(newKeyword)) {
                KnowledgeKeyword knowledgeKeyword = new KnowledgeKeyword();
                knowledgeKeyword.setKnowledgeId(id);
                knowledgeKeyword.setKeyword(newKeyword);
                knowledgeKeyword.setCreateTime(currentTime);
                knowledgeKeywordList.add(knowledgeKeyword);
            }
        }
        knowledgeService.deleteKkByKid(id);
        knowledgeService.insertKk(knowledgeKeywordList);
        return Result.success();

    }


    /**
     * 根据id审核编辑库
     */
    @GetMapping("/check")
    @ResponseBody
    public Result checkKnowlege(@RequestParam Integer id) {
        Users users = (Users) SecurityUtils.getSubject().getPrincipal();
        if (users == null) {
            return Result.fail("用户失效");
        }
        LocalDateTime dateTime = LocalDateTime.now();
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
        map.put("createTime", dateTime);
        knowledgeService.insertCheckEmployee(map);
        Knowledge knowledge = knowledgeService.getById(id);
        List<KnowledgeCheckRecord> knowledgeCheckRecordList = knowledge.getKnowledgeCheckRecordList();
        knowledge.setUpdateTime(dateTime);
        int size = knowledgeCheckRecordList.size();
        if (size > 0 && size < 5) {
            knowledge.setStatus(1);
        }
        if (size >= 5) {
            knowledge.setStatus(2);
            knowledge.setType(1);
            knowledge.setHeat(0);
            knowledge.setPassTime(dateTime);
        }
        knowledgeService.updateById(knowledge);
        return Result.success();
    }


}
