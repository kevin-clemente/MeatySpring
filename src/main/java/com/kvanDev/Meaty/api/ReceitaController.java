package com.kvanDev.Meaty.api;

import com.kvanDev.Meaty.model.Receita;
import com.kvanDev.Meaty.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/meaty")
public class ReceitaController {
    private final ReceitaService receitaService;
    @Autowired
    public ReceitaController(ReceitaService receitaService) {
        this.receitaService = receitaService;
    }

    @GetMapping(path = "/getAllReceitas")
    public @ResponseBody Iterable<Receita> getAll(){
        return receitaService.getAllReceitas();
    }

}
