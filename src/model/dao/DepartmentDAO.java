package model.dao;

import model.entities.Department;
import model.entities.Seller;

import java.util.List;

public interface DepartmentDAO {

    void insert(Seller obj);
    void update(Seller obj);
    void deleteByID(Integer id);
    void findById(Integer id);
    List<Department> findAll();
}
