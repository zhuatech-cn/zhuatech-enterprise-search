/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.enterprisesearch.controller;

import cn.zhuatech.enterprisesearch.common.ApiResponse;
import cn.zhuatech.enterprisesearch.service.SearchSourcePublicationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/enterprise/search")
public class SearchSourcePublicationController {
    private final SearchSourcePublicationService service;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public SearchSourcePublicationController(SearchSourcePublicationService service) { this.service = service; }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/source-publication")
    public ApiResponse<?> assess(@RequestBody SearchSourcePublicationService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
