package model.dao.impl;

import model.dao.DepartmentDAO;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.util.List;

public class DepartmentDAOJDBC implements DepartmentDAO {

    private Connection conn = null;

    public DepartmentDAOJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {

    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void deleteByID(Integer id) {

    }

    @Override
    public void findById(Integer id) {

    }

    @Override
    public List<Department> findAll() {
        return null;
    }
}
