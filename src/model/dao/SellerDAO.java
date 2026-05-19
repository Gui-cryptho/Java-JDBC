package model.dao;

import model.entities.Department;
import model.entities.Seller;

import java.util.List;

public interface SellerDAO {

    void insert(Seller obj);
    void updateById(Integer id);
    void deleteById(Integer id);
    Seller findByID(Integer id);
    Seller findByDepartment(Department obj);
    List<Seller> findAll();
}
