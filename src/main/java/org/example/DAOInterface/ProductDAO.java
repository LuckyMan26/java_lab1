package org.example.DAOInterface;

import org.example.connections.ConnectionWrapper;
import org.example.models.Product;

import java.sql.Connection;
import java.util.List;

public interface ProductDAO {
    public void addGood(Product product, ConnectionWrapper connection);
    public Product getGoodById(Long id, ConnectionWrapper connection);
    public List<Product> getAllGoods( ConnectionWrapper connection);
    public void deleteGood(Long id, ConnectionWrapper connection);
    public void decreaseQuantity(Long product_id, int num, ConnectionWrapper connection);
}

