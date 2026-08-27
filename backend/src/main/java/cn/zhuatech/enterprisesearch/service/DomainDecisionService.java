/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.enterprisesearch.service;
import jakarta.validation.constraints.*;
import org.springframework.stereotype.Service;
import java.util.*;
@Service public class DomainDecisionService {
 public DecisionResult assess(DecisionRequest request) { double precision=request.retrievedDocuments()==0?0:request.relevantDocuments()*100d/request.retrievedDocuments();double citation=request.relevantDocuments()==0?0:request.citedDocuments()*100d/request.relevantDocuments();int score=(int)Math.round((precision+citation)/2);List<String> actions=new ArrayList<>();if(request.permissionViolations()>0){score=0;actions.add("立即下线索引并修复权限过滤");}if(precision<70)actions.add("优化召回、重排和同义词");if(citation<90)actions.add("提高回答引用覆盖率");if(request.latencyMs()>1500){score-=15;actions.add("优化检索响应延迟");}if(request.freshnessDays()>7){score-=15;actions.add("恢复增量索引任务");}return result(score,actions,"READY","TUNE","BLOCKED",Map.of("precisionPercent",precision,"citationCoverage",citation,"latencyMs",request.latencyMs(),"freshnessDays",request.freshnessDays())); }
 private DecisionResult result(int raw,List<String> actions,String good,String warn,String bad,Map<String,Object> metrics) { int score=Math.max(0,Math.min(100,raw));String decision=score>=80?good:score>=50?warn:bad;return new DecisionResult(decision,score,metrics,List.copyOf(actions)); }
 private DecisionResult riskResult(int raw,List<String> actions,String good,String warn,String bad,Map<String,Object> metrics) { int score=Math.max(0,Math.min(100,raw));String decision=score>=70?bad:score>=40?warn:good;return new DecisionResult(decision,score,metrics,List.copyOf(actions)); }
 public record DecisionRequest(
        @NotBlank String queryNo,
        @PositiveOrZero int retrievedDocuments,
        @PositiveOrZero int relevantDocuments,
        @PositiveOrZero int citedDocuments,
        @PositiveOrZero int permissionViolations,
        @PositiveOrZero int latencyMs,
        @PositiveOrZero int freshnessDays) {}
 public record DecisionResult(String decision,int score,Map<String,Object> metrics,List<String> actions) {}
}
