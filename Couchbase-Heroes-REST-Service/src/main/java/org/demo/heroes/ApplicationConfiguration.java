package org.demo.heroes;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

//url = "http://localhost:8080/heroes/resource/heroes"
@ApplicationPath("/resource")
public class ApplicationConfiguration extends Application {
	
	/*
	 * String post = "curl -H \"Content-Type: application/json\" " + "-X POST " +
	 * " -d '{\"realName\": \"Tonya\", \"name\": \"iron_man\",\"powers\": [\"rich\"],\"age\": 34}'"
	 * + " http://localhost:8080/heroes/resource/heroes/" ;
	 */
}
