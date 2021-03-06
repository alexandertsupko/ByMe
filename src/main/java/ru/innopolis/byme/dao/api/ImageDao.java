package ru.innopolis.byme.dao.api;

import ru.innopolis.byme.entity.Image;

import java.util.List;

public interface ImageDao {
    void create(Image image);

    void update(Image image);

    boolean exists(int adId);

    List<Image> getAll();

    Image getImageByAd(int adId);
}
