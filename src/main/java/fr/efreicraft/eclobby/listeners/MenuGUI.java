package fr.efreicraft.eclobby.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class MenuGUI implements Listener {
    private final Inventory inv;
    private Player player;

    public MenuGUI(Object... args) {
        if (args.length == 1) {
            player = (Player) args[0];
        }
        // Create a new inventory, with no owner (as this isn't a real inventory), a size of nine, called example
        inv = Bukkit.createInventory(null, 45, "Menu");

        // Put the items into the inventory
        initializeItems();
    }

    // You can call this whenever you want to put the items in
    public void initializeItems() {
        /// LEFT NAV BAR SECTION
        // Player menu
        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta playerHeadMeta = (SkullMeta) playerHead.getItemMeta();
        playerHeadMeta.setOwningPlayer(player);
        playerHeadMeta.setDisplayName("§aProfil");
        playerHeadMeta.setLore(List.of("§7Cliquez pour voir votre profil"));
        playerHead.setItemMeta(playerHeadMeta);
        inv.setItem(9, playerHead);
        // Lobby button
        inv.setItem(27, createGuiItem(Material.GOLD_BLOCK, "§6Lobby", "§7Cliquez pour revenir au lobby"));
        // Warp selector button

        /// VERTICAL SEPARATOR
        for (int i = 1; i < 45; i+=9) {
            inv.setItem(i, createGuiItem(Material.BLACK_STAINED_GLASS_PANE, "§r"));
        }

        /// NAV MENU (RIGHT SIDE)
        // Vanilla Server
        inv.setItem(12, createGuiItem(Material.GRASS_BLOCK, "§aSurvie Vanilla", "§7Cliquez pour rejoindre le serveur Vanilla"));
        // Modded Server
        inv.setItem(13, createGuiItem(Material.REDSTONE_BLOCK, "§cSurvie Moddée", "§7Cliquez pour rejoindre le serveur Moddé"));
    }

    // Nice little method to create a gui item with a custom name, and description
    protected ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        meta.setDisplayName(name);

        // Set the lore of the item
        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
    }

    // You can open the inventory with this
    public void openInventory(final HumanEntity ent) {
        ent.openInventory(inv);
    }

    // Check for clicks on items
    @EventHandler
    public void onInventoryClick(final @NotNull InventoryClickEvent e) {
        if (!e.getInventory().equals(inv)) return;

        e.setCancelled(true);

        final ItemStack clickedItem = e.getCurrentItem();

        // verify current item is not null
        if (clickedItem == null || clickedItem.getType().isAir()) return;

        final Player p = (Player) e.getWhoClicked();

        // Using slots click is the best option for your inventory click's
        // p.sendMessage("You clicked at slot " + e.getRawSlot());

        switch (e.getRawSlot()) {
            case 9 -> p.performCommand("profile");
            case 27 -> {
                p.performCommand("lobby");
                p.closeInventory();
            }
            case 12 -> {
                p.performCommand("join vanilla");
                p.closeInventory();
            }
            case 13 -> {
                p.performCommand("join modded");
                p.closeInventory();
            }
        }
    }
}