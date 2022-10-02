package ru.ldeloff.pp_3_1_3.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ldeloff.pp_3_1_3.models.User;
import ru.ldeloff.pp_3_1_3.service.RoleService;
import ru.ldeloff.pp_3_1_3.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "")
    public String AdminPage(Model model) {
        return "admin/index";
    }

    @GetMapping(value = "/allUsers")
    public String AllUsers(Model model) {
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("users", userService.getAllUser());
        return "/admin/allUsers";
    }

    @GetMapping(value = "/new")
    public String newUser(Model model) {
        model.addAttribute("person", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/new";
    }

    @PostMapping(value = "/add")
    public String add(@RequestParam(name = "username") String username,
                      @RequestParam(name = "password") String password,
                      @RequestParam(name = "roleName") String roleName) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.addRole(roleService.getByName(roleName));
        userService.add(user);
        return "redirect:/admin/allUsers";
    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteUser(@RequestParam("id") long id) {
        userService.delete(id);
        return "redirect:/admin/allUsers";
    }

    @RequestMapping(value = "/edit")
    public String editUser(@RequestParam("id") long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAllRoles());
        return "/admin/edit";
    }

    @RequestMapping(value = "/update/{id}")
    public String updateUser(@PathVariable("id") long id,
                             @RequestParam(name = "username") String username,
                             @RequestParam(name = "password") String password,
                             @RequestParam(name = "roleName") String roleName) {
        User user = userService.getById(id);
        user.setUsername(username);
        user.setPassword(password);
        user.addRole(roleService.getByName(roleName));
        userService.edit(user);
        return "redirect:/admin/allUsers";
    }
}
