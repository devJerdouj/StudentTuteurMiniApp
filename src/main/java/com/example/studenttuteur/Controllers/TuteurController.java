package com.example.studenttuteur.Controllers;

import com.example.studenttuteur.DTOs.TuteurDTO;
import com.example.studenttuteur.Services.TuteurService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tuteurs")
public class TuteurController {

    private final TuteurService tuteurService;

    public TuteurController(TuteurService tuteurService) { // Injection des dépendances
        this.tuteurService = tuteurService;
    }



    @Operation(summary = "Get a tutor along with their students",
            description = "Fetches a tutor and their associated students by tutor ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tutor with students retrieved successfully",
                    content = @Content(schema = @Schema(implementation = TuteurDTO.class))),
            @ApiResponse(responseCode = "404", description = "Tutor not found",
                    content = @Content)
    })
    @GetMapping("/{id}/students")
    public TuteurDTO getTuteurWithStudents(@PathVariable Long id) {
        return tuteurService.getTuteurWithStudents(id);
    }


    @Operation(summary = "Create a new tutor", description = "Adds a new tutor to the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tutor created successfully",
                    content = @Content(schema = @Schema(implementation = TuteurDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content)
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TuteurDTO createTuteur(@Valid @RequestBody TuteurDTO tuteurDTO) {
        return tuteurService.createTuteur(tuteurDTO);
    }



    @Operation(summary = "Get all tutors", description = "Fetches a list of all tutors.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of tutors retrieved successfully",
                    content = @Content(schema = @Schema(implementation = TuteurDTO.class)))
    })
    // Get all Tuteurs
    @GetMapping
    public List<TuteurDTO> getAllTuteurs() {
        return tuteurService.getAllTuteurs();
    }


    @Operation(summary = "Get a tutor by ID", description = "Fetches a tutor by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tutor retrieved successfully",
                    content = @Content(schema = @Schema(implementation = TuteurDTO.class))),
            @ApiResponse(responseCode = "404", description = "Tutor not found",
                    content = @Content)
    })
    // Get a Tuteur by ID
    @GetMapping("/{id}")
    public TuteurDTO getTuteurById(@PathVariable Long id) {
        return tuteurService.getTuteurById(id);
    }


    @Operation(summary = "Update a tutor", description = "Updates the details of an existing tutor.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tutor updated successfully",
                    content = @Content(schema = @Schema(implementation = TuteurDTO.class))),
            @ApiResponse(responseCode = "404", description = "Tutor not found",
                    content = @Content)
    })
    // Update a Tuteur
    @PutMapping("/{id}")
    public TuteurDTO updateTuteur(@PathVariable Long id, @RequestBody TuteurDTO tuteurDTO) {
        return tuteurService.updateTuteur(id, tuteurDTO);
    }


    @Operation(summary = "Delete a tutor", description = "Deletes a tutor by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tutor deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Tutor not found",
                    content = @Content)
    })
    // Delete a Tuteur
    @DeleteMapping("/{id}")
    public String deleteTuteur(@PathVariable Long id) {
        tuteurService.deleteTuteur(id);
        return "Tuteur deleted successfully!";
    }
}
