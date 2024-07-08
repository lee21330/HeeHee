package com.shinhan.heehee.service;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.shinhan.heehee.dao.AuctionDAO;
import com.shinhan.heehee.dto.response.ElasticSyncDTO;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ElasticsearchService {

    Logger logger = LoggerFactory.getLogger(ElasticsearchService.class);
    
    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private AuctionDAO auctionDAO;

    // 검색 인덱스 생성 메서드
    public void createIndexWithNGramAnalyzer() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest("prod_index");

        // 인덱스 설정
        request.settings(Settings.builder()
            .put("index.analysis.analyzer.ngram_analyzer.tokenizer", "standard")
            .putList("index.analysis.analyzer.ngram_analyzer.filter", "lowercase", "ngram_filter")
            .put("index.analysis.filter.ngram_filter.type", "ngram")
            .put("index.analysis.filter.ngram_filter.min_gram", 1)  // 최소 글자
            .put("index.analysis.filter.ngram_filter.max_gram", 25) // 최대 글자
            .put("index.max_ngram_diff", 24)  // 최대 최소 글자 차이
        );

        // 매핑 설정
        String mapping = "{"
            + "\"properties\": {"
            + "  \"gubun\": {\"type\": \"text\"},"
            + "  \"productSeq\": {\"type\": \"integer\"},"
            + "  \"title\": {"
            + "    \"type\": \"text\","
            + "    \"analyzer\": \"ngram_analyzer\","
            + "    \"search_analyzer\": \"standard\","
            + "    \"fields\": {"
            + "      \"keyword\": {"
            + "        \"type\": \"keyword\""
            + "      }"
            + "    }"
            + "  },"
            + "  \"idate\": {\"type\": \"text\"},"
            + "  \"imgName\": {\"type\": \"text\"}"
            + "}"
            + "}";
        request.mapping(mapping, XContentType.JSON);

        // 인덱스 생성 요청
        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
        boolean acknowledged = createIndexResponse.isAcknowledged();
        if (acknowledged) {
            System.out.println("인덱스 생성 완료");
        }
    }

    // 애플리케이션 시작 시 서치 인덱스 초기화
    @PostConstruct
    public void init() throws IOException {
        recreateIndex();
    }

    // 인덱스 재생성 메소드
    public void recreateIndex() throws IOException {
        try {
            client.indices().delete(new DeleteIndexRequest("prod_index"), RequestOptions.DEFAULT);
        } catch (Exception e) {
            // 인덱스가 존재하지 않는 경우 무시
        }
        createIndexWithNGramAnalyzer();
        syncDataToElasticsearch();
    }

    // 주기적으로 Elasticsearch 서버에 데이터를 동기화하는 메소드
    // 1분마다 실행
    @Scheduled(fixedRate = 60000)
    public void syncDataToElasticsearch() throws IOException {
        List<ElasticSyncDTO> dataList = auctionDAO.aucProdAll();
        for (ElasticSyncDTO data : dataList) {
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("gubun", data.getGubun());
            jsonMap.put("productSeq", data.getProductSeq());
            jsonMap.put("title", data.getTitle());
            jsonMap.put("price", data.getPrice());
            jsonMap.put("idate", data.getIdate());
            jsonMap.put("imgName", data.getImgName());

            IndexRequest request = new IndexRequest("prod_index")
                    .id(String.valueOf(data.getProductSeq()))
                    .source(jsonMap, XContentType.JSON);
            client.index(request, RequestOptions.DEFAULT);
        }
        logger.info("Elasticsearch 서버에 데이터 동기화 메소드 실행");
    }

    // 모든 데이터 검색
    public List<ElasticSyncDTO> search(String keyword) throws IOException {
        SearchRequest searchRequest = new SearchRequest("prod_index");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchQuery("title", keyword).analyzer("standard"));
        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        List<ElasticSyncDTO> results = new ArrayList<>();

        searchResponse.getHits().forEach(hit -> {
            Map<?, ?> sourceAsMap = hit.getSourceAsMap();
            ElasticSyncDTO prod = new ElasticSyncDTO(
                (String) sourceAsMap.get("gubun"),
                (Integer) sourceAsMap.get("productSeq"),
                (Integer) sourceAsMap.get("price"),
                (String) sourceAsMap.get("title"),
                (String) sourceAsMap.get("introduce"),
                (String) sourceAsMap.get("idate"),
                (String) sourceAsMap.get("imgName"),
                (Integer) sourceAsMap.get("cateNum")
            );
            results.add(prod);
        });

        return results;
    }
    
    // 비슷한 title 그룹화
    public List<Map<String, Object>> grouppingSearch(String keyword) throws IOException {
        SearchRequest searchRequest = new SearchRequest("prod_index");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        
        // 검색 쿼리 및 그룹핑 설정
        sourceBuilder.query(QueryBuilders.matchQuery("title", keyword).analyzer("standard"))
                     .aggregation(AggregationBuilders.terms("group_by_title").field("title.keyword"));
        
        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        
        List<Map<String, Object>> results = new ArrayList<>();
        Aggregations aggregations = searchResponse.getAggregations();
        Terms terms = aggregations.get("group_by_title");
        
        int putCnt = 0;
        
        for (Terms.Bucket bucket : terms.getBuckets()) {
            Map<String, Object> result = new HashMap<>();
            result.put("title", bucket.getKeyAsString());
            result.put("count", bucket.getDocCount());
            results.add(result);
            if (putCnt == 14) break;
            putCnt++;
        }

        return results;
    }
    
    public List<ElasticSyncDTO> searchKeyword(String keyword) {
    	return auctionDAO.findByKeywordProd(keyword);
    }
    
    public List<ElasticSyncDTO> searchCategory(int category) {
    	return auctionDAO.findByCategoryProd(category);
    }
}