package com.rogue.squadran.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Keep track of the Equipped, Unequipped, and Consumables in the inventory.
 */
class InventoryComponent extends Component {

    private HashMap<Equipment, Point2D> equipment;
    private HashMap<Consumable, Point2D> consumables;
    ArrayList<Equipment> equippedItems;

    InventoryComponent() {
        equipment = new HashMap<Equipment, Point2D>();
        consumables = new HashMap<Consumable, Point2D>();
        equippedItems = new ArrayList<Equipment>();
    }

    void addEquipment(Equipment equipItem) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                if (!equipment.containsValue(new Point2D(i, j))) {
                    equipment.put(equipItem, new Point2D(i, j));
                    i = 7;
                    j = 9;
                }
            }
        }
    }

    void removeEquipment(Point2D location) {
        for (Equipment e: equipment.keySet()) {
            if (equipment.get(e).getX() == location.getX()
                    && equipment.get(e).getY() == location.getY()) {
                equipment.remove(e);
            }
        }
    }

    void addConsumable(Consumable consumeItem) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                if (!consumables.containsValue(new Point2D(i, j))) {
                    consumables.put(consumeItem, new Point2D(i, j));
                    i = 7;
                    j = 9;
                }
            }
        }
    }

    void removeConsumable(Point2D location) {
        for (Consumable c: consumables.keySet()) {
            if (consumables.get(c).getX() == location.getX()
                    && consumables.get(c).getY() == location.getY()) {
                consumables.remove(c);
            }
        }
    }

    void equip(Equipment equipItem) {
        equippedItems.add(equipItem);
        ((StatComponent)attachedEntity
                .getComponent(StatComponent.class))
                .increaseAllStats(equipItem.stats);
    }

    void unequip(Equipment removeItem) {
        equippedItems.remove(removeItem);
        ((StatComponent)attachedEntity
                .getComponent(StatComponent.class))
                .decreaseAllStats(removeItem.stats);
    }

    Equipment checkEquipment(Point2D location) {
        Equipment returnEquip = null;

        for (Equipment e: equipment.keySet()) {
            if (equipment.get(e).getX() == location.getX()
                    && equipment.get(e).getY() == location.getY()) {
                returnEquip = e;
            }
        }

        return returnEquip;
    }

    Consumable checkForConsumable(Point2D location) {
        Consumable returnConsume = null;

        for (Consumable c: consumables.keySet()) {
            if (consumables.get(c).getX() == location.getX()
                    && consumables.get(c).getY() == location.getY()) {
                returnConsume = c;
            }
        }

        return returnConsume;
    }
}
