package net.braydinh.testmod;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;
import net.neoforged.neoforge.client.IArmPoseTransformer;

public final class BluntArmPose {

    public static final EnumProxy<HumanoidModel.ArmPose> SMOKING_POSE =
            new EnumProxy<>(
                    HumanoidModel.ArmPose.class,
                    false,
                    (IArmPoseTransformer) BluntArmPose::applySmokingPose
            );

    private BluntArmPose() {
    }

    private static void applySmokingPose(
            HumanoidModel<?> model,
            LivingEntity entity,
            HumanoidArm arm
    ) {
        ModelPart smokingArm =
                arm == HumanoidArm.RIGHT
                        ? model.rightArm
                        : model.leftArm;

        float side =
                arm == HumanoidArm.RIGHT
                        ? 1.0F
                        : -1.0F;

        /*
         * Raise the arm toward the mouth.
         * More negative raises the arm higher.
         */
        smokingArm.xRot = -1.60F;

        /*
         * Turn the arm inward across the body.
         */
        smokingArm.yRot = -side * 0.45F;

        /*
         * Add a slight sideways tilt.
         */
        smokingArm.zRot = side * 0.45F;
    }
}