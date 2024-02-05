package com.dncoyote.schoolmgmt.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dncoyote.schoolmgmt.model.School;
import com.dncoyote.schoolmgmt.service.SchoolService;

@RestController
@RequestMapping("/schools")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @GetMapping
    public List<School> getAllSchools() {
        return schoolService.getAllSchools();
    }

    // Get a school by ID
    @GetMapping("/{id}")
    public ResponseEntity<School> getschoolById(@PathVariable Long id) {
        Optional<School> school = schoolService.getSchoolById(id);
        return school.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new school
    @PostMapping
    public ResponseEntity<School> createschool(@RequestBody School school) {
        School createdschool = schoolService.saveSchool(school);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdschool);
    }

    // Update an existing school
    @PutMapping("/{id}")
    public ResponseEntity<School> updateschool(@PathVariable Long id, @RequestBody School updatedSchool) {
        Optional<School> existingSchool = schoolService.getSchoolById(id);

        if (existingSchool.isPresent()) {
            updatedSchool.setId(id);
            School savedSchool = schoolService.saveSchool(updatedSchool);
            return ResponseEntity.ok(savedSchool);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a school by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteschool(@PathVariable Long id) {
        if (schoolService.schoolExists(id)) {
            schoolService.deleteSchool(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
