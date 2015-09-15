package untouchedwagons.minecraft.plasmacraft.config;

public class OreGenConfig {
    private int vein_count;
    private int vein_size;
    private int vein_y_start;
    private int vein_y_range;
    private boolean vein_generate;

    public OreGenConfig(int vein_count, int vein_size, int vein_y_range, int vein_y_start) {
        this(vein_count, vein_size, vein_y_range, vein_y_start, true);
    }

    public OreGenConfig(int vein_count, int vein_size, int vein_y_range, int vein_y_start, boolean vein_generate) {
        this.vein_count = vein_count;
        this.vein_size = vein_size;
        this.vein_y_range = vein_y_range;
        this.vein_y_start = vein_y_start;
        this.vein_generate = vein_generate;
    }

    public int getVeinCount() {
        return vein_count;
    }

    public int getVeinSize() {
        return vein_size;
    }

    public int getVeinYRange() {
        return vein_y_range;
    }

    public int getVeinYStart() {
        return vein_y_start;
    }

    public boolean doVeinGeneration() {
        return vein_generate;
    }
}
