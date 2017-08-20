package permit.action;

import java.util.List;

import javassist.expr.NewArray;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.HttpRequestHandler;

import permit.model.ExamManager;
import permit.model.ExamRole;
import permit.service.ExamManagerService;
import permit.service.ExamRoleService;
import web.common.BeanUtil;
import web.common.MD5;
import web.common.Page;
import web.common.XAction;

public class ExamManagerAction extends XAction {
	private ExamManagerService examManagerService = null;
	private ExamRoleService examRoleService = null;
	
	public ActionForward findAllExamManager(ActionMapping mapping,ActionForm actionForm,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		//第几页
		int pageShow = 1;
		String page = null;
		//文本框里查询页
		String pageShowTxt = null;
		pageShowTxt = request.getParameter("pageShowTxt");
		if (pageShowTxt == null || "".equals(pageShowTxt)) {
			page = request.getParameter("pageShow");
			if (page == null || "".equals(page)) {
				pageShow = 1;
			}else {
				pageShow = Integer.parseInt(page);
			}
		}else {
			pageShow = Integer.parseInt(pageShowTxt);
		}
		Page p = new Page();
		//获得分页是第几页
		p.setPageShow(pageShow);
		//计算数据 的初始值
		p.accountInitialize();
		p = examManagerService.fingAll(p);
		//页数
		request.setAttribute("pageShow", new Integer(pageShow));
		//记录总数
		request.setAttribute("rowCount", new Integer(p.getRowCount()));
		//总共有多少页
		request.setAttribute("pageCount", new Integer(p.getPageCount()));
		request.setAttribute("examManager", p.getData());
		return mapping.findForward("find");
	}
	
	public ActionForward addExamManager(ActionMapping mapping,ActionForm actionForm,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		//md5加密
		MD5 md5 = new MD5();
		ExamManager em = new ExamManager();
		//将ActionForm对象数据转换到ExamManager类
		BeanUtil.actionFormToModel(actionForm, em);
		//将密码进行MD5加密
		String password = md5.EncoderByMd5(em.getPassword());
		//将加密过的密码添加到ExamManager类对象中
		em.setPassword(password);
		try{
			//将数据添加到数据库中
			examManagerService.add(em);
			return mapping.findForward("addSuccess");
		}catch(Exception e){
			return mapping.findForward("fail");
		}

	}
	
	public ActionForward modExamManager(ActionMapping mapping,ActionForm actionForm,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		ExamManager em = new ExamManager();
		//将ActionForm类数据转换到ExamManager类
		BeanUtil.actionFormToModel(actionForm, em);
		//将修改的用户数据更新到数据库
		examManagerService.update(em);
		return mapping.findForward("updateSuccess");
	}
	
	//中转数据
	public ActionForward modExamManagerData(ActionMapping mapping,ActionForm actionForm,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		ExamManager em = new ExamManager();
		String managerId = request.getParameter("managerId");
		//根据用户ID查询数据
		List list = examManagerService.findByManagerId(managerId);
		if (!list.isEmpty()) {
			em = (ExamManager)list.get(0);
		}
		//将查询出来的数据保存
		request.setAttribute("examManager", em);
		return mapping.findForward("data");
	} 
	
	public ActionForward delExamManager(ActionMapping mapping,ActionForm actionForm,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		ExamManager em = new ExamManager();
		String managerId = request.getParameter("managerId");
		em.setManagerId(new Integer(managerId));
		//根据用户ID将数据删除
		examManagerService.delete(em);
		return mapping.findForward("deleteSuccess");
	}
	
	//修改密码
	public ActionForward modPassword(ActionMapping mapping,ActionForm actionForm, 
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		MD5 md = new MD5();
		ExamManager em = new ExamManager();
		String managerId = request.getParameter("managerId");
		String password = request.getParameter("password");
		//根据用户ID将用户数据查询出来
		List list = examManagerService.findByManagerId(managerId);
		if (!list.isEmpty()) {
			em = (ExamManager)list.get(0);
			//将用户密码进行修改并加密
			em.setPassword(md.EncoderByMd5(password));
			//将修改后的数据更新到数据库
			examManagerService.update(em);
		}
		return mapping.findForward("passwordUpdate");
	} 
	
	//显示角色数据
	public ActionForward getRole(ActionMapping mapping ,ActionForm actionForm,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		String managerId = request.getParameter("managerId");
		//获得用户原有的角色数据
		String roleList = request.getParameter("roleList");
		String[] rList;
		//查询数据库所有的角色数据
		List role = examRoleService.findAll();
		if (roleList == null || "".equals(roleList)) {
			//程序执行此段代码，表示用户拥有的角色数据为空
			request.setAttribute("managerId", managerId);
			request.setAttribute("role", role);
			return mapping.findForward("new");
		}else {
			rList = roleList.split(","); //根据正则表达式拆分字符串
			//次循环的作用是，将所有的角色数据取出来之后，与用户现在拥有的角色数据进行匹配，拥有的角色数据进行标识
			for (int i = 0; i < rList.length; i++) {
				for (int j = 0; j < role.size(); j++) {
					ExamRole er = new ExamRole();
					er = (ExamRole)role.get(j);  
					//将拥有的角色别名与所有的角色别名进行匹配             er.getRoleId().intValue()
					if (rList[i].equals(String.valueOf(er.getRoleId()))) {
						//别名相等，此对象是数据进行标识，即设置对象的roleDescription属性值为1
						er.setRoleDescription("1");
						//删除没有标识的对象
						role.remove(j);
						//将已经被标识的对象插入到被移除对象的位置
						role.add(j, er);
						break;
					}
				}
			}
			request.setAttribute("managerId", managerId);
			request.setAttribute("role", role);
			return mapping.findForward("old");
		}
	}
	
	//添加角色
	public ActionForward addRole(ActionMapping mapping,ActionForm actionForm,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		ExamManager em = new ExamManager();
		String managerId = request.getParameter("managerId");
		//将页面上被选中的角色ID获得
		String[] roleId = request.getParameterValues("roleId");
		String roleDetail = "";
		//roleId == null 没有选中任何角色
		if (roleId == null || "".equals(roleId)) {
			List list = examManagerService.findByManagerId(managerId);
			if (!list.isEmpty()) {
				em = (ExamManager)list.get(0);
			}
			//将用户的角色列表清空
			em.setRoleList("");
			//将清空角色列表的用户数据更新到数据库
			examManagerService.update(em);
			return mapping.findForward("addRoleSuccess");
		}else{
			//将JSP页面传过的角色ID组成一个字符串
			for (int i = 0; i < roleId.length; i++) {
				if (i == 0) {
					roleDetail = roleId[i];
				}else{
					roleDetail = roleDetail +","+roleId[i];  //将资源组ID成一个列表
				}
			}
			//根据用户ID查询出，需要更新角色的用户
			List list = examManagerService.findByManagerId(managerId);
			if (!list.isEmpty()) {
				em = (ExamManager)list.get(0);
				//将组合好的角色列表设置到对象中
				em.setRoleList(roleDetail);
				//将更新过的角色列表，更新到数据库
				examManagerService.update(em);
			}
			return mapping.findForward("addRoleSuccess");
		}
	}
	
	//用户登录验证   并获取权限数据
	public ActionForward login(ActionMapping mapping,ActionForm actionForm,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		MD5 md = new MD5();   //MD5加密类
		ExamManager em = new ExamManager();  //model类
		//从login.jsp页面获取用户输入的账号
		String loginName = request.getParameter("loginName");
		//从login.jsp页面获取用户输入的密码，然后进行MD5加密
		String password = md.EncoderByMd5(request.getParameter("password"));
		/**
		 * examManagerService 是ExamManagerService接口的对象，负责将账号与密码传到数据库进行匹配，实际上
		 * 就是用账号和密码作为查询条件数据，然后Hibernate会将从数据库返回来的数据封装成一个List对象
		 */
		List list =examManagerService.login(loginName, password);
		String permList = "";
		
		/**
		 * list.isEmpty()此方法是用来判断上面返回的List中是否存在数据，如果不存在数据就证明用户输入的账号或者密码
		 * 不存在，那么list.isEmpty会返回一个true。if（true）条件很显然成立，程序会进入if体内执行 return mapping.findForward("loginFail");
		 * 程序会根据loginFail名字找到需要跳转的路径/loginFail.jsp，跳转到loginFail.jsp页面对用户进行错误提示。如果
		 * 用户的账号与密码存在，那么程序会跳转到else中执行
		 */
		if(list.isEmpty()){
			return mapping.findForward("loginFail");
		}else{
			//程序执行到else中，表示用户的账号与密码是正确的
			//else体内命令的作用是为用户登录后提取权限，为用户提供权限数据
			//list.get(0)取的list对象中的第一条数据，根据账号和密码查询出来的数据在一个表中有且只有一条
			em = (ExamManager)list.get(0);
			
			/**
			 * 下面是判断用户是否有权限，在权限系统中，用户本身不与权限有关系，只与有权限的角色有关系，当用户
			 * 拥有有权限的角色，那么用户也就具备那个角色拥有的权限了。例如教师这个角色拥有查阅试卷的权限。现在
			 * 有一个学生想要查阅试卷，当他使用这个权限的时候，发现权限不够，因为他不是一名教师，所以不能够查询。
			 * 如果此时一教师来查阅试卷，那么程序会让他通过，因为他具备了只用这个权限的角色。em.getRoleList()
			 * 返回的是一个字符串，这里面的内容是用户拥有 的角色列表。例如：一个用户同时拥有教师、校长这两个角色
			 * ，那么这个用户在不同环境下可以执行不同的权限。em.getRoleList()==null表示此用户没有拥有
			 * 任何角色，那么此用户也就不具备任何的权限。程序中em.getRoleList()有可能返回的是null也可能
			 * 返回的是控制，但是不论返回哪个值都表示此用户没有权限
			 */
			if(em.getRoleList() == null||"".equals(em.getRoleList())){
				//执行if体内的代码，表示用户没有权限
				request.setAttribute("empty", "");
				return mapping.findForward("loginSuccess");
			}else{
				//此类是用来封装用户数据
				ExamManager emx = new ExamManager();
				//将用户ID添加到ExamManagerEx类的managerId属性中
				emx.setManagerId(em.getManagerId());
				//将用户名称添加到ExamManagerEx类的name属性中
				emx.setName(em.getName());
				/*
				 * 根据正则表达式截断字符串 例如字符串String str="1,2,3" 调用split(",")方法后数据
				 * 将会转变成 String str1="1",String str2="2",String str3="3",所以此方法
				 * 的返回值是一个字符串数组
				 */
				String[] roleId = em.getRoleList().split(",");
				/**
				 * 此循环需要做的是，提取用户拥有角色的权限，例如教师这个角色拥有阅卷的权限，那么将阅卷的权限
				 * 提取出来后存放到名为permList的字符串变量中。实际上在变量里面存只是一个别名，例如readPaper
				 * 这个就代表阅卷，存在变量中然后传到JSP页面中，然后再JSP页面中进行判断，如果有readPaper这个值
				 * 就允许当前用户进行阅卷操作，否则就不允许
				 */
				
				for(int i=0;i<roleId.length;i++){
					ExamRole er = new ExamRole();
					List lRole = examRoleService.findByRoleId(roleId[i]);
					if(!lRole.isEmpty()){
						er = (ExamRole)lRole.get(0);
						if("".equals(permList)){
							permList = er.getResourceList();  //将权限组合成列表
						}else{
							permList = permList +","+er.getResourceList();
						}
					}
				}
				/**
				 * 将权限数据添加到ExamManager类的roleList属性中，并且以1，2，3的形式存在。以1，2，3cunfang
				 *的目的是为了后续程序中使用方便，在后面使用标签的时候，会为读者讲解
				 */
				emx.setRoleList(","+permList+",");
				//将整个ExamMangerEx类以session的形式存放
				request.getSession().setAttribute("manager", emx);
				return mapping.findForward("loginSuccess");
				
			}
		}
	}
	
	//验证用户账号
	public ActionForward valAccount(ActionMapping mapping,ActionForm actionForm,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		return null;
	}
	
	//注销
	public ActionForward exit(ActionMapping mapping,ActionForm actionForm,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		return null;
	}

	public ExamManagerService getExamManagerService() {
		return examManagerService;
	}

	public void setExamManagerService(ExamManagerService examManagerService) {
		this.examManagerService = examManagerService;
	}

	public ExamRoleService getExamRoleService() {
		return examRoleService;
	}

	public void setExamRoleService(ExamRoleService examRoleService) {
		this.examRoleService = examRoleService;
	}
	
	
	

}
