package com.example.studenttuteur.Mappers;

import com.example.studenttuteur.DTOs.TuteurDTO;
import com.example.studenttuteur.Entitys.Tuteur;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TuteurMapper {

    private final StudentMapper studentMapper;

    public TuteurMapper(StudentMapper studentMapper) { // Injection des dépendances
        this.studentMapper = studentMapper;
    }

    public TuteurDTO toDTO(Tuteur tuteur) {
        TuteurDTO dto = new TuteurDTO();
        dto.setId(tuteur.getId());
        dto.setNom(tuteur.getNom());
        dto.setPrenom(tuteur.getPrenom());
        dto.setTelephone(tuteur.getTelephone());
        dto.setStudents(tuteur.getStudents().stream()
                .map(studentMapper::toDTO)
                .collect(Collectors.toList()));
        return dto;
    }

    public Tuteur toEntity(TuteurDTO dto) {
        Tuteur tuteur = new Tuteur();
        tuteur.setNom(dto.getNom());
        tuteur.setPrenom(dto.getPrenom());
        tuteur.setTelephone(dto.getTelephone());
        return tuteur;
    }

}

