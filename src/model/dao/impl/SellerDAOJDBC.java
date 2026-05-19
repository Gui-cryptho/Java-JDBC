package model.dao.impl;

import db.DB;
import db.ExceptionDB;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDAOJDBC implements SellerDAO {

    private Connection conn = null;

    public SellerDAOJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("INSERT INTO seller "+
                    "(Name, Email, BirthDate, BaseSalary, DepartmentId) "+
                    "VALUES "+
                    "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getName());
            st.setString(2,obj.getEmail());
            st.setDate(3, java.sql.Date.valueOf(obj.getBirthDate()));
            st.setDouble(4, obj.getBaseSalary());
            st.setInt(5, obj.getDepartment().getId());

           int rowsAffected = st.executeUpdate();

           if (rowsAffected > 0){
               rs = st.getGeneratedKeys();
               if (rs.next()){
                   int id = rs.getInt(1);
                   obj.setId(id);
               }
               DB.closeResultSet(rs);
           }else {
               throw new ExceptionDB("Error²! No rows Affected");
           }


        }catch (SQLException e){
            throw new ExceptionDB(e.getMessage());
        }finally {
            DB.closeStatment(st);
        }

    }

    @Override
    public void updateById(Seller obj) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("UPDATE seller "+
                    "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "+
                    "WHERE Id = ?");

            st.setString(1, obj.getName());
            st.setString(2, obj.getEmail());
            st.setDate(3, java.sql.Date.valueOf(obj.getBirthDate()));
            st.setDouble(4, obj.getBaseSalary());
            st.setInt(5, obj.getDepartment().getId());
            st.setInt(6, obj.getId());

            st.executeUpdate();

        }catch (SQLException e){
            throw new ExceptionDB(e.getMessage());
        }finally {
            DB.closeStatment(st);
        }

    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("DELETE FROM seller "+
                    "WHERE Id = ?");

            st.setInt(1, id);

            int rowsAffected = st.executeUpdate();

            if (rowsAffected == 0) {
                throw new ExceptionDB("Id not found!");
            }

        }catch (SQLException e){
            throw new ExceptionDB(e.getMessage());
        }finally {
            DB.closeStatment(st);
        }

    }

    @Override
    public Seller findByID(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT seller.*,department.Name as DepName " +
                    "FROM seller INNER JOIN department " +
                    "ON seller.DepartmentId = department.Id " +
                    "WHERE seller.Id = ?");

            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Department dep = createDepartment(rs);
                Seller seller = createSeller(rs, dep);

                return seller;
            }

            return  null;

        } catch (SQLException e) {
            throw new ExceptionDB(e.getMessage());
        }finally {
            DB.closeStatment(st);
            DB.closeResultSet(rs);
        }

    }
    private Department createDepartment(ResultSet rs) throws SQLException{
        Department aux = new Department();
        aux.setName(rs.getString("DepName"));
        aux.setId(rs.getInt("DepartmentID"));

        return aux;
    }

    private Seller createSeller(ResultSet rs, Department dep) throws SQLException{
        Seller aux = new Seller();
        aux.setName(rs.getString("Name"));
        aux.setEmail(rs.getString("Email"));
        aux.setId(rs.getInt("Id"));
        aux.setBirthDate(rs.getDate("BirthDate").toLocalDate());
        aux.setBaseSalary(rs.getDouble("BaseSalary"));
        aux.setDepartment(dep);

        return aux;
    }

    @Override
    public List<Seller> findByDepartment(Department obj) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            st = conn.prepareStatement("SELECT seller.*,department.Name as DepName "+
                    "FROM seller INNER JOIN department "+
                    "ON seller.DepartmentId = department.Id "+
                    "WHERE DepartmentId = ? "+
                    "ORDER BY Name");

            st.setInt(1, obj.getId());
            rs = st.executeQuery();

            List<Seller> sellerList = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while (rs.next()){
                Department dep = map.get(rs.getInt("departmentId"));

                if(dep == null){
                    dep = createDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }

                Seller seller = createSeller(rs, dep);
                sellerList.add(seller);

            }
            return sellerList;

        }catch (SQLException e){
            throw new ExceptionDB(e.getMessage());
        }finally {
            DB.closeStatment(st);
            DB.closeResultSet(rs);
        }

    }

    @Override
    public List<Seller> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT seller.*,department.Name as DepName "+
                    "FROM seller INNER JOIN department "+
                    "ON seller.DepartmentId = department.Id "+
                    "ORDER BY Name");

            rs = st.executeQuery();

            List<Seller> sellerList = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while (rs.next()){

                Department dep = map.get(rs.getInt("DepartmentId"));

                if(dep == null){
                    dep = createDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }

                Seller seller = createSeller(rs, dep);
                sellerList.add(seller);
            }

            return sellerList;

        }catch (SQLException e){
            throw new ExceptionDB(e.getMessage());
        }finally {
            DB.closeStatment(st);
            DB.closeResultSet(rs);

        }
    }
}
