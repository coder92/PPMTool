package in.akd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.akd.domain.Project;
import in.akd.repository.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	public Project saveOrupdateProject(Project project) {
		return projectRepository.save(project);
	}

}
