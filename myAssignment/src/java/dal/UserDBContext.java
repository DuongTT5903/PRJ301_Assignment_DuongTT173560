    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.User;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Lecturer;
import model.Role;

/**
 *
 * @author sonnt-local
 */
public class UserDBContext extends DBContext<User> {



   public User getUserByUsernamePassword(String username, String password, int roleid) {
    PreparedStatement stm = null;
    User user = null;
    try {
        String sql = "SELECT u.username, u.displayname, l.lid, l.lname, r.roleid, r.rolename " +
                     "FROM users u " +
                     "LEFT JOIN users_lecturers ul ON ul.username = u.username AND ul.active = 1 " +
                     "LEFT JOIN lecturers l ON ul.lid = l.lid " +
                     "LEFT JOIN roles ur ON ur.roleid = u.roleid " +
                     "JOIN roles r ON ur.roleid = r.roleid " +
                     "WHERE u.username = ? AND u.[password] = ? AND r.roleid = ?";

        stm = connection.prepareStatement(sql);
        stm.setString(1, username);
        stm.setString(2, password);
        stm.setInt(3, roleid);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            user = new User();
            user.setDisplayname(rs.getString("displayname"));
            user.setUsername(username);
            int lid = rs.getInt("lid");
            if (lid != 0) {
                Lecturer lecturer = new Lecturer();
                lecturer.setId(lid);
                lecturer.setName(rs.getString("lname"));
                user.setLecturer(lecturer);
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        try {
            if (stm != null) {
                stm.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    return user;
}

public List<Role> getRoles() {
    PreparedStatement stm = null;
    List<Role> roles = new ArrayList<>();
    try {
        String sql = "SELECT roleid, rolename FROM roles";
        stm = connection.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            int roleid = rs.getInt("roleid");
            String rolename = rs.getString("rolename");
            Role role = new Role(roleid, rolename);
            roles.add(role);
        }
    } catch (SQLException ex) {
        Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        try {
            if (stm != null) {
                stm.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    return roles;
}
    @Override
    public void insert(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<User> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
