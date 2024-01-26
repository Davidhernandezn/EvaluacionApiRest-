package com.mifel.demo.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@SpringBootApplication
@RequestMapping("/pokemon")
public class PokemonController {

    @GetMapping("/dato")
    public String getPokemonData(@RequestParam String nombre) {
        String apiUrl = "https://pokeapi.co/api/v2/pokemon/" + nombre;

        RestTemplate restTemplate = new RestTemplate();
        String jsonResponse = restTemplate.getForObject(apiUrl, String.class);
        return jsonResponse;
    }

    @GetMapping("/{nombre}")
    public String getPokemonDataByPath(@PathVariable String nombre) {
        String apiUrl = "https://pokeapi.co/api/v2/pokemon/" + nombre;

        RestTemplate restTemplate = new RestTemplate();
        String jsonResponse = restTemplate.getForObject(apiUrl, String.class);
        return jsonResponse;
    }
}
