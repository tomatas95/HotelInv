package com.homework.homework.controller;

import com.homework.homework.model.PVModel;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("pvmModel", new PVModel());
        return "index";
    }

    @PostMapping("/pvm-result")
    public String calcPVM(@Valid @ModelAttribute("pvmModel") PVModel pvmModel, Model model) {

        if(pvmModel.getPrice() <= 0){
            model.addAttribute("priceErr", "Price has to be greater than 0.");
            return "index";
        }
        if(pvmModel.getPrice() == null){
            model.addAttribute("priceErr", "Price is required.");
            return "index";
        }

        if(pvmModel.getQuantity() <= 0){
            model.addAttribute("quantityErr", "Quantity has to be greater than 0.");
            return "index";
        }

        if(pvmModel.getQuantity() == null){
            model.addAttribute("quantityErr", "Quantity is required.");
            return "index";
        }

        double PVM_Rate = 0.2;
        double priceWithPVM = pvmModel.getPrice() / (1 + PVM_Rate);
        double ResultWithNoPVM = pvmModel.getPrice() * pvmModel.getQuantity();
        double ResultWithPVM = PVM_Rate * pvmModel.getPrice() * pvmModel.getQuantity();
        double totalPriceWithPVM = priceWithPVM * pvmModel.getQuantity();

        String formattedPriceWithNoPVM = String.format("%.2f", pvmModel.getPrice());
        String formattedPriceWithPVM = String.format("%.2f", priceWithPVM);
        String formattedResultWithNoPVM = String.format("%.2f", ResultWithNoPVM);
        String formattedResultWithPVM = String.format("%.2f", ResultWithPVM);
        String formattedTotalPriceWithPVM = String.format("%.2f", totalPriceWithPVM);

        model.addAttribute("PVM_Rate", PVM_Rate);
        model.addAttribute("priceWithNoPVM", formattedPriceWithNoPVM);
        model.addAttribute("priceWithPVM", formattedPriceWithPVM);
        model.addAttribute("ResultWithNoPVM", formattedResultWithNoPVM);
        model.addAttribute("ResultWithPVM", formattedResultWithPVM);
        model.addAttribute("totalPriceWithPVM", formattedTotalPriceWithPVM);

        return "pvm-result";
    }

}
