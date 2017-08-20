package permit.service;

import java.util.List;

import permit.model.ExamManager;
import web.common.Page;

public interface ExamManagerService {
	//查询所有数据
		public Page fingAll(Page p);
		//添加一行记录，自动生成主键ID
		public void add(ExamManager em);
		//更新一行记录，根据主键更新
		public void update(ExamManager em);
		//删除一条记录，根据主键删除
		public void delete(ExamManager em);
		//根据管理员ID查询
		public List findByManagerId(String managerId);
		//用户登录验证
		public List login(String account,String password);
		//验证账号是否合法
		public List valAccounts(String accounts);
}
