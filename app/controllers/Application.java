package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    private static String query1 = "SELECT name, chrom, exonStarts, exonEnds, cdsStart, cdsEnd FROM refFlat WHERE chrom=? AND txStart<? AND txEnd>?}";
    private static String query2 = "SELECT chromStart, chromEND FROM snp132 WHERE chrom=? AND chromStart>=? AND chromEnd<=? AND avHet>$avHet_min}";

    public static void index() {
        render();
    }

    public static void primer3Manual() {
        renderTemplate("/util/primer3-2.2.3/primer3_manual.htm");
    }

    public static void refFlat(String chrom, String chromStart, String chromEnd) {
        // Hook up later as a RESTFUL webservice to serve up this data
    }

    public static void snp(String id, String chrom, String chromStart, String chromEnd) {
        // Hook up later as a RESTFUL webservice to serve up this data
    }
    
}