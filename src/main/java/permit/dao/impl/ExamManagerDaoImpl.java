package permit.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import permit.dao.ExamManagerDao;
import permit.model.ExamManager;
import web.common.Page;

public class ExamManagerDaoImpl extends HibernateDaoSupport implements
		ExamManagerDao {
	//添加数据的方法
	public void add(ExamManager em){
		//以对象的形式将数据添加到数据库
		this.getHibernateTemplate().save(em);
	}
	
	//删除数据的方法
	public void delete(ExamManager em){
		//以对象的形式删除一条数据
		this.getHibernateTemplate().delete(em);
	}
	
	//此方法有分页功能
	public Page findAll(Page p){
		//查询所有的数据，读者需要注意一个地方就是ExamManager  这个不是数据库表名而是model类的名字
		Query query = this.getSession().createQuery("From ExamManager");
		//从指定的位置开发取数据库
		query.setFirstResult(p.getInitialize());
		//取的数据的最大条数
		query.setMaxResults(p.getPageSize());
		//将数据保存到Page类中
		p.setData(query.list());
		//查询此表的数据总共有多少条
		List list = this.getHibernateTemplate().find("select count(*) From ExamManager");
		//获得记录总数
		p.setRowCount((int)Long.parseLong(list.get(0).toString()));
		return p;
	}
	
	//更新数据的方法
	public void update(ExamManager em){
		this.getHibernateTemplate().update(em);
	}
	//根据表ID查询数据
	public List findByManagerId(String managerId){
		return this.getHibernateTemplate().find("From ExamManager where managerId="+managerId);

	}
	
	//登录时验证账号密码是否存在
	public List login(String account,String password){
		return this.getHibernateTemplate().find("From ExamManager where accounts='"+account+"' and password='"+password+ "'");
	}
	
	//根据账号查询数据
	public List valAccounts(String accounts){
		return this.getHibernateTemplate().find("From ExamManager where accounts='"+accounts+"'");
	}

	public Page fingAll(Page p) {
		// TODO Auto-generated method stub
		return null;
	}

}
