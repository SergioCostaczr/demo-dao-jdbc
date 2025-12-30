package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Department;
import model.entities.Seller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        SellerDao sellerDao = DaoFactory.creatSellerDao();
        System.out.println("=== TEST1: seller find by id ===");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);

        System.out.println("\n=== TEST2: seller findByDepartment ===");
        Department department = new Department(2,null);
        List<Seller> list = sellerDao.findByDepartment(department);
        list.forEach(System.out::println);

        System.out.println("\n=== TEST3: seller findAll ===");
        list = sellerDao.findAll();
        list.forEach(System.out::println);

        System.out.println("\n=== TEST4: seller insert ===");
        Seller seller1 = new Seller(null, "Greg", "greg@gmail.com", LocalDate.now(),4000.0,department);
        sellerDao.insert(seller1);
        System.out.println("Inserted! New id = " + seller1.getId());

        System.out.println("\n=== TEST5: seller update ===");
        seller = sellerDao.findById(1);
        seller.setName("Andre");
        sellerDao.update(seller);

        System.out.println("\n=== TEST6: seller delete ===");
        System.out.print("Enter id for delete test: ");
        int id = sc.nextInt();
        sellerDao.deleteById(id);
        System.out.println("Delete complete");
        sc.close();
    }
}
