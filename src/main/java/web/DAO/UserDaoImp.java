package web.DAO;

import org.springframework.transaction.annotation.Transactional;
import web.Model.User;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import java.util.List;


@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<User> allUser() {
        return  entityManager.createQuery("select u from User u", User.class).getResultList();
    }
    @Override
    public User getUser(int id){
        return entityManager.find(User.class,id);
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);

    }

    @Override
    public User updateUser(User user) {
        return entityManager.merge(user);
    }
    @Override
    public void delete(int id) {
        entityManager.remove(getUser(id));

    }
}