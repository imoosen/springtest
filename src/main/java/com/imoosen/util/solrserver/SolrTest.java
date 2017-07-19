package com.imoosen.util.solrserver;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrResponse;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

/**
 * Created by Administrator on 2017/7/10 0010.
 */
public class SolrTest {

   public static void adddocument(){
       //创建一连接
       HttpSolrServer solrServer = new HttpSolrServer("http://127.0.0.1:8099/solr/core_1");
        SolrInputDocument document =  new SolrInputDocument();

       document.addField("id","test002");
       document.addField("name","doudou");
       try {
           solrServer.add(document);
           solrServer.commit();
       }catch (Exception e){
           e.printStackTrace();
       }

    }

    public static void querydocument(){
        HttpSolrServer solrServer = new HttpSolrServer("http://127.0.0.1:8099/solr/core_1");
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("*:*");
        try{
            QueryResponse queryResponse  = solrServer.query(solrQuery);
            SolrDocumentList solrDocuments=queryResponse.getResults();
            for (SolrDocument solrDocument : solrDocuments) {

                System.out.println(solrDocument.getFieldValuesMap());
                System.out.println("<<<<<<<<<<>>>>>>>>>>>>");
            }
            System.out.println(solrDocuments.getNumFound());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void deletedocument()throws Exception{
        HttpSolrServer solrServer = new HttpSolrServer("http://127.0.0.1:8099/solr/core_1");
        solrServer.deleteById("test002");
        solrServer.deleteByQuery("name:ying*");
        solrServer.commit();
        System.out.println();
    }
    public static void main(String[] args) throws Exception{
        //adddocument();
        deletedocument();
        querydocument();
    }

}
