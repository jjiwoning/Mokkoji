package com.ssafy.Mokkoji.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HomeController {

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Response home() {
        return Response.of("OK");
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    static class Response {

        private String status;

        static Response of(String status) {
            return new Response(status);
        }
    }
}
