package com.batty.registry.datastore;


import com.batty.framework.interfaces.DatastoreInterface;
import com.batty.registry.model.DeleteServiceSchema;
import com.batty.registry.model.MongoDate;
import com.batty.registry.model.ServiceSchema;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.mongodb.client.model.IndexOptions;
import jakarta.annotation.PostConstruct;
import org.bson.Document;
import com.mongodb.client.model.Filters.*;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.batty.framework.datastore.DatabaseHandler;
import com.batty.framework.datastore.DatastoreUtil;

import java.util.Date;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;


@Component("RegistryDataStoreImpl")
public class DatastoreImpl implements DatastoreInterface {

    protected Logger log = LoggerFactory.getLogger(DatastoreImpl.class);

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected DatabaseHandler datastore;

    @Autowired
    protected DatastoreUtil utils;

    @PostConstruct
    public void initialize()
    {
        createIndex();
    }



    @Override
    public void createIndex() {
        try
        {
            Document index = new Document();
            index.put("serviceId",1);
            this.datastore.createIndex(index, this.utils.getOptions().unique(true));
            index.clear();
        }
        catch(Exception ignored)
        {

        }
    }

    public ServiceSchema findServiceById(String serviceId) {
        Document doc = new Document();
        doc.put("serviceId",serviceId);
        try
        {
          return this.datastore.findOne(doc,ServiceSchema.class);
        }
        catch(Exception e)
        {
                    log.info("error in findServiceById"+e);
                    return null;
                    // throw new RuntimeException(e);
        }
    }

    public boolean addService(ServiceSchema serviceSchemaModel)  {
        Document doc;
        try
        {
            doc  = Document.parse(this.objectMapper.writeValueAsString(serviceSchemaModel));
            return this.datastore.insertOne(doc) ;
        } catch (Exception ignored) {
            throw new RuntimeException();
        }

    }

    public DeleteServiceSchema deleteServiceById(String serviceId) {

        Bson doc = eq("serviceId", serviceId);
        try
        {
            DeleteServiceSchema del = new DeleteServiceSchema();
             if( this.datastore.removeOne(doc) > 0  ) {
                 del.setServiceId(serviceId);
                 del.setDeleteStatus("deleted");
                 MongoDate lastModifiedDateTimeStamp = new MongoDate();
                 lastModifiedDateTimeStamp.$date(new Date());
                 del.setLastModifiedTimeStamp(lastModifiedDateTimeStamp);
             }

             return del;
        }
        catch(Exception e)
        {
            log.info("error in findServiceById"+e);

            throw new RuntimeException(e);
        }
    }

}
