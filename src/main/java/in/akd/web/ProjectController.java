package in.akd.web;

import javax.validation.Valid;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import in.akd.domain.Project;
import in.akd.service.MapValidationErrorService;
import in.akd.service.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

	@Autowired
	ProjectService projectService;
	
	@Autowired
	MapValidationErrorService mapValidationErrorService;
		
	//@Valid - to get valid request body
	@PostMapping("") 
	public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){
		//validate the project object 
		ResponseEntity<?>errorMap = mapValidationErrorService.mapValidationService(result);
		if(errorMap!=null) {
			return errorMap;
		}
		//save the project request object to db 
		Project project1 = projectService.saveOrUpdateProject(project);
		
		return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
	}
	
	@GetMapping("/{projectId}")
	public ResponseEntity<Project> getProjectById(@PathVariable String projectId){
		Project project = projectService.findByProjectIdentifier(projectId);
		return new ResponseEntity<Project>(project, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public Iterable<Project> getAllProjects() {
		 return projectService.findAllProjects();
		
	}
	
	@DeleteMapping("/{projectId}")
	public ResponseEntity<?> deleteProject(@PathVariable String projectId){
		projectService.deleteProjectById(projectId);
		String mString = "project with projectId="+projectId+" has been deleted successfully";
		return new ResponseEntity(mString,HttpStatus.OK);
	}
	
	@DeleteMapping("/all")
	public ResponseEntity<?> deleteProject(){
		
		for(Project project :projectService.findAllProjects()) {
			projectService.deleteProjectById(project.getProjectIdentifier());
		}
		
		String mString = "all projects deleted successfully";
		return new ResponseEntity(mString,HttpStatus.OK);
	}

}
