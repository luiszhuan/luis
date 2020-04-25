package com.luis.webdemo.controller.person;

import com.luis.webdemo.bean.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class PersonController {
    @RequestMapping(value = "person")
    public String index(Model model) {
        Person person = new Person();
        person.setName("张三");
        person.setAge(22);

        List<Person> people = new ArrayList<>();
        Person p1 = new Person();
        p1.setName("李四");
        p1.setAge(23);
        people.add(p1);

        Person p2 = new Person();
        p2.setName("王五");
        p2.setAge(24);
        people.add(p2);

        Person p3 = new Person();
        p3.setName("赵六");
        p3.setAge(25);
        people.add(p3);

        model.addAttribute("person", person);
        model.addAttribute("people", people);

        return "person";
    }
}
