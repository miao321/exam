package permit.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import permit.model.ExamResource;
import permit.service.ExamResourceService;
import permit.model.ExamRole;
import permit.service.ExamRoleService;
import web.common.BeanUtil;
import web.common.XAction;

public class ExamResourceAction extends XAction {
	private ExamResourceService examResourceService = null;
	//查询资源所有的记录
	public ActionForward findAllExamResource(ActionMapping mapping,ActionForm actionForm,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		String moduleId = request.getParameter("moduleId");
		request.setAttribute("moduleId", moduleId);
		//查询所有资源数据
		List list = examResourceService.findAll(moduleId);
		if (list.isEmpty()) {
			request.setAttribute("empty", "");
		}else{
			ExamResource ere = new ExamResource();
			ere = (ExamResource)list.get(0);
			request.setAttribute("moduleId", ere.getModuleId());
			request.setAttribute("examResource", list);
		}
		return mapping.findForward("find");
	}
	
	//添加资源数据
	public ActionForward addExamResource(ActionMapping mapping,ActionForm actionForm,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		//model类
		ExamResource er = new ExamResource();
		//将ActionForm的值转化给model类
		BeanUtil.actionFormToModel(actionForm, er);
		//判断数据是否已经存在
		List list = examResourceService.findByResourceName(er.getResourceName());
		if (list.isEmpty()) {
			//将数据插入到数据库
			examResourceService.add(er);
			request.setAttribute("moduleId", er.getModuleId());
			//跳转到/examJsp/permit/examResource/addSuccess.jsp
			return mapping.findForward("addSuccess");
		}else {
			request.setAttribute("moduleId", er.getModuleId());
			return mapping.findForward("fail");
		}
	}
	//更新资源数据
	public ActionForward modExamResource(ActionMapping mapping ,ActionForm actionForm ,
			 HttpServletRequest request ,HttpServletResponse response)throws Exception{
		 ExamResource er = new ExamResource();
		 //将ActionForm数据转换成ExamResource类的数据
		 BeanUtil.actionFormToModel(actionForm, er);
		 try{
			 //将数据更新到数据库
			 examResourceService.update(er);
			 request.setAttribute("moduleId", er.getModuleId());
			 return mapping.findForward("updateSuccess");
		 }catch(Exception e){
			 return mapping.findForward("updateFail");
		 }
	}
	
	//查询更新数据
	public ActionForward modExamResourceData(ActionMapping mapping,ActionForm actionForm,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		ExamResource er = new ExamResource();
		String resourceId = request.getParameter("resourceId");
		//根据资源ID查询数据
		List list = examResourceService.findByResourceId(resourceId);
		if (!list.isEmpty()) {
			er =(ExamResource)list.get(0);
		}
		//将查询出来的数据保存到名为examResource的request范围
		request.setAttribute("examResource", er);
		return mapping.findForward("data");
	}
	
	//删除资源数据
	public ActionForward delExamResource(ActionMapping mapping,ActionForm actionForm,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		String resourceId = request.getParameter("resourceId");
		String moduleId = request.getParameter("moduleId");
		request.setAttribute("moduleId", moduleId);
		ExamResource er = new ExamResource();
		er.setResourceId(new Integer(resourceId));
		//根据ID删除资源数据
		examResourceService.delete(er);
		return mapping.findForward("delSuccess");
	}
	
	//将权限授予角色
	public ActionForward roleResource(ActionMapping mapping ,ActionForm actionForm,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		String roleId = request.getParameter("roleId");
		String moduleId = request.getParameter("moduleId");
		String moduleAlias = request.getParameter("moduleAlias");
		String resList = request.getParameter("resourceList");
		String[] resourceList;
		//将所有资源数据全部取出
		List list = examResourceService.findAll(moduleId);
		//判断角色列表中是否有资源
		if(resList == null || "".equals(resList)){
			//角色不拥有资源
			request.setAttribute("resource", list);
			request.setAttribute("roleId", roleId);
			request.setAttribute("moduleId", moduleId);
			request.setAttribute("moduleAlias", moduleAlias);
			return mapping.findForward("new");
		}else{
			/**
			 * 将角色的资源列表进行解析，数据存放的形式是a,b,c。此处a,b,c是资源别名，分别代表不同的资源
			 * 
			 */
			resourceList = resList.split(",");  //根据正则表达式拆分字符串
			for (int i = 0; i < resourceList.length; i++) {
				for (int j = 0; j < list.size(); j++) {
					ExamResource er = new ExamResource();
					er = (ExamResource)list.get(0);
					//将角色已经拥有的资源别名，与所有的资源别名进行匹配
					if (resourceList[i].equals(String.valueOf(er.getResourceAlias()))) {
						/**
						 * 匹配成功，表示角色拥有此资源，将resourceDescription属性值设置为1；用来表示此资源资源角色已经拥有
						 */
						er.setResourceDescription("1");
						//移除一个对象
						list.remove(j);
						//将resourceDescription属性值设置为1的对象保存到被移除对象的位置
						list.add(j, er);
						break;
					}
				}
			}
			request.setAttribute("roleId", roleId);
			request.setAttribute("moduleId", moduleId);
			request.setAttribute("moduleAlias", moduleAlias);
			request.setAttribute("resource", list);
			return mapping.findForward("old");
		}
	}

	public ExamResourceService getExamResourceService() {
		return examResourceService;
	}

	public void setExamResourceService(ExamResourceService examResourceService) {
		this.examResourceService = examResourceService;
	}
}
