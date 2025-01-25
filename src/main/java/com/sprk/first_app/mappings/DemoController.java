package com.sprk.first_app.mappings;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    // Mappings

    // GetMapping
    @GetMapping("/hello")
    public String sayHello() {
        Date date = new Date();
        String output = "<h1>Hello Team, <br>Time on server is: " + date + "</h1>";
        return output;
    }

    @GetMapping("/sum")
    public String performSum(@RequestParam(name = "num1", defaultValue = "0") int n1,
            @RequestParam(defaultValue = "0") int n2) {
        int sum = n1 + n2;
        String resp = "The sum of " + n1 + ", " + n2 + " = " + sum;
        return resp;
    }

    @GetMapping("/add")
    public String performAddition(@RequestParam String n1,
            @RequestParam String n2) {

        String resp;
        String regex = "^\\d+$";
        if (Pattern.matches(regex, n1) && Pattern.matches(regex, n2)) {
            int sum = Integer.parseInt(n1) + Integer.parseInt(n2);
            resp = "The sum of " + n1 + ", " + n2 + " = " + sum;

        } else {
            resp = "Please enter numbers only in n1 and n2";
        }
        return resp;
    }

    @GetMapping("/perform-sum/{num1}/{n2}")
    public String performSum(@PathVariable(name = "num1") String n1,
            @PathVariable String n2) {

        String resp;
        String regex = "^\\d+$";
        if (Pattern.matches(regex, n1) && Pattern.matches(regex, n2)) {
            int sum = Integer.parseInt(n1) + Integer.parseInt(n2);
            resp = "The sum using path variable of " + n1 + ", " + n2 + " = " + sum;

        } else {
            resp = "Please enter numbers only in n1 and n2";
        }
        return resp;
    }

    // Print all the number from 1 to n which are prime number
    @GetMapping("/prime/{num}")
    public String displayPrime(@PathVariable String num)
    {
        StringBuilder resp = new StringBuilder();
        String regex = "^\\d+$";
        if (Pattern.matches(regex, num)) {
            int n = Integer.parseInt(num);

            for (int i = 2; i <= n; i++) {
                // assume all value of i initially is prime
                boolean isPrime = true;
                // Check each value of i is prime or not
                for (int j = 2; j <= i - 1; j++) {
                    if (i % j == 0) {
                        isPrime = false;
                        break;
                    }
                }
                if (isPrime == true) {
                    resp.append(i + " ");
                }
            }

        } else {
            resp.append("Please enter numbers only to print series of prime numbers");
        }
        return resp.toString();
    }
    
    // Print all the number from 1 to n which are prime number
    @GetMapping("/prime-list/{num}")
    public ResponseEntity<?> displayPrimeList(@PathVariable String num)
    {
        List<Integer> primeNumbers = new ArrayList<>();
        String regex = "^\\d+$";
        if (Pattern.matches(regex, num)) {
            int n = Integer.parseInt(num);

            for (int i = 2; i <= n; i++) {
                // assume all value of i initially is prime
                boolean isPrime = true;
                // Check each value of i is prime or not
                for (int j = 2; j <= i - 1; j++) {
                    if (i % j == 0) {
                        isPrime = false;
                        break;
                    }
                }
                if (isPrime == true) {
                    primeNumbers.add(i);
                }
            }

            return ResponseEntity.ok(primeNumbers);

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Please enter numbers only to print series of prime numbers");

        }

    }
    
    // create a mapping in which you will pass a number as a request param
    // and then check whether the number is armstrong number or not

}
