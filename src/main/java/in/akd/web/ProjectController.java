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
import in.akd.service.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

	@Autowired
	ProjectService projectService;
		
	//@Valid - to get valid request body and response 
	@PostMapping("") 
	public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){
		
		if(result.hasErrors()) {
			// if object has errors then custom response
			return new ResponseEntity<String>("Invalid Project Object", HttpStatus.BAD_REQUEST);
		}
		
		//save the project request object to db 
		Project project1 = projectService.saveOrupdateProject(project);
		
		return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
	}
}
