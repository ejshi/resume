package com.migu.resume.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.SqlUtil;
import com.migu.resume.persistence.module.User;
import com.migu.resume.persistence.module.UserExample;
import com.migu.resume.service.IUserService;
import com.migu.resume.vo.UserRequest;

@Controller  
@RequestMapping("/user")  
public class UserController {  
    @Resource  
    private IUserService userService;  
      
    @RequestMapping("/showUser")  
    public String toIndex(@Valid UserRequest userReq/*,BindingResult result*/,HttpServletRequest request,Model model){  
//        if(result.hasErrors()){//出错，则打印错误信息
//        	System.out.println("#######:::"+result.getFieldError().getDefaultMessage());
//        }
    	int sex = userReq.getSex();
        System.out.println("##############:::"+sex);
    	int userId = Integer.parseInt(request.getParameter("id"));  
        User user = this.userService.getUserById(userId);  
        model.addAttribute("user", user);  
        return "showUser";  
    } 
    
    @RequestMapping(value="query")
    @ResponseBody
    public Map<String,Object> query(HttpServletRequest request,HttpServletResponse response){
    	PageHelper.startPage(1, 10, true);//添加分页参数，只有紧跟在PageHelper.startPage方法后的第一个Mybatis的查询（Select方法）方法会被分页。
    	UserExample example = new UserExample();
		List<User> userList= userService.selectByExample(example );//分页
		List<User> userLists= userService.selectByExample(example );//不分页，若需要分页在写一遍PageHelper.startPage方法
		PageInfo<User> pageInfo = new PageInfo(userList);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("data", pageInfo);
    	return map;
    }
}  
