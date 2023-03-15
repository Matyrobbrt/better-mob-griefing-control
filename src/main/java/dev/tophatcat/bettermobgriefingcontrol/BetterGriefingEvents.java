/*
 * Better Mob Griefing Control - https://github.com/tophatcats-mods/better-mob-griefing-control
 * Copyright (C) 2016-2023 <KiriCattus>
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation;
 * Specifically version 2.1 of the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301
 * USA
 * https://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
 */
package dev.tophatcat.bettermobgriefingcontrol;

import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraftforge.event.entity.EntityMobGriefingEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BetterMobGriefingControl.MOD_ID)
public class BetterGriefingEvents {

    private static void onEndermanGrief(EntityMobGriefingEvent event, EnderMan entity) {
        if (!event.getEntity().getLevel().getGameRules().getBoolean(
            BetterMobGriefingControl.ENDERMEN_MOVE_BLOCKS)) {
            event.setResult(Event.Result.DENY);
        }
    }

    private static void onCreeperGrief(EntityMobGriefingEvent event, Creeper creeper) {
        if (!event.getEntity().getLevel().getGameRules().getBoolean(
            BetterMobGriefingControl.DO_CREEPER_BLOCK_DAMAGE)) {
            event.setResult(Event.Result.DENY);
        }
    }

    private static void onGhastGrief(EntityMobGriefingEvent event, Ghast entity) {
        if (event.getEntity().getLevel().getGameRules().getBoolean(
            BetterMobGriefingControl.DO_GHAST_BLOCK_DAMAGE)) {
            event.setResult(Event.Result.DENY);
        }
    }

    private static void onWitherGrief(EntityMobGriefingEvent event, WitherBoss entity) {
        if (!event.getEntity().getLevel().getGameRules().getBoolean(
            BetterMobGriefingControl.DO_WITHER_BLOCK_DAMAGE)) {
            event.setResult(Event.Result.DENY);
        }
    }

    @SubscribeEvent
    public static void onGriefing(EntityMobGriefingEvent event) {
        var entity = event.getEntity();
        if (entity instanceof EnderMan enderman) {
            onEndermanGrief(event, enderman);
        } else if (entity instanceof Creeper creeper) {
            onCreeperGrief(event, creeper);
        } else if (entity instanceof Ghast ghast) {
            onGhastGrief(event, ghast);
        } else if (entity instanceof WitherBoss wither) {
            onWitherGrief(event, wither);
        }
    }
}
