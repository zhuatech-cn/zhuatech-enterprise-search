/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.enterprisesearch.domain;
import org.springframework.stereotype.Component;
import java.util.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Component
public class DomainCatalog {
    private final Map<String, WorkflowAction> actions = new LinkedHashMap<>();
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public DomainCatalog() {
        actions.put("INDEX", new WorkflowAction("INDEX", "提交索引", List.of("草稿"), "索引中", "OPERATOR"));
        actions.put("PUBLISH", new WorkflowAction("PUBLISH", "发布知识库", List.of("索引中"), "已发布", "ADMIN"));
        actions.put("ARCHIVE", new WorkflowAction("ARCHIVE", "归档知识库", List.of("已发布"), "已归档", "ADMIN"));
    }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String systemName() { return "知华科技企业知识搜索与检索增强平台"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String scene() { return "数据源、采集、索引、权限、搜索、语义检索、引用、反馈、评测与运营"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String initialStatus() { return "草稿"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String partyLabel() { return "知识库/数据源"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String amountLabel() { return "知识价值"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String quantityLabel() { return "文档数量"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String dueLabel() { return "更新期限"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public List<ModuleDefinition> modules() { return List.of(
            new ModuleDefinition("SOURCE", "知识数据源", "连接文档库、业务系统、网页和数据库"),
            new ModuleDefinition("INGESTION", "内容采集", "解析、清洗、切分、去重和增量同步"),
            new ModuleDefinition("INDEX", "索引管理", "管理全文、向量、字段和版本索引"),
            new ModuleDefinition("ACL", "权限同步", "继承用户、组织、角色和文档访问范围"),
            new ModuleDefinition("SEARCH", "企业搜索", "提供关键词、语义、筛选和排序"),
            new ModuleDefinition("RAG", "检索增强", "组织上下文、引用和可配置模型适配器"),
            new ModuleDefinition("FEEDBACK", "用户反馈", "采集相关性、纠错和无答案反馈"),
            new ModuleDefinition("EVALUATION", "检索评测", "管理测试集、召回、准确和权限泄露指标"),
            new ModuleDefinition("OPERATIONS", "搜索运营", "分析热点、零结果、延迟和索引健康")
        ); }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Map<String, WorkflowAction> actions() { return Collections.unmodifiableMap(actions); }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record ModuleDefinition(String code,String name,String description) {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record WorkflowAction(String code,String label,List<String> from,String to,String requiredRole) {}
}
