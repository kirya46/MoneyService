package com.common.res;

import com.common.db.dao.impl.CompanyDao;
import com.common.db.entity.Company;
import com.sun.jersey.spi.resource.Singleton;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Kirill Stoianov on 06/09/17.
 */
@Path("/company")
@Singleton
public class CompanyResource {

    private CompanyDao companyDao;

    public CompanyResource() {
        this.companyDao = new CompanyDao();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/create")
    public Response createCompany(){
        Company company = new Company();
        company.setName("Some company");
        company.setBalance(0);
        final Long save = this.companyDao.save(company);
        final Company savedCompany = this.companyDao.findById(save);
        return Response.status(200).entity(savedCompany).build();
    }
}
