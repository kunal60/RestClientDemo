import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/*
 Getting this XML from server
  
 <project2s>
<project2>
<duration>2</duration>
<id>101</id>
<name>APMT</name>
</project2>
<project2>
<duration>3</duration>
<id>102</id>
<name>OKM</name>
</project2>
<project2>
<duration>4</duration>
<id>103</id>
<name>Vertex</name>
</project2>
</project2s>
  */

public class JerseyClientGet {

	public static void main(String[] args) {
		String output = null;
		try {
			Client client = Client.create();
			WebResource webResource = client.resource("http://localhost:8899/rest/project-services2/projects");
			ClientResponse response = webResource.accept("application/xml").get(ClientResponse.class);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			output = response.getEntity(String.class);

			System.out.println("Output from Server .... \n");
			System.out.println(output);
			System.out.println("************************");
			projectObjUsingJAXB(output);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	private static void projectObjUsingJAXB(String xmlStr) throws JAXBException {
		System.out.println("Projects retrieved using JAXB");
		JAXBContext jaxbContext = JAXBContext.newInstance(Project2s.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		StringReader reader = new StringReader(xmlStr);
		Project2s project2s = (Project2s) unmarshaller.unmarshal(reader);

		for (Project2 project : project2s.getEmployees()) {
			System.out.print(project.getId() + ":");
			System.out.print(project.getName() + ":");
			System.out.print(project.getDuration());
			System.out.println();
		}
	}
}
