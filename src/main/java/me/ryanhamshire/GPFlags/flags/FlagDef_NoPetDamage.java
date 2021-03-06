package me.ryanhamshire.GPFlags.flags;

import me.ryanhamshire.GPFlags.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Tameable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.Arrays;
import java.util.List;

public class FlagDef_NoPetDamage extends FlagDefinition {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        if (!(entity instanceof Tameable)) return;

        Tameable tameable = (Tameable) entity;
        if (!tameable.isTamed() || tameable.getOwner() == null) return;

        Flag flag = this.GetFlagInstanceAtLocation(entity.getLocation(), null);
        if (flag != null) {
            event.setCancelled(true);
        }
    }

    public FlagDef_NoPetDamage(FlagManager manager, GPFlags plugin) {
        super(manager, plugin);
    }

    @Override
    public String getName() {
        return "NoPetDamage";
    }

    @Override
	public MessageSpecifier getSetMessage(String parameters) {
        return new MessageSpecifier(Messages.EnableNoPetDamage);
    }

    @Override
    public MessageSpecifier getUnSetMessage() {
        return new MessageSpecifier(Messages.DisableNoPetDamage);
    }

    @Override
    public List<FlagType> getFlagType() {
        return Arrays.asList(FlagType.CLAIM, FlagType.WORLD, FlagType.SERVER);
    }

}
