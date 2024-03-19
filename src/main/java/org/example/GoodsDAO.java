package org.example;

import java.util.List;

public interface GoodsDAO {
    void addGood(Good good);
    Good getGoodById(int id);
    List<Good> getAllGoods();
    void deleteGood(int id);

}
