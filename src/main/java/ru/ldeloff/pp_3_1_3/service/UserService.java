package ru.ldeloff.pp_3_1_3.service;

import ru.ldeloff.pp_3_1_3.models.Role;
import ru.ldeloff.pp_3_1_3.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
@Service
public interface UserService {
    List<User> getAllUser();
    void add(User user);
    void delete(long id);
    void edit(User user);
    User getById(long id);
    void addRole(long userID, Role role);
    Set<Role> getRoles(long userID);
    User getByName(String userName);
}
