package com.mifel.demo.controller;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@SpringBootApplication
public class PokemonController {

    @GetMapping("/pokemon")
    public String getPokemonData() {
        String apiUrl = "https://pokeapi.co/api/v2/pokemon/ditto";

        RestTemplate restTemplate = new RestTemplate();
        String jsonResponse = restTemplate.getForObject(apiUrl, String.class);

        // Devolver la respuesta en formato JSON
        return jsonResponse;
    }
}
