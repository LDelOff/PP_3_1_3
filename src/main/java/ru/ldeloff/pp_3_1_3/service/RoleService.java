package ru.ldeloff.pp_3_1_3.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ldeloff.pp_3_1_3.models.Role;
import ru.ldeloff.pp_3_1_3.models.User;

import java.util.List;

@Service
public interface RoleService {

    List<Role> getAllRoles();
    void add(Role role);
    void delete(long id);
    void edit(Role role);
    Role getById(long id);
    Role getByName(String roleName);

}







