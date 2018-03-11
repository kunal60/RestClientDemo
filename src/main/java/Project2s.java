import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "project2s")

public class Project2s {
	@XmlElement(name = "project2")
	private List<Project2> projects = null;

	public List<Project2> getEmployees() {
		return projects;
	}

	public void setEmployees(List<Project2> projects) {
		this.projects = projects;
	}

}
