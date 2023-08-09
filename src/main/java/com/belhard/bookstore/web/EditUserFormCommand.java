package com.belhard.bookstore.web;

import com.belhard.bookstore.data.dto.UserDto;
import com.belhard.bookstore.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller("edit_user_form")
@RequiredArgsConstructor
public class EditUserFormCommand implements Command {
    public final UserService userService;

    @Override
    public String process(HttpServletRequest req) {
        Long id = Long.parseLong(req.getParameter("id"));
        UserDto userDto = userService.getById(id);
        req.setAttribute("user", userDto);
        return "jsp/editUserForm.jsp";
    }
}
