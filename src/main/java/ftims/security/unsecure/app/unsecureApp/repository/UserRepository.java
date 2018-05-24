package ftims.security.unsecure.app.unsecureApp.repository;

import ftims.security.unsecure.app.unsecureApp.config.DBConnection;
import ftims.security.unsecure.app.unsecureApp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by bryka on 24.05.18.
 */
@Component
public class UserRepository {

    @Autowired
    DBConnection dbConnection;

    public User findUserByUsernameAndPassword(String username, String password) {
        PreparedStatement ps = null;
        User user = null;
        try {
            String sql = "Select * from user where username = '"+username+"' and password = '"+password+"'";
            ps = dbConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                user = new User();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }

            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.print("SQLException");
        } finally {
            return user;
        }
    }
}
