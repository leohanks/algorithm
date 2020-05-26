package com.hanks.algorithm.common.exception;

public class ServiceException extends RuntimeException {

    ServiceException(){
        super();
    }

    /**
     * 此处为显示模型层间异常，临时写成这样
     */
    @Override
    public void printStackTrace() {
        System.out.println("服务层异常：" + System.err);
    }
}
