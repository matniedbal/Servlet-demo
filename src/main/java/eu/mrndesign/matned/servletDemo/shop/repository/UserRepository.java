package eu.mrndesign.matned.servletDemo.shop.repository;

import eu.mrndesign.matned.servletDemo.shop.repository.model.entity.User;

import java.util.*;

public class UserRepository {

    private static UserRepository repository;

    public static UserRepository getInstance() {
        if (repository == null) {
            repository = new UserRepository();
        }
        return repository;
    }

    private final List<User> users;

    private UserRepository() {
        users = new ArrayList<>();
        users.add(User.builder().name("Admin").surname("Admin").username("matned").password("asd").build());
    }

    public boolean existsByUsername(String username){
        return users.stream()
                .anyMatch(user->user.getUsername().equalsIgnoreCase(username));
    }

    public void save(User user) {
        users.add(user);
    }

    public List<User> findAll() {
        return Collections.unmodifiableList(users);
    }

    public Optional<User> findByLoginAndPassword(String login, String password) {
        return users.stream()
                .filter(u->u.getUsername().equalsIgnoreCase(login) && u.getPassword().equals(password))
                .findFirst();
    }

}
