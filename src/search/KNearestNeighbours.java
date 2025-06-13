package search;

import java.util.*;

public class KNearestNeighbours {
    public static void main(String[] args) {
        Hero warrior = new Hero("knight", 7, 7, 6, 4, 7);
        Hero berserk = new Hero("berserk", 7, 9, 5, 1, 3);
        Hero rogue = new Hero("rogue", 5, 4, 7, 2, 7);
        Hero mage = new Hero("mage", 3, 3, 5, 10, 4);
        Hero dwarf = new Hero("dwarf", 8, 6, 2, 2, 4);
        Hero elf = new Hero("elf", 3, 4, 6, 7, 9);
        Hero druid = new Hero("druid", 5, 7, 4, 7, 6);
        Hero ogre = new Hero("ogre", 9, 8, 3, 1, 2);
        List<Hero> allHeroes = new ArrayList<>();
        allHeroes.add(warrior);
        allHeroes.add(berserk);
        allHeroes.add(rogue);
        allHeroes.add(mage);
        allHeroes.add(dwarf);
        allHeroes.add(elf);
        allHeroes.add(druid);
        allHeroes.add(ogre);
        Hero sergei = new Hero("sergei", 9, 6, 4, 2, 3);
        List<Hero> threeNearest = findNearest(allHeroes, sergei, 3);
        threeNearest.forEach(System.out::println);

    }

    public static List<Hero> findNearest(List<Hero> allHeroes, Hero user, int number) {
        HashMap<Hero, Double> map = new HashMap<>();
        double distance;
        for (Hero hero : allHeroes) {
            distance = Math.sqrt(Math.pow((user.power - hero.power), 2) +
                    Math.pow((user.vitality - hero.vitality), 2) +
                    Math.pow((user.speed - hero.speed), 2) +
                    Math.pow((user.magic - hero.magic), 2) +
                    Math.pow((user.dexterity - hero.dexterity), 2));
            map.put(hero, distance);
        }

        List<Hero> nearest = map.
                entrySet().
                stream().
                sorted(Map.Entry.comparingByValue()).
                limit(3).
                map(Map.Entry::getKey).
                toList();
        return nearest;
    }
}

class Hero {
    String name;
    int power;
    int vitality;
    int speed;
    int magic;
    int dexterity;

    public Hero(String name, int power, int vitality, int speed, int magic, int dexterity) {
        this.name = name;
        this.power = power;
        this.vitality = vitality;
        this.speed = speed;
        this.magic = magic;
        this.dexterity = dexterity;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "name='" + name + '\'' +
                '}';
    }
}
