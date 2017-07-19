package com.imoosen.util.solrserver;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.impl.HttpSolrServer;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/6 0006.
 */
public class SolrSearchServer {
    private static Map<String, HttpSolrServer> httpQueryServers = new HashMap();

    private static Logger logger = Logger.getLogger(SolrSearchServer.class);

    public SolrSearchServer(String configPath) {
        try {
            this.init(configPath);
        } catch (Exception var3) {
            logger.error("初始化搜索客户端异常：" + var3.getMessage(), var3);
        }

    }

    private synchronized void init(String configPath) throws Exception {
        if(httpQueryServers.size() == 0) {
            Map solrConfigs = SolrSearchServerConfig.getInstance(configPath);
            logger.debug("开始初始化Solr查询客户端：################################");
            Iterator var4 = solrConfigs.entrySet().iterator();

            while(var4.hasNext()) {
                Map.Entry entry = (Map.Entry)var4.next();
                HttpSolrServer httpQueryServer = new HttpSolrServer(((SolrSearchServerConfig)entry.getValue()).getUrl());
                httpQueryServer.setSoTimeout(((SolrSearchServerConfig)entry.getValue()).getSoTimeout().intValue());
                httpQueryServer.setConnectionTimeout(((SolrSearchServerConfig)entry.getValue()).getConnectionTimeout().intValue());
                httpQueryServer.setDefaultMaxConnectionsPerHost(((SolrSearchServerConfig)entry.getValue()).getMaxTotalConntion());
                httpQueryServer.setMaxTotalConnections(((SolrSearchServerConfig)entry.getValue()).getMaxTotalConntion());
                httpQueryServer.setFollowRedirects(((SolrSearchServerConfig)entry.getValue()).isFollowRedirects());
                httpQueryServer.setAllowCompression(((SolrSearchServerConfig)entry.getValue()).isAllowCompression());
                httpQueryServer.setMaxRetries(((SolrSearchServerConfig)entry.getValue()).getMaxRetries());
                httpQueryServers.put(((SolrSearchServerConfig)entry.getValue()).getClientId(), httpQueryServer);
                this.printStartLog((SolrSearchServerConfig)entry.getValue());
            }

            logger.debug("已完成初始化Solr查询客户端：################################");
        }

    }

    private void printStartLog(SolrSearchServerConfig cfg) {
        StringBuffer logstr = new StringBuffer();
        logstr.append("启动Solr HTTP client:");
        logstr.append(cfg.getClientId());
        logstr.append("\r\n");
        logstr.append("url:");
        logstr.append(cfg.getUrl());
        logstr.append("\r\n");
        logstr.append("SoTimeout:");
        logstr.append(cfg.getSoTimeout());
        logstr.append("\r\n");
        logstr.append("ConnectionTimeout:");
        logstr.append(cfg.getConnectionTimeout());
        logstr.append("\r\n");
        logstr.append("DefaultMaxConnectionsPerHost:");
        logstr.append(cfg.getDefaultMaxConnectionsPerHost());
        logstr.append("\r\n");
        logstr.append("MaxTotalConnections:");
        logstr.append(cfg.getMaxTotalConntion());
        logstr.append("\r\n");
        logstr.append("FollowRedirects:");
        logstr.append(cfg.isFollowRedirects());
        logstr.append("\r\n");
        logstr.append("AllowCompression:");
        logstr.append(cfg.isAllowCompression());
        logstr.append("\r\n");
        logstr.append("MaxRetries:");
        logstr.append(cfg.getMaxRetries());
        logstr.append("\r\n\r\n\r\n");
        logger.debug(logstr.toString());
    }

    public HttpSolrServer getInstance(String clientId) {
        return (HttpSolrServer)httpQueryServers.get(clientId);
    }
}
