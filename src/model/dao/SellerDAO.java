package model.dao;

import model.entities.Department;
import model.entities.Seller;

import java.util.List;

public interface SellerDAO {

    void insert(Seller obj);
    void updateById(Seller obj);
    void deleteById(Integer id);
    Seller findByID(Integer id);
    List<Seller> findByDepartment(Department obj);
    List<Seller> findAll();
}
