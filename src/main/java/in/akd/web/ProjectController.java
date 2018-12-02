package in.akd.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
		//TODO: custom debugging
		System.out.println(">>1>>"+this.getClass());
		//validate the project object 
		ResponseEntity<?>errorMap = mapValidationErrorService.mapValidationService(result);
		if(errorMap!=null) {
			return errorMap;
		}
		//save the project request object to db 
		Project project1 = projectService.saveOrupdateProject(project);
		
		return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
	}
}
