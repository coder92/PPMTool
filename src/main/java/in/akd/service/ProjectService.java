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
	
	//JPA check the DB for the id if exist then it updated it 
	//only we need to give id from client if we wanted update
	//if we wanted create new then no "id" should provide
	public Project saveOrUpdateProject(Project project) {
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);
		}catch(Exception e) {
			throw new ProjectIdException(
					"Project Id:'"+project.getProjectIdentifier().toUpperCase()+
					"' already exist");
		}
	
	}
	
	public Project findByProjectIdentifier(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		if(project==null) {
			throw new ProjectIdException("Project with "+projectId+" does not exist");
		}
		
		return project;
	}
	
	public Iterable<Project> findAllProjects(){
		return projectRepository.findAll();
	}

	public void deleteProjectById(String projectId) {
		
		Project project = projectRepository.findByProjectIdentifier(projectId);
		if(project==null) {
			throw new ProjectIdException("Can't delete. Project with "+projectId+" does not exist.");
		}
		
		projectRepository.delete(project);
		
	}
}
