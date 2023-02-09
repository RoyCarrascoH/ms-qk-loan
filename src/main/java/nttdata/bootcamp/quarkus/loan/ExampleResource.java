package nttdata.bootcamp.quarkus.loan;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nttdata.bootcamp.quarkus.loan.entity.LoanEntity;
import nttdata.bootcamp.quarkus.loan.service.LoanService;
import org.jboss.logging.Logger;

import java.util.List;

@Path("/api/loan")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExampleResource {

    private static final Logger LOGGER = Logger.getLogger(ExampleResource.class.getName());

    @Inject
    private LoanService loanService;
    @GET
    public List<LoanEntity> getClients() {
        return loanService.listAll();
    }

    @GET
    @Path("{idLoan}")
    public LoanEntity viewClientDetails(@PathParam("idLoan") Long idLoan) {
        LoanEntity entity = loanService.findById(idLoan);
        if (entity == null) {
            throw new WebApplicationException("Client with id of " + idLoan + " does not exist.", 404);
        }
        return entity;
    }

    @POST
    @Transactional
    public Response create(LoanEntity loan) {
        if (loan.getIdLoan() != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }
        loanService.save(loan);
        return Response.ok(loan).status(201).build();
    }

    @DELETE
    @Path("{idClient}")
    @Transactional
    public Response delete(@PathParam("idClient") Long idLoan) {
        LoanEntity entity = loanService.findById(idLoan);
        if (entity == null) {
            throw new WebApplicationException("Loan with id of " + idLoan + " does not exist.", 404);
        }
        loanService.delete(entity.getIdLoan());
        return Response.status(204).build();
    }

}