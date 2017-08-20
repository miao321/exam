package permit.dao;

import java.util.List;

import permit.model.ExamRole;

public interface ExamRoleDao {
	//查询所有数据
	public List findAll(String moduleId);
	//添加一行记录，自动生成主键ID
	public void add(ExamRole er);
	//更新一行记录，根据主键更新
	public void update(ExamRole er);
	//删除一条记录，根据主键删除
	public void delete(ExamRole er);
	//根据角色ID查询
	public List findByRoleId(String roleId);
	//根据角色名称查询
	public List findByRoleName(String roleName);
	//查询所有数据
	public List findAll();
	

}
