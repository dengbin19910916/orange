package com.geek.orange.product.site.app;

import com.geek.orange.api.app.dto.Passport;
import com.geek.orange.api.app.service.PassportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.*;

@Api(tags = "证件处理")
@RestController
public class PassportController implements PassportService {

    private static final Logger log = LogManager.getLogger(PassportController.class);

    private static final Base64.Decoder DECODER = Base64.getDecoder();

    @Value("${passport.image.directory}")
    private String imageDir;

    @ApiOperation("查询所有的证件图片")
    @Override
    public Passport[] search(@RequestParam("reqId") Long reqId) {
        System.err.println("request id = " + reqId);
        return new Passport[]{new Passport("one.jpg", "123456")};
    }

    @ApiOperation("保存所有的证件图片")
    @Override
    public String[] save(@RequestBody Passport[] passports) {
        ExecutorService executor = Executors.newCachedThreadPool();
        List<PassportHandler> tasks = new ArrayList<>();
        for (Passport passport : passports) {
            tasks.add(new PassportHandler(passport, imageDir));
        }

        List<Future<String>> results = null;
        try {
            results =executor.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (executor.isShutdown()) {
                executor.shutdown();
            }
        }

        List<String> paths = new ArrayList<>(); // file saved paths.
        if (results != null) {
            results.forEach(future -> {
                try {
                    paths.add(future.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            });
        }

        return paths.toArray(new String[paths.size()]);
    }

    /**
     * 证件图片处理。
     *
     * @see java.util.concurrent.Callable
     */
    @AllArgsConstructor
    public static class PassportHandler implements Callable<String> {

        private final Base64.Decoder DECODER = Base64.getDecoder();

        private Passport passport;
        private String imageDir;

        @Override
        public String call() throws Exception {
            Path imageFile = Paths.get(imageDir, passport.getName());
            if (passport.getData() != null) {
                byte[] data = DECODER.decode(passport.getData());
                try {
                    Files.write(imageFile, data);
                } catch (IOException e) {
                    log.error("文件无法保存", e);
                }
            }
            return imageFile.toString();
        }
    }
}
