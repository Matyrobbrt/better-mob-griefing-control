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
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BedBlock.class)
public class BedMixin {
    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;explode(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;Lnet/minecraft/world/level/ExplosionDamageCalculator;Lnet/minecraft/world/phys/Vec3;FZLnet/minecraft/world/level/Level$ExplosionInteraction;)Lnet/minecraft/world/level/Explosion;"), method = "use")
    private Explosion bettermobgriefingcontrol$onExplosion(Level instance, Entity p_255653_, DamageSource p_256558_, ExplosionDamageCalculator p_255929_, Vec3 p_256001_, float p_255963_, boolean p_256099_, Level.ExplosionInteraction p_256371_) {
        if (instance.getGameRules().getBoolean(BetterMobGriefingControl.BED_EXPLOSION)) {
            return instance.explode(p_255653_, p_256558_, p_255929_, p_256001_, p_255963_, p_256099_, p_256371_);
        }
        return null;
    }
}
