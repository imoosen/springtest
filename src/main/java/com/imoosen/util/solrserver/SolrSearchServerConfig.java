package com.imoosen.util.solrserver;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/6 0006.
 */
public class SolrSearchServerConfig {
    private String url;
    private int soTimeout;
    private int connectionTimeout;
    private int defaultMaxConnectionsPerHost;
    private int maxTotalConntion;
    private boolean followRedirects;
    private boolean allowCompression;
    private int maxRetries;
    private String clientId;
    private static volatile Map<String, SolrSearchServerConfig> config = new HashMap(10);
    private static Logger logger = Logger.getLogger(SolrSearchServerConfig.class);

    public SolrSearchServerConfig() {
    }

    public static Map<String, SolrSearchServerConfig> getInstance(String path) throws Exception {
        if(config.size() == 0) {
            Map var1 = config;
            synchronized(config) {
                if(config.size() == 0) {
                    SAXReader saxRead = new SAXReader();
                    File xmlFile = new File(path);
                    if(xmlFile.isFile()) {
                        Document doc = saxRead.read(xmlFile);
                        Element root = doc.getRootElement();
                        List eles = root.elements("client");

                        for(int i = 0; i < eles.size(); ++i) {
                            Element client = (Element)eles.get(i);
                            SolrSearchServerConfig solrConfig = new SolrSearchServerConfig();
                            solrConfig.url = client.element("url").getData().toString();
                            solrConfig.soTimeout = Integer.parseInt(client.element("soTimeout").getData().toString());
                            solrConfig.connectionTimeout = Integer.parseInt(client.element("connectionTimeout").getData().toString());
                            solrConfig.defaultMaxConnectionsPerHost = Integer.parseInt(client.element("defaultMaxConnectionsPerHost").getData().toString());
                            solrConfig.maxTotalConntion = Integer.parseInt(client.element("maxTotalConntion").getData().toString());
                            solrConfig.followRedirects = Boolean.parseBoolean(client.element("followRedirects").getData().toString());
                            solrConfig.allowCompression = Boolean.parseBoolean(client.element("allowCompression").getData().toString());
                            solrConfig.maxRetries = Integer.parseInt(client.element("maxRetries").getData().toString());
                            solrConfig.clientId = client.attributeValue("id");
                            config.put(solrConfig.clientId, solrConfig);
                        }
                    } else {
                        logger.error("solr 客户端配置文件不存在[" + path + "].");
                    }
                }
            }
        }

        return config;
    }

    public String getClientId() {
        return this.clientId;
    }

    public String getUrl() {
        return this.url;
    }

    public Integer getSoTimeout() {
        return Integer.valueOf(this.soTimeout);
    }

    public Integer getConnectionTimeout() {
        return Integer.valueOf(this.connectionTimeout);
    }

    public int getDefaultMaxConnectionsPerHost() {
        return this.defaultMaxConnectionsPerHost;
    }

    public int getMaxTotalConntion() {
        return this.maxTotalConntion;
    }

    public boolean isFollowRedirects() {
        return this.followRedirects;
    }

    public boolean isAllowCompression() {
        return this.allowCompression;
    }

    public int getMaxRetries() {
        return this.maxRetries;
    }
}
