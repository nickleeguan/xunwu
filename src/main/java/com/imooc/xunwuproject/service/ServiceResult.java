package com.imooc.xunwuproject.service;

/**
 * 服务接口通用结构
 */
public class ServiceResult<T> {
    private boolean success;
    private String message;
    private T result;

    public ServiceResult(boolean success) {
        this.success = success;
    }

    public ServiceResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ServiceResult(boolean success, String message, T result) {
        this.success = success;
        this.message = message;
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public static <T> ServiceResult<T> success(T result){
        return new ServiceResult<>(true);
    }

    public static <T> ServiceResult<T> success(){
        return new ServiceResult<T>(true);
    }

    public static <T> ServiceResult<T> of(T result){
        ServiceResult<T> serviceResult = new ServiceResult<T>(true);
        serviceResult.setResult(result);
        return serviceResult;
    }

    public static <T> ServiceResult<T> notFound() {
        return new ServiceResult<>(false, Message.NOT_FOUND.getValue());
    }

    public enum Message{
        NOT_FOUND("NOT FOUND RESOURCES"),
        NOT_LOGIN("USER NOT LOGIN");

        private String value;
        Message(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
