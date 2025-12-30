package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.util.List;

public class Main2 {
    public static void main(String[] args) {
        DepartmentDao departmentDao = DaoFactory.creatDepartmentDao();

        List<Department> list  = departmentDao.findAll();
        list.forEach(System.out::println);





    }
}
