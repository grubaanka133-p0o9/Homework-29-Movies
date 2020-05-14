package com.example.demo.movie;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class MovieRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void addMovie(Movie movie) {
        entityManager.persist(movie);
    }

    @Transactional
    public List<Movie> showAll() {
        TypedQuery<Movie> query = entityManager.createQuery("SELECT m FROM Movie m", Movie.class);
        return query.getResultList();
    }

    @Transactional
    public Movie filteredByCategory(Categories categories) {
        TypedQuery<Movie> query = entityManager.createQuery("SELECT m FROM Movie m WHERE m.category=" + categories,
                Movie.class);
        return query.getSingleResult();
    }

    @Transactional
    public Movie findById(Long id) {
        TypedQuery<Movie> query = entityManager.createQuery("SELECT m FROM Movie m WHERE m.id =" + id, Movie.class);
        return query.getSingleResult();
    }
}
