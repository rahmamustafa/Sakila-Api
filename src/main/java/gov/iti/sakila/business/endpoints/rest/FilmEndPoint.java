package gov.iti.sakila.business.endpoints.rest;

import gov.iti.sakila.business.services.FilmService;
import gov.iti.sakila.business.services.servicesimpl.FilmServiceImpl;
import gov.iti.sakila.business.services.dtos.actor.ActorDto;
import gov.iti.sakila.business.services.dtos.customer.CustomerDto;
import gov.iti.sakila.business.services.dtos.film.FilmDto;
import gov.iti.sakila.business.services.dtos.film.FilmDtoCreate;
import gov.iti.sakila.business.services.dtos.film.FilmDtoWithCountForStore;
import gov.iti.sakila.business.services.dtos.store.StoreDto;
import gov.iti.sakila.business.services.dtos.store.StoreInventoryDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.Date;
import java.util.List;

@Path("films")
public class FilmEndPoint {
    private final FilmService filmServiceImpl = new FilmServiceImpl();

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createFilm(FilmDtoCreate filmDtoCreate, @Context UriInfo uriInfo) {
        try {
            FilmDto film = filmServiceImpl.createFilm(filmDtoCreate);
            Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
            return Response.ok().links(self).status(Response.Status.OK).entity(film).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findFilmById(@PathParam("id") Short filmId, @Context UriInfo uriInfo) {
        try {
            FilmDto film = filmServiceImpl.findFilmById(filmId);
            Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
            Link getActors = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder().path("actors")).rel("actors").build();
            Link getCategories = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder().path("categories")).rel("categories").build();
            return Response.ok().links(getActors,self,getCategories).status(Response.Status.OK).entity(film).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteFilmById(@QueryParam("filmId") Short filmId) {
        try {
            filmServiceImpl.deleteFilmById(filmId);
            return Response.ok().status(Response.Status.OK).entity("success").build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findAllFilms() {
        try {
            List<FilmDto> films = filmServiceImpl.findAllFilms();
            return Response.ok().status(Response.Status.OK).entity(films).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }

    }

    @Path("{id}/actors")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findFilmActors(@PathParam("id") Short filmId) {
        try {
            List<ActorDto> actors = filmServiceImpl.findFilmActors(filmId);
            return Response.ok().status(Response.Status.OK).entity(actors).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @Path("{id}/language")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findFilmLanguage(@PathParam("id") Short filmId) {
        try {
            String language = filmServiceImpl.findFilmLanguage(filmId);
            return Response.ok().status(Response.Status.OK).entity(language).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @Path("{id}/original-language")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findFilmOriginalLanguage(@PathParam("id") Short filmId) {
        try {
            String language = filmServiceImpl.findFilmOriginalLanguage(filmId);
            return Response.ok().status(Response.Status.OK).entity(language).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Path("release-year/{releaseYear}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findFilmByReleaseYear(@PathParam("releaseYear") Date releaseYear) {
        try {
            List<FilmDto> films = filmServiceImpl.findFilmByReleaseYear(releaseYear);
            return Response.ok().status(Response.Status.OK).entity(films).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @Path("{id}/categories")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findFilmCategories(@PathParam("id") Short filmId) {
        try {
            List<String> categories = filmServiceImpl.findFilmCategories(filmId);
            return Response.ok().status(Response.Status.OK).entity(categories).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @Path("rating/{rating}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findByRating(@PathParam("rating") String rating) {
        try {
            List<FilmDto> films = filmServiceImpl.findByRating(rating);
            return Response.ok().status(Response.Status.OK).entity(films).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }


    }

    @Path("rental-duration/{rentalDuration}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findByRentalDuration(@PathParam("rentalDuration") String rentalDuration) {
        try {
            List<FilmDto> films = filmServiceImpl.findByRentalDuration(rentalDuration);
            return Response.ok().status(Response.Status.OK).entity(films).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Path("replacement-cost/{replacementCost}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findFilmByReplacementCost(@PathParam("replacementCost") String replacementCost) {
        try {
            List<FilmDto> films = filmServiceImpl.findFilmByReplacementCost(replacementCost);
            return Response.ok().status(Response.Status.OK).entity(films).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }
    @Path("length/{length}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findFilmByLength(@PathParam("length") Short length) {
        try {
            List<FilmDto> films = filmServiceImpl.findFilmByLength(length);
            return Response.ok().status(Response.Status.OK).entity(films).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
    @Path("{id}/customers")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findFilmCustomers(@PathParam("id") Short filmId) {
        try {
            List<CustomerDto> customers = filmServiceImpl.findFilmCustomers(filmId);
            return Response.ok().status(Response.Status.OK).entity(customers).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
    @Path("{id}/stores")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findFilmStores(@PathParam("id")  Short filmId) {
        try {
            List<StoreDto> stores = filmServiceImpl.findFilmStores(filmId);
            return Response.ok().status(Response.Status.OK).entity(stores).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
    @Path("{id}/actors/{actorId}")
    @PUT
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addActorToFilm(@PathParam("id") Short filmId,@PathParam("actorId") Short actorId) {
        try {
           filmServiceImpl.addActorToFilm(filmId, actorId);
            return Response.ok().status(Response.Status.OK).entity("success").build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }
    @Path("{id}/categories/{categoryId}")
    @PUT
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addCategoryToFilm(@PathParam("id") Short filmId, @PathParam("categoryId")Short categoryId) {
        try {
            filmServiceImpl.addCategoryToFilm(filmId, categoryId);
            return Response.ok().status(Response.Status.OK).entity("success").build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }
    @Path("title/{title}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findFilmByTitle(@PathParam("title") String title) {
        try {
            List<FilmDto> film = filmServiceImpl.findFilmByTitle(title);
            return Response.ok().status(Response.Status.OK).entity(film).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
    @Path("{id}/actors-number")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response  findNumberOfActors(@PathParam("id") Short filmId) {
        try {
            FilmDtoWithCountForStore film = filmServiceImpl.findNumberOfActors(filmId);
            return Response.ok().status(Response.Status.OK).entity(film).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
    @Path("{id}/stock-count")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findFilmCountInStock(@PathParam("id")Short filmId) {
        try {
            Long count = filmServiceImpl.findFilmCountInStock(filmId);
            return Response.ok().status(Response.Status.OK).entity(count).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }
    @Path("{id}/rental-count")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findFilmRentalCount(@PathParam("id")Short filmId) {
        try {
            Long count = filmServiceImpl.findFilmRentalCount(filmId);
            return Response.ok().status(Response.Status.OK).entity(count).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }
    @Path("{id}/available-inventory")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findWhereFilmAvailable(@PathParam("id") Short filmId) {
        try {
            List<StoreInventoryDto> storeInventoryDtos = filmServiceImpl.findWhereFilmAvailable(filmId);
            return Response.ok().status(Response.Status.OK).entity(storeInventoryDtos).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }
    @Path("{id}/available-stores")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findFilmAvailableInWhichStore(@PathParam("id") Short filmId) {
        try {
            List<StoreDto> stores = filmServiceImpl.findFilmAvailableInWhichStore(filmId);
            return Response.ok().status(Response.Status.OK).entity(stores).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }
}
