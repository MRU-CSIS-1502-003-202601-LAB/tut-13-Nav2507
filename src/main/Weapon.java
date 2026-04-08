package main;

public class Weapon extends Loot {

    private int damage;

    public Weapon(String name, String rarity, int damage) {

        super(name, rarity);
        this.damage = damage;

    }

    @Override
    public String getEffectDescription() {
        return String.format("%s (%s): D%d", getName(), getRarity(), damage);
    }

    @Override
    public String asCsvRow() {
        return String.format("Weapon,%s,%s,%d", getName(), getRarity(), damage);
    }

}
