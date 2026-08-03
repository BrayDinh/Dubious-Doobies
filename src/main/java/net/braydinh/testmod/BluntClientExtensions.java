package net.braydinh.testmod;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.minecraft.client.model.geom.ModelPart;

public class BluntClientExtensions implements IClientItemExtensions {



    @Override
    public boolean applyForgeHandTransform(
            PoseStack poseStack,
            LocalPlayer player,
            HumanoidArm arm,
            ItemStack itemInHand,
            float partialTick,
            float equipProgress,
            float swingProgress
    ) {
        HumanoidArm usingArm =
                player.getUsedItemHand() == InteractionHand.MAIN_HAND
                        ? player.getMainArm()
                        : player.getMainArm().getOpposite();

        if (!player.isUsingItem()
                || player.getUseItemRemainingTicks() <= 0
                || usingArm != arm) {
            return false;
        }

        float side =
                arm == HumanoidArm.RIGHT ? 1.0F : -1.0F;

        int usedTicks =
                itemInHand.getUseDuration(player)
                        - player.getUseItemRemainingTicks();

        float progress = Mth.clamp(
                (usedTicks + partialTick) / 8.0F,
                0.0F,
                1.0F
        );

        progress =
                progress * progress
                        * (3.0F - 2.0F * progress);

        /*
         * Base first-person position.
         */
        poseStack.translate(
                side * 0.22F,
                -0.38F - equipProgress * 0.6F,
                -0.72F
        );

        /*
         * Move inward, upward and closer to the mouth.
         */
        poseStack.translate(
                side * -0.18F * progress,
                0.18F * progress,
                0.25F * progress
        );

        /*
         * Lay the blunt horizontally.
         */
        poseStack.mulPose(
                Axis.ZP.rotationDegrees(
                        side * -90.0F * progress
                )
        );

        /*
         * Aim the end inward toward the mouth.
         */
        poseStack.mulPose(
                Axis.YP.rotationDegrees(
                        side * 5.0F * progress
                )
        );

        /*
         * Small natural tilt.
         */
        poseStack.mulPose(
                Axis.XP.rotationDegrees(
                        -90.0F * progress
                )
        );

        return true;
    }



}