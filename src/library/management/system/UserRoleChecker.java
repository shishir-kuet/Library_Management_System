/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library.management.system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class UserRoleChecker {
     private Connection con;

    public UserRoleChecker() {
        this.con = Connect.Connection(); 
}
    public boolean isAdmin(String userid) {
        String query = "SELECT role FROM login WHERE userid = ?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, userid);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    String role = rs.getString("role");
                    return "admin".equalsIgnoreCase(role);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error checking user role: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
     public static void main(String[] args) {
        UserRoleChecker roleChecker = new UserRoleChecker();
        String userid = "Shishir"; 
        if (roleChecker.isAdmin(userid)) {
            System.out.println("User is an admin.");
        } else {
            System.out.println("User is not an admin.");
        }
    }
}