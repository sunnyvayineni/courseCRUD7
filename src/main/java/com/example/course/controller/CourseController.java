package com.example.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.example.course.entity.Course;
import com.example.course.service.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    CourseService service;

    @PostMapping
    public ResponseEntity<?> addCourse(@RequestBody Course course){

        Course c = service.addCourse(course);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(c);
    }

    @GetMapping
    public ResponseEntity<List<Course>> getCourses(){

        return ResponseEntity.ok(service.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourse(@PathVariable int id){

        Course c = service.getCourseById(id);

        if(c == null)
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Course Not Found");

        return ResponseEntity.ok(c);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable int id,
                                          @RequestBody Course course){

        Course updated = service.updateCourse(id, course);

        if(updated == null)
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Course Not Found");

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable int id){

        boolean deleted = service.deleteCourse(id);

        if(!deleted)
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Course Not Found");

        return ResponseEntity.ok("Course Deleted Successfully");
    }

    @GetMapping("/search/{title}")
    public ResponseEntity<List<Course>> searchCourse(@PathVariable String title){

        return ResponseEntity.ok(service.searchCourse(title));
    }
}