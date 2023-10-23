package com.belhard.bookstore.web.controller;

import com.belhard.bookstore.service.dto.BookDto;
import com.belhard.bookstore.service.impl.BookServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final BookServiceImpl bookService;

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id, HttpSession session) {
        @SuppressWarnings("unchecked")
        HashMap<Long, Integer> cart = (HashMap<Long, Integer>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
            cart.put(id, 1);
        } else if (cart.containsKey(id)) {
            cart.replace(id, cart.get(id), cart.get(id) + 1);
        } else {
            cart.put(id, 1);
        }
        session.setAttribute("cart", cart);
        return "redirect:/books";
    }

    @GetMapping("/remove/{id}")
    public String removeFromCart(@PathVariable Long id, HttpSession session) {
        @SuppressWarnings("unchecked")
        HashMap<Long, Integer> cart = (HashMap<Long, Integer>) session.getAttribute("cart");
        if (!cart.isEmpty()) {
            if (cart.get(id) > 0) {
                cart.replace(id, cart.get(id), cart.get(id) - 1);
            } else {
                cart.remove(id);
            }
            session.setAttribute("cart", cart);
        } else {
            session.removeAttribute("cart");
        }
        return "cart";
    }

    @GetMapping
    public String cart(HttpSession session, Model model) {
        @SuppressWarnings("unchecked")
        HashMap<Long, Integer> cart = (HashMap<Long, Integer>) session.getAttribute("cart");
        if (cart != null) {
            HashMap<BookDto, Integer> books = new HashMap<>();
            cart.forEach((aLong, integer) -> books.put(bookService.getById(aLong), integer));
            model.addAttribute("books", books);
        }
        return "cart";
    }

    @GetMapping("/clear")
    public String clearCart(HttpSession session, Model model) {
        session.removeAttribute("cart");
        return "cart";
    }
}
