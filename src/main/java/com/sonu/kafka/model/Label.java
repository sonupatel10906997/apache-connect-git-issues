package com.sonu.kafka.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Label {
    private Integer id;
    private String nodeId;
    private String url;
    private String name;
    private String description;
    private String color;
    private Boolean defaultFlag;

    public Label(){}

    public Label(Integer id, String nodeId, String url, String name, String description, String color, Boolean defaultFlag) {
        this.id= id;
        this.nodeId = nodeId;
        this.url = url;
        this.name = name;
        this.description = description;
        this.color = color;
        this.defaultFlag = defaultFlag;
    }

    public Integer getId(){return id;}
    public void setId(Integer id) {this.id = id;}
    public Label withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUrl(){return url;}
    public void setUrl(String url) { this.url = url;}
    public Label withUrl(String url) {
        this.url = url;
        return this;
    }

    public String getName(){return name;}
    public void setName(String name) { this.name = name;}
    public Label withName(String name) {
        this.name = name;
        return this;
    }
    public String getDescription(){return description;}
    public void setDescription(String description) { this.description = description;}
    public Label withDescription(String description) { this.description = description;
        return this;
    }

    public Boolean getDefaultFlag() {return defaultFlag;}
    public void setDefaultFlag(Boolean defaultFlag) { this.defaultFlag = defaultFlag;}
    public Label withDefaultFlag(Boolean defaultFlag) { this.defaultFlag = defaultFlag;
        return this;
    }

    public static List<Label> fromJson(JSONArray jsonArray) {
        List<Label> labels = new ArrayList<>(jsonArray.length());

        for(int i=0; i< jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Label label = new Label();
            label.withId(jsonObject.getInt("id"));
            label.withUrl(jsonObject.getString("url"));
            label.withName(jsonObject.getString("name"));
            if(jsonObject.isNull("description")) {
                label.withDescription("na");
            } else {
                label.withDescription(jsonObject.getString("description"));
            }
            label.withDefaultFlag(jsonObject.getBoolean("default"));
            labels.add(label);
        }
        return labels;



    }



}
