package ru.sber.dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.nullValue;
import static ru.sber.dao.util.PhotoTestDataGenerator.createListOfRandomPhotos;
import static ru.sber.dao.util.PhotoTestDataGenerator.createRandomPhoto;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sber.dao.util.EntityManagerUtil;
import ru.sber.entity.Photo;

public class PhotoDaoTest {
    private EntityManagerUtil emUtil;
    private PhotoDao photoDao;
    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    public void setup() {
        entityManagerFactory = Persistence.createEntityManagerFactory("PhotoComments");
        emUtil = new EntityManagerUtil(entityManagerFactory);
        photoDao = new PhotoDaoImpl(entityManagerFactory);
    }

    @AfterEach
    public void destroy() {
        entityManagerFactory.close();
    }

    @Test
    public void testSavePhoto() {
        Photo photo = createRandomPhoto();

        photoDao.save(photo);

        Photo fountPhoto = emUtil.performReturningWithinTx(entityManager -> entityManager.find(Photo.class, photo.getId()));
        assertThat(fountPhoto, equalTo(photo));
    }

    @Test
    public void testFindPhotoById() {
        Photo photo = createRandomPhoto();
        emUtil.performWithinTx(entityManager -> entityManager.persist(photo));

        Photo foundPhoto = photoDao.findById(photo.getId());

        assertThat(foundPhoto, equalTo(photo));
    }

    @Test
    public void testFindAllPhotos() {
        List<Photo> listOfRandomPhotos = createListOfRandomPhotos(5);
        emUtil.performWithinTx(entityManager -> listOfRandomPhotos.forEach(entityManager::persist));

        List<Photo> foundPhotos = photoDao.findAll();

        assertThat(foundPhotos, containsInAnyOrder(listOfRandomPhotos.toArray()));
    }

    @Test
    public void testRemovePhoto() {
        Photo photo = createRandomPhoto();
        emUtil.performWithinTx(entityManager -> entityManager.persist(photo));

        photoDao.remove(photo);

        Photo removedPhoto = emUtil.performReturningWithinTx(entityManager -> entityManager.find(Photo.class, photo.getId()));
        assertThat(removedPhoto, nullValue());
    }

    @Test
    public void testAddPhotoComment() {
        Photo photo = createRandomPhoto();
        emUtil.performWithinTx(entityManager -> entityManager.persist(photo));

        photoDao.addComment(photo.getId(), "Nice picture!");

        emUtil.performWithinTx(entityManager -> {
            Photo managedPhoto = entityManager.find(Photo.class, photo.getId());
            assertThat(managedPhoto.getComments(),
                    hasItem(hasProperty("text", equalTo("Nice picture!"))));
        });
    }
}
