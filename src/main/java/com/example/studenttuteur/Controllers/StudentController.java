package com.example.studenttuteur.Controllers;

import com.example.studenttuteur.DTOs.StudentDTO;
import com.example.studenttuteur.Services.StudentService;
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
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) { // Injection des dépendances
        this.studentService = studentService;
    }


    @Operation(summary = "Create a new student", description = "Adds a new student to the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student created successfully",
                    content = @Content(schema = @Schema(implementation = StudentDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content)
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDTO createStudent(@Valid @RequestBody StudentDTO studentDTO) {
        return studentService.createStudent(studentDTO);
    }


    @Operation(summary = "Get all students", description = "Fetches a list of all students.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of students retrieved",
                    content = @Content(schema = @Schema(implementation = StudentDTO.class))),
    })
    // Get all students
    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudents();
    }



    @Operation(summary = "Get a student by ID", description = "Fetches a student by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student retrieved successfully",
                    content = @Content(schema = @Schema(implementation = StudentDTO.class))),
            @ApiResponse(responseCode = "404", description = "Student not found",
                    content = @Content)
    })
    // Get a student by ID
    @GetMapping("/{id}")
    public StudentDTO getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }





    @Operation(summary = "Update a student", description = "Updates the details of an existing student.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student updated successfully",
                    content = @Content(schema = @Schema(implementation = StudentDTO.class))),
            @ApiResponse(responseCode = "404", description = "Student not found",
                    content = @Content)
    })
    // Update a student
    @PutMapping("/{id}")
    public StudentDTO updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
        return studentService.updateStudent(id, studentDTO);
    }



    @Operation(summary = "Delete a student", description = "Deletes a student by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Student not found",
                    content = @Content)
    })
    // Delete a student
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "Student deleted successfully!";
    }
}
