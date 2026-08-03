package net.braydinh.testmod.item;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class BluntItem extends Item {

    private static final int USE_DURATION = 60;

    public BluntItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(
            Level level,
            Player player,
            InteractionHand hand
    ) {
        ItemStack stack = player.getItemInHand(hand);

        player.startUsingItem(hand);

        return InteractionResultHolder.consume(stack);
    }

    @Override
    public int getUseDuration(
            ItemStack stack,
            LivingEntity entity
    ) {
        return USE_DURATION;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.NONE;
    }

    @Override
    public void onUseTick(
            Level level,
            LivingEntity entity,
            ItemStack stack,
            int remainingUseDuration
    ) {
        int elapsedTicks = USE_DURATION - remainingUseDuration;

        if (elapsedTicks > 10 && elapsedTicks % 10 == 0) {
            if (level instanceof ServerLevel serverLevel) {
                double lookX = entity.getLookAngle().x;
                double lookY = entity.getLookAngle().y;
                double lookZ = entity.getLookAngle().z;

                double smokeX = entity.getX() + lookX * 0.45D;
                double smokeY =
                        entity.getEyeY() - 0.10D + lookY * 0.25D;
                double smokeZ = entity.getZ() + lookZ * 0.45D;

                serverLevel.sendParticles(
                        ParticleTypes.SMOKE,
                        smokeX,
                        smokeY,
                        smokeZ,
                        3,
                        0.04D,
                        0.04D,
                        0.04D,
                        0.01D
                );
            }

            level.playSound(
                    null,
                    entity.getX(),
                    entity.getY(),
                    entity.getZ(),
                    SoundEvents.CAMPFIRE_CRACKLE,
                    SoundSource.PLAYERS,
                    0.25F,
                    1.4F + level.random.nextFloat() * 0.2F
            );
        }
    }

    @Override
    public ItemStack finishUsingItem(
            ItemStack stack,
            Level level,
            LivingEntity entity
    ) {
        if (!level.isClientSide) {
            entity.addEffect(new MobEffectInstance(
                    MobEffects.REGENERATION,
                    20 * 20,
                    1
            ));

            entity.addEffect(new MobEffectInstance(
                    MobEffects.ABSORPTION,
                    20 * 120,
                    3
            ));

            entity.addEffect(new MobEffectInstance(
                    MobEffects.DAMAGE_RESISTANCE,
                    20 * 300,
                    0
            ));

            entity.addEffect(new MobEffectInstance(
                    MobEffects.FIRE_RESISTANCE,
                    20 * 300,
                    0
            ));

            if (entity instanceof Player player) {
                player.awardStat(Stats.ITEM_USED.get(this));

                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }
            } else {
                stack.shrink(1);
            }
        }

        return stack;
    }
}