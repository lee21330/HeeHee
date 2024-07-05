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
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.shinhan.heehee.dao.AuctionDAO;
import com.shinhan.heehee.dto.response.auction.AuctionProdDTO;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        CreateIndexRequest request = new CreateIndexRequest("auction_index");

        // 인덱스 설정
        request.settings(Settings.builder()
            .put("index.analysis.analyzer.ngram_analyzer.tokenizer", "standard")
            .putList("index.analysis.analyzer.ngram_analyzer.filter", "lowercase", "ngram_filter")
            .put("index.analysis.filter.ngram_filter.type", "ngram")
            .put("index.analysis.filter.ngram_filter.min_gram", 1)		// 최소글자
            .put("index.analysis.filter.ngram_filter.max_gram", 25)	// 최대 글자
            .put("index.max_ngram_diff", 24)  // max_ngram_diff 설정 추가
        );

        // 매핑 설정
        String mapping = "{"
            + "\"properties\": {"
            + "  \"productSeq\": {\"type\": \"integer\"},"
            + "  \"aucPrice\": {\"type\": \"integer\"},"
            + "  \"auctionTitle\": {\"type\": \"text\", \"analyzer\": \"ngram_analyzer\"},"
            + "  \"expDate\": {\"type\": \"date\"},"
            + "  \"expTime\": {\"type\": \"keyword\"},"
            + "  \"imgName\": {\"type\": \"keyword\"}"
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
        try {
            client.indices().delete(new DeleteIndexRequest("auction_index"), RequestOptions.DEFAULT);
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
        List<AuctionProdDTO> dataList = auctionDAO.aucProdAll();
        for (AuctionProdDTO data : dataList) {
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("productSeq", data.getProductSeq());
            jsonMap.put("aucPrice", data.getAucPrice());
            jsonMap.put("auctionTitle", data.getAuctionTitle());

            // expDate를 yyyy-MM-dd 형식으로 변환 (변환 안하니까 오류 뜸..)
            String expDateStr = data.getExpDate();
            LocalDate date = LocalDate.parse(expDateStr, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            String formattedDate = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
            
            jsonMap.put("expDate", formattedDate);
            jsonMap.put("expTime", data.getExpTime());
            jsonMap.put("imgName", data.getImgName());

            IndexRequest request = new IndexRequest("auction_index")
                    .id(String.valueOf(data.getProductSeq()))
                    .source(jsonMap, XContentType.JSON);
            client.index(request, RequestOptions.DEFAULT);
        }
        logger.info("Elasticsearch 서버에 데이터 동기화 메소드 실행");
    }

    public List<AuctionProdDTO> search(String keyword) throws IOException {
        SearchRequest searchRequest = new SearchRequest("auction_index");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchQuery("auctionTitle", keyword).analyzer("standard"));
        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        List<AuctionProdDTO> results = new ArrayList<>();

        searchResponse.getHits().forEach(hit -> {
            Map<?, ?> sourceAsMap = hit.getSourceAsMap();
            AuctionProdDTO prod = new AuctionProdDTO(
                (Integer) sourceAsMap.get("productSeq"),
                (Integer) sourceAsMap.get("aucPrice"),
                (String) sourceAsMap.get("auctionTitle"),
                (String) sourceAsMap.get("expDate"),
                (String) sourceAsMap.get("expTime"),
                (String) sourceAsMap.get("imgName")
            );
            results.add(prod);
        });

        return results;
    }
}