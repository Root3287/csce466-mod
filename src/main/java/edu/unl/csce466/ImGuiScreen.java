package edu.unl.csce466;

import java.util.Objects;

import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.vertex.PoseStack;

import imgui.ImGui;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ImGuiScreen extends Screen{
	private final ImGuiImplGlfw imGuiGlfw = new ImGuiImplGlfw();
	private final ImGuiImplGl3 imGuiGl = new ImGuiImplGl3();
	
	private static ImGuiScreen _INSTANCE = null;
	
	private boolean _init = false;
	
	public static ImGuiScreen getInstance() {
		if(_INSTANCE == null) { _INSTANCE = new ImGuiScreen(); }
		return _INSTANCE;
	}

	private ImGuiScreen() {
		super(Component.literal("ImGui"));
	}

	public void init(){
		System.out.printf("GLFWWindow %d\n", Minecraft.getInstance().getWindow().getWindow());
		// GLFW.glfwMakeContextCurrent(Minecraft.getInstance().getWindow().getWindow());
		ImGui.createContext();
		imGuiGlfw.init(Minecraft.getInstance().getWindow().getWindow(), false);
		GLFW.glfwMakeContextCurrent(Minecraft.getInstance().getWindow().getWindow());
	}
	
	public void render(PoseStack proseStack, int x, int y, float partialTicks) {
		if(!_init) {
			imGuiGl.init();
			_init = true;
		}
		
		imGuiGlfw.newFrame();
		ImGui.newFrame();
		
		// Render ImGui Here
		ImGui.showDemoWindow();
		
		ImGui.render();
		imGuiGl.renderDrawData(Objects.requireNonNull(ImGui.getDrawData()));
	}
}
