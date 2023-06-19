package com.example.application;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileReader;
import java.util.List;

@RestController
@RequestMapping("/enclosures")
public class EnclosureController {

    private final EnclosureService enclosureService;

    @Autowired
    public EnclosureController(EnclosureService enclosureService) {
        this.enclosureService = enclosureService;
    }

    @GetMapping
    public String insertEnclosuresFromJSON() {
        try {
            // Read the enclosure JSON file
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("enclosures.json"));

            // Parse the enclosure JSON file and insert data into the database
            for (Object jsonObj : jsonArray) {
                JSONObject jsonObject = (JSONObject) jsonObj;
                Enclosure enclosure = new Enclosure();
                enclosure.setName((String) jsonObject.get("name"));
                enclosure.setSize((String) jsonObject.get("size"));
                enclosure.setLocation((String) jsonObject.get("location"));

                enclosureService.insertEnclosure(enclosure);
            }

            return "Enclosures inserted successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred while inserting enclosures!";
        }
    }

    @GetMapping("/{id}")
    public Enclosure getEnclosureById(@PathVariable Long id) {
        return enclosureService.getEnclosureById(id);
    }

    @GetMapping("/all")
    public List<Enclosure> getAllEnclosures() {
        return enclosureService.getAllEnclosures();
    }

    @PostMapping
    public Enclosure createEnclosure(@RequestBody Enclosure enclosure) {
        return enclosureService.insertEnclosure(enclosure);
    }

    @PutMapping("/{id}")
    public Enclosure updateEnclosure(@PathVariable Long id, @RequestBody Enclosure enclosure) {
        return enclosureService.updateEnclosure(id, enclosure);
    }

    @DeleteMapping("/{id}")
    public String deleteEnclosure(@PathVariable Long id) {
        enclosureService.deleteEnclosure(id);
        return "Enclosure deleted successfully!";
    }
}