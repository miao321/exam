package permit.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;

import permit.model.ExamResource;
import permit.model.ExamRole;
import permit.service.ExamRoleService;
import web.common.BeanUtil;
import web.common.XAction;

public class ExamRoleAction extends XAction {
	private ExamRoleService examRoleService = null;
	//查询角色所有的记录
	public ActionForward findAllExamRole(ActionMapping mapping,ActionForm actionForm,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		String moduleId = request.getParameter("moduleId");
		String moduleAlias = request.getParameter("moduleAlias");
		request.setAttribute("moduleId", moduleId);
		//通过模块ID来查询角色表的数据，那么只有属于此模块的数据才会被查询出来
		List list = examRoleService.findAll(moduleId);
		if (list.isEmpty()) {
			//程序到此，表示此模块中还没有角色数据
			request.setAttribute("empty", "");
		}else{
			ExamRole er = new ExamRole();
			er = (ExamRole)list.get(0);
			request.setAttribute("moduleAlias", moduleAlias);
			//将所有的角色数据保存
			request.setAttribute("examRole", list);
		}
		return mapping.findForward("find");
	}
	//添加角色数据
	public ActionForward addExamRole(ActionMapping mapping,ActionForm actionForm,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		//model类
		ExamRole er = new ExamRole();
		//将ActionForm的值转化给model类
		BeanUtil.actionFormToModel(actionForm, er);
		//验证数据是否是唯一的
		List list = examRoleService.findByRoleName(er.getRoleName().trim());
		if (list.isEmpty()) {
			//如果数据是唯一的，那么将数据添加到数据库
			examRoleService.add(er);
			request.setAttribute("moduleId", er.getModuleId());
			request.setAttribute("moduleAlias", er.getModuleAlias());
			return mapping.findForward("addSuccess");
		}else{
			return mapping.findForward("fail");
		}
	}
	//更新角色数据
	public ActionForward modNcRole(ActionMapping mapping ,ActionForm actionForm,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		ExamRole er = new ExamRole();
		//将ActionForm从表单中获得的数据，转换成ExamRole类的数据
		BeanUtil.actionFormToModel(actionForm, er);
		try{
			//将ExamRole类的数据更新到数据库
			examRoleService.update(er);
			request.setAttribute("moduleId", er.getModuleId());
			request.setAttribute("moduleAlias", er.getModuleAlias());
			return mapping.findForward("updateSuccess");
		}catch(Exception e){
			return mapping.findForward("updateFail");
		}
	}
	//更新角色数据
	public ActionForward modData(ActionMapping mapping ,ActionForm actionForm,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		//model类
		ExamRole er = new ExamRole();
		String roleId = request.getParameter("roleId");
		//根据角色ID将数据查询出来
		List list = examRoleService.findByRoleId(roleId);
		if (!list.isEmpty()) {
			er = (ExamRole)list.get(0);
		}
		//将数据保存在request范围中
		request.setAttribute("examRole", er);
		return mapping.findForward("data");
				
	}
	//删除角色数据
	public ActionForward delExamRole(ActionMapping mapping,ActionForm actionForm,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		ExamRole er = new ExamRole();
		String roleId = request.getParameter("roleId");
		String moduleId = request.getParameter("moduleId");
		String moduleAlias = request.getParameter("moduleAlias");
		request.setAttribute("moduleId", moduleId);
		request.setAttribute("moduleAlias", moduleAlias);
		//将角色ID存入ExamRole类
		er.setRoleId(new Integer(roleId));
		/**
		 * 在Hibernate数据处理中，更新与删除又是一样，以主键为依据，所以删除的时候只需要知道主键就可以将整条数据删除
		 */
		examRoleService.delete(er);
		return mapping.findForward("delSuccess");
	}
	//权限授予角色
	public ActionForward modPerm(ActionMapping mapping ,ActionForm actionForm,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		ExamRole er = new ExamRole();
		String roleId = request.getParameter("roleId");
		String moduleId = request.getParameter("moduleId");
		String moduleAlias = request.getParameter("moduleAlias");
		String[] resId = request.getParameterValues("resourceAlias");
		String resDetail = "";
		if (resId == null || "".equals(resId)) {
			//如果没有一个被选中，证明此角色不拥有任何资源，则将此角色的资源列表清空
			List list = examRoleService.findByRoleId(roleId);
			if (!list.isEmpty()) {
				er = (ExamRole)list.get(0);
			}
			er.setResourceList("");
			examRoleService.update(er);
			request.setAttribute("moduleId", moduleId);
			request.setAttribute("moduleId", moduleAlias);
			return mapping.findForward("updatePerm");
		}else{
			//将资源组合成一个列表
			for (int i = 0; i < resId.length; i++) {
				if(i == 0){
					resDetail = resId[i];
				}else {
					resDetail = resDetail+","+resId[i];
				}
			}
			List list = examRoleService.findByRoleId(roleId);
			if (!list.isEmpty()) {
				er = (ExamRole)list.get(0);
			}
			//将资源列表保存到ExamRole类中
			er.setResourceList(resDetail);
			//将ExamRole类中的数据更新到数据库
			examRoleService.update(er);
			request.setAttribute("moduleId", moduleId);
			request.setAttribute("moduleId", moduleAlias);
			return mapping.findForward("updatePerm");
		}
	}
	public ExamRoleService getExamRoleService() {
		return examRoleService;
	}
	public void setExamRoleService(ExamRoleService examRoleService) {
		this.examRoleService = examRoleService;
	}
	

}
