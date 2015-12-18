package com.machine.topic.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.machine.topic.TopicExtractionConfiguration;
import com.machine.topic.Util.TEUtil;
import com.machine.topic.common.Constants.EntityType;
import com.machine.topic.exceptions.FailedResponseException;
import com.machine.topic.model.Entity;
import com.machine.topic.model.EntityMatch;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rishabh.tripathi on 11/9/15.
 */
public class TopicService {
    private TEUtil teUtil;
    private TopicExtractionConfiguration topicExtractionConfiguration;

    @Inject
    public void setTeUtil(TEUtil teUtil) {
        this.teUtil = teUtil;
    }

    @Inject
    public void setTopicExtractionConfiguration(TopicExtractionConfiguration topicExtractionConfiguration) {
        this.topicExtractionConfiguration = topicExtractionConfiguration;
    }


    public List<EntityMatch> getTopicsFromText(String text) throws IOException, FailedResponseException {
        String url = topicExtractionConfiguration.getCalisUrl();
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", "");
        post.setHeader("X-AG-Access-Token", topicExtractionConfiguration.getCalisAuth());
        post.setHeader("Content-Type", "text/raw");
        post.setHeader("outputformat", "application/json");
        HttpEntity entity = new StringEntity(text);
        post.setEntity(entity);

        HttpResponse response = client.execute(post);
        if(response.getStatusLine().getStatusCode() == 200) {
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));
            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            List<EntityMatch> matches = parseTopicMatchResponse(result.toString());
            return matches;
        } else {
            throw new FailedResponseException("Filed response from client,  Response Code: " + response.getStatusLine().getStatusCode());
        }
    }

    public List<EntityMatch> parseTopicMatchResponse(String result) {

        Map<String, Map> doc = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            doc = objectMapper.readValue(result, HashMap.class);
        } catch (Exception ex) {

        }
        List<EntityMatch> entityMatches = new ArrayList<>();
        for(String key : doc.keySet()) {
            if(!key.equalsIgnoreCase("doc")) {
                Map<String, Object> topicMap = doc.get(key);
                Entity entity = new Entity();
                EntityMatch entityMatch = new EntityMatch();
                if(topicMap.get("_typeGroup").toString().equalsIgnoreCase(EntityType.topics.toString())) {
                    entity.setDisplayName(topicMap.get("name").toString());
                    entity.setName(teUtil.sanitizeEntityName(topicMap.get("name").toString()));
                    entityMatch.setEntity(entity);
                    entityMatch.setType(EntityType.topics);
                    String score = topicMap.get("score").toString();
                    entityMatch.setRelevence(Float.parseFloat(score));
                    entityMatches.add(entityMatch);
                } else if(topicMap.get("_typeGroup").toString().equalsIgnoreCase(EntityType.language.toString())) {

                } else if(topicMap.get("_typeGroup").toString().equalsIgnoreCase(EntityType.entities.toString())) {
                    entity.setDisplayName(topicMap.get("name").toString());
                    entity.setName(teUtil.sanitizeEntityName(topicMap.get("name").toString()));
                    entityMatch.setEntity(entity);
                    entityMatch.setType(EntityType.entities);
                    entityMatch.setRelevence(Float.parseFloat(topicMap.get("relevance").toString()));
                    entityMatches.add(entityMatch);
                } else if(topicMap.get("_typeGroup").toString().equalsIgnoreCase(EntityType.socialTag.toString())) {
                    entity.setDisplayName(topicMap.get("name").toString());
                    entity.setName(teUtil.sanitizeEntityName(topicMap.get("name").toString()));
                    entityMatch.setEntity(entity);
                    entityMatch.setType(EntityType.socialTag);
                    entityMatch.setRelevence(Float.parseFloat(topicMap.get("importance").toString()));
                    entityMatches.add(entityMatch);
                }
            }
        }
        return entityMatches;
    }

}
