package com.glmapper.bridge.boot.controller;

import com.alipay.common.tracer.core.tags.SpanTags;
import com.alipay.sofa.tracer.plugin.flexible.annotations.Tracer;
import org.springframework.stereotype.Service;

/**
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/8/10 12:14 AM
 * @since:
 **/
@Service
public class TestService {

    @Tracer
    public String testService(){
        SpanTags.putTags("testKey","testVal");
        SpanTags.putTags("testKeyNum",123);
        SpanTags.putTags("testKeyBoolean",true);
        return "testService";
    }
}
