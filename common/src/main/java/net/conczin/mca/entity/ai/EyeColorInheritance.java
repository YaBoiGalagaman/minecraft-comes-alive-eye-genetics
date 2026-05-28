package net.conczin.mca.entity.ai;

import java.util.Map;
import net.minecraft.util.RandomSource;


public class EyeColorInheritance {
    // Eye color probability table
    private static final Map<String, Map<String, Map<EyeColor, Double>>> CHART = Map.of(
            "brown", Map.of(
                    "brown", Map.of(EyeColor.GREEN, 0.07, EyeColor.BROWN, 0.75, EyeColor.BLUE, 0.19),
                    "green", Map.of(EyeColor.GREEN, 0.38, EyeColor.BROWN, 0.50, EyeColor.BLUE, 0.12),
                    "blue",  Map.of(EyeColor.GREEN, 0.00, EyeColor.BROWN, 0.50, EyeColor.BLUE, 0.50)
            ),
            "green", Map.of(
                    "brown", Map.of(EyeColor.GREEN, 0.38, EyeColor.BROWN, 0.50, EyeColor.BLUE, 0.12),
                    "green", Map.of(EyeColor.GREEN, 0.75, EyeColor.BROWN, 0.00, EyeColor.BLUE, 0.25),
                    "blue",  Map.of(EyeColor.GREEN, 0.50, EyeColor.BROWN, 0.00, EyeColor.BLUE, 0.50)
            ),
            "blue", Map.of(
                    "brown", Map.of(EyeColor.GREEN, 0.00, EyeColor.BROWN, 0.50, EyeColor.BLUE, 0.50),
                    "green", Map.of(EyeColor.GREEN, 0.50, EyeColor.BROWN, 0.00, EyeColor.BLUE, 0.50),
                    "blue",  Map.of(EyeColor.GREEN, 0.01, EyeColor.BROWN, 0.00, EyeColor.BLUE, 0.99)
            )
    );


    // Returns the child's eye color based on the parents' colors.
    public static EyeColor inherit(EyeColor mom, EyeColor dad, RandomSource rand) {
        String momKey = mom.name().toLowerCase();
        String dadKey = dad.name().toLowerCase();

        if (momKey.compareTo(dadKey) > 0) {
            String tmp = momKey;
            momKey = dadKey;
            dadKey = tmp;
        }

        Map<String, Map<EyeColor, Double>> momTable = CHART.getOrDefault(momKey, CHART.get("brown"));
        Map<EyeColor, Double> table = momTable.getOrDefault(dadKey, Map.of(EyeColor.BROWN, 1.0));

        double r = rand.nextDouble();
        double cumulative = 0.0;
        for (Map.Entry<EyeColor, Double> entry : table.entrySet()) {
            cumulative += entry.getValue();
            if (r <= cumulative) return entry.getKey();
        }
        return EyeColor.BROWN;
    }

    public enum EyeColor {
        BLUE,
        GREEN,
        BROWN;
    }
}
