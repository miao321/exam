package permit.service;

import java.util.List;

import permit.model.ExamModule;
import web.common.Page;

public interface ExamModuleService {
	//查询所有数据
		public Page findAll(Page p);
		//添加一行记录，自动生成主键ID
		public void add(ExamModule em);
		//更新一行记录，根据主键更新
		public void update(ExamModule em);
		//删除一条记录，根据主键删除
		public void delete(ExamModule em);
		//根据模块ID查询一条记录
		public List findByModuleId(String moduleId);
		//根据模块名称查询一条记录
		public List findByModuleName(String moduleName);
		//根据模块别名查询一条记录
		public List findByModuleAlias(String moduleAlias);

}
