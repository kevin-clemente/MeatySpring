package com.kvanDev.Meaty.api;

import com.kvanDev.Meaty.model.Type;
import com.kvanDev.Meaty.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(path = "/type")
public class TypeController {
    private final TypeService typeService;

    @Autowired
    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping(path = "/all")
    public @ResponseBody List<Type> getAll(){return typeService.getAllType();}
}
