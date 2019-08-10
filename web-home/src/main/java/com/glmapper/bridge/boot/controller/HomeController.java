package com.glmapper.bridge.boot.controller;

import com.alipay.sofa.tracer.plugin.flexible.annotations.Tracer;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 使用 Tracer 注解使用动态埋点
 *
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/8/9 6:12 PM
 * @since:
 **/
@RestController
public class HomeController {

    @Autowired
    TestService testService;

    @RequestMapping("home")
    @Tracer
    public String home(){
        testService.testService();
        // 由于 spring aop 代理的限制，这里掉用相当this.test ,没有使用代理对象
        test("it will not be work");
        // 可以使用这种方式调用，或者使用 AppplicationContext.getBean 获取目前 bean 调用
        ((HomeController)AopContext.currentProxy()).test("it will be work");
        return "home";
    }

    /**
     *
     * @param params
     */
    @Tracer(operateName = "nested-method")
    public  void test(String params){
        System.out.println("---------------"+params+"-----------------");
    }
}
