package com.example.studenttuteur;

import com.example.studenttuteur.Entitys.Student;
import com.example.studenttuteur.Entitys.Tuteur;
import com.example.studenttuteur.Repositories.StudentRepository;
import com.example.studenttuteur.Repositories.TuteurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StudentTuteurApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentTuteurApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(StudentRepository studentRepository, TuteurRepository tuteurRepository) {


        return args -> {
            // Créer un tuteur en utilisant un constructeur et des setters
            Tuteur tuteur1 = new Tuteur();
            tuteur1.setNom("El Khattabi");
            tuteur1.setPrenom("Ahmed");

            // Sauvegarder le tuteur dans la base de données
            tuteurRepository.save(tuteur1);

            // Créer un étudiant et l'associer au tuteur
            Student student1 = new Student();
            student1.setNom("El Khattabi");
            student1.setPrenom("Youssef");
            student1.setAge(20);
            student1.setTuteur(tuteur1);

            // Sauvegarder l'étudiant dans la base de données
            studentRepository.save(student1);

            // Exemple de création d'un autre étudiant
            Student student2 = new Student();
            student2.setNom("El Khattabi");
            student2.setPrenom("Fatima");
            student2.setAge(22);
            student2.setTuteur(tuteur1);

            // Sauvegarder le deuxième étudiant
            studentRepository.save(student2);

            System.out.println("Les étudiants et leurs tuteurs ont été sauvegardés avec succès !");
        };


}
    }




