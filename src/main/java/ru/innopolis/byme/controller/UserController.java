package ru.innopolis.byme.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.byme.entity.Ad;
import ru.innopolis.byme.entity.City;
import ru.innopolis.byme.entity.User;
import ru.innopolis.byme.form.AdFilter;
import ru.innopolis.byme.service.CityService;
import ru.innopolis.byme.service.UserService;
import ru.innopolis.byme.transfer.CategoryTree;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private static final int MAX_ADVERT_NUMBER = 20;
    public static final int ALL_CATEGORIES_ID = 0;
    public static final int ALL_CITIES_ID = 0;

    @Autowired
    private UserService userService;
    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        LOGGER.info("registration обработан userController get");
        model.addAttribute("user", userService.newUser());
        model.addAttribute("cities", cityService.getAll());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("user") User user, Model model) {
        LOGGER.info("registration обработан userController POST");
        try {
            userService.saveUser(user);
            LOGGER.info("добавление USER в БД");
        } catch (Exception e) {
            LOGGER.info("пользователь уже зарегистрирован");
            model.addAttribute("error", "user email reserved by other user");
            return "redirect:/registration";
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, Principal principal) {
        LOGGER.info("index обработан userController get");

        List<Ad> advs = userService.getAdvs(MAX_ADVERT_NUMBER);
        List<City> cityList = userService.getCityListWithSelected(ALL_CITIES_ID);
        List<CategoryTree> categoryList  = userService.getCategoryListWithSelected(ALL_CATEGORIES_ID);

        return buildMainModel(model, principal, advs, cityList, categoryList);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String index(AdFilter filter, Model model, Principal principal){
        List<Ad> advs = userService.getAdvs(MAX_ADVERT_NUMBER, filter.getCategoryId(), filter.getCityId());
        List<City> cityList = userService.getCityListWithSelected(filter.getCityId());
        List<CategoryTree> categoryList  = userService.getCategoryListWithSelected(filter.getCategoryId());

        return buildMainModel(model, principal, advs, cityList, categoryList);
    }

    private String buildMainModel(Model model, Principal principal,
                                  List<Ad> advs, List<City> cityList, List<CategoryTree> categoryList){
        model.addAttribute("list", advs);
        model.addAttribute("cityList", cityList);
        model.addAttribute("categoryList", categoryList);

        if (principal == null) {
            model.addAttribute("logUrl", "/login");
            model.addAttribute("logStatus", "Log In");
        } else {
            model.addAttribute("logUrl", "/logout");
            model.addAttribute("logStatus", "Log Out");
            model.addAttribute("user", principal.getName());
        }
        return "index";

    }

    @GetMapping("login")
    public String login(Principal principal) {
        if (principal == null) {
            return "login";
        } else {
            return "redirect:/";
        }
    }
}
