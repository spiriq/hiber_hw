package ru.sber.dao;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import ru.sber.entity.Photo;

public class PhotoDaoImpl implements PhotoDao {
    private EntityManagerFactory entityManagerFactory;

    public PhotoDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void save(Photo photo) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public Photo findById(long id) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public List<Photo> findAll() {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public void remove(Photo photo) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public void addComment(long photoId, String comment) {
        throw new UnsupportedOperationException("TODO");
    }
}
