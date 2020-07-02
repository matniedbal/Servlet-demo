package eu.mrndesign.matned.servletDemo.shop.service;

import java.util.*;

import eu.mrndesign.matned.servletDemo.shop.repository.model.entity.User;
import eu.mrndesign.matned.servletDemo.shop.repository.UserRepository;

public class UserService {

    private static UserService service;

    public static UserService getInstance() {
        if (service == null) {
            service = new UserService();
        }
        return service;
    }

    private final UserRepository repository;

    private UserService() {repository = UserRepository.getInstance();}

    public boolean existsByUsername(String username){
        return repository.existsByUsername(username);
    }

    public void save(User user){
        repository.save(user);
    }

    public List<User> findAll(){
        return repository.findAll();
    }

    public Optional<User> findUserByLoginAndPassword(String login, String password) {
        return repository.findByLoginAndPassword(login, password);
    }
}
