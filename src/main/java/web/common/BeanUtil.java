package web.common;

import java.lang.reflect.Method;

import org.apache.struts.action.ActionForm;

public class BeanUtil {
	public BeanUtil(){
		
	}
	//此方法是将ActionForm的属性值转换成model类的属性值
	public static void actionFormToModel(ActionForm actionForm,Object model)throws Exception{
		//定义两个Class类对象
		//一个是model类的Class对象
		//一个是ActionForm类的Class对象
		Class afClass = actionForm.getClass();
		Class modelClass = model.getClass();
		try{
			//下面是获得model类方法的集合
			Method modelMethod[] = modelClass.getMethods();
			for (int i = 0; i < modelMethod.length; i++) {
				//获得方法的方法名
				String setMethodName = modelMethod[i].getName();
				if(setMethodName.indexOf("set") == 0){
					//将方法名前缀为set的方法替换成get
					String getMethodName = setMethodName.replaceFirst("set", "get");
					Object value[] = {
							//通过反射机制获得ActionForm类中的属性值
							/**
							 * getMethod方法中的getMethodName表示方法名，逗号后面的null是方法的参数列表
							 * 如actionFormToModel(ActionForm actionForm,Object model)方法中有两个参数，
							 * 那么null就表示此方法没有参数，后面的invoke方法的作用是调用类中的方法，在此环境中调用的是actionForm
							 * 对象中名字为getAbc方法（假设getMethodName字符串里面的名称是getAbc）,参数为null
							 */
							afClass.getMethod(getMethodName, null).invoke(actionForm, null)};
							//调用model类前缀为set的方法
							modelMethod[i].invoke(model,value);
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		//此方法是将model的属性值转换成ActionForm类的属性值
	public static void modelToActionform(Object model,ActionForm actionForm)throws Exception{
		Class afClass = actionForm.getClass();
		Class modelClass = model.getClass();
		try{
			//此处获得类的所有参数，类必须是别实例化的或者已经声明的
			//所以不能将modelClass替换成afClass
			Method modelMethod[] = modelClass.getMethods();
			for (int i = 0; i < modelMethod.length; i++) {
				String setMethodName = modelMethod[i].getName();
				if(setMethodName.indexOf("set") == 0){
					String getMethodName = setMethodName.replaceFirst("set", "get");
					Object value[] = {
						modelClass.getMethod(getMethodName, null).invoke(model, null)	
					};
					/**
					 * modelClass.getMethod(getMethodName,null).getReturnType()此方法的作用是获得方法的返回类型
					 */
					Class type[] = {
							modelClass.getMethod(getMethodName, null).getReturnType()
					};
					afClass.getMethod(setMethodName, type).invoke(actionForm, value);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}			
}
