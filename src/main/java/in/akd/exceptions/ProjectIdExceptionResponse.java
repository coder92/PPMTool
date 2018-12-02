package in.akd.exceptions;

public class ProjectIdExceptionResponse {
	
	private String projectIdentifier;
	
	public ProjectIdExceptionResponse(String projectIdentifier) {
		System.out.println(">>5>>"+this.getClass());
		this.projectIdentifier = projectIdentifier;
	}

	public String getProjectIdentifier() {
		return projectIdentifier;
	}

	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}
	
	

}
