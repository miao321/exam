package permit.service.impl;

import java.util.List;

import permit.dao.ExamManagerDao;
import permit.model.ExamManager;
import permit.service.ExamManagerService;
import web.common.Page;

public class ExamManagerServiceImpl implements ExamManagerService {
	private ExamManagerDao dao = null;
	public void add(ExamManager em){
		dao.add(em);
	}
	public void delete(ExamManager em){
		dao.delete(em);
	}
	public Page findAll(Page p){
		return dao.fingAll(p);
	}
	public void update(ExamManager em){
		dao.update(em);
	}
	public List findByManagerId(String managerId){
		return dao.findByManagerId(managerId);
	}
	public List login(String account,String password){
		return dao.login(account, password);
	}
	public List valAccounts(String accounts){
		return dao.valAccounts(accounts);
	}
	public ExamManagerDao getDao() {
		return dao;
	}
	public void setDao(ExamManagerDao dao) {
		this.dao = dao;
	}
	public Page fingAll(Page p) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
