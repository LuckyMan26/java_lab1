package org.example;
import org.example.ConenctionPool;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionWrapper implements Closeable {
    private final Connection connection;
    private final PreparedStatement beginTransactionStatement;
    private final PreparedStatement commitTransactionStatement;
    private final PreparedStatement rollbackTransactionStatement;
    private final ConenctionPool pool;
    TransactionWrapper(ConenctionPool pool) throws SQLException, InterruptedException {
        this.pool = pool;
        this.connection = this.pool.getConnection();
        try {
            beginTransactionStatement = connection.prepareStatement("BEGIN");
            commitTransactionStatement = connection.prepareStatement("COMMIT");
            rollbackTransactionStatement = connection.prepareStatement("ROLLBACK");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public PreparedStatement prepareStatement(String command) {

        try {
            return connection.prepareStatement(command);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public <T> T executeTransaction(TransactionOperation<T> transaction) {

        try {
            connection.setAutoCommit(false);
            beginTransactionStatement.execute();

            T result = transaction.execute(connection);
            commitTransactionStatement.execute();
            connection.setAutoCommit(true);

            return result;
        } catch (Exception e) {
            try {
                rollbackTransactionStatement.execute();
                connection.setAutoCommit(true);
            } catch (SQLException sqlException) {
                throw new RuntimeException(sqlException);
            }
            throw new RuntimeException(e);
        }
    }
    @Override
    public void close() {
        synchronized (pool) {
                pool.releaseConnection(connection);
        }
    }
    public interface TransactionOperation<T> {
        T execute(Connection connection) throws SQLException;
    }
}
