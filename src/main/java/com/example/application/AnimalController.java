package com.example.application;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileReader;
import java.util.List;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    private final AnimalService animalService;

    @Autowired
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public String insertAnimalsFromJSON() {
        try {
            // Read the animal JSON file
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("animals.json"));

            // Parse the animal JSON file and insert data into the database
            for (Object jsonObj : jsonArray) {
                JSONObject jsonObject = (JSONObject) jsonObj;
                Animal animal = new Animal();
                animal.setSpecies((String) jsonObject.get("species"));
                animal.setFood((String) jsonObject.get("food"));
                animal.setAmount(((Long) jsonObject.get("amount")).intValue());


                animalService.insertAnimal(animal);
            }

            return "Animals inserted successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred while inserting animals!";
        }
    }

    @GetMapping("/{id}")
    public Animal getAnimalById(@PathVariable Long id) {
        return animalService.getAnimalById(id);
    }

    @GetMapping("/all")
    public List<Animal> getAllAnimals() {
        return animalService.getAllAnimals();
    }

    @PostMapping
    public Animal createAnimal(@RequestBody Animal animal) {
        return animalService.insertAnimal(animal);
    }

    @PutMapping("/{id}")
    public Animal updateAnimal(@PathVariable Long id, @RequestBody Animal animal) {
        return animalService.updateAnimal(id, animal);
    }

    @DeleteMapping("/{id}")
    public String deleteAnimal(@PathVariable Long id) {
        animalService.deleteAnimal(id);
        return "Animal deleted successfully!";
    }
}