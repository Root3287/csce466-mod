package edu.unl.csce466.imgui;

import java.util.ArrayList;
import java.util.Objects;

import org.lwjgl.glfw.GLFW;

import imgui.ImGui;
import imgui.flag.ImGuiConfigFlags;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import net.minecraft.client.Minecraft;

public class ImGuiRenderer {
	private static ImGuiRenderer _INSTANCE = null;
	
	public static ImGuiRenderer getInstance() {
		if(_INSTANCE == null) _INSTANCE = new ImGuiRenderer();
		return _INSTANCE;
	}
	
	private ArrayList<ImGuiCall> _preDrawCalls = new ArrayList<ImGuiCall>();
	private ArrayList<ImGuiCall> _drawCalls = new ArrayList<ImGuiCall>();
	private final ImGuiImplGlfw imGuiGlfw = new ImGuiImplGlfw();
	private final ImGuiImplGl3 imGuiGl = new ImGuiImplGl3();
	
	private ImGuiRenderer() {
		// TODO Auto-generated constructor stub
	}
	
	public void init() {
		init(()->{});
	}
	
	public void init(ImGuiCall config) {
		ImGui.createContext();
		config.execute();
		imGuiGlfw.init(Minecraft.getInstance().getWindow().getWindow(), false);
		imGuiGl.init("#version 410 core");
	}
	
	public void preDraw(ImGuiCall drawCall) {
		_preDrawCalls.add(drawCall);
	}
	
	public void draw(ImGuiCall drawCall) {
		_drawCalls.add(drawCall);
	}
	
	public void render() {
		for(ImGuiCall preDrawCall : _preDrawCalls) {
			preDrawCall.execute();
		}
		_preDrawCalls.clear();
		
		imGuiGlfw.newFrame();
		ImGui.newFrame();
		
		// Render ImGui Here
		for(ImGuiCall drawCall : _drawCalls) {
			drawCall.execute();
		}
		_drawCalls.clear();
		
		ImGui.render();
		imGuiGl.renderDrawData(Objects.requireNonNull(ImGui.getDrawData()));
		
		if (ImGui.getIO().hasConfigFlags(ImGuiConfigFlags.ViewportsEnable)) {
			final long backupWindowPtr = GLFW.glfwGetCurrentContext();
			ImGui.updatePlatformWindows();
			ImGui.renderPlatformWindowsDefault();
			GLFW.glfwMakeContextCurrent(backupWindowPtr);
		}
	}
}
