package com.howei.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.howei.pojo.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.howei.service.BehaviorService;
import com.howei.service.ExampublishService;
import com.howei.service.QuestionBankService;
import com.howei.service.SituationService;
import com.howei.util.JsonResult;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.apache.shiro.authz.annotation.Logical.OR;

/**
 * 考试管理 考试管理页面所以接口
 * 
 * @author cj
 *
 */
@RequestMapping("/exam/manage")
@RestController
public class ExamManagementController {

	@Autowired
	ExampublishService exampublishService;

	@RequestMapping("/getPrincipal")
	@ResponseBody
	public String getPrincipal(){
		Subject subject=SecurityUtils.getSubject();
		boolean bool=subject.isPermitted("考试管理员");
		if(bool){
			Users users=(Users) subject.getPrincipal();
			return users.getUserNumber();
		}
		return null;
	}

	public Users getPrincipal1(){
		Subject subject=SecurityUtils.getSubject();
		Users user=(Users)subject.getPrincipal();
		return user;
	}

	/**
	 * 考试管理界面
	 *
	 * @return
	 */
	@RequestMapping("/toExamManagement")
	@RequiresPermissions(value = {"考试配置"},logical = OR)
	public ModelAndView toExamManagement() {
		ModelAndView toExam = new ModelAndView();
		toExam.setViewName("examManagement");
		return toExam;
	}

	/**
	 * 考试结果界面
	 * 
	 * @return
	 */
	@RequestMapping("/result")
	public ModelAndView toResult() {
		ModelAndView toResult = new ModelAndView();
		toResult.setViewName("result");
		return toResult;
	}

	@Autowired
	QuestionBankService questionBankService;

	@Autowired
	BehaviorService behaviorService;

	@Autowired
	SituationService situationService;

	/**
	 * 获取所有考试记录
	 * 
	 * @return
	 */
	@RequestMapping("/findExampublish")
	@RequiresPermissions(value = {"考试"},logical = OR)
	private String findExampublish(HttpServletRequest request) {
		String page=request.getParameter("page");
		String limit=request.getParameter("limit");
		Subject subject=SecurityUtils.getSubject();
		boolean bool=subject.isPermitted("考试管理员");
		List<Exampublish> examinations = exampublishService.findExampublish();
		Integer size = examinations.size();
		if (page == null && limit == null) {

		} else {
			List<Exampublish> examinations2 = new ArrayList<>();
			for (int i = (Integer.parseInt(page) - 1) * Integer.parseInt(limit); i < Integer.parseInt(page) * (Integer.parseInt(limit)); i++) {
				if (i < examinations.size()) {
					Exampublish exampublish=examinations.get(i);
					if(bool){
						examinations2.add(examinations.get(i));
					}else{
						if(exampublish.getState()==1){
							examinations2.add(examinations.get(i));
						}
					}
				}
			}
			examinations = examinations2;
		}
		HashMap<String, Object> map = new HashMap<>();
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", size);
		map.put("data", examinations);
		return JSONObject.toJSONString(map);
	}

	/**
	 * 增加一次考试记录
	 * 
	 * @param exampublish
	 * @return
	 */
	@RequestMapping("addExampublish")
	private String addExampublish(Exampublish exampublish) {
		exampublishService.addExampublish(exampublish);
		return "success";
	}

	/**
	 * 修改考试信息
	 * 
	 * @param exampublish
	 * @return
	 */
	@RequestMapping("updExampublish")
	private String updExampublish(Exampublish exampublish) {
		exampublishService.updateExampublish(exampublish);
		return "success";
	}

	/**
	 * 通过id删除考试信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("deleteExampublish")
	private String deleteExampublish(int id) {
		exampublishService.deleteExampublish(id);
		return "success";
	}

	/**
	 * 通过时间获取历史考试成绩
	 */
	@ResponseBody
	@RequestMapping("/findAchievementRecords")
	public JsonResult findAchievementRecords(String cycle) {
		Users users=this.getPrincipal1();
		String userNumber=null;
		if(users!=null){
			userNumber=users.getUserNumber();
		}
		List<AchievementRecord> achievementRecords = exampublishService.findAchievementRecords(cycle,userNumber);
		return new JsonResult(achievementRecords);
	}

	@ResponseBody
	@RequestMapping(value = "/showByExam")
	public List<QuestionBank> showByExam(String UserName, String Cycle, Integer Week, Integer times) {
		List<QuestionBank> QuestionList = questionBankService.showByExam(UserName, Cycle, Week, times);
		System.out.println(QuestionList);
		return QuestionList;
	}

	// Layui数据表格的后端写法，获取历史考试信息
	@ResponseBody
	@RequestMapping(value = "/findExamHis", produces = "text/json;charset=UTF-8")
	public String findExamHis(Integer page, Integer limit, String userName) {
		List<Examination> examinations = null;
		examinations = exampublishService.findExamHis(userName);

		Integer size = examinations.size();
		if (page == null && limit == null) {
			// informs = (List) informService.findAll();
		} else {
			List<Examination> examinations2 = new ArrayList<>();
			for (int i = (page - 1) * limit; i < page * (limit); i++) {
				if (i < examinations.size()) {
					examinations2.add(examinations.get(i));
				}
			}
			examinations = examinations2;
		}
		HashMap<String, Object> map = new HashMap<>();
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", size);
		map.put("data", examinations);
		System.out.println(JSONObject.toJSONString(map));
		return JSONObject.toJSONString(map);
	}

	// 考试开始
	@ResponseBody
	@PostMapping(value = "/startexam")
	public String startexam(Integer id) {
		QuestionBank questionbank = questionBankService.findAllfrompublish(id);
		int state = questionbank.getState();
		if (state == 0) {
			return "failstate";
		}
		int i = questionBankService.countstart();
		if (i != 0) {
			return "fail";
		} else {
			if (id != null) {
				questionBankService.startexam(id);
				return "success";
			} else {
				return "fail1";
			}
		}

	}

	// 结束考试
	@ResponseBody
	@PostMapping(value = "/finishexam")
	public String finishexam(Integer id, int week) throws ParseException {
		if (id != null) {
			QuestionBank questionbank = questionBankService.findAllfrompublish(id);
			int state = questionbank.getState();
			if (state == 0) {
				return "failstate";
			} else if (state == 2) {
				return "failstate2";
			} else {
				try {
					SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM");
					String cycle = sim.format(sim.parse(questionbank.getStartdate()));// 获取开始考试日期
					List<Integer> employeeId = questionBankService.selectemployeeId(cycle);

					for (int i = 0; i < employeeId.size(); i++) {
						questionBankService.insertcycle(cycle, employeeId.get(i));
					}
					questionBankService.insertscore("week" + week, cycle);
					questionBankService.finishexam(id);
					// 将缺考人员的考试成绩修改为0
					Behavior behavior = behaviorService.selscore(cycle, "week" + week);
					if (behavior != null) {
						for (int i = 0; i < employeeId.size(); i++) {
							if (week == 1) {
								behavior.setWeek1("0");
							} else if (week == 2) {
								behavior.setWeek2("0");
							} else if (week == 3) {
								behavior.setWeek3("0");
							} else {
								behavior.setWeek4("0");
							}
							behaviorService.updateWeek(behavior);
						}
					}
				} catch (Exception e) {
					return "error";
				} finally {
					return "success";
				}
			}
		} else {
			return "no id";
		}

	}

	@ResponseBody
	@RequestMapping("/selecttimes") // 获取考试次数
	public Integer selecttimes() {
		Users users=(Users)this.getPrincipal1();
		if(users!=null){
			return situationService.selecttimes(users.getUserNumber());
		}
		return null;
	}

	// 获取当前cycle-week的week数(写法参照getAssessmentByEmployeeId)
	@ResponseBody
	@RequestMapping("/getExamWeek")
	public Examination getWeek(Integer week) {
		return exampublishService.getWeek(week);
	}

	// 获取当前cycle-week的考试次数(写法参照getAssessmentByEmployeeId)
	@ResponseBody
	@RequestMapping("/getExamTimes")
	public Examination getExamTimes(String userName, String cycle, Integer week, Integer questionId, Integer times) {
		return exampublishService.getExamTimes(userName, cycle, week, questionId, times);
	}

	// 插入考试历史数据
	@ResponseBody
	@PostMapping(value = "/insertExamHis")
	public String insertExamHis(Examination examination) {
		exampublishService.insertExamHis(examination);
		return "success";
	}

	@ResponseBody
	@RequestMapping(value = "/submitScore", method = { RequestMethod.POST, RequestMethod.GET })
	public int submitScore(String TestScore, String Username) {
		Users users=getPrincipal1();
		Username=users.getUserNumber();
		int result = exampublishService.updatescore(TestScore, Username);
		return result;
	}

	@ResponseBody
	@RequestMapping("/addAchievement")
	public int addAchievement(int TestScore, String Username) throws ParseException {
		Users users=getPrincipal1();
		Username=users.getUserNumber();
		int num = exampublishService.addAchievement(TestScore, Username);
		return num;
	}

	@ResponseBody
	@RequestMapping("/GetPeriod")
	public List<Situation> findbyUsr(String Username) {
		List<Situation> UsrPeriod = exampublishService.findbyUsr(Username);
		return UsrPeriod;
	}

	@ResponseBody
	@RequestMapping("/addtimes") //每次考试完，考试次数加一
	public String addtimes(String UserName) {
		Users users=getPrincipal1();
		UserName=users.getUserNumber();
		Integer times = situationService.selecttimes(UserName);
		if (times == null) {
			times = 1;
			situationService.updatetime(UserName, times);
			return "success";
		} else {
			times += 1;
			situationService.updatetime(UserName, times);
			return "success";
		}
	}

}
