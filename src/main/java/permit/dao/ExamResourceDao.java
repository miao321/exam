package permit.dao;

import java.util.List;

import permit.model.ExamResource;

public interface ExamResourceDao {
	//查询所有数据
	public List findAll(String moduleId);
	//添加一行记录，自动生成主键ID
	public void add(ExamResource er);
	//更新一行记录，根据主键更新
	public void update(ExamResource er);
	//删除一条记录，根据主键删除
	public void delete(ExamResource er);
	//根据资源名称查询数据
	public List findByResourceName(String resourceName);
	//根据资源ID查询
	public List findByResourceId(String resourceId);

}
