package com.migu.resume.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.migu.resume.persistence.permission.module.Permission;
import com.migu.resume.persistence.user.module.User;
import com.migu.resume.service.IUserService;
import com.migu.resume.util.CollectionUtils;
/**
 * 自定义realm
 * <p>Company: richinfo</p>
 * @author: jshi
 * @date: 2016年9月14日 下午1:48:46
 * @version: V1.0
 */
public class CustomRealm extends AuthorizingRealm{
	@Autowired
	private IUserService userService;
	@Override
	public void setName(String name) {
		super.setName("customRealm");
	}
	/**
	 * 用户认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//1、获取用户身份信息
		String account = (String) token.getPrincipal();
		if(null==account){//用户名认证失败
			return null;
		}
		User user = userService.selectByUserAccount(account);
		//2、查询数据库判断用户名是否存在，不存在或者账号停用，则返回null
		if(null==user || user.getStatus().compareTo(0L)==0){
			return null;
		}
		//3、密码校验
		return new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
	}
	/**
	 * 用户权限校验
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//1、获取认证通过后用户的信息
		User user = (User) principals.getPrimaryPrincipal();
		//2、根据用户信息查询数据库，查询用户所拥有的权限
		List<Permission> permissionList = userService.selectPermissionsByUserId(user.getId());
		SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
		if(!CollectionUtils.isEmpty(permissionList)){
			Set<String> roles = new HashSet<String>();
			for(Permission permission : permissionList){
				roles.add(permission.getPermCode());//permCode格式如下：user:create
			}
			info.setStringPermissions(roles);
		}
		return info;
	}

	

}
