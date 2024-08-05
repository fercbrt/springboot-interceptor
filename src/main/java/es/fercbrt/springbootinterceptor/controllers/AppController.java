package es.fercbrt.springbootinterceptor.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/app")
public class AppController {
    @GetMapping()
    public Map<String, String> index() {
        return Map.of("message", "Hello, World!");
    }

    @GetMapping("/pre")
    public Map<String, String> pre() {
        return Map.of("message", "Pre Handle");
    }

    @GetMapping("/post")
    public Map<String, String> post() {
        return Map.of("message", "Post Handle");
    }
}
