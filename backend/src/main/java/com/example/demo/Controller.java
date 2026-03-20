package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class Controller {

    @Autowired
    private MatrixService service;

    @PostMapping("/multiply")
public Result multiply(@RequestBody Input input) {
    return service.multiply(input.A, input.B);
}
}

class Input {
    public int[][] A;
    public int[][] B;
}