import db.DB;
import model.dao.DAOfactory;
import model.dao.DepartmentDAO;
import model.dao.SellerDAO;

import java.sql.Connection;
import java.sql.Statement;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        //Connection conn = DB.getConnection();

        SellerDAO sellerDAO = DAOfactory.createSellerDAO();

        DB.closeConnection();

        //System.out.println("C:\\Users\\guilh\\IdeaProjects\\jdbc-git");
    }
}