package com.ekownyenku.tacocloud.controllers;

import com.ekownyenku.tacocloud.data.IngredientData;
import com.ekownyenku.tacocloud.data.IngredientData.Type;
import com.ekownyenku.tacocloud.data.TacoData;
import com.ekownyenku.tacocloud.data.TacoOrderData;
import com.ekownyenku.tacocloud.repository.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;

    public DesignTacoController(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model){
        Iterable<IngredientData> ingredients = ingredientRepo.findAll();
         /*List<IngredientData> ingredients = Arrays.asList(
                new IngredientData("FLTO", "Flour Tortilla", Type.WRAP),
                new IngredientData("COTO", "Corn Tortilla", Type.WRAP),
                new IngredientData("GRBF", "Ground Beef", Type.PROTEIN),
                new IngredientData("CARN", "Carnitas", Type.PROTEIN),
                new IngredientData("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new IngredientData("LETC", "Lettuce", Type.VEGGIES),
                new IngredientData("CHED", "Cheddar", Type.CHEESE),
                new IngredientData("JACK", "Monterrey Jack", Type.CHEESE),
                new IngredientData("SLSA", "Salsa", Type.SAUCE),
                new IngredientData("SRCR", "Sour Cream", Type.SAUCE)
        );*/

        Type [] types = IngredientData.Type.values();
        for(Type type:types){
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }


    @ModelAttribute(name = "tacoOrder")
    public TacoOrderData order(){
        return new TacoOrderData();
    }

    @ModelAttribute(name = "taco")
    public TacoData taco(){
        return new TacoData();
    }

    @GetMapping
    public String showDesignForm(){
        return "view/design";
    }

    @PostMapping
    public String processOrder(@Valid TacoData taco,
                               Errors errors, @ModelAttribute("tacoOrder") TacoOrderData tacoOrder, HttpSession Session){
        if (errors.hasErrors()){
            return "view/design";
        }
        tacoOrder.addTaco(taco);
        System.out.println("tacoOrder model in session: "+ Session.getAttribute("tacoOrder"));
        log.info("Processing taco: {}", taco);
        return "redirect:/orders/current";
    }

    private List<IngredientData> filterByType(Iterable<IngredientData> ingredients, Type type) {
        ingredients
               /* .forEach(x -> {if(x.getType().equals(type))
                return x;});*/
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }


}
