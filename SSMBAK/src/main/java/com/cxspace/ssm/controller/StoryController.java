package com.cxspace.ssm.controller;

import com.cxspace.ssm.model.Story;
import com.cxspace.ssm.service.StoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liujie on 2017/8/16.
 */

@RestController

public class StoryController {

    @Resource
    private StoryService storyService;

    @RequestMapping(value = "/story_insert",method = RequestMethod.POST)
    public String storyInsert(){

        return "story_list";
    }

    /**
     * 获取所有故事列表
     * @return
     */

    @RequestMapping(value = "/storys",method = RequestMethod.GET)
    public ResponseEntity<List<Story>> storys(){

        List<Story> storyList = new ArrayList<Story>();

        storyList = storyService.selectAll();

        System.out.println(storyList);

        return new ResponseEntity<List<Story>>(storyList, HttpStatus.OK);
    }


    /**
     *
     * 获取指定id的故事
     *
     * @param id
     * @return
     * @throws Exception
     */

    @RequestMapping(value = "/storys/{id}", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Story> getStory(@PathVariable("id") String id) throws Exception
    {

        System.out.println(id);

        Story story = new Story();

        story = storyService.select(new Story(id,"","","","",""));

        return new ResponseEntity<Story>(story,HttpStatus.OK);
    }

}
