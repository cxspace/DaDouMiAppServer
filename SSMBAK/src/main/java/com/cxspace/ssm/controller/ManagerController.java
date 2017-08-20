package com.cxspace.ssm.controller;

import com.cxspace.ssm.model.Comment;
import com.cxspace.ssm.model.Share;
import com.cxspace.ssm.model.Story;
import com.cxspace.ssm.model.User;
import com.cxspace.ssm.service.CommentService;
import com.cxspace.ssm.service.ShareService;
import com.cxspace.ssm.service.StoryService;
import com.cxspace.ssm.service.UserService;
import com.cxspace.ssm.utils.DateTimeHelper;
import com.cxspace.ssm.utils.FileUploadHelper;
import com.sun.javafx.sg.prism.NGShape;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * Created by liujie on 2017/8/18.
 */

@Controller

@RequestMapping("/sys")

public class ManagerController {

    @Resource
    private StoryService storyService;

    @Resource
    private UserService userService;

    @Resource
    private ShareService shareService;

    @Resource
    private CommentService commentService;

    @RequestMapping("/index")
    public String index()
    {
        return "index";
    }

    @RequestMapping("/prohibit_user")
    public String prohibitUser(HttpServletRequest request)
    {
        String user_id = request.getParameter("user_id");

        userService.prohibitUserById(user_id);

        return "redirect:/sys/user_listUI.do";
    }

    @RequestMapping("/active_user")
    public String activeUser(HttpServletRequest request)
    {
        String user_id = request.getParameter("user_id");

        userService.activeUserById(user_id);

        return "redirect:/sys/user_listUI.do";
    }


    @RequestMapping("/prohibit_share")
    public String prohibitShare(HttpServletRequest request)
    {
        String share_id = request.getParameter("share_id");

        shareService.prohibitShareById(share_id);

        return "redirect:/sys/share_listUI.do";
    }

    @RequestMapping("/active_share")
    public String activeShare(HttpServletRequest request)
    {
        String share_id = request.getParameter("share_id");

        shareService.activeShareById(share_id);

        return "redirect:/sys/share_listUI.do";
    }

    @RequestMapping("/loginUI")
    public String loginUI()
    {
        return "login";
    }

    @RequestMapping("/registerUI")
    public String registerUI(){
        return "register";
    }

    @RequestMapping("/doLogin")
    public String doLogin(HttpServletRequest request,Model model)
    {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username.equals("dadoumi")&&password.equals("dadoumi.2017"))
        {

            return "redirect:/sys/index.do";

        }else {

            model.addAttribute("MSG","用户名或密码错误！！！");
            return "login";

        }

    }

    @RequestMapping("/share_comment_listUI")
    public String share_comment_listUI(HttpServletRequest request,Model model){

        String share_id = request.getParameter("share_id");

        List<Comment> commentList = commentService.selectCommentsByShareId(share_id);

        model.addAttribute("commentList",commentList);

        return "share_comment_list";
    }

    @RequestMapping("/share_listUI")
    public String share_listUI(Model model){

        List<Share> shareList = shareService.getAll();

        model.addAttribute("shareList",shareList);

        return "share_list";
    }

    @RequestMapping("/user_listUI")
    public String user_listUI(Model model){

        List<User> userList = userService.selectAll();

        model.addAttribute("userList",userList);

        return "user_list";
    }


    @RequestMapping("/story_listUI")
    public String story_listUI(Model model)
    {
        List<Story> storyList = storyService.selectAll();

        model.addAttribute("storyList",storyList);

        return "story_list";
    }

    @RequestMapping("/story_addUI")
    public String story_addUI()
    {

        return "story_add";
    }

    @RequestMapping("/story_editUI")
    public String story_editUI(HttpServletRequest request , Model model) throws Exception {

        String storyId = request.getParameter("storyId");

        Story story = storyService.select(new Story(storyId,"","","","",""));

        model.addAttribute("story",story);


        return "story_edit";
    }

    @RequestMapping("/story_edit")
    public String stroy_edit(Story story, Model model) throws Exception{

        storyService.update(story);

        return "redirect:/sys/story_listUI.do";

    }

    @RequestMapping("/story_delete")
    public String story_delete(HttpServletRequest request) throws Exception{

        String storyId = request.getParameter("storyId");

        storyService.delete(new Story(storyId,"","","","",""));

        return "redirect:/sys/story_listUI.do";
    }


    /**
     *
     * 添加美食故事
     *
     * @param file
     * @param request
     * @return
     */

    @RequestMapping("/story_photo_upload")
    public String story_photo_upload(@RequestParam(value = "photo" , required = false)MultipartFile file , HttpServletRequest request)
    {
        Story story = new Story();

        String title = request.getParameter("title");

        String content = request.getParameter("content");

        String fileName = FileUploadHelper.uploadImg(file,request);


        story.setTitle(title);
        story.setIconsrc("http://121.42.184.102/DaDouMiImg/icon.png");
        story.setContent(content);
        story.setImgsrc("upload/"+fileName);
        story.setCreate_time(DateTimeHelper.getCurrentTime());

        try {
            storyService.insert(story);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/sys/story_listUI.do";
    }






}
