package com.howei.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.howei.pojo.Poision;
import com.howei.pojo.QuestionBank;
import com.howei.pojo.Users;
import com.howei.service.QuestionBankService;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static org.apache.shiro.authz.annotation.Logical.AND;
import static org.apache.shiro.authz.annotation.Logical.OR;

/**
 * 题库管理 题库管理页面所有接口
 * 
 * @author cj 去问问
 */

@RestController
@RequestMapping("exam/question")
public class QuestionBankController {

	@Autowired
	QuestionBankService questionBankSerivce;

	@RequestMapping("/getPrincipal")
	@ResponseBody
	public String getPrincipal1(){
		Subject subject=SecurityUtils.getSubject();
		boolean bool=subject.isPermitted("题库管理员");
		if(bool){
			Users users=(Users) subject.getPrincipal();
			return users.getUserNumber();
		}
		return null;
	}

	@RequestMapping("/toExerciseManagement")
	@RequiresPermissions(value = {"题库管理"},logical = AND)
	public ModelAndView toExerciseManagement() {
		ModelAndView exercise = new ModelAndView();
		exercise.setViewName("exerciseManagement");
		return exercise;
	}

	@RequestMapping("findRand")
	private List<QuestionBank> findRand(String CourseID, String ChapterID) {
		return questionBankSerivce.findRand(CourseID, ChapterID);
	}

	@RequestMapping("findPoisionA")
	public List<String> findPoisionA() {
		return questionBankSerivce.findPoisionA();
	}

	@RequestMapping("findPoisionB1")
	public List<String> findPoisionB1(String option1) {
		return questionBankSerivce.findPoisionB1(option1);
	}

	@RequestMapping("findquestionpublish")
	public List<QuestionBank> findquestionpublish(QuestionBank questionBank) {
		return questionBankSerivce.findquestionpublish(questionBank);
	}

	@RequestMapping("findExercise")
	public String findExercise(Integer page, Integer limit, String PoisionA, String PoisionB1) {
		List<QuestionBank> questionbanks = questionBankSerivce.findExercise(PoisionA, PoisionB1);

		Integer size = questionbanks.size();
		HashMap<String, Object> map = new HashMap<>();
		if (page == null && limit == null) {
			// informs = (List) informService.findAll();
		} else {

			List<QuestionBank> questionbanks2 = new ArrayList<>();
			for (int i = (page - 1) * limit; i < page * (limit); i++) {
				if (i < questionbanks.size()) {

					questionbanks2.add(questionbanks.get(i));
				}
			}
			questionbanks = questionbanks2;
		}
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", size);
		map.put("data", questionbanks);
		return JSONObject.toJSONString(map);
	}

	@RequestMapping("delete")
	public String delete(String questionID) {
		questionBankSerivce.delete(questionID);
		return "success";
	}

	@RequestMapping("update")
	public String update(QuestionBank questionBank) {
		questionBankSerivce.update(questionBank);
		return "success";
	}

	@RequestMapping("insert")
	public String insert(QuestionBank questionBank) {
		questionBankSerivce.insert(questionBank);

		return "success";
	}

	@RequestMapping("/getQuestion")
	public List<QuestionBank> getQuestion() throws ParseException {
		List<QuestionBank> questiontips = questionBankSerivce.findquestiontips();
		Date d = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sim.parse(sim.format(d));
		for (int i = 0; i < questiontips.size(); i++) {
			if ((date.before(sim.parse(questiontips.get(i).getEnddate()))
					| date.equals(sim.parse(questiontips.get(i).getEnddate())))
					&& (date.after(sim.parse(questiontips.get(i).getStartdate())))
							| date.equals(sim.parse(questiontips.get(i).getStartdate()))) {
				List<QuestionBank> QuestionList = questionBankSerivce.findquestionpublish(questiontips.get(i));
				return QuestionList;
			}
		}
		return null;
	}

	@RequestMapping("getQuestion1")
	public String getQuestion1() {

		List<QuestionBank> questiontips = questionBankSerivce.findquestiontips();
		HashMap<String, Object> map = new HashMap<>();

		map.put("code", 0);
		map.put("msg", "");
		map.put("data", questiontips);
		return JSONObject.toJSONString(map);
	}

	@RequestMapping("/getExamTime")
	public QuestionBank getExamTime() throws ParseException {
		List<QuestionBank> questiontips = questionBankSerivce.findquestiontips();
		return questiontips.get(0);
	}

	@RequestMapping("/GetPoisionB")
	public List<Poision> findbypoision(String PoisionA) {
		List<Poision> PoisionB = questionBankSerivce.findbypoision(PoisionA);
		return PoisionB;
	}

	@RequestMapping("/ShowQ")
	public List<QuestionBank> showQ(String PoisionA, String PoisionB1, int ChapterID) {
		List<QuestionBank> QuestionList = questionBankSerivce.showQ(PoisionA, PoisionB1, ChapterID);
		return QuestionList;
	}

	// 题库管理excel导入------------------------------------------------------------------
	@ResponseBody
	@RequestMapping("/impExcel")
	public void impExcel(@RequestParam("ufiles") MultipartFile file) throws IOException {
		// 构造 Workbook 对象，execelFile 是传入文件路径(获得Excel工作区)
		QuestionBank questionbank = new QuestionBank();
		Workbook book = null;
		InputStream inputStream = file.getInputStream();
		POIFSFileSystem poifsFileSystem = new POIFSFileSystem(inputStream);
		book = new HSSFWorkbook(poifsFileSystem);

		// 读取表格的第一个sheet页
		Sheet sheet = book.getSheetAt(0);
		// 定义 row、cell
		Row row;
		String cell;
		// 总共有多少行,从0开始
		int totalRows = sheet.getLastRowNum();
		// 总共多少列
		int totalCells = sheet.getRow(0).getLastCellNum();
		// 循环输出表格中的内容,首先循环取出行,再根据行循环取出列
		for (int i = 1; i <= totalRows; i++) {
			row = sheet.getRow(i);
			// 处理空行
			if (row == null) {
				continue;
			}
			if (row.getCell(0) != null)
				questionbank.setCourseId((int) Double.parseDouble(row.getCell(0).toString()));
			if (row.getCell(1) != null)
				questionbank.setChapterId((int) Double.parseDouble(row.getCell(1).toString()));
			if (row.getCell(2) != null)
				questionbank.setPoisionA(row.getCell(2).toString());
			if (row.getCell(3) != null)
				questionbank.setPoisionB1(row.getCell(3).toString());
			if (row.getCell(4) != null)
				questionbank.setPoisionB2(row.getCell(4).toString());
			if (row.getCell(5) != null)
				questionbank.setPoisionB3(row.getCell(5).toString());
			if (row.getCell(6) != null)
				questionbank.setQuestion(row.getCell(6).toString());
			if (row.getCell(7) != null)
				questionbank.setAnswer(row.getCell(7).toString());
			if (row.getCell(8) != null)
				questionbank.setOptionA(row.getCell(8).toString());
			if (row.getCell(9) != null)
				questionbank.setOptionB(row.getCell(9).toString());
			if (row.getCell(10) != null)
				questionbank.setOptionC(row.getCell(10).toString());
			if (row.getCell(11) != null)
				questionbank.setOptionD(row.getCell(11).toString());
			if (row.getCell(12) != null)
				questionbank.setRemarks(row.getCell(12).toString());
			if (row.getCell(13) != null)
				questionbank.setQuestionTime((int) Double.parseDouble(row.getCell(13).toString()));
			if (row.getCell(14) != null)
				questionbank.setType((int) Double.parseDouble(row.getCell(14).toString()));
			questionBankSerivce.insert(questionbank);
		}
	}
}
