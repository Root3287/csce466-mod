package edu.unl.csce466.event;

import com.google.common.eventbus.Subscribe;
import edu.unl.csce466.ExampleMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.entity.PersistentEntitySectionManager;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ModEvents {
    @Mod.EventBusSubscriber(modid = ExampleMod.MODID)



    public static class ForgeEvents{
        public PersistentEntitySectionManager<Entity> entityManager;

        public static boolean start = false;

        @SubscribeEvent
        public static void onPlayerInteract(PlayerInteractEvent event) {
            if (event instanceof PlayerInteractEvent.RightClickEmpty) {
                if (start) {
                    System.out.println("right click");

                    LivingEntity player = event.getEntity();

                    //double rayLength = new double[100];
                    //Vec3 playerRotation = player.getViewVector(0);
                    //Vec3 rayPath = playerRotation.scale(rayLength);

                    Vec3 plrPos = player.getEyePosition(0);
                    //Vec3 to = from.add(rayPath);

                    Vec3 spawnLightingPos = new Vec3(plrPos.x + 10, plrPos.y, plrPos.z);

                    //LightningBolt bolt = new LightningBolt();

                    //entityManager.addNewEntity(Entity LightningBolt);


                    System.out.println("Player looking at: " + spawnLightingPos);

                }
            }
        }
    }
}
