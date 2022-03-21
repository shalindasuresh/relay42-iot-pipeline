package com.relay42.iot.pipeline.mapper;//import org.springframework.data.cassandra.core.mapping.PrimaryKey;


import java.io.Serializable;

public class DeviceRead implements Serializable {

    DeviceRead(){}

    private int id;
    private String type;
    private String name;
    private int clusterId;
    private String timestamp;
    private double value;
    private boolean initialized;


    public DeviceRead(int id, String type, String name, int clusterid, String timestamp, double value, boolean initialized) {
      this.id=id;
      this.type=type;
      this.name=name;
      this.clusterId=clusterid;
      this.timestamp=timestamp;
      this.value=value;
      this.initialized=initialized;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getClusterId() {
        return clusterId;
    }

    public void setClusterId(int clusterId) {
        this.clusterId = clusterId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isInitialized() {
        return initialized;
    }

    public void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }
}