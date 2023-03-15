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

import net.minecraft.world.level.GameRules;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkConstants;

@Mod(BetterMobGriefingControl.MOD_ID)
public class BetterMobGriefingControl {

    public static final String MOD_ID = "bettermobgriefingcontrol";

    public BetterMobGriefingControl() {
        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class,
            () -> new IExtensionPoint.DisplayTest(() -> NetworkConstants.IGNORESERVERONLY, (a, b) -> true));
    }

    public static final GameRules.Key<GameRules.BooleanValue>
        DO_GHAST_BLOCK_DAMAGE = registerGameRule("doGhastBlockDamage"),
        DO_CREEPER_BLOCK_DAMAGE = registerGameRule("doCreeperBlockDamage"),
        ENDERMEN_MOVE_BLOCKS = registerGameRule("letEndermenMoveBlocks"),
        DO_WITHER_BLOCK_DAMAGE = registerGameRule("doWitherBlockDamage"),

        RESPAWN_ANCHOR_EXPLOSION = registerGameRule("doRespawnAnchorExplosion", GameRules.Category.PLAYER),
        BED_EXPLOSION = registerGameRule("doBedExplosion", GameRules.Category.PLAYER);

    private static GameRules.Key<GameRules.BooleanValue> registerGameRule(String name) {
        return registerGameRule(name, GameRules.Category.MOBS);
    }

    private static GameRules.Key<GameRules.BooleanValue> registerGameRule(String name, GameRules.Category category) {
        return GameRules.register(name, category, GameRules.BooleanValue.create(true));
    }
}
