package com.belhard.bookstore.web;

import com.belhard.bookstore.data.dto.UserDto;
import com.belhard.bookstore.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
public class UserCommand implements Command {
    private final UserService userService;


    @Override
    public String process(HttpServletRequest req) {
        String idRaw = req.getParameter("id");
        Long id = Long.valueOf(idRaw);
        UserDto userDto = userService.getById(id);
        req.setAttribute("user", userDto);
        return "jsp/user.jsp";
    }
}
