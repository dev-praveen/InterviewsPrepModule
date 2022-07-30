package com.interview;

import java.util.Optional;

public class OptionalClient {

    public static void main(String[] args) throws NoSuchFieldException {

        //Optional<String> optional = Optional.of(null); // will throw exception
        //Optional<String> nullOptional = Optional.ofNullable(null); // no exception
        Optional<String> nullOptional = Optional.ofNullable("Praveen");

        nullOptional.ifPresent(System.out::println); // if the optional is present then print.

        //Return true if there is a value present, otherwise false.
        if(nullOptional.isPresent()) {

            System.out.println(nullOptional.get().toUpperCase());
        }

        // returns the value if the optional is present otherwise returns default.
        final String default_value = nullOptional.orElse("Default value");
        System.out.println(default_value);

        //Return the value if present, otherwise invoke other and return the result of that invocation.
        final String supplied_value = nullOptional.orElseGet(() -> "Supplied value");
        System.out.println(supplied_value);

        //Return the contained value, if present, otherwise throw an exception to be created by the provided supplier.
        final String s = nullOptional.orElseThrow(() -> new NoSuchFieldException("Optional is null or empty, provide some value to it"));
        System.out.println(s);

        //final Optional<String> fullName = getFullName(101);
        //final Optional<String> fullName = getFullName(100);
        final Optional<String> fullName = getFullName(10000);
        fullName.ifPresent(System.out::println);


    }

    static Optional<String> getFullName(Integer id){

        if(id.equals(100))
           return Optional.ofNullable("praveen sana");
        else if(id > 1000)
            return Optional.ofNullable(null);
        return Optional.empty();
    }
}
