package com.horbatenko.testtask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class UIController {
    @GetMapping
    public String root() {
        return "redirect:kpacs";
    }

    @GetMapping("kpacs")
    public ModelAndView getKPacs(ModelAndView model) {
        model.setViewName("kpacs");
        model.addObject("title", "K-PACs");
        model.addObject("headerName", "List of K-PACs");
        model.addObject("href", "sets");
        model.addObject("anotherPageName", "K-PAC Sets");
        model.addObject("dataUrl", "data/kpacs/");
        model.addObject("isSet", "false");
        return model;
    }

    @GetMapping("sets")
    public ModelAndView getSets(ModelAndView model) {
        model.setViewName("sets");
        model.addObject("title", "K-PAC Sets");
        model.addObject("headerName", "List of K-PAC Sets");
        model.addObject("href", "kpacs");
        model.addObject("anotherPageName", "K-PACs");
        model.addObject("dataUrl", "data/sets/");
        model.addObject("isSet", "true");
        return model;
    }

    @GetMapping("set/{id}")
    public ModelAndView getSetById(@PathVariable("id") int id, ModelAndView model) {
        model.setViewName("set");
        model.addObject("title", "K-PAC Set");
        model.addObject("headerName", "K-PAC Set: ");
        model.addObject("href", "sets");
        model.addObject("anotherPageName", "K-PAC Sets");
        model.addObject("dataUrl", "data/sets/"+id);
        model.addObject("isSet", "false");
        return model;
    }

}
