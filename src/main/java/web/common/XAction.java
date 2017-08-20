package web.common;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class XAction extends Action {
	public ActionForward execute(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		//methodName存放的是方法名称
		String methodName = mapping.getParameter();
		//type是定义一个属性类型数组
		Class type[] = {
			ActionMapping.class,ActionForm.class,HttpServletRequest.class,HttpServletResponse.class	
		};
		//获得当前类子类的方法
		Method method = this.getClass().getMethod(methodName, type);
		//定义一个Object类数组，用来存放方法中需要的参数
		Object value[] = {mapping,actionForm,request,response};
		//调用子类的方法，并且返回一个ActionForward类
		ActionForward actionForward = (ActionForward)method.invoke(this, value);
		return actionForward;
	}

}
