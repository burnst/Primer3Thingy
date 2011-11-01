package models;

import java.util.HashMap;
import java.util.Map;


public class OverlappingExons {

    public Map<String,String> properties = new HashMap<String, String>();


    public OverlappingExons() {
        properties.put("PRIMER_PRODUCT_SIZE_RANGE.min", "100");
        properties.put("PRIMER_PRODUCT_SIZE_RANGE.max", "190");
        properties.put("PRIMER_DEFAULT", "22");
        properties.put("PRIMER_OPT_SIZE", "22");
        properties.put("PRIMER_MAX_SIZE", "28");
        properties.put("PRIMER_MIN_SIZE", "20");
        properties.put("PRIMER_MAX_POLY_X", "4");
        properties.put("PRIMER_GC_CLAMP", "1");
        properties.put("PRIMER_MAX_TM", "65.0");
        properties.put("PRIMER_OPT_TM", "60.0");
        properties.put("PRIMER_MIN_TM", "57.0");
        properties.put("PRIMER_MAX_DIFF_TM", "1.0");
        properties.put("PRIMER_SALT_CONC", "52");
        properties.put("PRIMER_MISPRIMING_LIBRARY", "repeats.Mm");
        properties.put("PRIMER_PAIR_MAX_MISPRIMING", "18");
        properties.put("PRIMER_MAX_END_STABILITY", "9");
        properties.put("PRIMER_SELF_ANY", "8");
        properties.put("PRIMER_NUM_RETURN", "1");
        properties.put("PRIMER_EXPLAIN_FLAG", "1");
        properties.put("targeted_amp_size","100");
        properties.put("genomic_padding","100");
        properties.put("intron_buffer","5");
        properties.put("avHet_min","0.1");

    }



}
