package com.migu.resume.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.migu.resume.Exception.CustomException;
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
	@RequiresPermissions(value="demo:query")
    @RequestMapping(value="/query")
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
    
    /**
     * 登录
     * @param request
     * @param response
     * @return
     * @throws Exception 
     */
    @RequestMapping(value="/login")
    public String login(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	//获取验证结果
    	Object obj = request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
    	if(null!=obj){//登录成功
    		String shiroLoginFailure = obj.toString();
    		if("org.apache.shiro.authc.UnknownAccountException".equals(shiroLoginFailure)){
    			throw new CustomException("用户不存在");
    		}else if("org.apache.shiro.authc.IncorrectCredentialsException".equals(shiroLoginFailure)){
    			throw new CustomException("密码错误");
    		}else{
    			throw new RuntimeException();
    		}
    	}
    	boolean isLogin = SecurityUtils.getSubject().isAuthenticated();
    	if(isLogin){//登录成功
    		System.out.println("登录成功");
    	}
    	return "login";
    }
    
    /**
     * 进入登录页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/tologin")
    public String tologin(HttpServletRequest request,HttpServletResponse response){
    	System.out.println("进入登录页面");
    	return "login";
    }
}
