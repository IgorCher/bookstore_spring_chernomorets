package com.belhard.bookstore.web;

import com.belhard.bookstore.service.UserService;
import com.belhard.bookstore.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public String getOne(@PathVariable long id, Model model) {
        UserDto userDto = userService.getById(id);
        model.addAttribute("user", userDto);
        return "user";
    }

    @GetMapping
    public String getAll(Model model) {
        List<UserDto> users = userService.getAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable long id, Model model) {
        UserDto userDto = userService.getById(id);
        model.addAttribute("user", userDto);
        return "editUserForm";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute UserDto userDto) {
        userService.update(userDto);
        return "redirect:/users/" + userDto.getId();
    }

    @GetMapping("/create")
    public String createForm() {
        return "/userForm";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute UserDto userDto) {
        UserDto created = userService.create(userDto);
        return "redirect:/users/" + created.getId();
    }

    @PostMapping("/delete")
    public String delete(@RequestParam long id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
