package gov.iti.sakila.business.endpoints.rest;

import gov.iti.sakila.business.services.InventoryService;
import gov.iti.sakila.business.services.servicesimpl.InventoryServiceImpl;
import gov.iti.sakila.business.services.dtos.InventoryDto;
import gov.iti.sakila.business.services.dtos.film.StoreFilmInventoryDto;
import gov.iti.sakila.business.services.dtos.store.StoreInventoryDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;

@Path("inventories")
public class InventoryEndPoint {
    InventoryService inventoryService = new InventoryServiceImpl();

    @PUT
    @Consumes("application/x-www-form-urlencoded")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createInventory(@FormParam("filmId") Short filmId, @FormParam("storeId") Short storeId , @Context UriInfo uriInfo) {
        try {
            InventoryDto inventory = inventoryService.createInventory(filmId, storeId);
            Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
            return Response.ok().links(self).status(Response.Status.OK).entity(inventory).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findInventoryById(@PathParam("id") Short inventoryId , @Context UriInfo uriInfo) {
        try {
            InventoryDto inventory = inventoryService.findInventoryById(inventoryId);
            Link getFilms = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder().path("available-films")).rel("available-films").build();
            return Response.ok().links(getFilms).status(Response.Status.OK).entity(inventory).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteInventoryById(@QueryParam("inventoryId") Short inventoryId) {
        try {
            inventoryService.deleteInventoryById(inventoryId);
            return Response.ok().status(Response.Status.OK).entity("success").build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findAllInventories() {
        try {
            List<InventoryDto> inventories = inventoryService.findAllInventories();
            return Response.ok().status(Response.Status.OK).entity(inventories).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @Path("available-films")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findAllAvailableFilmInventory() {
        try {
            List<StoreFilmInventoryDto> inventories = inventoryService.findAllAvailableFilmInventory();
            return Response.ok().status(Response.Status.OK).entity(inventories).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @Path("film-inventory/{filmId}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findFilmRentedInventory(@PathParam("filmId") Short filmId) {
        try {
            List<StoreInventoryDto> inventories = inventoryService.findFilmRentedInventory(filmId);
            return Response.ok().status(Response.Status.OK).entity(inventories).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
        catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
    @Path("rented-inventory")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findAllRentedInventory() {
        try {
            List<InventoryDto> inventories = inventoryService.findAllRentedInventory();
            return Response.ok().status(Response.Status.OK).entity(inventories).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
