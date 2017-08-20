package permit.service.impl;

import java.util.List;

import permit.dao.ExamModuleDao;
import permit.model.ExamModule;
import permit.service.ExamModuleService;
import web.common.Page;

public class ExamModuleServiceImpl implements ExamModuleService {
	private ExamModuleDao dao = null;
	
	public Page findAll(Page p){
		return dao.findAll(p);
	}
	
	public void add(ExamModule em){
		dao.add(em);;
	}
	
	public void update(ExamModule em){
		dao.update(em);
	}
	
	public void delete(ExamModule em){
		dao.delete(em);
	}
	
	public List findByModuleId(String moduleId){
		return dao.findByModuleId(moduleId);
	}
	
	public List findByModuleName(String moduleName){
		return dao.findByModuleName(moduleName);
	}
	
	public List findByModuleAlias(String moduleAlias){
		return dao.findByModuleAlias(moduleAlias);
	}

	public ExamModuleDao getDao() {
		return dao;
	}

	public void setDao(ExamModuleDao dao) {
		this.dao = dao;
	}
	
	

}
