package com.cxspace.ssm.controller;
import com.cxspace.ssm.model.Comment;
import com.cxspace.ssm.model.CommentAndUser;
import com.cxspace.ssm.model.User;
import com.cxspace.ssm.service.CommentService;
import com.cxspace.ssm.service.UserService;
import com.cxspace.ssm.utils.DateTimeHelper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liujie on 2017/8/20.
 */


@RestController

public class CommentController {

    @Resource
    private CommentService commentService;

    @Resource
    private UserService userService;


    /**
     * 查询评论列表
     *
     *
     *
     * @param comment
     * @param builder
     * @return
     * @throws Exception
     */

    @RequestMapping(value = "/comment_select",method = RequestMethod.POST)
    ResponseEntity<List<CommentAndUser>> commentSelect(@RequestBody Comment comment , UriComponentsBuilder builder) throws Exception {

        List<CommentAndUser> commentAndUsers = new ArrayList<CommentAndUser>();

        List<Comment> commentList = new ArrayList<Comment>();

        commentList = commentService.selectCommentsByShareId(comment.getShare_id());

        //关联评论和评论相关的用户信息

        for (int i = commentList.size() - 1 ; i >= 0 ; i--){

            CommentAndUser commentAndUser = new CommentAndUser();

            User user = new User();
            user.setId(commentList.get(i).getUser_id());
            user = userService.select(user);

            commentAndUser.setUser_name(user.getName());
            commentAndUser.setUser_imgsrc(user.getImgsrc());
            commentAndUser.setComment_id(commentList.get(i).getId());
            commentAndUser.setComment_content(commentList.get(i).getContent());
            commentAndUser.setComment_create_time(commentList.get(i).getCreate_time());

            commentAndUsers.add(commentAndUser);

        }

        HttpHeaders headers = new HttpHeaders();
        //允许跨域请求
        headers.setAccessControlAllowOrigin("*");

        return new ResponseEntity<List<CommentAndUser>>(commentAndUsers,headers,HttpStatus.OK);
    }


    /**
     * 添加评论信息
     *
     * @param comment
     * @param builder
     * @return
     */

    @RequestMapping(value = "/comment_insert", method = RequestMethod.POST)
    ResponseEntity<Comment> commentInsert(@RequestBody Comment comment , UriComponentsBuilder builder){

        Comment returnComment = new Comment();

        comment.setCreate_time(DateTimeHelper.getCurrentTime());
        comment.setStatus(1);

        try {
            commentService.insert(comment);
            returnComment.setContent("success");
        } catch (Exception e) {
            e.printStackTrace();
            returnComment.setContent("error");
        }

        HttpHeaders headers = new HttpHeaders();
        //允许跨域请求
        headers.setAccessControlAllowOrigin("*");

        return new ResponseEntity<Comment>(returnComment,headers, HttpStatus.OK);
    }


}
