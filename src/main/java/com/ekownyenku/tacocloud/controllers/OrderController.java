package com.ekownyenku.tacocloud.controllers;

import com.ekownyenku.tacocloud.data.TacoOrderData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {
    @GetMapping("/current")
    public String orderForm()
    {
        return "view/orderForm";
        }

    @PostMapping
    public String processOrder(@Valid @ModelAttribute(name = "tacoOrder") TacoOrderData order,
                               Errors error, SessionStatus sessionStatus) {

      if (error.hasErrors()){
          log.info("Error: {}", error);
          return "view/orderForm";}
        log.info("Order submitted: {}", order);
        sessionStatus.setComplete();
        return "redirect:/";
    }

}
