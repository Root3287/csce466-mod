package edu.unl.csce466.screens;

import com.mojang.blaze3d.vertex.PoseStack;

import edu.unl.csce466.imgui.ImGuiRenderer;
import imgui.ImGui;
import imgui.flag.ImGuiConfigFlags;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import edu.unl.csce466.ExampleMod;

public class ImGuiScreen extends Screen{
	
	private static ImGuiScreen _INSTANCE = null;
	
	public static ImGuiScreen getInstance() {
		if(_INSTANCE == null) { _INSTANCE = new ImGuiScreen(); }
		return _INSTANCE;
	}

	private ImGuiScreen() {
		super(Component.literal("ImGui"));
	}
	
	public void init() {
	}
	
	public void render(PoseStack proseStack, int x, int y, float partialTicks) {
		ImGuiRenderer.getInstance().draw(()->{
			//ImGui.showDemoWindow();
			ShowModMenu();
		});
	}

	private void ShowModMenu()
	{
		// init zeus class
		ExampleMod.Zeus zeus = new ExampleMod.Zeus();

		//zeus menu
		if (ImGui.beginMenu("Zeus")) {
			ImGui.text("Channel your inner thunder!");
			if (ImGui.button("Activate"))
			{
				zeus.Init();
			}
			ImGui.endMenu();
		}

	}
}
