package model.dao;

import db.DB;
import model.dao.impl.DepartmentDAOJDBC;
import model.dao.impl.SellerDAOJDBC;

public class DAOfactory {

    public static DepartmentDAO createDepartmentDAO(){
        return new DepartmentDAOJDBC(DB.getConnection());
    }

    public static SellerDAO createSellerDAO(){
        return new SellerDAOJDBC(DB.getConnection());
    }
}
