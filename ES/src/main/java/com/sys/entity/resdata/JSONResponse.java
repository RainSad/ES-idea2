package com.sys.entity.resdata;

/**
 * 通用返回数据格式
 *
 * @author 孙文祥
 * @Description:
 * @ClassName: JSONResponse
 * @date 2017年9月19日 下午5:55:25
 */
public class JSONResponse {

    private Integer status;
    private Object data;
    private Integer count;

    public JSONResponse() {
        super();
    }

    /**
     * 返回数据格式
     *
     * @param status 状态位 1 为成功 0为失败
     * @param data   返回数据主体
     */
    public JSONResponse(Integer status, Object data) {
        super();
        this.status = status;
        this.data = data;
    }

    /**
     * 返回数据格式
     *
     * @param status 状态位 1 为成功 0为失败
     * @param data   返回数据主体
     * @param count  数据条数
     */
    public JSONResponse(Integer status, Object data, Integer count) {
        super();
        this.status = status;
        this.data = data;
        this.count = count;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
