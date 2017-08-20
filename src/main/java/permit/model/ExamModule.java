package permit.model;

import java.io.Serializable;

public class ExamModule implements Serializable{
	private int moduleId;
	private String moduleName;
	private String moduleAlias;
	private String moduleDescription;
	
	public ExamModule() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExamModule(int moduleId, String moduleName, String moduleAlias,
			String moduleDescription) {
		super();
		this.moduleId = moduleId;
		this.moduleName = moduleName;
		this.moduleAlias = moduleAlias;
		this.moduleDescription = moduleDescription;
	}

	public int getModuleId() {
		return moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleAlias() {
		return moduleAlias;
	}

	public void setModuleAlias(String moduleAlias) {
		this.moduleAlias = moduleAlias;
	}

	public String getModuleDescription() {
		return moduleDescription;
	}

	public void setModuleDescription(String moduleDescription) {
		this.moduleDescription = moduleDescription;
	}
	
	
	

}
