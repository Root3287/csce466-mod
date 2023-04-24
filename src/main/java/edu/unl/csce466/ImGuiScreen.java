package edu.unl.csce466;

import com.mojang.blaze3d.vertex.PoseStack;

import imgui.ImGui;
import imgui.ImguiKt;
import imgui.classes.Context;
import imgui.impl.gl.ImplGL3;
import imgui.impl.glfw.ImplGlfw;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import uno.glfw.GlfwWindow;

public class ImGuiScreen extends Screen{
	private static ImGui imgui = ImGui.INSTANCE;
	private static ImplGL3 imguiImplGL3;
	private static ImplGlfw imguiImplGLFW;

	static {
		ImguiKt.MINECRAFT_BEHAVIORS = true;
        GlfwWindow window = GlfwWindow.from(Minecraft.getInstance().getWindow().getWindow());
        window.makeContextCurrent();
        new Context();
        imguiImplGLFW = new ImplGlfw(window, false, null);
        imguiImplGL3 = new ImplGL3();
	}
	
	public ImGuiScreen() {
		super(Component.literal("ImGui"));
	}
	
	public void render(PoseStack proseStack, int x, int y, float partialTicks) {
		imguiImplGL3.newFrame();
		imguiImplGLFW.newFrame();
		imgui.newFrame();
		
		// Render ImGui Here
		boolean[] showDemo = {true};
		imgui.showDemoWindow(showDemo);
		
		imgui.render();
		imguiImplGL3.renderDrawData(imgui.getDrawData());
	}
}
