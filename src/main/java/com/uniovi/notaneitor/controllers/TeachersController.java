package com.uniovi.notaneitor.controllers;

import com.uniovi.notaneitor.entities.Teacher;
import com.uniovi.notaneitor.services.TeachersService;
import com.uniovi.notaneitor.validators.AddTeacherFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class TeachersController {

    @Autowired
    private TeachersService teachersService;

    @Autowired
    private AddTeacherFormValidator addTeacherFormValidator;

    @RequestMapping(value = "/teacher/list")
    public String getList(Model model){
        model.addAttribute("teacherList", teachersService.getTeachers());
        return "teacher/list";
    }

    @RequestMapping(value = "/teacher/add")
    public String getTeacher(Model model){
        model.addAttribute("teacher", new Teacher());
        return "teacher/add";
    }

    @RequestMapping(value = "/teacher/add", method = RequestMethod.POST)
    public String setTeacher(@Validated Teacher teacher, BindingResult result){
        addTeacherFormValidator.validate(teacher, result);
        if(result.hasErrors()){
            return "teacher/add";
        }

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

    @RequestMapping(value = "/teacher/edit")
    public String getEdit(){
        return "teacher/edit";
    }

    @RequestMapping(value = "/teacher/edit", method = RequestMethod.POST)
    public String setEdit(@ModelAttribute Teacher teacher){
        teachersService.addTeacher(teacher);
        return "redirect:/teacher/details/" + teacher.getId();
    }
}
