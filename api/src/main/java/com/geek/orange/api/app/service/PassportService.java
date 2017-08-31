package com.geek.orange.api.app.service;

import com.geek.orange.api.app.dto.Passport;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/app")
public interface PassportService {

    /**
     * 根据预申请单号返回证件对象。
     *
     * @param reqId 预申请单号
     * @return Passport object
     * @see Passport
     */
    @GetMapping("/passport")
    Passport[] search(@RequestParam("reqId") Long reqId);

    /**
     * Save all passports.
     *
     * @param passports passport objects
     * @return passport file saved path
     */
    @PostMapping("/passport")
    String[] save(@RequestBody Passport[] passports);
}
