package permit.service.impl;

import java.util.List;

import permit.dao.ExamRoleDao;
import permit.model.ExamRole;
import permit.service.ExamRoleService;

public class ExamRoleServiceImpl implements ExamRoleService {
	private ExamRoleDao dao = null;
	
	public List findAll(String moduleId){
		return dao.findAll(moduleId);
	}
	
	public void add(ExamRole er){
		dao.add(er);
	}

	public void update(ExamRole er){
		dao.update(er);
	}
	
	public void delete(ExamRole er){
		dao.delete(er);
	}
	
	public List findByRoleId(String roleId){
		return dao.findByRoleId(roleId);
	}
	
	public List findByRoleName(String roleName){
		return dao.findByRoleName(roleName);
	}
	
	public List findAll(){
		return dao.findAll();	
	}

}
