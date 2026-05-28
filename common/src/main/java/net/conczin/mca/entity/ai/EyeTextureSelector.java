package net.conczin.mca.entity.ai;

import java.util.Map;


import net.minecraft.util.RandomSource;


public class EyeTextureSelector {

    private static final Map<String, int[]> FEMALE_INDICES = Map.of(
            "brown", new int[]{0, 1},
            "blue",  new int[]{2, 3, 4},
            "green", new int[]{5, 6}
    );

    private static final Map<String, int[]> MALE_INDICES = Map.of(
            "brown", new int[]{0, 1},
            "blue",  new int[]{2, 3, 4, 5},
            "green", new int[]{6}
    );


    // Chooses a random texture index within the valid set for this villager.
    public static int selectIndex(String color, boolean female, RandomSource rand) {
        Map<String, int[]> table = female ? FEMALE_INDICES : MALE_INDICES;
        int[] indices = table.getOrDefault(color, FEMALE_INDICES.get("brown"));
        return indices[rand.nextInt(indices.length)];
    }

    public static EyeColorInheritance.EyeColor colorOfIndex(int index, boolean female) {
        // Female map
        if (female) {
            if (index == 0 || index == 1) return EyeColorInheritance.EyeColor.BROWN;
            if (index == 2 || index == 3 || index == 4) return EyeColorInheritance.EyeColor.BLUE;
            if (index == 5 || index == 6) return EyeColorInheritance.EyeColor.GREEN;
        } else {
            // Male map
            if (index == 0 || index == 1) return EyeColorInheritance.EyeColor.BROWN;
            if (index == 2 || index == 3 || index == 4 || index == 5) return EyeColorInheritance.EyeColor.BLUE;
            if (index == 6) return EyeColorInheritance.EyeColor.GREEN;
        }
        // Safe default if out-of-range
        return EyeColorInheritance.EyeColor.BROWN;
    }
}
