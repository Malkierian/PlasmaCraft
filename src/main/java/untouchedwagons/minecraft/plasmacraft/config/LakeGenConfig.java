package untouchedwagons.minecraft.plasmacraft.config;

public class LakeGenConfig {
    private int lake_chance;
    private int lake_cutoff;
    private int lake_y_start;
    private int lake_y_size;

    public LakeGenConfig(int lake_chance, int lake_cutoff, int lake_y_size, int lake_y_start) {
        this.lake_chance = lake_chance;
        this.lake_cutoff = lake_cutoff;
        this.lake_y_size = lake_y_size;
        this.lake_y_start = lake_y_start;
    }

    public int getLakeChance() {
        return lake_chance;
    }

    public int getLakeCutoff() {
        return lake_cutoff;
    }

    public int getLakeYSize() {
        return lake_y_size;
    }

    public int getLakeYStart() {
        return lake_y_start;
    }
}
