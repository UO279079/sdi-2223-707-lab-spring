package com.uniovi.notaneitor.controllers;

import com.uniovi.notaneitor.entities.Teacher;
import com.uniovi.notaneitor.services.TeachersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TeachersController {

    @Autowired
    private TeachersService teachersService;

    @RequestMapping(value = "/teacher/list")
    public String getList(Model model){
        model.addAttribute("teacherList", teachersService.getTeachers());
        return "teacher/list";
    }

    @RequestMapping(value = "/teacher/add")
    public String getTeacher(){
        return "teacher/add";
    }

    @RequestMapping(value = "/teacher/add", method = RequestMethod.POST)
    public String setTeacher(@ModelAttribute Teacher teacher){
        teachersService.addTeacher(teacher);
        return "redirect:/teacher/list";
    }

    @RequestMapping(value = "/teacher/details/{id}")
    public String getDetail(Model model, @PathVariable Long id){
        model.addAttribute("teacher", teachersService.getTeacher(id));
        return "teacher/details";
    }

    @RequestMapping(value = "/teacher/delete/{id}")
    public String deleteTeacher(@PathVariable Long id) {
        teachersService.deleteTeacher(id);
        return "redirect:/teacher/list";
    }
}
