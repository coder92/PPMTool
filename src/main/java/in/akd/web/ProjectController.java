package in.akd.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
		
	@PostMapping("")
	public ResponseEntity<Project> createNewProject(@RequestBody Project project){
		//save the project request object to db 
		Project project1 = projectService.saveOrupdateProject(project);
		
		return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
	}
}
