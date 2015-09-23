package untouchedwagons.minecraft.plasmacraft.config;

public class SpoutGenConfig {
    private int spout_y_start;
    private int spout_y_size;
    private int spout_count;

    public SpoutGenConfig(int spout_count, int spout_y_size, int spout_y_start) {
        this.spout_count = spout_count;
        this.spout_y_size = spout_y_size;
        this.spout_y_start = spout_y_start;
    }

    public int getSpoutCount() {
        return spout_count;
    }

    public int getSpoutYSize() {
        return spout_y_size;
    }

    public int getSpoutYStart() {
        return spout_y_start;
    }
}
