package com.common.db.dao.impl;

import com.common.db.dao.GenericDaoImpl;
import com.common.db.entity.TransactionInfo;

/**
 * Created by Kirill Stoianov on 06/09/17.
 */
public class TransactionInfoDao extends GenericDaoImpl<TransactionInfo,Long> {
    @Override
    protected Class<TransactionInfo> getEntityClass() {
        return TransactionInfo.class;
    }
}
