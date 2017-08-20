package permit.model;

import java.io.Serializable;
import java.util.List;

public class ExamRole implements Serializable{
	private int roleId;
	private String roleName;
	private String moduleAlias;
	private String roleDescription;
	private int moduleId;
	private String resourceList;
	public ExamRole() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ExamRole(int roleId, String roleName, String moduleAlias,
			String roleDescription, int moduleId, String resourceList) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.moduleAlias = moduleAlias;
		this.roleDescription = roleDescription;
		this.moduleId = moduleId;
		this.resourceList = resourceList;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getModuleAlias() {
		return moduleAlias;
	}
	public void setModuleAlias(String moduleAlias) {
		this.moduleAlias = moduleAlias;
	}
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	public int getModuleId() {
		return moduleId;
	}
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
	public String getResourceList() {
		return resourceList;
	}
	public void setResourceList(String resourceList) {
		this.resourceList = resourceList;
	}
	
	
	

}
