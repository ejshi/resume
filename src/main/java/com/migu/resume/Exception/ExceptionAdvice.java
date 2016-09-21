package com.migu.resume.Exception;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.alibaba.fastjson.JSONException;
import com.migu.resume.vo.RemoteResponse;
/**
 * 异常信息处理
 * <p>Company: richinfo</p>
 * @author: jshi
 * @date: 2016年9月1日 下午4:50:13
 * @version: V1.0
 */
@ControllerAdvice
public class ExceptionAdvice {
	
	private static Logger logger = Logger.getLogger(ExceptionAdvice.class);
	/**
	 * 用户不具有该权限
	 * @param e
	 * @return
	 */
	@ExceptionHandler(UnauthorizedException.class)
    public String handleUnauthorizedException(UnauthorizedException e) {
        logger.info("用户不具有该权限");
		return "error/refuse";
    }
	/**
     * 自定义异常信息
     * @param ex
     * @return
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public RemoteResponse handleMethodCustomException(CustomException ex) {
        return new RemoteResponse(0, ex.getMessage());
    }
	/**
     * 数据校验失败
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
   // @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public RemoteResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldValidationError> errorList=new LinkedList<FieldValidationError>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorList.add(new FieldValidationError( fieldError.getField(),fieldError.getDefaultMessage()));
        }
        return new RemoteResponse(0, errorList);
    }

    /**
     * 数据校验失败
     * @param ex
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public RemoteResponse handleBindException(BindException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldValidationError> errorList=new LinkedList<FieldValidationError>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorList.add(new FieldValidationError( fieldError.getField(),fieldError.getDefaultMessage()));
        }
        return new RemoteResponse(0, errorList);
    }



    /**
     * 400 - Bad Request
     */
   @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public RemoteResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error("参数解析失败", e);
        return new RemoteResponse(0);
    }


    /**
     * 400 - Bad Request
     */
  //  @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(JSONException.class)
    @ResponseBody
    public RemoteResponse handleJSONException(JSONException e) {
        logger.error("json读取失败", e);
        return new RemoteResponse(0);
    }

    /**
     * 405 - Method Not Allowed
     */

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public RemoteResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        logger.error("不支持当前请求方法", e);
        return new RemoteResponse(0);
    }

    /**
     * 415 - Unsupported Media Type
     */

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public RemoteResponse handleHttpMediaTypeNotSupportedException(Exception e) {
        logger.error("不支持当前媒体类型", e);
        return new RemoteResponse(0);
    }

    /**
     * 数据库操作失败
     */
    @ExceptionHandler(SQLException.class)
    @ResponseBody
    public RemoteResponse handleSQLException(SQLException e) {
        logger.error("数据库异常", e);
        return new RemoteResponse(0);
    }

    /**
     * 数据库操作失败
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseBody
    public RemoteResponse handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        logger.error("上传文件过大，上传失败，最大只能上传2M", e);
        return new RemoteResponse(0);
    }
    
    /**
     * 500 - Internal Server Error
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RemoteResponse handleException(Exception e) {
        logger.error("服务运行异常", e);
        return new RemoteResponse(0);
    }
}
