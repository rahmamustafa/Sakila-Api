package gov.iti.sakila.business.endpoints.rest;

import gov.iti.sakila.business.services.StoreService;
import gov.iti.sakila.business.services.servicesimpl.StoreServiceImpl;
import gov.iti.sakila.business.services.dtos.AddressDto;
import gov.iti.sakila.business.services.dtos.RentalDto;
import gov.iti.sakila.business.services.dtos.StaffDto;
import gov.iti.sakila.business.services.dtos.film.FilmDto;
import gov.iti.sakila.business.services.dtos.store.StoreDto;
import gov.iti.sakila.business.services.dtos.store.StoreDtoCreate;
import jakarta.jws.WebParam;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;

@Path("stores")
public class StoreEndPoint {

    private final StoreService storeService = new StoreServiceImpl();

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createStore(@WebParam(name = "store") StoreDtoCreate storeDtoCreate) {
        try {
            StoreDto store = storeService.createStore(storeDtoCreate);
            return Response.ok().status(Response.Status.OK).entity(store).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findStoreById(@PathParam("id") Short storeId, @Context UriInfo uriInfo) {
        try {
            StoreDto store = storeService.findStoreById(storeId);
            Link getFilms = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder().path("films")).rel("films").build();
            return Response.ok().links(getFilms).status(Response.Status.OK).entity(store).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteStoreById(@QueryParam("id") Short storeId) {
        try {
            storeService.deleteStoreById(storeId);
            return Response.ok().status(Response.Status.OK).entity("success").build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findAllStores() {
        try {
            List<StoreDto> stores = storeService.findAllStores();
            return Response.ok().status(Response.Status.OK).entity(stores).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @Path("{id}/manager-staff")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findStoreManagerStaff(@PathParam("id") Short storeId) {
        try {
            StaffDto staffDto = storeService.findStoreManagerStaff(storeId);
            return Response.ok().status(Response.Status.OK).entity(staffDto).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Path("{id}/address")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findStoreAddress(@PathParam("id") Short storeId) {
        try {
            AddressDto address = storeService.findStoreAddress(storeId);
            return Response.ok().status(Response.Status.OK).entity(address).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @Path("{id}/films")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findStoreFilms(@PathParam("id") Short storeId) {
        try {
            List<FilmDto> films = storeService.findStoreFilms(storeId);
            return Response.ok().status(Response.Status.OK).entity(films).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Path("{id}/rentals")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findStoreRentals(@PathParam("id") Short storeId) {
        try {
            List<RentalDto> rentals = storeService.findStoreRentals(storeId);
            return Response.ok().status(Response.Status.OK).entity(rentals).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Path("{id}/films-count")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findNumberStoreFilms(@PathParam("id") Short storeId) {
        try {
            int rentalsCount = storeService.findNumberStoreFilms(storeId);
            return Response.ok().status(Response.Status.OK).entity(rentalsCount).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Path("rentals-count")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findRentalCountAllStores() {
        try {
            long rentalsCount = storeService.findRentalCountAllStores();
            return Response.ok().status(Response.Status.OK).entity(rentalsCount).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Path("{id}/rentals-count")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findRentalCountInStore(@PathParam("id") Short storeId) {
        try {
            long rentalsCount = storeService.findRentalCountInStore(storeId);
            return Response.ok().status(Response.Status.OK).entity(rentalsCount).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @Path("film-count/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findFilmCountInStores(@PathParam("id") Short filmId) {
        try {
            long rentalsCount = storeService.findFilmCountInStores(filmId);
            return Response.ok().status(Response.Status.OK).entity(rentalsCount).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Path("{id}/film-count/{filmId}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findFilmCountInStore(@PathParam("filmId") Short filmId, @PathParam("id") Short storeId) {
        try {
            long rentalsCount = storeService.findFilmCountInStore(filmId, storeId);
            return Response.ok().status(Response.Status.OK).entity(rentalsCount).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

}
