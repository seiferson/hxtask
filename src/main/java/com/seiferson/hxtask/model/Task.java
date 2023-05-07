package com.seiferson.hxtask.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;

@Document
public class Task {

    @Id
    private String id;

    private String title;
    private String description;
    private String owner;
    private String status;
    private List<Action> actions;
    private List<Update> history;
    private HashMap<String, String> metadata;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public List<Update> getHistory() {
        return history;
    }

    public void setHistory(List<Update> history) {
        this.history = history;
    }

    public HashMap<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(HashMap<String, String> metadata) {
        this.metadata = metadata;
    }
}
