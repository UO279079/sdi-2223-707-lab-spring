package com.uniovi.notaneitor.services;

import com.uniovi.notaneitor.entities.Teacher;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

@Service
public class TeachersService {

    private List<Teacher> teachersList = new LinkedList<>();

    @PostConstruct
    public void init(){
        teachersList.add(new Teacher(1L, "1", "Pepito", "García", "Prácticas"));
        teachersList.add(new Teacher(2L, "2", "María", "Pérez", "Interina"));
    }

    public List<Teacher> getTeachers(){
        return teachersList;
    }

    public Teacher getTeacher(Long id){
        return teachersList.stream().filter(teacher -> teacher.getId().equals(id)).findFirst().get();
    }

    public void addTeacher(Teacher teacher){
        if(teacher.getId() == null){
            teacher.setId(teachersList.get(teachersList.size() - 1).getId() + 1);
        }

        teachersList.add(teacher);
    }

    public void deleteTeacher(Long id){
        teachersList.removeIf(teacher -> teacher.getId().equals(id));
    }
}
