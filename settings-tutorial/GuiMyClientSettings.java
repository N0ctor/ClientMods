package net.minecraft.client.gui;

import java.awt.Button;
import java.awt.Color;
import java.io.IOException;
import java.util.HashMap;

import dev.micah.skyclient.hud.HUDManager;
import dev.micah.skyclient.hud.IRenderer;
import dev.micah.skyclient.mod.Mod;
import dev.micah.skyclient.mod.ModInstances;
import dev.micah.skyclient.settings.SettingsConf;

public class GuiMyClientSettings extends GuiScreen {
	
	private boolean xyzValue = SettingsConf.getValueFromSavedConf(SettingsConf.XYZ);
  private boolean fpsValue = SettingsConf.getValueFromSavedConf(SettingsConf.FPS);
	
	public void initGui() {
		this.buttonList.add(new GuiCheckBox(1, 10, 10, "XYZ Mod", pingValue));
		this.buttonList.add(new GuiCheckBox(2, 10, 35, "FPS Mod", fpsValue));
	}
 	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if (button.id == 1) {
			swapValues(SettingsConf.PING, ModInstances.getModPing());
		}
		if (button.id == 2) {
			swapValues(SettingsConf.FPS, ModInstances.getModfps());
		}
	}
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	private HUDManager hm;
	public void swapValues(SettingsConf conf, IRenderer modInstance) {
		hm = HUDManager.getInstance();
		if (SettingsConf.getValueFromSavedConf(conf)) {
			hm.unregister(modInstance);
			SettingsConf.saveConfSettingsValue(conf, false);
		} else {
			hm.register(modInstance);
			SettingsConf.saveConfSettingsValue(conf, true);
		}
	}
	
}
