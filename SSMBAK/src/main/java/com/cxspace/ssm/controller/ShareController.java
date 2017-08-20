package com.cxspace.ssm.controller;

import com.cxspace.ssm.model.Share;
import com.cxspace.ssm.model.ShareAndUser;
import com.cxspace.ssm.model.User;
import com.cxspace.ssm.service.CommentService;
import com.cxspace.ssm.service.ShareService;
import com.cxspace.ssm.service.UserService;
import com.cxspace.ssm.utils.DateTimeHelper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liujie on 2017/8/19.
 */

@RestController

public class ShareController {

    @Resource
    private ShareService shareService;

    @Resource
    private UserService userService;

    @Resource
    private CommentService commentService;

    @RequestMapping(value = "/inc_support",method = RequestMethod.POST)
    public ResponseEntity<Share> inc_support(@RequestBody Share share , UriComponentsBuilder builder){

        Share returnShare  = new Share();

        shareService.inc_support(share.getId());

        HttpHeaders headers = new HttpHeaders();
        //允许跨域请求
        headers.setAccessControlAllowOrigin("*");

        return new ResponseEntity<Share>(returnShare,headers,HttpStatus.OK);
    }

    @RequestMapping(value = "/share_insert",method = RequestMethod.POST)
    public ResponseEntity<Share> shareInsert(@RequestBody Share share, UriComponentsBuilder builder) {

        share.setCreate_time(DateTimeHelper.getCurrentTime());
        share.setSupport(0);
        share.setStatus(1);
        share.setComment_count(0);

        System.out.println(share);

        Share returnShare = new Share();

        try {
            shareService.insert(share);
            returnShare.setContent("success");
        } catch (Exception e) {

            returnShare.setContent("error");
            e.printStackTrace();
        }

        HttpHeaders headers = new HttpHeaders();
        //允许跨域请求
        headers.setAccessControlAllowOrigin("*");

        return new ResponseEntity<Share>(returnShare,headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/round_table",method = RequestMethod.GET)
    public ResponseEntity<List<ShareAndUser>> round_table() throws Exception {

        List<ShareAndUser> shareAndUsers = new ArrayList<ShareAndUser>();

        List<Share> shareList = shareService.selectAll();

        for (int i = shareList.size() - 1  ; i >= 0  ; i--){

            User user = new User();

            //查到发布该分享的用户
            user = userService.select(new User(shareList.get(i).getUser_id(),"","","",1,"",""));

            ShareAndUser shareAndUser = new ShareAndUser();

            shareAndUser.setUser_name(user.getName());
            shareAndUser.setUser_imgsrc(user.getImgsrc());
            shareAndUser.setShare_time(shareList.get(i).getCreate_time());
            shareAndUser.setShare_imgsrc(shareList.get(i).getImgsrc());
            shareAndUser.setShare_content(shareList.get(i).getContent());
            shareAndUser.setShare_suport(shareList.get(i).getSupport());
            shareAndUser.setShare_comment_count(commentService.selectCommentsCountByShareId(shareList.get(i).getId()));
            shareAndUser.setShare_id(shareList.get(i).getId());

            shareAndUsers.add(shareAndUser);

        }

        return new ResponseEntity<List<ShareAndUser>>(shareAndUsers,HttpStatus.OK);
    }



    @RequestMapping(value = "/shares/{id}", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Share> getStory(@PathVariable("id") String id) throws Exception
    {

        System.out.println(id);

        Share share = new Share();

        share = shareService.select(new Share(id,"","","","",0,0,0));

        return new ResponseEntity<Share>(share,HttpStatus.OK);
    }

}
