package gov.iti.sakila.business.endpoints.rest;

import gov.iti.sakila.business.services.CustomerService;
import gov.iti.sakila.business.services.servicesimpl.CustomerServiceImpl;
import gov.iti.sakila.business.services.dtos.customer.CustomerDto;
import gov.iti.sakila.business.services.dtos.customer.CustomerDtoCreate;
import gov.iti.sakila.business.services.dtos.film.FilmDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Path("customers")
public class CustomerEndPoint {
    private final CustomerService customerServiceImpl = new CustomerServiceImpl();

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createCustomer(CustomerDtoCreate customerDtoCreate , @Context UriInfo uriInfo) {
        try {
            CustomerDto customer = customerServiceImpl.createCustomer(customerDtoCreate);
            Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
            return Response.ok().links(self).status(Response.Status.OK).entity(customer).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findCustomerById(@PathParam("id") Short customerId,  @Context UriInfo uriInfo) {
        try {
            CustomerDto customer = customerServiceImpl.findCustomerById(customerId);
            Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
            Link getRentedFilms = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder().path("rented-films")).rel("rented-films").build();
            Link getUnreturnedFilms = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder().path("unreturned-films")).rel("unreturned-films").build();
            return Response.ok().links(getRentedFilms,getUnreturnedFilms,self).status(Response.Status.OK).entity(customer).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @DELETE
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteCustomerById(@QueryParam("customerId") Short customerId) {
        try {
            customerServiceImpl.deleteCustomerById(customerId);
            return Response.ok().status(Response.Status.OK).entity("success").build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findAllCustomers() {
        try {
            List<CustomerDto> customers = customerServiceImpl.findAllCustomers();
            return Response.ok().status(Response.Status.OK).entity(customers).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }

    }

    @Path("first-name")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findCustomerByFirstName(@QueryParam("firstName") String firstName) {
        try {
            List<CustomerDto> customers = customerServiceImpl.findCustomerByFirstName(firstName);
            return Response.ok().status(Response.Status.OK).entity(customers).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Path("email")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findCustomerByEmail(@QueryParam("email") String email) {
        try {
            List<CustomerDto> customers = customerServiceImpl.findCustomerByEmail(email);
            return Response.ok().status(Response.Status.OK).entity(customers).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @Path("active")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findActiveCustomers(@QueryParam("active") Boolean active) {
        try {
            List<CustomerDto> customers = customerServiceImpl.findActiveCustomers(active);
            return Response.ok().status(Response.Status.OK).entity(customers).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @Path("create-date")
    @GET
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findCustomerByCreateDate(String createDate) {
        try {
            //2006-02-15T02:57:20Z[UTC]
            //"2010-07-28T22:25:51Z"
            DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
            if (createDate.contains("Z"))
                df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            else if (createDate.contains(":"))
                df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:s");

            Date result1 = df1.parse(createDate);
            System.out.println(result1);
            List<CustomerDto> customers = customerServiceImpl.findCustomerByCreateDate(result1);
            return Response.ok().status(Response.Status.OK).entity(customers).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Path("{id}/rented-films")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findCustomerRentedFilms(@PathParam("id") Short customerId) {
        try {
            List<FilmDto> films  = customerServiceImpl.findCustomerRentedFilms(customerId);
            return Response.ok().status(Response.Status.OK).entity(films).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @Path("{id}/unreturned-films")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findCustomerUnReturnedFilms(@PathParam("id") Short customerId) {
        try {
            List<FilmDto> films  = customerServiceImpl.findCustomerUnReturnedFilms(customerId);
            return Response.ok().status(Response.Status.OK).entity(films).build();
        } catch (NotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }catch (IllegalArgumentException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
