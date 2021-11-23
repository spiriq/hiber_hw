package ru.sber.dao.util;

import static java.util.stream.Collectors.toList;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;
import org.apache.commons.lang3.RandomStringUtils;
import ru.sber.entity.Photo;
import ru.sber.entity.PhotoComment;

public class PhotoTestDataGenerator {
    public static Photo createRandomPhoto(){
        Photo photo = new Photo();
        photo.setUrl(RandomStringUtils.randomAlphabetic(30));
        photo.setDescription(RandomStringUtils.randomAlphabetic(50));
        return photo;
    }

    public static PhotoComment createRandomPhotoComment() {
        PhotoComment photoComment = new PhotoComment();
        photoComment.setCreatedOn(LocalDateTime.now());
        photoComment.setText(RandomStringUtils.randomAlphabetic(50));
        return photoComment;
    }

    public static List<Photo> createListOfRandomPhotos(int size) {
        return Stream.generate(PhotoTestDataGenerator::createRandomPhoto).limit(size).collect(toList());
    }
    public static List<PhotoComment> createListOfRandomComments(int size) {
        return Stream.generate(PhotoTestDataGenerator::createRandomPhotoComment).limit(size).collect(toList());
    }
}

