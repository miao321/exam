package permit.model;

import java.io.Serializable;

public class ExamManager implements Serializable {
	//管理员ID
	private Integer managerId;
	//账号
	private String accounts;
	//姓名
	private String name;
	//密码
	private String password;
	//角色列表
	private String roleList;
	//性别
	private String gender;
	//职位
	private String position;
	/**
	 * default constructor
	 */
	public ExamManager(){
		
	}
	/**
	 * minimal constructor
	 */
	public ExamManager(Integer managerId){
		this.managerId = managerId;
	}
	/**
	 * full constructor
	 */
	public ExamManager(Integer managerId, String accounts, String name,
			String password, String roleList, String gender, String position) {
		super();
		this.managerId = managerId;
		this.accounts = accounts;
		this.name = name;
		this.password = password;
		this.roleList = roleList;
		this.gender = gender;
		this.position = position;
	}
	//Property accessors
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public String getAccounts() {
		return accounts;
	}
	public void setAccounts(String accounts) {
		this.accounts = accounts;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoleList() {
		return roleList;
	}
	public void setRoleList(String roleList) {
		this.roleList = roleList;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	

}
