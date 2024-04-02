package org.example.DAOInterface;

import org.example.models.Product;

import java.util.List;

public interface ProductDAO {
    void addGood(Product product);
    Product getGoodById(int id);
    List<Product> getAllGoods();
    void deleteGood(int id);

}
