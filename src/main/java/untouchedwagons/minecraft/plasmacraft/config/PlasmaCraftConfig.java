package untouchedwagons.minecraft.plasmacraft.config;

import net.minecraftforge.common.config.Configuration;

public class PlasmaCraftConfig {
    public OreGenConfig lead;
    public OreGenConfig neptunium;
    public OreGenConfig obsidium;
    public OreGenConfig plutonium;
    public OreGenConfig radionite;
    public OreGenConfig uranium;

    public boolean destroy_source_blocks;

    public void loadConfig(Configuration configuration)
    {
        int vein_count, vein_size, vein_y_start, vein_y_range;
        boolean do_ore_gen;

        vein_count = configuration.get("vein-count", "lead", 6, "").getInt();
        vein_size = configuration.get("vein-size", "lead", 6, "").getInt();
        vein_y_start = configuration.get("vein-y-start", "lead", 4, "").getInt();
        vein_y_range = configuration.get("vein-y-range", "lead", 80, "").getInt();
        do_ore_gen = configuration.get("vein_generation", "lead", true, "").getBoolean();

        this.lead = new OreGenConfig(vein_count, vein_size, vein_y_range, vein_y_start, do_ore_gen);

        vein_count = configuration.get("vein-count", "neptunium", 6, "").getInt();
        vein_size = configuration.get("vein-size", "neptunium", 10, "").getInt();
        vein_y_start = configuration.get("vein-y-start", "neptunium", 32, "").getInt();
        vein_y_range = configuration.get("vein-y-range", "neptunium", 64, "").getInt();

        this.neptunium = new OreGenConfig(vein_count, vein_size, vein_y_range, vein_y_start);

        vein_count = configuration.get("vein-count", "obsidium", 4, "").getInt();
        vein_size = configuration.get("vein-size", "obsidium", 10, "").getInt();
        vein_y_start = configuration.get("vein-y-start", "obsidium", 32, "").getInt();
        vein_y_range = configuration.get("vein-y-range", "obsidium", 64, "").getInt();

        this.obsidium = new OreGenConfig(vein_count, vein_size, vein_y_range, vein_y_start);

        vein_count = configuration.get("vein-count", "plutonium", 4, "").getInt();
        vein_size = configuration.get("vein-size", "plutonium", 6, "").getInt();
        vein_y_start = configuration.get("vein-y-start", "plutonium", 32, "").getInt();
        vein_y_range = configuration.get("vein-y-range", "plutonium", 64, "").getInt();
        do_ore_gen = configuration.get("vein_generation", "plutonium", true, "").getBoolean();

        this.plutonium = new OreGenConfig(vein_count, vein_size, vein_y_range, vein_y_start, do_ore_gen);

        vein_count = configuration.get("vein-count", "radionite", 4, "").getInt();
        vein_size = configuration.get("vein-size", "radionite", 6, "").getInt();
        vein_y_start = configuration.get("vein-y-start", "radionite", 4, "").getInt();
        vein_y_range = configuration.get("vein-y-range", "radionite", 24, "").getInt();

        this.radionite = new OreGenConfig(vein_count, vein_size, vein_y_range, vein_y_start);

        vein_count = configuration.get("vein-count", "uranium", 6, "").getInt();
        vein_size = configuration.get("vein-size", "uranium", 6, "").getInt();
        vein_y_start = configuration.get("vein-y-start", "uranium", 4, "").getInt();
        vein_y_range = configuration.get("vein-y-range", "uranium", 16, "").getInt();
        do_ore_gen = configuration.get("vein_generation", "uranium", true, "").getBoolean();

        this.uranium = new OreGenConfig(vein_count, vein_size, vein_y_range, vein_y_start, do_ore_gen);

        this.destroy_source_blocks = configuration.get("destroy-source-blocks", "general", true, "When two liquids touch and explode, are the source blocks destroyed too?").getBoolean();
    }
}
