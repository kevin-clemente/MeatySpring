package com.kvanDev.Meaty;

import com.kvanDev.Meaty.api.IngredientController;
import com.kvanDev.Meaty.model.Ingredient;
import com.kvanDev.Meaty.model.Type;
import com.kvanDev.Meaty.service.IngredientsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import javax.net.ssl.SSLEngineResult;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(IngredientController.class)
class MeatyApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    IngredientsService ingredientsService;

	@Test
	void contextLoads() throws Exception {
        Type type = new Type(1l,"carne");
        when(ingredientsService.getAllIngredients()).thenReturn(
                List.of(new Ingredient(1L, "Picanha", type),
                        new Ingredient(1L, "Maminha", type)
                        ));
        this.mockMvc.perform(get("/ingredientes/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Maminha")));
	}

}
