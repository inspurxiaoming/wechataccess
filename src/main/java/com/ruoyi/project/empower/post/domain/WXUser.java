package com.ruoyi.project.empower.post.domain;


import lombok.Data;

import java.util.Date;

@Data
public class WXUser {

  private String id;
  private String name;
  private String password;
  private String phone;
  private String roles;
  private String userId;
  private String jobNumber;
  private String realName;
  private String avatarUrl;
  private Date createdTime;
  /**
   * 空查全部，1查授权过的，0查未授权的
   */
  private Integer enable;
}
