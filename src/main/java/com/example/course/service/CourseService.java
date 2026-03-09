package com.example.course.service;

import java.util.*;
import org.springframework.stereotype.Service;
import com.example.course.entity.Course;

@Service
public class CourseService {

    private Map<Integer, Course> courses = new HashMap<>();

    public Course addCourse(Course course) {
        courses.put(course.getCourseId(), course);
        return course;
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courses.values());
    }

    public Course getCourseById(int id) {
        return courses.get(id);
    }

    public Course updateCourse(int id, Course course) {

        if(courses.containsKey(id)) {
            courses.put(id, course);
            return course;
        }

        return null;
    }

    public boolean deleteCourse(int id) {

        if(courses.containsKey(id)) {
            courses.remove(id);
            return true;
        }

        return false;
    }

    public List<Course> searchCourse(String title) {

        List<Course> result = new ArrayList<>();

        for(Course c : courses.values()) {
            if(c.getTitle().equalsIgnoreCase(title)) {
                result.add(c);
            }
        }

        return result;
    }
}