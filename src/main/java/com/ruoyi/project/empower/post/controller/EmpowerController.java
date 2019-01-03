package com.ruoyi.project.empower.post.controller;

import com.github.pagehelper.Page;
import com.ruoyi.common.utils.bean.ResponseBean;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.empower.post.domain.WXUser;
import com.ruoyi.project.system.post.service.IPostService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 岗位信息操作处理
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/empower/post")
public class EmpowerController extends BaseController {
    private String prefix = "empower/empower/post";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private IPostService postService;

//    private String usersUrl = "https://www.chengym.info/user/list";
    @Value("${operateruser.usersUrl}")
    private String usersUrl = "https://www.chengym.info/user/list";
    @Value("${operateruser.setAdminUrl}")
    private String setAdminUrl = "https://www.chengym.info/user/%s/set-admin";
    @Value("${operateruser.setUserUrl}")
    private String setUserUrl = "https://www.chengym.info/user/%s/set-user";

    @RequiresPermissions("operation:user:view")
    @GetMapping()
    public String operlog() {
        return prefix;
    }

    @RequiresPermissions("operation:user:view")
    @PostMapping("/user-list")
    @ResponseBody
    public TableDataInfo list() {
        Page page = startPages();
        String url = usersUrl + "?start="+page.getStartRow()+"&length="+page.getPageSize();
        ResponseEntity<ResponseBean> responseEntity = restTemplate.getForEntity(url, ResponseBean.class, page);
        ResponseBean<WXUser> responseBean = responseEntity.getBody();
        return getDataTable((List<WXUser>) responseBean.getData(),responseBean.getTotal());
    }
    @RequiresPermissions("operation:user:view")
    @PostMapping("/update/{userId}/set-admin")
    @ResponseBody
    public ResponseBean setAdmin(@PathVariable("userId")String userId) {
        ResponseBean responseBean = new ResponseBean();
        String url = String.format(setAdminUrl,userId);
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(null,httpHeaders);
        ResponseEntity<ResponseBean> responseEntity = restTemplate.exchange(url, HttpMethod.PUT,httpEntity,ResponseBean.class,"");
        if(HttpStatus.OK == responseEntity.getStatusCode()){
            responseBean = responseEntity.getBody();
            responseBean.setCode(0);
        }else {
            return responseEntity.getBody();
        }
        return responseBean;
    }
    @RequiresPermissions("operation:user:view")
    @PostMapping("/update/{userId}/set-user")
    @ResponseBody
    public ResponseBean setUser(@PathVariable("userId")String userId) {
        ResponseBean responseBean = new ResponseBean();
        String url = String.format(setUserUrl,userId);
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(null,httpHeaders);
        ResponseEntity<ResponseBean> responseEntity = restTemplate.exchange(url, HttpMethod.PUT,httpEntity,ResponseBean.class,"");
        if(HttpStatus.OK == responseEntity.getStatusCode()){
            responseBean = responseEntity.getBody();
            responseBean.setCode(0);
        }else {
            return responseEntity.getBody();
        }
        return responseEntity.getBody();
    }
}
