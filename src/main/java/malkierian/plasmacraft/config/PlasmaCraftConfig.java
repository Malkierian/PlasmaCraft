package malkierian.plasmacraft.config;

import net.minecraftforge.common.config.Configuration;

public class PlasmaCraftConfig {
    public OreGenConfig lead;
    public OreGenConfig neptunium;
    public OreGenConfig obsidium;
    public OreGenConfig plutonium;
    public OreGenConfig radionite;
    public OreGenConfig uranium;

    public SpoutGenConfig neptunium_spout;
    public SpoutGenConfig netherflow_spout;
    public SpoutGenConfig acid_spout;

    public LakeGenConfig netherflow_lake;

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

        int spout_count, spout_y_start, spout_y_range;

        spout_count = configuration.get("spout-count", "neptunium", 20, "").getInt();
        spout_y_start = configuration.get("spout-y-start", "neptunium", 8, "").getInt();
        spout_y_range = configuration.get("spout-y-range", "neptunium", 64, "").getInt();

        this.neptunium_spout = new SpoutGenConfig(spout_count, spout_y_range, spout_y_start);

        spout_count = configuration.get("spout-count", "netherflow", 20, "").getInt();
        spout_y_start = configuration.get("spout-y-start", "netherflow", 8, "").getInt();
        spout_y_range = configuration.get("spout-y-range", "netherflow", 64, "").getInt();

        this.netherflow_spout = new SpoutGenConfig(spout_count, spout_y_range, spout_y_start);

        spout_count = configuration.get("spout-count", "acid", 20, "").getInt();
        spout_y_start = configuration.get("spout-y-start", "acid", 8, "").getInt();
        spout_y_range = configuration.get("spout-y-range", "acid", 30, "").getInt();

        this.acid_spout = new SpoutGenConfig(spout_count, spout_y_range, spout_y_start);

        int lake_chance, lake_y_cutoff, lake_y_start, lake_y_range;

        lake_chance = configuration.get("lake-chance", "netherflow-lake", 32, "").getInt();
        lake_y_cutoff = configuration.get("lake-y-cutoff", "netherflow-lake", 96, "").getInt();
        lake_y_start = configuration.get("lake-y_start", "netherflow-lake", 56, "").getInt();
        lake_y_range = configuration.get("lake-y-range", "netherflow-lake", 16, "").getInt();

        this.netherflow_lake = new LakeGenConfig(lake_chance, lake_y_cutoff, lake_y_range, lake_y_start);

        this.destroy_source_blocks = configuration.get("destroy-source-blocks", "general", true, "When two liquids touch and explode, are the source blocks destroyed too?").getBoolean();
    }
}
