package com.common.res;

import com.common.db.dao.impl.CompanyDao;
import com.common.db.dao.impl.TransactionInfoDao;
import com.common.db.entity.Company;
import com.common.db.entity.TransactionInfo;
import com.sun.jersey.spi.resource.Singleton;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Kirill Stoianov on 06/09/17.
 */
@Path("/debit")
public class DebitResource {


    private CompanyDao companyDao;
    private TransactionInfoDao transactionInfoDao;

    public DebitResource() {
        this.companyDao = new CompanyDao();
        this.transactionInfoDao = new TransactionInfoDao();
    }

    @POST
    @Path("/{companyId}/{debitValue}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response debit(@PathParam("companyId") long companyId,
                          @PathParam("debitValue") double debitValue) {

        Company company = null;

        //check is company exist in database
        final boolean exists = this.companyDao.isExists(companyId);

        //send error if company is not found in database
        if (!exists) {
            return Response.status(400).entity(new Body("Company does not exists")).build();
        }

        //get destination company from db
        company = this.companyDao.findById(companyId);

        //calculate new company  balance
        final double currentBalance = company.getBalance() + debitValue;


        //if current operation leads to negative balance - send error
        if (currentBalance<0){
            TransactionInfo transactionInfo = new TransactionInfo();
            transactionInfo.setAction("debit");
            transactionInfo.setCompany(company.getId());
            transactionInfo.setValue(debitValue);
            transactionInfo.setBalance(company.getBalance());
            transactionInfo.setStatus("refused");
            this.transactionInfoDao.save(transactionInfo);
            return Response.status(400).entity(new Body("Operation leads to negative balance. Operation refused.")).build();
        }


        //update company with new balance value
        company.setBalance(currentBalance);
        this.companyDao.saveOrUpdate(company);

        //save transaction info
        TransactionInfo transactionInfo = new TransactionInfo();
        transactionInfo.setAction("debit");
        transactionInfo.setCompany(company.getId());
        transactionInfo.setValue(debitValue);
        transactionInfo.setBalance(company.getBalance());
        transactionInfo.setStatus("successful");
        this.transactionInfoDao.save(transactionInfo);


        //get updated company
        company = this.companyDao.findById(companyId);

        return Response.status(200).entity(new Body("successful")).build();
    }

}
