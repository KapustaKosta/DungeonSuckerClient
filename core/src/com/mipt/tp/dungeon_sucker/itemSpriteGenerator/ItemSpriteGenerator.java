package com.mipt.tp.dungeon_sucker.itemSpriteGenerator;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.helper.Constants;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ItemSpriteGenerator {
    static int[] chromakey = {255, 0, 181, 255};
    static int rarityTransparency = 165;

    public static Texture generateTexture(Weapon weapon) {
        File template_file = new File(Constants.WEAPON_TEXTURES_FOLDER + "/templates/" + weapon.type + '/' + weapon.level + ".png");
        File texture_file = new File(Constants.WEAPON_TEXTURES_FOLDER + "/textures/" + weapon.getElementName() + ".png");
        File rarity_file = new File(Constants.WEAPON_TEXTURES_FOLDER + "/rarity/" + weapon.getRarity() + ".png");
        File cell_file = new File(Constants.WEAPON_TEXTURES_FOLDER + "/cell.png");
        try {
            Pixmap result = new Pixmap(16, 16, Pixmap.Format.RGBA8888);


            BufferedImage template = ImageIO.read(template_file);
            BufferedImage texture = ImageIO.read(texture_file);
            BufferedImage rarity = ImageIO.read(rarity_file);
            BufferedImage cell = ImageIO.read(cell_file);



            WritableRaster template_raster = template.getRaster();
            WritableRaster texture_raster = texture.getRaster();
            WritableRaster rarity_raster = rarity.getRaster();
            WritableRaster cell_raster = cell.getRaster();


            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 16; j++) {
                    if (i == 0 || j == 0 || i == 15 || j == 15) {
                        int[] cell_pixel = cell_raster.getPixel(i, j, new int[4]);
                        result.drawPixel(i, j, RGBAtoRGBA8888(cell_pixel));
                    }
                    else {
                        int[] template_pixel = template_raster.getPixel(i, j, new int[4]);
                        if (template_pixel[3] == 0)  { // Пиксель полностью прозрачен, значит это атакующая часть
                            int[] texturePixel = texture_raster.getPixel(i, j, new int[4]);
                            result.drawPixel(i, j, RGBAtoRGBA8888(texturePixel));
                        }
                        else if (Arrays.equals(template_pixel, chromakey)) {
                            int[] rarityPixel = rarity_raster.getPixel(i, j, new int[4]);
                            rarityPixel[3] = rarityTransparency;
                            result.drawPixel(i, j, RGBAtoRGBA8888(rarityPixel));
                        }
                        else {
                            result.drawPixel(i, j, RGBAtoRGBA8888(template_pixel));
                        }
                    }
                }
            }

            return new Texture(result);

        } catch (IOException e) {
            // throw new RuntimeException(e);
            return null;
        }
    }

    public static int RGBAtoRGBA8888(int[] rgba) {

        int red = rgba[0];
        int green = rgba[1];
        int blue = rgba[2];
        int alpha = rgba[3];

        int rgba8888Color = (red << 24) | (green << 16) | (blue << 8) | alpha; // Конвертация в формат RGBA8888

        return rgba8888Color;
    }
}
