package org.example;

import java.util.List;

public interface BlackListDAO {
    void addClientToBlackList(BlackListElement good);
    BlackListElement getBlackListElementId(int id);
    List<BlackListElement> getAllBlackList();
    void deleteBlackListElement(int id);
}
