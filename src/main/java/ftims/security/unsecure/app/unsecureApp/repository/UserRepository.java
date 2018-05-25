package ftims.security.unsecure.app.unsecureApp.repository;

import ftims.security.unsecure.app.unsecureApp.config.DBConnection;
import ftims.security.unsecure.app.unsecureApp.model.Comment;
import ftims.security.unsecure.app.unsecureApp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public void setNewComment(Comment comment){
        PreparedStatement ps = null;
        try {
            String sql = "insert into comments(username, content) values ('" + comment.getUsername() + "','" + comment.getContent() + "');";
            ps = dbConnection.getConnection().prepareStatement(sql);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException e) {
            System.out.print("SQLException");
        }
    }

    public List<Comment> findAllComments(){
        PreparedStatement ps = null;
        List<Comment> comments = new ArrayList<>();
        try {
            String sql = "Select * from comments";
            ps = dbConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Comment comment = new Comment();
                comment.setUsername(rs.getString("username"));
                comment.setContent(rs.getString("content"));
                comments.add(comment);
            }

            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.print("SQLException");
        } finally {
            return comments;
        }
    }
}
