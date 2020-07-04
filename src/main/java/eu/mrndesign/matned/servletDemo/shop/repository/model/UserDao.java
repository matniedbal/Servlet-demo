package eu.mrndesign.matned.servletDemo.shop.repository.model;

import eu.mrndesign.matned.servletDemo.shop.repository.model.entity.User;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class UserDao implements MainDaoInterface<User> {


    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void add(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void remove(User user) {

    }
}
