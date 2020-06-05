package com.kvanDev.Meaty;

import com.kvanDev.Meaty.model.Ingrediente;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Controller
@RequestMapping(path = "/")
public class MeatyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeatyApplication.class, args);
	}

	@GetMapping
	public @ResponseBody
	List<String> home(){
		List a = new ArrayList<String>();
		a.add("olaa");
		return a;
	}
}
