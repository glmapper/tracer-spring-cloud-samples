package com.glmapper.bridge.boot.spi;

import com.alipay.common.tracer.core.appender.TracerLogRootDaemon;
import com.alipay.common.tracer.core.reporter.facade.Reporter;
import com.alipay.common.tracer.core.span.SofaTracerSpan;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * 自定义 reporter
 *
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/8/10 1:53 PM
 * @since:
 **/
public class MyReporter implements Reporter {

    protected static String logDirectoryPath = TracerLogRootDaemon.LOG_FILE_DIR;

    @Override
    public String getReporterType() {
        return "test";
    }

    @Override
    public void report(SofaTracerSpan span) {
        try {
            File file = new File(logDirectoryPath + "/biz.txt");
            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(span.getOperationName());
            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        // ignore
    }
}
