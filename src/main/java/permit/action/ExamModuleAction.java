package permit.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import permit.model.ExamModule;
import permit.service.ExamModuleService;
import permit.service.ExamResourceService;
import permit.service.ExamRoleService;
import web.common.BeanUtil;
import web.common.Page;
import web.common.XAction;

public class ExamModuleAction extends XAction {
	private ExamModuleService examModuleService = null;
	private ExamResourceService examResourceService = null;
	private ExamRoleService examRoleService = null;
	
	//查询模块所有记录
	public ActionForward findAllExamModule(ActionMapping mapping,ActionForm actionForm,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		//此段代码的作用是处理页面传递的分页参数，表示的是第几页
		int pageShow = 1;  //第几页
		String page = null;
		String pageShowTxt = null;  //文本框里查询页
		pageShowTxt = request.getParameter("pageShowTxt");
		if(pageShowTxt == null || "".equals(pageShowTxt)){
			page = request.getParameter("pageShow");
			if(page == null || "".equals(page)){
				pageShow =1;
			}else{
				pageShow = Integer.parseInt(page);
			}
		}else{
			pageShow = Integer.parseInt(pageShowTxt);
		}
		//分页类
		Page p = new Page();
		//获得分页是第几页
		p.setPageShow(pageShow);
		//计算数据的初始值
		p.accountInitialize();
		//查询一个页面显示的数据，如一个页面只能显示10条数据，那么返回的p对象中存放需要显示的10条数据
		p = examModuleService.findAll(p);
		//将第几页的值保存
		request.setAttribute("pageShow", new Integer(pageShow));
		//保存数据库表记录的总数
		request.setAttribute("rowCount", new Integer(p.getRowCount()));
		//保存总页数
		request.setAttribute("pageCount", new Integer(p.getPageCount()));
		//examModule保存的是页面上需要显示的数据
		request.setAttribute("examModule", p.getData());
		return mapping.findForward("find");
	}
	
	//添加模块数据
	public ActionForward addExamModule(ActionMapping mapping,ActionForm actionForm,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		//model类
		ExamModule em = new ExamModule();
		
		//将actionForm内变量的值转化成model类
		BeanUtil.actionFormToModel(actionForm, em);
		//获得分页的参数
		String pageShow = request.getParameter("pageShow");
		/**
		 * findByModuleName()方法和findByModuleAlias()方法是用来验证数据的唯一性的，例如模块名称
		 * 字段已经存在一个考试模块，如果再次添加一个考试模块，那么久不允许，所以这里需要验证一下
		 */
		List list = examModuleService.findByModuleName(em.getModuleName().trim());
		List list2 = examModuleService.findByModuleAlias(em.getModuleAlias().trim());
		//当模块的名称和别名都是唯一的时候，才允许添加数据
		if(list.isEmpty() && list2.isEmpty()){
			examModuleService.add(em);  //将数据保存到数据库
			request.setAttribute("pageShow", pageShow);
			return mapping.findForward("addSuccess");
		}else{
			request.setAttribute("pageShow", pageShow);
			return mapping.findForward("fail");
		}
		
	}
	
	//更新模块数据
	public ActionForward modExamModule(ActionMapping mapping,ActionForm actionForm ,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		ExamModule em = new ExamModule();  //model类
		String pageShow = request.getParameter("pageShow");
		BeanUtil.actionFormToModel(actionForm, em);
		try{
			//调用Service层来实现数据更新
			examModuleService.update(em);
			request.setAttribute("pageShow", pageShow);
			return mapping.findForward("updateSuccess");
		}catch(Exception e){
			request.setAttribute("pageShow", pageShow);
			return mapping.findForward("updateFail");
		}
	}
	
	//更新模块数据
	public ActionForward modData(ActionMapping mapping,ActionForm actionForm ,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		ExamModule em = new ExamModule();
		String pageShow = request.getParameter("pageShow");
		//获得模块表的ID
		String moduleId =request.getParameter("moduleId");
		//根据模块ID查询出需要更新的一条记录
		List list = examModuleService.findByModuleId(moduleId);
		//判断是否有数据，如果有则获得
		if(!list.isEmpty()){
			em =(ExamModule)list.get(0);
		}
		request.setAttribute("pageShow", pageShow);
		
		//将取出来的数据保存，然后传到页面
		request.setAttribute("examModule", em);
		return mapping.findForward("data");
				
	}
	
	//删除模块数据
	public ActionForward delExamModule(ActionMapping mapping,ActionForm actionForm,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		ExamModule em = new ExamModule();  
		String moduleId= request.getParameter("moduleId");
		String pageShow = request.getParameter("pageShow");
		//将模块ID保存到ExamModule类的moduleId属性中
		em.setModuleId(new Integer(moduleId));
		/**
		 * 下面是根据模块ID去查询角色数据以及资源数据，这样做是为了检查此模块下是否还存在角色和资源数据，如果存在则不能
		 * 删除此模块，需要先删除角色和资源数据，这样做是为了保证数据的完整性
		 */
		List role = examRoleService.findAll(moduleId);
		List resource = examResourceService.findAll(moduleId);
		//验证是否还存在角色和资源数据
		if(role.isEmpty() && resource.isEmpty()){
			//不存在则删除此模块
			/**
			 * 在Hibernate数据处理中，更新和删除都是一样，以主键为依据，所以删除的时候只需要知道主键就可以将整条数据删除
			 */
			examModuleService.delete(em);
			request.setAttribute("pageShow", pageShow);
			return mapping.findForward("delSuccess");
		}else{
			request.setAttribute("pageShow", pageShow);
			return mapping.findForward("delFail");
		}
	}

	public ExamModuleService getExamModuleService() {
		return examModuleService;
	}

	public void setExamModuleService(ExamModuleService examModuleService) {
		this.examModuleService = examModuleService;
	}

	public ExamResourceService getExamResourceService() {
		return examResourceService;
	}

	public void setExamResourceService(ExamResourceService examResourceService) {
		this.examResourceService = examResourceService;
	}

	public ExamRoleService getExamRoleService() {
		return examRoleService;
	}

	public void setExamRoleService(ExamRoleService examRoleService) {
		this.examRoleService = examRoleService;
	}
	
	

}
