/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.enterprisesearch.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
class SearchSourcePublicationServiceTest {
    private final SearchSourcePublicationService service = new SearchSourcePublicationService();

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void publishesTrustedSearchSource() {
        var result = service.assess(new SearchSourcePublicationService.Request("SRC-100", true, true, true,
                true, true, true, true, true, true, true, true));
        assertThat(result.decision()).isEqualTo(SearchSourcePublicationService.Decision.PUBLISH);
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void routesOperationalReadinessToReview() {
        var result = service.assess(new SearchSourcePublicationService.Request("SRC-101", false, true, true,
                true, false, false, true, true, false, false, true));
        assertThat(result.actions()).hasSize(5);
        assertThat(result.decision()).isEqualTo(SearchSourcePublicationService.Decision.REVIEW);
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void blocksUnsafeSearchSource() {
        var result = service.assess(new SearchSourcePublicationService.Request("", false, false, false,
                false, false, false, false, false, false, false, false));
        assertThat(result.blockers()).hasSize(7);
        assertThat(result.decision()).isEqualTo(SearchSourcePublicationService.Decision.BLOCKED);
    }
}
