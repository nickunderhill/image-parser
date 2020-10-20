package com.agileengine.imageparser.service.impl;

import com.agileengine.imageparser.domain.Picture;
import com.agileengine.imageparser.repository.PictureRepository;
import com.agileengine.imageparser.service.PictureService;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {

    private static final Logger log = LoggerFactory.getLogger(PictureService.class);

    @PersistenceContext
    private EntityManager entityManager;

    private final PictureRepository pictureRepository;

    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public List<Picture> searchPicturesByMetadata(String searchTerm) {

        log.info("Searching string \"{}\" in database...", searchTerm);

        Query simpleQueryStringQuery = getQueryBuilder()
                .simpleQueryString()
                .onFields("author", "camera", "tags")
                .matching(searchTerm)
                .createQuery();

        List<Picture> result = getJpaQuery(simpleQueryStringQuery).getResultList();

        log.info("Found {} entries", result.size());

        return result;
    }

    @Override
    public List<Picture> getAll() {
        return pictureRepository.findAll();
    }

    @Override
    public Picture getPictureDetails(String id) {
        return pictureRepository.getOne(id);
    }

    private FullTextQuery getJpaQuery(org.apache.lucene.search.Query luceneQuery) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        return fullTextEntityManager.createFullTextQuery(luceneQuery, Picture.class);
    }

    private QueryBuilder getQueryBuilder() {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        return fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Picture.class)
                .get();
    }
}
