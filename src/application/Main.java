package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        SellerDao sellerDao = DaoFactory.creatSellerDao();
        System.out.println("=== TEST1: seler find by id ===");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);




    }
}
