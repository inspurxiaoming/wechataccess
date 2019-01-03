package com.ruoyi.common.utils.bean;

/**
 * @Description: 自定义响应数据结构
 * 				这个类是提供给门户，ios，安卓，微信商城用的
 * 				门户接受此类数据后需要使用本类的方法转换成对于的数据类型格式（类，或者list）
 * 				其他自行处理
 * 				200：表示成功
 * 				500：表示错误，错误信息在msg字段中
 * 				501：bean验证错误，不管多少个错误都以map形式返回
 * 				502：拦截器拦截到用户token出错
 * 				555：异常抛出信息
 */
public class ResponseBean<T> {
    private Integer code;

    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private T data;
    int total;

    private String ok;	// 不使用

    private String userId;

    public String getManageUrl() {
        return manageUrl;
    }

    public void setManageUrl(String manageUrl) {
        this.manageUrl = manageUrl;
    }

    private String manageUrl = "../introduce/introduce";

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    private String roles;

    public static ResponseBean build(Integer status, String msg, Object data) {
        return new ResponseBean(status, msg, data);
    }

    public static ResponseBean ok(Object data) {
        return new ResponseBean(data);
    }

    public static ResponseBean ok() {
        return new ResponseBean(null);
    }

    public static ResponseBean errorMsg(String msg) {
        return new ResponseBean(500, msg, null);
    }

    public static ResponseBean errorMap(Object data) {
        return new ResponseBean(501, "error", data);
    }

    public static ResponseBean errorTokenMsg(String msg) {
        return new ResponseBean(502, msg, null);
    }

    public static ResponseBean errorException(String msg) {
        return new ResponseBean(555, msg, null);
    }

    public ResponseBean() {

    }

    public ResponseBean(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ResponseBean(T data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }
    public ResponseBean(T data, String roles) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
        this.roles = roles;
    }

    public Boolean isOK() {
        return this.status == 200;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

	public String getOk() {
		return ok;
	}

	public void setOk(String ok) {
		this.ok = ok;
	}
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
