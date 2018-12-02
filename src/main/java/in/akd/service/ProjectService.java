package in.akd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.akd.domain.Project;
import in.akd.exceptions.ProjectIdException;
import in.akd.repository.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	public Project saveOrupdateProject(Project project) {
		System.out.println(">>2>>"+this.getClass());
		try {
			//avoid ambiguity - saved all project with unique upper case
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);
		}catch(Exception e) {
			System.out.println(this.getClass()+">>exception");
			throw new ProjectIdException(
					"Project Id:'"+project.getProjectIdentifier().toUpperCase()+
					"' already exist");
		}
	
	}

}
