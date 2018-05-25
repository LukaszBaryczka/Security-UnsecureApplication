package ftims.security.unsecure.app.unsecureApp.service.impl;

import ftims.security.unsecure.app.unsecureApp.model.Comment;
import ftims.security.unsecure.app.unsecureApp.repository.UserRepository;
import ftims.security.unsecure.app.unsecureApp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultCommentService implements CommentService{


    @Autowired
    UserRepository userRepository;


    @Override
    public void setNewComment(Comment comment) {
        userRepository.setNewComment(comment);
    }

    @Override
    public List<Comment> findAllComments() {
        return userRepository.findAllComments();
    }
}
