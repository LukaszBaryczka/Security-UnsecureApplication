package ftims.security.unsecure.app.unsecureApp.service;

import ftims.security.unsecure.app.unsecureApp.model.Comment;

import java.util.List;

public interface CommentService {


    public void setNewComment(Comment comment);

    public List<Comment> findAllComments();
}
