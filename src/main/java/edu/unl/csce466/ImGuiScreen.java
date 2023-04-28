package edu.unl.csce466;

import com.mojang.blaze3d.vertex.PoseStack;

import imgui.ImGui;
import imgui.flag.ImGuiConfigFlags;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

import java.util.Objects;

public class ImGuiScreen extends Screen{
	private final ImGuiImplGlfw imGuiGlfw = new ImGuiImplGlfw();
    private final ImGuiImplGl3 imGuiGl3 = new ImGuiImplGl3();

	public ImGuiScreen() {
		super(Component.literal("ImGui"));
	}

	public void init(){
		System.out.printf("GLFWWindow %d\n", Minecraft.getInstance().getWindow().getWindow());
		GLFW.glfwMakeContextCurrent(Minecraft.getInstance().getWindow().getWindow());
		ImGui.createContext();
        imGuiGlfw.init(Minecraft.getInstance().getWindow().getWindow(), false);
		imGuiGl3.init("#version 150");
	}
	
	public void render(PoseStack proseStack, int x, int y, float partialTicks) {
		imGuiGlfw.newFrame();
		ImGui.newFrame();
		
		// Render ImGui Here
		ImGui.showDemoWindow();
		
		ImGui.render();
		imGuiGl3.renderDrawData(Objects.requireNonNull(ImGui.getDrawData()));
	}
}
