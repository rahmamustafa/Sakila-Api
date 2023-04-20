package gov.iti.sakila.business.endpoints.rest;

import gov.iti.sakila.business.services.ActorService;
import gov.iti.sakila.business.services.servicesimpl.ActorServiceImpl;
import gov.iti.sakila.business.services.dtos.actor.ActorDto;
import gov.iti.sakila.business.services.dtos.actor.ActorDtoCreate;
import gov.iti.sakila.business.services.dtos.film.FilmDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Path("actors")
public class ActorEndPoint {
    ActorService actorService = new ActorServiceImpl();

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createActor(ActorDtoCreate actorDtoCreate ,@Context UriInfo uriInfo) {
        try {
            ActorDto actorDto = actorService.createActor(actorDtoCreate);
            Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
            Link getFilms = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder().path("films")).rel("films").build();
            return Response.ok().links(self).links(getFilms).status(Response.Status.OK).entity(actorDto).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findActorById(@PathParam("id") Short actorId,@Context UriInfo uriInfo) {
        try {
            ActorDto actorDto = actorService.findActorById(actorId);
            Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
            Link getFilms = Link.fromUri(uriInfo.getAbsolutePathBuilder().toString()).rel("films").build();
            System.out.println(getFilms);
            return Response.ok().links(self).links(getFilms).status(Response.Status.OK).entity(actorDto).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteActorById(@QueryParam("actorId") Short actorId) {
        try {
            actorService.deleteActorById(actorId);
            return Response.ok().status(Response.Status.OK).entity("success").build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findAllActors() {
        try {
            List<ActorDto> actors = actorService.findAllActors();
            return Response.ok().status(Response.Status.OK).entity(actors).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @Path("first-name")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findActorByFirstName(@QueryParam("firstName") String firstName) {
        try {
            List<ActorDto> actors = actorService.findActorByFirstName(firstName);
            return Response.ok().status(Response.Status.OK).entity(actors).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @Path("{id}/films")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findActorFilms(@PathParam("id") Short actorId) {
        try {
            List<FilmDto> films = actorService.findActorFilms(actorId);
            return Response.ok().status(Response.Status.OK).entity(films).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Path("{id}/films/{filmId}")
    @PUT
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addFilmToActor(@PathParam("id") Short actorId, @PathParam("filmId") Short filmId) {
        try {
            actorService.addFilmToActor(actorId,filmId);
            return Response.ok().status(Response.Status.OK).entity("success").build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }
}
