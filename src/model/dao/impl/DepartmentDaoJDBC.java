package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DepartmentDaoJDBC implements DepartmentDao {
    private Connection conn;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department dp) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO department "
                   +   "(Name) "
                   +   "VALUES "
                   +   "(?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1,dp.getName());

           int rowsAffected = st.executeUpdate();

           if(rowsAffected > 0){
               ResultSet rs = st.getGeneratedKeys();
               if(rs.next()){
                   dp.setId(rs.getInt(1));
               }
           } else {
               throw new DbException("Unexpected error! No rows affected");
           }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }

    }

    @Override
    public void update(Department dp) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE department "
                            +   "SET Name = ? "
                            +   "WHERE Id = ?");

            st.setString(1, dp.getName());
            st.setInt(2, dp.getId());

            st.executeUpdate();

        } catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }

    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM department WHERE Id = ?");
            st.setInt(1,id);
            int rows = st.executeUpdate();
            if (rows == 0){
                throw new DbException("Invalid Id");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
        }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * from department WHERE department.Id = ? ");
            st.setInt(1,id);
            rs = st.executeQuery();

            if(rs.next()){
                Department dp = instantiateDepartment(rs);
                return  dp;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Department> list = new ArrayList<>();
        try {
            st = conn.prepareStatement("SELECT * FROM department");
            rs = st.executeQuery();
            while (rs.next()){
                list.add(instantiateDepartment(rs));
            }
            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("Id"));
        dep.setName(rs.getString("Name"));
        return dep;
    }
}
