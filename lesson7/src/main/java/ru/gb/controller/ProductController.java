package ru.gb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.gb.persist.Product;
import ru.gb.persist.ProductRepository;

import javax.validation.Valid;
import java.util.Optional;

@RequestMapping("/product")
@Controller
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String listPage(@RequestParam Optional<String> productFilter, Model model) {
        if (productFilter.isEmpty() || productFilter.get().isBlank()) {
            model.addAttribute("products", productRepository.findAll());
        } else {
            model.addAttribute("products", productRepository.findProductByProductLike("%" + productFilter.get() + "%"));
        }
        return "product";
    }

    @GetMapping("/{id}")
    public String form(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", productRepository.findById(id));
        return "product_form";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("product", new Product("",null));
        return "product_form";
    }

    @PostMapping
    public String save(@Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product_form";
        }
        productRepository.save(product);
        return "redirect:/product";
    }

    @DeleteMapping("{id}")
    public String form(@PathVariable long id) {
        productRepository.deleteById(id);
        return "redirect:/product";
    }

}
