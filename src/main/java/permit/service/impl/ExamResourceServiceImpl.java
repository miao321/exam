package permit.service.impl;

import java.util.List;

import permit.dao.ExamResourceDao;
import permit.model.ExamResource;
import permit.service.ExamResourceService;

public class ExamResourceServiceImpl implements ExamResourceService {
	private ExamResourceDao dao = null;
	
	public List findAll(String moduleId){
		return dao.findAll(moduleId);
	}
	
	public void add(ExamResource er){
		dao.add(er);
	}
	
	public void update(ExamResource er){
		dao.update(er);
	}
	
	public void delete(ExamResource er){
		dao.delete(er);
	}
	
	public List findByResourceName(String resourceName){
		return dao.findByResourceName(resourceName);
	}
	
	public List findByResourceId(String resourceId){
		return dao.findByResourceId(resourceId);
	}

	public ExamResourceDao getDao() {
		return dao;
	}

	public void setDao(ExamResourceDao dao) {
		this.dao = dao;
	}
	
	

}
