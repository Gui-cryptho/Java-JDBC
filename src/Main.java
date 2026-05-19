import db.DB;
import model.dao.DAOfactory;
import model.dao.DepartmentDAO;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        //Connection conn = DB.getConnection();

        SellerDAO sellerDAO = DAOfactory.createSellerDAO();

        // ==== 1º findById ===
        Seller seller = new Seller();
        seller = sellerDAO.findByID(2);
        System.out.println(seller);
        System.out.println();

        // ==== 2º findByDepartment ===
        List<Seller> sellerList = new ArrayList<>();
        Department dep = new Department(3, null);
        sellerList = sellerDAO.findByDepartment(dep);

        for(Seller obj : sellerList){
            System.out.println(obj);
        }
        System.out.println();

        // ==== 3º findAll ===
        sellerList = sellerDAO.findAll();
        for (Seller obj : sellerList){
            System.out.println(obj);
        }
        System.out.println();

        // === 4º insert ===
        /*
        Department dep1 = new Department(2, "Electronics");

        Seller seller1 = new Seller(
                null,
                "Gustavo Souza",
                "gustavo@gmail.com",
                LocalDate.of(2003, 5, 10),
                4000.0,
                dep1
        );

        sellerDAO.insert(seller1);
        */

        // === 5º update ===
        seller.setName("Jão Brown");
        sellerDAO.updateById(seller);

        // === 6º deleteById
        sellerDAO.deleteById(17);

        DB.closeConnection();
        //System.out.println("C:\\Users\\guilh\\IdeaProjects\\jdbc-git");
    }
}