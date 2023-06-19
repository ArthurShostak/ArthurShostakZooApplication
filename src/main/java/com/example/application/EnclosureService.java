package com.example.application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnclosureService {

    private final EnclosureRepository enclosureRepository;

    @Autowired
    public EnclosureService(EnclosureRepository enclosureRepository) {
        this.enclosureRepository = enclosureRepository;
    }

    public Enclosure insertEnclosure(Enclosure enclosure) {
        return enclosureRepository.save(enclosure);
    }

    public Enclosure getEnclosureById(Long id) {
        Optional<Enclosure> optionalEnclosure = enclosureRepository.findById(id);
        return optionalEnclosure.orElse(null);
    }

    public List<Enclosure> getAllEnclosures() {
        return enclosureRepository.findAll();
    }

    public Enclosure updateEnclosure(Long id, Enclosure updatedEnclosure) {
        Optional<Enclosure> optionalEnclosure = enclosureRepository.findById(id);
        if (optionalEnclosure.isPresent()) {
            Enclosure enclosure = optionalEnclosure.get();
            enclosure.setName(updatedEnclosure.getName());
            enclosure.setSize(updatedEnclosure.getSize());
            enclosure.setLocation(updatedEnclosure.getLocation());

            return enclosureRepository.save(enclosure);
        } else {
            return null;
        }
    }

    public void deleteEnclosure(Long id) {
        enclosureRepository.deleteById(id);
    }
}
