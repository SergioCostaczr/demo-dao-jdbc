package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.List;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        DepartmentDao departmentDao = DaoFactory.creatDepartmentDao();

        System.out.println("Teste 1: department insert");
        Department departmentInsterted = new Department(null, "Football");
        departmentDao.insert(departmentInsterted);
        System.out.println("Inserted! new Id: "+ departmentInsterted.getId());

        System.out.println("\nTeste 2: department update");
        Department departmentToUpdate = departmentDao.findById(6);
        departmentToUpdate.setName("Movies");
        departmentDao.update(departmentToUpdate);

        System.out.println("\nTeste 3: department deleteById");
        System.out.print("Enter id for delete test: ");
        int id = sc.nextInt();
        departmentDao.deleteById(id);
        System.out.println("Delete complete");

        System.out.println("\nTeste 4: department findById");
        Department departmentFound = departmentDao.findById(1);
        System.out.println(departmentFound);


        System.out.println("\nTeste 5: department findAll");
        List<Department> list;
        list = departmentDao.findAll();
        list.forEach(System.out::println);







    }
}
