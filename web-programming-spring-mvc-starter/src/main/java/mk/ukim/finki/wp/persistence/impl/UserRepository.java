package mk.ukim.finki.wp.persistence.impl;

import mk.ukim.finki.wp.model.User;
import mk.ukim.finki.wp.persistence.BaseRepository;
import mk.ukim.finki.wp.persistence.IUserRepository;
import mk.ukim.finki.wp.persistence.helper.PredicateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Darko on 2/20/2016.
 */

@Repository
public class UserRepository implements IUserRepository {
    @Autowired
    BaseRepository baseRepository;

    @Override
    public void save(User user) {
        baseRepository.saveOrUpdate(user);
    }

    private List<User> findByUsername(final String username){
        return baseRepository.find(User.class, new PredicateBuilder<User>() {
            @Override
            public Predicate toPredicate(CriteriaBuilder cb, CriteriaQuery<User> cq, Root<User> root) {
                return cb.equal(root.get("username"), username);
            }
        });
    }

    @Override
    public User logIn(String username, String password) {
        List<User> users = findByUsername(username);

        if (users.size() != 0){ //the user exists
            if (users.get(0).getPassword().equals(password))
                return users.get(0);
        }
        return null;
    }

    @Override
    public User findById(Long id) {
        return baseRepository.getById(User.class, id);
    }

    @Override
    public List<User> findAll() {
        return baseRepository.find(User.class, null);
    }

    @Override
    public void delete(Long id) {
        baseRepository.delete(User.class, id);
    }
}
