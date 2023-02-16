package com.uniovi.notaneitor.controllers;

import com.uniovi.notaneitor.entities.Teacher;
import com.uniovi.notaneitor.services.TeachersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TeachersController {

    @Autowired
    private TeachersService teachersService;

    @RequestMapping(value = "/teacher/list")
    public String getList(){
        return teachersService.getTeachers().toString();
    }

    @RequestMapping(value = "/teacher/add", method = RequestMethod.POST)
    public String setTeacher(@ModelAttribute Teacher teacher){
        teachersService.addTeacher(teacher);
        return "Ok";
    }

    @RequestMapping(value = "/teacher/details/{id}")
    public String getDetail(@PathVariable Long id){
        return  teachersService.getTeacher(id).toString();
    }

    @RequestMapping(value = "/teacher/delete/{id}")
    public String deleteTeacher(@PathVariable Long id){
        teachersService.deleteTeacher(id);
        return "Ok";
    }
}
