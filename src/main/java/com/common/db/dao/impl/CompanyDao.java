package com.common.db.dao.impl;

import com.common.db.dao.GenericDaoImpl;
import com.common.db.entity.Company;

/**
 * Created by Kirill Stoianov on 06/09/17.
 */
public class CompanyDao extends GenericDaoImpl<Company,Long>{
    @Override
    protected Class<Company> getEntityClass() {
        return Company.class;
    }
}
