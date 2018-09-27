package com.example.demo.controller;

import com.example.demo.vo.MessageVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>创建时间: 2018/9/27 20:01 </p>
 *
 * @author shimengqiang
 * @version v1.0
 */

@RestController
public class ApiController {


    @RequestMapping(value = "/api/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> get(@PathVariable String id){


        System.out.println(id);
        MessageVO messageVO = new MessageVO("Successfully uploaded!",id);

        return ResponseEntity.ok(messageVO);
    }
}
