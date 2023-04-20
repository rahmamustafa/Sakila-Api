package gov.iti.sakila.business.endpoints.rest;

import gov.iti.sakila.business.services.CategoryService;
import gov.iti.sakila.business.services.servicesimpl.CategoryServiceImpl;
import gov.iti.sakila.business.services.dtos.CategoryDto;
import gov.iti.sakila.business.services.dtos.film.FilmDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;

@Path("categories")
public class CategoryEndPoint {
    private final CategoryService categoryService = new CategoryServiceImpl();

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createCategory(String categoryName , @Context UriInfo uriInfo) {
        try {
            CategoryDto categoryDto = categoryService.createCategory(categoryName);
            Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
            Link getFilms = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder().path("films")).rel("films").build();
            return Response.ok().links(self,getFilms).status(Response.Status.OK).entity(categoryDto).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findCategoryById(@PathParam("id") Short categoryId, @Context UriInfo uriInfo) {
        try {
            CategoryDto categoryDto = categoryService.findCategoryById(categoryId);
            Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
            Link getFilms = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder().path("films")).rel("films").build();
            return Response.ok().links(getFilms,self).status(Response.Status.OK).entity(categoryDto).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @DELETE
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteCategoryById(@QueryParam("categoryId") Short categoryId) {
        try {
            categoryService.deleteCategoryById(categoryId);
            return Response.ok().status(Response.Status.OK).entity("success").build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findAllCategories() {
        try {
            List<CategoryDto> categories = categoryService.findAllCategories();
            return Response.ok().status(Response.Status.OK).entity(categories).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @Path("first-name")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findCategoryByName(@QueryParam("firstName") String name) {
        try {
            CategoryDto categoryDto = categoryService.findCategoryByName(name);
            return Response.ok().status(Response.Status.OK).entity(categoryDto).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }
    @Path("{id}/films")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findCategoryFilms(@PathParam("id")Short CategoryId) {
        try {
            List<FilmDto> films = categoryService.findCategoryFilms(CategoryId);
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
    public Response addFilmToCategory( @PathParam("filmId") Short filmId,@PathParam("id") Short categoryId) {
        try {
            categoryService.addFilmToCategory(filmId, categoryId);
            return Response.ok().status(Response.Status.OK).entity("success").build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }


}