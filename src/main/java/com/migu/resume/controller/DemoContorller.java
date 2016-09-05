package com.migu.resume.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.migu.resume.persistence.demo.module.Demo;
import com.migu.resume.persistence.demo.module.DemoExample;
import com.migu.resume.service.IDemoService;
import com.migu.resume.vo.DemoRequest;
/**
 * 测试用例Controller
 * <p>Company: richinfo</p>
 * @author: jshi
 * @date: 2016年9月5日 下午5:33:16
 * @version: V1.0
 */
@Controller
@RequestMapping(value="/demo")
public class DemoContorller {
	@Autowired
	private IDemoService demoService;
	/**
	 * 测试用例，添加参数校验
	 * @param demoReq
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/showDemo") //测试用例浏览器请求：http://localhost:8080/resume/demo/showDemo?id=1&sex=5 ，其中sex不能大于3，大于3的话，报响应的错误信息
    public String toIndex(@Valid DemoRequest demoReq/*,BindingResult result*/,HttpServletRequest request,Model model){  
//        if(result.hasErrors()){//出错，则打印错误信息
//        	System.out.println("#######:::"+result.getFieldError().getDefaultMessage());
//        }
    	int sex = demoReq.getSex();
        System.out.println("##############:::"+sex);
    	int userId = Integer.parseInt(request.getParameter("id"));  
        Demo demo = this.demoService.getDemoById(userId);  
        model.addAttribute("demo", demo);  
        return "showDemo";  
    } 
    /**
     * 测试用例添加sql分页
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="query")
    @ResponseBody
    public Map<String,Object> query(HttpServletRequest request,HttpServletResponse response){
    	PageHelper.startPage(1, 10, true);//添加分页参数，只有紧跟在PageHelper.startPage方法后的第一个Mybatis的查询（Select方法）方法会被分页。
    	DemoExample example = new DemoExample();
		List<Demo> demoList= demoService.selectByExample(example );//分页
		List<Demo> demoLists= demoService.selectByExample(example );//不分页，若需要分页在写一遍PageHelper.startPage方法
		PageInfo<Demo> pageInfo = new PageInfo(demoList);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("data", pageInfo);
    	return map;
    }
}
