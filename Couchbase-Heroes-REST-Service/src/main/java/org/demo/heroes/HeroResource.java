package org.demo.heroes;


import org.jnosql.artemis.Database;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.function.Supplier;

import static org.jnosql.artemis.DatabaseType.DOCUMENT;


//url = "http://localhost:8080/heroes/resource/heroes"
@ApplicationScoped
@Path("heroes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HeroResource {


    private static final Supplier<WebApplicationException> NOT_FOUND =
            () -> new WebApplicationException(Response.Status.NOT_FOUND);


    @Inject
    @Database(DOCUMENT)
    private HeroRepository repository;

    @GET
    public List<Hero> findAll() {
        return repository.findAll();
    }

    @GET
    @Path("/{id}")
    public Hero findById(@PathParam("id") String id) {
    	System.out.println("Finding hereo by  "+id);
        return repository.findById(id).orElseThrow(NOT_FOUND);
    }

    @GET
    @Path("seniors/{age}")
    public List<Hero> findByOlder(@PathParam("age") Integer age) {
    	System.out.println("Finding older hero by age  "+age);
        return repository.findByAgeGreaterThan(age);
    }

    @GET
    @Path("youngs/{age}")
    public List<Hero> findByYounger(@PathParam("age") Integer age) {
    	System.out.println("Finding young hero by age  "+age);
        return repository.findByAgeLessThan(age);
    }


    @POST
    public void save(Hero hero) {
    	System.out.println("Saving "+hero);
        repository.save(hero);
    }

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") String id, Hero hero) {
    	System.out.println("Updating "+hero);
        repository.save(hero);
    }

    @Path("/{id}")
    @DELETE
    public void delete(@PathParam("id") String name) {
    	System.out.println("Deleting "+name);
        repository.deleteById(name);
    }
}
