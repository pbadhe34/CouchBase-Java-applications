package org.demo.heroes;


import org.jnosql.diana.api.Settings;
import org.jnosql.diana.api.document.DocumentCollectionManager;
import org.jnosql.diana.api.document.DocumentCollectionManagerFactory;
import org.jnosql.diana.api.document.DocumentConfiguration;
import org.jnosql.diana.couchbase.document.CouchbaseDocumentConfiguration;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class DocumentCollectionManagerProducer {


    private static final String DOCUMENT_COLLECTION = "heroes";

    private DocumentCollectionManager entityManager;

    @PostConstruct
    public void setUp() {
        DocumentConfiguration<?> configuration = new CouchbaseDocumentConfiguration();
        System.setProperty("com.couchbase.queryEnabled", "true");
        Settings settings = Settings.builder()
                .put("couchbase-host-1", "localhost")
                .put("couchbase-user", "Administrator")
                .put("couchbase-password", "MyPass123").build();
        DocumentCollectionManagerFactory<?> managerFactory = configuration.get(settings);
        entityManager = managerFactory.get(DOCUMENT_COLLECTION);
    }

    @Produces
    public DocumentCollectionManager getEntityManager() {
        return entityManager;
    }
}
