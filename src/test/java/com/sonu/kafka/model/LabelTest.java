package com.sonu.kafka.model;

import org.json.JSONArray;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class LabelTest {
    String labelStr = "[\n" +
            "{ \n" +
            "\"id\": 208045946, \n" +
            "\"node_id\": \"MDU6TGFiZWwyMDgwNDU5NDY=\", \n" +
            "\"url\": \"https://api.github.com/repos/octocat/Hello-World/labels/bug\", \n" +
            "\"name\": \"bug\", \n" +
            "\"description\": \"Something isn't working\", \n" +
            "\"color\": \"f29513\", \n" +
            "\"default\": true \n" +
            "} \n" +
            "]";

    JSONArray labelJsonArray = new JSONArray(labelStr);

    @Test
    public void parseJsonArray() {
        //System.out.println(labelJsonArray);
//        List<Label> labels = Label.fromJson(labelJsonArray);
//
//        assertEquals(labels.get(0).getUrl(), "https://api.github.com/repos/octocat/Hello-World/labels/bug");
//        assertEquals(labels.get(0).getId(),(Integer) 208045946);
//        assertEquals(labels.get(0).getName(), "bug");
//        assertEquals(labels.get(0).getDescription(), "Something isn't working");
//        assertEquals(labels.get(0).getDefaultFlag(), true);
    }
}
