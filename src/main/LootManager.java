package main;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Manages the inventory of RPG Loot.
 */
public class LootManager {
    private ArrayList<Loot> inventory;

    private LootManager() {
        this.inventory = new ArrayList<>();
    }

    public static LootManager load(String START_FILE_PATH) throws FileNotFoundException {

        LootManager lootManager = new LootManager();

        Scanner scanner = new Scanner(new File(START_FILE_PATH));

        scanner.nextLine();

        while (scanner.hasNextLine()) {
            String[] parts = scanner.nextLine().split(",");
            Loot loot = LootFactory.create(parts);
            lootManager.add(loot);
        }

        scanner.close();

        return lootManager;

    }

    public void add(Loot currLoot) {
        if (currLoot != null) {
            inventory.add(currLoot);
        }
    }

    public void save(String START_FILE_PATH) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File(START_FILE_PATH));
        writer.println("TYPE,NAME,RARITY,SPECIAL_1");
        for (Loot currLoot: inventory ){
            writer.println(currLoot.asCsvRow());

        }
        writer.close();

    }

    /**
     * Polymorphically displays all items in the inventory.
     */
    public void displayInventory() {
        System.out.println();
        System.out.println("--- Current Inventory ---");
        for (Loot item : inventory) {
            System.out.println(item.getName() + " [" + item.getRarity() + "] - " +
                    item.getEffectDescription());
        }
        System.out.println("-------------------------");
        System.out.println();
    }
}