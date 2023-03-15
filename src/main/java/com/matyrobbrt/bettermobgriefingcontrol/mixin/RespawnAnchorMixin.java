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
package com.matyrobbrt.bettermobgriefingcontrol.mixin;

import com.matyrobbrt.bettermobgriefingcontrol.BetterMobGriefingControl;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RespawnAnchorBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RespawnAnchorBlock.class)
public class RespawnAnchorMixin {
    @Inject(at = @At(value = "HEAD"), method = "explode", cancellable = true)
    private void bettermobgriefingcontrol$onExplosion(BlockState p_55891_, Level p_55892_, BlockPos p_55893_, CallbackInfo ci) {
        if (!p_55892_.getGameRules().getBoolean(BetterMobGriefingControl.RESPAWN_ANCHOR_EXPLOSION)) {
            ci.cancel();
        }
    }
}
