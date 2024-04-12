package org.example.DAOInterface;

import org.example.models.Product;

import java.util.List;

public interface ProductDAO {
    public void addGood(Product product);
    public Product getGoodById(Long id);
    public List<Product> getAllGoods();
    public void deleteGood(Long id);

}
