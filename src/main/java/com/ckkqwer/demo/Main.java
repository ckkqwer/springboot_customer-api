package com.ckkqwer.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
public class Main
{
    public static void main(String[] args){
        SpringApplication.run(Main.class, args);
    }

    @GetMapping("/")
    @ResponseBody
    public String root(){
        return "demo app: ckkqwer";
    }

    @GetMapping("greet")
    @ResponseBody
    public GreetingResponse greet(@RequestParam String name){
        GreetingResponse greetingResponse = new GreetingResponse("Hello, " + name,
                                                                 List.of("Java", "C#", "JS"),
                                                                 new Person("Alex", 22, 18000));
        return greetingResponse;
    }

    record Person(String name, int age, double salary){}
    record GreetingResponse(String greet,
                            List<String> favLanguage,
                            Person person){}
//    class GreetingResponse{
//        private final String greet;
//
//        public GreetingResponse(String greet)
//        {
//            this.greet = greet;
//        }
//
//        public String getGreet()
//        {
//            return greet;
//        }
//
//        @Override
//        public String toString()
//        {
//            return "GreetingResponse{" +
//                    "greet='" + greet + '\'' +
//                    '}';
//        }
//
//        @Override
//        public boolean equals(Object o)
//        {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            GreetingResponse that = (GreetingResponse) o;
//            return Objects.equals(greet, that.greet);
//        }
//
//        @Override
//        public int hashCode()
//        {
//            return Objects.hash(greet);
//        }
//    }
}
