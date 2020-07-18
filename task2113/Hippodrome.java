package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {

    private List<Horse> horses = new ArrayList<>();
    static Hippodrome game;

    public static void main(String[] args) {

        game = new Hippodrome(new ArrayList<>());

        game.getHorses().add(new Horse("Skakun1",3,0));
        game.getHorses().add(new Horse("Skakun2",3,0));
        game.getHorses().add(new Horse("Skakun3",3,0));

        game.run();
        game.printWinner();
    }

    public void run(){
        for(int i = 0; i < 100; i++){
            move();
            print();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void move(){
        for(Horse horse : getHorses()){
            horse.move();
        }
    }

    public void print(){
        for (Horse horse : getHorses()){
            horse.print();
        }
        for (int i = 0; i < 10; i++){
            System.out.println();
        }
    }



    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public Horse getWinner(){

        double maxDistance = 0;
        Horse winner = null;
        for (Horse horse : horses
        ) {
            if (horse.getDistance() > maxDistance) {
                maxDistance = horse.getDistance();
                winner = horse;
            }
        }
        return winner;
    }

    public void printWinner(){
        Horse winner = getWinner();
        System.out.println("Winner is " + winner.getName() + "!");
    }


}
