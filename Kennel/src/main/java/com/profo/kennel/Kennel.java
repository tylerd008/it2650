package com.profo.kennel;

public class Kennel {

    public static void main(String[] args) {
        //Complete the Dog class before finishing these tasks.
        
        Owner john = new Owner("John", "123 Front St.", "Cleveland", "Ohio");
        Owner macy = new Owner("Macy", "789 Grove Ave.", "Columbus", "Ohio");
        
        //Create three dog objects.
        //You can give them and name, owner, and address that you would like.
        //For weight, start with one dog that weight 28, one that weights 35, and one that weights 49.
        Dog dog1 = new Dog("Bones", 28.0, john);
        Dog dog2 = new Dog("Scruffy", 35.0, john);
        Dog dog3 = new Dog("Bottle", 49.0, macy);

        //After creating the three dogs, have each dog speak and output the resulting strings here.
        System.out.println(dog1.getName() + ": " +dog1.speak());
        System.out.println(dog2.getName() + ": " +dog2.speak());
        System.out.println(dog3.getName() + ": " +dog3.speak());


        //Then, have the first dog poop an amount of 5,
        //and have the third dog eat an amount of 10.
        
        dog1.poop(5.0);
        dog3.eat(10.0);
        
        //Finally, have each dog speak again.
        System.out.println(dog1.getName() + ": " +dog1.speak());
        System.out.println(dog2.getName() + ": " +dog2.speak());
        System.out.println(dog3.getName() + ": " +dog3.speak());
        
        System.out.println(dog1);
        System.out.println(dog2);
        System.out.println(dog3);
    }
}
