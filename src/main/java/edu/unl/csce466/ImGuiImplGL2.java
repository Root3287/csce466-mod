package edu.unl.csce466;

import imgui.ImDrawData;
import imgui.ImFontAtlas;
import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.ImGuiViewport;
import imgui.ImVec2;
import imgui.ImVec4;
import imgui.callback.ImPlatformFuncViewport;
import imgui.flag.ImGuiConfigFlags;
import imgui.flag.ImGuiViewportFlags;
import imgui.type.ImInt;
import imgui.flag.ImGuiBackendFlags;

import java.nio.ByteBuffer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.lwjgl.opengl.GL20.*;

/**
 * ImGuiImplGL2
 *
 * This class is exactly the same as ImGuiImplGL3, but uses OpenGLv2.
 * Minecraft should really get on that or use Vulkan.
 */
public class ImGuiImplGL2 {

	public void init() {

	}

	public void renderDrawData(final ImDrawData drawData) {

	}

	public void dispose(){

	}

	public void updateFontsTexture() {

	}

	private void initPlatformInterface() {
		// ImGui.getPlatformIO().setRendererRenderWindow(new ImPlatformFuncViewport() {
		//     @Override
		//     public void accept(final ImGuiViewport vp) {
		//         if (!vp.hasFlags(ImGuiViewportFlags.NoRendererClear)) {
		//             glClearColor(0, 0, 0, 0);
		//             glClear(GL_COLOR_BUFFER_BIT);
		//         }
		//         renderDrawData(vp.getDrawData());
		//     }
		// });
	}

}