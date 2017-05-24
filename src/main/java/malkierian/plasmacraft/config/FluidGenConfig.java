package malkierian.plasmacraft.config;

public class FluidGenConfig
{
    private int spout_count;
    private int spout_y_start;
    private int spout_y_size;

    public FluidGenConfig(int spout_count, int spout_y_size, int spout_y_start) {
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
