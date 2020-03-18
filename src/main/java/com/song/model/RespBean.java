package com.song.model;

/**
 * @author song
 * @data 2020/2/17
 */
public class RespBean {
    private Integer status;
    private String msg;
    private Object obj;

    public RespBean(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public static final RespBean DELETE_SUCCESS = new RespBean(200,"删除成功");
    public static final RespBean DELETE_ERROR = new RespBean(-1,"删除失败");

    public static final RespBean ADD_SUCCESS = new RespBean(200,"添加成功");
    public static final RespBean ADD_ERROR = new RespBean(-1,"添加失败");

    public static final RespBean UPDATE_SUCCESS = new RespBean(200,"更新成功");
    public static final RespBean UPDATE_ERROR = new RespBean(-1,"更新失败");

    public static RespBean ok(String msg){
        return new RespBean(200,msg,null);
    }
    public static RespBean ok(String msg,Object obj){
        return new RespBean(200,msg,obj);
    }
    public static RespBean error(String msg){
        return new RespBean(500,msg,null);
    }
    public static RespBean error(String msg,Object obj){
        return new RespBean(500,msg,obj);
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

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    private RespBean(Integer status, String msg, Object obj) {
        this.status = status;
        this.msg = msg;
        this.obj = obj;
    }

    private RespBean() {
    }
}
