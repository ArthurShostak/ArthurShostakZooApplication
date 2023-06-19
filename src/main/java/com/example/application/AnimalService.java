package com.example.application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    @Autowired
    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public Animal insertAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    public Animal getAnimalById(Long id) {
        Optional<Animal> optionalAnimal = animalRepository.findById(id);
        return optionalAnimal.orElse(null);
    }

    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    public Animal updateAnimal(Long id, Animal updatedAnimal) {
        Optional<Animal> optionalAnimal = animalRepository.findById(id);
        if (optionalAnimal.isPresent()) {
            Animal animal = optionalAnimal.get();
            animal.setSpecies(updatedAnimal.getSpecies());
            animal.setFood(updatedAnimal.getFood());
            animal.setAmount(updatedAnimal.getAmount());


            return animalRepository.save(animal);
        } else {
            return null;
        }
    }

    public void deleteAnimal(Long id) {
        animalRepository.deleteById(id);
    }
}