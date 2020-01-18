package dev.micah.skyclient.mod.mods;

import java.util.Collection;

import org.lwjgl.opengl.GL11;

import dev.micah.skyclient.hud.ScreenPosition;
import dev.micah.skyclient.mod.ModDraggable;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class ModPotionStatus extends ModDraggable {

	private ScreenPosition pos = ScreenPosition.fromRelativePosition(0.5, 0.5);
	protected FontRenderer fontRendererObj;
	
	@Override
	public int getWidth() {
		return font.getStringWidth("Potion Effects");
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT;
	}
	
	@Override
	public void render(ScreenPosition pos) {
		int i = 80;
        int i2 = 16;
        Collection<PotionEffect> collection = this.mc.thePlayer.getActivePotionEffects();

        if (!collection.isEmpty())
        {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.disableLighting();
            int l = 33;

            if (collection.size() > 5)
            {
                l = 132 / (collection.size() - 1);
            }

            for (PotionEffect potioneffect : this.mc.thePlayer.getActivePotionEffects())
            {
                Potion potion = Potion.potionTypes[potioneffect.getPotionID()];
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                String s1 = I18n.format(potion.getName(), new Object[0]);
                if (potioneffect.getAmplifier() == 1)
                {
                    s1 = s1 + " " + I18n.format("enchantment.level.2", new Object[0]);
                }
                else if (potioneffect.getAmplifier() == 2)
                {
                    s1 = s1 + " " + I18n.format("enchantment.level.3", new Object[0]);
                }
                else if (potioneffect.getAmplifier() == 3)
                {
                    s1 = s1 + " " + I18n.format("enchantment.level.4", new Object[0]);
                }
                font.drawString(s1, pos.getAbsoluteX(), pos.getAbsoluteY() + i2, 16777215, true);
                String s = Potion.getDurationString(potioneffect);
                font.drawString(s, pos.getAbsoluteX(), pos.getAbsoluteY() + i2 + 10, 8355711, true);
                i2 += l;
            }
        }
	}
	
	public void renderDummy(ScreenPosition pos) {
		font.drawString("Potion Effects", pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
	}

	@Override
	public void save(ScreenPosition pos) {
		this.pos = pos;
	}

	@Override
	public ScreenPosition load() {
		// TODO Auto-generated method stub
		return pos;
	}

}
