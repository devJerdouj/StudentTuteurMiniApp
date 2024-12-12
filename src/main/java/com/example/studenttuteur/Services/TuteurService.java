package com.example.studenttuteur.Services;

import com.example.studenttuteur.DTOs.TuteurDTO;
import com.example.studenttuteur.Entitys.Tuteur;
import com.example.studenttuteur.Mappers.TuteurMapper;
import com.example.studenttuteur.Repositories.TuteurRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TuteurService {

    private final TuteurRepository tuteurRepository; // Injection des dépendances
    private final TuteurMapper tuteurMapper;

    public TuteurService(TuteurRepository tuteurRepository, TuteurMapper tuteurMapper) {
        this.tuteurRepository = tuteurRepository;
        this.tuteurMapper = tuteurMapper;
    }

    public TuteurDTO getTuteurWithStudents(Long id) {
        Tuteur tuteur = tuteurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tuteur non trouvé"));
        return tuteurMapper.toDTO(tuteur);
    }

    public TuteurDTO createTuteur(TuteurDTO tuteurDTO) {
        Tuteur tuteur = tuteurMapper.toEntity(tuteurDTO);
        Tuteur savedTuteur = tuteurRepository.save(tuteur);
        return tuteurMapper.toDTO(savedTuteur);
    }

    // Get all Tuteurs
    public List<TuteurDTO> getAllTuteurs() {
        return tuteurRepository.findAll()
                .stream()
                .map(tuteurMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Get a Tuteur by ID
    public TuteurDTO getTuteurById(Long id) {
        Tuteur tuteur = tuteurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tuteur not found with id: " + id));
        return tuteurMapper.toDTO(tuteur);
    }

    // Update a Tuteur
    public TuteurDTO updateTuteur(Long id, TuteurDTO tuteurDTO) {
        Tuteur existingTuteur = tuteurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tuteur not found with id: " + id));

        existingTuteur.setNom(tuteurDTO.getNom());
        existingTuteur.setPrenom(tuteurDTO.getPrenom());
        existingTuteur.setTelephone(tuteurDTO.getTelephone());

        Tuteur updatedTuteur = tuteurRepository.save(existingTuteur);
        return tuteurMapper.toDTO(updatedTuteur);
    }

    // Delete a Tuteur
    public void deleteTuteur(Long id) {
        if (!tuteurRepository.existsById(id)) {
            throw new RuntimeException("Tuteur not found with id: " + id);
        }
        tuteurRepository.deleteById(id);
    }
}

