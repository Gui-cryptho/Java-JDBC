package model.dao;

import model.dao.impl.DepartmentDAOJDBC;
import model.dao.impl.SellerDAOJDBC;

public class DAOfactory {

    public static DepartmentDAO createDepartmentDAO(){
        return new DepartmentDAOJDBC();
    }

    public static SellerDAO createSellerDAO(){
        return new SellerDAOJDBC();
    }
}
