package permit.model;

import java.io.Serializable;

public class ExamResource implements Serializable{
	private int resourceId;
	private String resourceName;
	private String resourceAlias;
	private String resourceDescription;
	private int moduleId;
	
	
	public ExamResource() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ExamResource(int resourceId, String resourceName,
			String resourceAlias, String resourceDescription, int moduleId) {
		super();
		this.resourceId = resourceId;
		this.resourceName = resourceName;
		this.resourceAlias = resourceAlias;
		this.resourceDescription = resourceDescription;
		this.moduleId = moduleId;
	}


	public int getResourceId() {
		return resourceId;
	}


	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}


	public String getResourceName() {
		return resourceName;
	}


	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}


	public String getResourceAlias() {
		return resourceAlias;
	}


	public void setResourceAlias(String resourceAlias) {
		this.resourceAlias = resourceAlias;
	}


	public String getResourceDescription() {
		return resourceDescription;
	}


	public void setResourceDescription(String resourceDescription) {
		this.resourceDescription = resourceDescription;
	}


	public int getModuleId() {
		return moduleId;
	}


	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
	
	

}
