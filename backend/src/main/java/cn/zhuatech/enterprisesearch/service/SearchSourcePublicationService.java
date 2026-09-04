/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.enterprisesearch.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SearchSourcePublicationService {
    public Result assess(Request request) {
        var blockers = new ArrayList<String>();
        var actions = new ArrayList<String>();
        if (request.sourceId() == null || request.sourceId().isBlank()) blockers.add("搜索数据源编号不能为空");
        if (!request.accessAclMapped()) blockers.add("源权限与搜索 ACL 未映射");
        if (!request.piiClassified()) blockers.add("敏感信息未识别分级");
        if (!request.retentionApproved()) blockers.add("索引保留策略未批准");
        if (!request.malwareScanPassed()) blockers.add("内容安全或恶意文件扫描未通过");
        if (!request.legalHoldRespected()) blockers.add("法律保全要求未落实");
        if (!request.auditReady()) blockers.add("搜索数据源发布证据不完整");
        if (!request.ownerAssigned()) actions.add("指定数据源责任人");
        if (!request.crawlScopeValidated()) actions.add("复核采集范围与排除规则");
        if (!request.freshnessPolicyConfigured()) actions.add("配置内容新鲜度策略");
        if (!request.searchQualityVerified()) actions.add("完成检索质量验收");
        if (!request.rollbackReady()) actions.add("准备索引回滚方案");
        var decision = !blockers.isEmpty() ? Decision.BLOCKED : actions.isEmpty() ? Decision.PUBLISH : Decision.REVIEW;
        return new Result(decision, List.copyOf(blockers), List.copyOf(actions));
    }

    public enum Decision { PUBLISH, REVIEW, BLOCKED }
    public record Request(String sourceId, boolean ownerAssigned, boolean accessAclMapped,
                          boolean piiClassified, boolean retentionApproved, boolean crawlScopeValidated,
                          boolean freshnessPolicyConfigured, boolean malwareScanPassed,
                          boolean legalHoldRespected, boolean searchQualityVerified,
                          boolean rollbackReady, boolean auditReady) {}
    public record Result(Decision decision, List<String> blockers, List<String> actions) {}
}
