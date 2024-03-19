package org.example;

import java.sql.SQLException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class GoodsDAOImpl implements GoodsDAO{
    private static final Logger logger = LogManager.getLogger(GoodsDAOImpl.class);
    @Override
    public void addGood(Good good) {
        try {
            TransactionWrapper transactionWrapper = new TransactionWrapper(ConenctionPool.getInstance());
        }
    catch (InterruptedException | SQLException e){
        logger.error(e.getMessage());
    }
    }

    @Override
    public Good getGoodById(int id) {
        return null;
    }

    @Override
    public List<Good> getAllGoods() {
        return null;
    }

    @Override
    public void deleteGood(int id) {

    }
}
