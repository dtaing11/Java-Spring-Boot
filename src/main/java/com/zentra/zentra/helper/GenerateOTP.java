package com.zentra.zentra.helper;

import java.security.SecureRandom;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GenerateOTP {
    private static SecureRandom random = new SecureRandom();
    public static String generateOTP(){
        return random.ints(6,1,10).mapToObj(String::valueOf).collect(Collectors.joining());
    }
}
