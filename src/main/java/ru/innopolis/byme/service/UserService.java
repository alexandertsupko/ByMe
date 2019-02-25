package ru.innopolis.byme.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.innopolis.byme.dao.UserDao;
import ru.innopolis.byme.entity.User;
import ru.innopolis.byme.exception.UserLoginAlreadyExistsException;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * слой сервиса для User Controller
 * реализует логику работы с пользователем
 *
 * @author Komovskiy Dmitriy
 * @since 21.02.2019
 */
@Component
public class UserService {

    // TODO: 2019-02-21 for testing only
    private static final List<String> images = new ArrayList<>();

    static {
        images.add("static/testImg/cat0.jpeg");
        images.add("static/testImg/cat1.jpeg");
        images.add("static/testImg/cat2.jpeg");
        images.add("static/testImg/cat3.jpeg");
        images.add("static/testImg/cat4.jpeg");
        images.add("static/testImg/cat5.jpeg");
        images.add("static/testImg/cat6.jpeg");
        images.add("static/testImg/cat7.jpeg");
        images.add("static/testImg/cat8.jpeg");
        images.add("static/testImg/cat0.jpeg");
        images.add("static/testImg/cat1.jpeg");
        images.add("static/testImg/cat2.jpeg");
        images.add("static/testImg/cat3.jpeg");
        images.add("static/testImg/cat4.jpeg");
        images.add("static/testImg/cat5.jpeg");
        images.add("static/testImg/cat6.jpeg");
        images.add("static/testImg/cat7.jpeg");
        images.add("static/testImg/cat8.jpeg");
    }

    // TODO: 2019-02-21 for testing only
    private static final List<String> cityList = new ArrayList<>();

    static {
        cityList.add("Казань");
        cityList.add("Москва");
        cityList.add("Санкт-Петербург");
        cityList.add("Новосибирск");
    }

    // TODO: 2019-02-21 for testing only
    private static final List<String> categoryList = new ArrayList<>();

    static {
        categoryList.add("пылесосы");
        categoryList.add("утюги");
        categoryList.add("консервы");
        categoryList.add("разносол");
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final PasswordEncoder encoder;
    private final UserDao dao;

    @Autowired
    public UserService(PasswordEncoder encoder, UserDao dao) {
        LOGGER.info("создали UserService");
        this.encoder = encoder;
        this.dao = dao;
    }

    public void saveUser(User user) throws UserLoginAlreadyExistsException {
        LOGGER.info("регистрируем нового пользователя в БД");
        user.setPassword(encoder.encode(user.getPassword()));
        dao.create(user);
    }

    public User newUser() {
        LOGGER.info("создаем пользователя");
        return new User();
    }

    public PasswordEncoder getEncoder() {
        LOGGER.info("запрос на получение кодировки");
        return encoder;
    }

    public DataSource getDataSource() {
        return dao.getDataSource();
    }

    public List<String> getCategoryList() {
        // TODO: 2019-02-21 create this list from DB exists list
        return categoryList;
    }

    public List<String> getImages() {
        // TODO: 2019-02-21 create this list from DB images list
        return images;
    }

    public List<String> getCityList() {
        // TODO: 2019-02-21 create this list from cities in DB
        return cityList;
    }
}