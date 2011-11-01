package controllers;

import play.Logger;
import play.mvc.Controller;
import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Bootstrapper extends Controller {


      public static void index() {
          render();
      }

      public static void initializeLocalMysqlDB() {
        Connection conn = null;
        Statement s = null;
        try {
            // Establish a connection to the database
            conn = DatabaseUtil.getConnection("mysql", "localhost", "3306", "p3thingy", "root", "");
            Logger.info("Connected to server.");

            try {
                // Create the database if it does not exist
                s = conn.createStatement();
                int r = s.executeUpdate("CREATE DATABASE IF NOT EXISTS p3thingy");
                Logger.info("Created database if it did not exist.");

                // Use the database
                s.executeUpdate("use p3thingy");
                Logger.info("Switched to project db.");

                // Initialize the refFlat table
                String dropRefFlatTable = "DROP TABLE IF EXISTS `refFlat`;";
                s.executeUpdate(dropRefFlatTable);
                Logger.info("Deleted table refFlat.");


                String createRefFlatTable =
                        "CREATE TABLE `refFlat` (\n" +
                                "  `geneName` varchar(255) NOT NULL,\n" +
                                "  `name` varchar(255) NOT NULL,\n" +
                                "  `chrom` varchar(255) NOT NULL,\n" +
                                "  `strand` char(1) NOT NULL,\n" +
                                "  `txStart` int(10) unsigned NOT NULL,\n" +
                                "  `txEnd` int(10) unsigned NOT NULL,\n" +
                                "  `cdsStart` int(10) unsigned NOT NULL,\n" +
                                "  `cdsEnd` int(10) unsigned NOT NULL,\n" +
                                "  `exonCount` int(10) unsigned NOT NULL,\n" +
                                "  `exonStarts` longblob NOT NULL,\n" +
                                "  `exonEnds` longblob NOT NULL,\n" +
                                "  KEY `chrom` (`chrom`,`txStart`),\n" +
                                "  KEY `name` (`name`),\n" +
                                "  KEY `geneName` (`geneName`(10))\n" +
                                ") ENGINE=MyISAM DEFAULT CHARSET=latin1;";
                s.executeUpdate(createRefFlatTable);
                Logger.info("Created table refFlat.");


                // Initialize the refFlat table
                String dropSNP132Table = "DROP TABLE IF EXISTS `snp132`;";
                s.executeUpdate(dropSNP132Table);
                Logger.info("Deleted table snp132.");

                // Create the memberData table
                String createSNP132Table =
                                "CREATE TABLE `snp132` (\n" +
                                "  `bin` smallint(5) unsigned NOT NULL,\n" +
                                "  `chrom` varchar(31) NOT NULL,\n" +
                                "  `chromStart` int(10) unsigned NOT NULL,\n" +
                                "  `chromEnd` int(10) unsigned NOT NULL,\n" +
                                "  `name` varchar(15) NOT NULL,\n" +
                                "  `score` smallint(5) unsigned NOT NULL,\n" +
                                "  `strand` enum('+','-') NOT NULL,\n" +
                                "  `refNCBI` blob NOT NULL,\n" +
                                "  `refUCSC` blob NOT NULL,\n" +
                                "  `observed` varchar(255) NOT NULL,\n" +
                                "  `molType` enum('unknown','genomic','cDNA') NOT NULL,\n" +
                                "  `class` enum('unknown','single','in-del','het','microsatellite','named','mixed','mnp','insertion','deletion') NOT NULL,\n" +
                                "  `valid` set('unknown','by-cluster','by-frequency','by-submitter','by-2hit-2allele','by-hapmap','by-1000genomes') NOT NULL,\n" +
                                "  `avHet` float NOT NULL,\n" +
                                "  `avHetSE` float NOT NULL,\n" +
                                "  `func` set('unknown','coding-synon','intron','near-gene-3','near-gene-5','nonsense','missense','frameshift','cds-indel','untranslated-3','untranslated-5','splice-3','splice-5') NOT NULL,\n" +
                                "  `locType` enum('range','exact','between','rangeInsertion','rangeSubstitution','rangeDeletion') NOT NULL,\n" +
                                "  `weight` int(10) unsigned NOT NULL,\n" +
                                "  `exceptions` set('RefAlleleMismatch','RefAlleleRevComp','DuplicateObserved','MixedObserved','FlankMismatchGenomeLonger','FlankMismatchGenomeEqual','FlankMismatchGenomeShorter','NamedDeletionZeroSpan','NamedInsertionNonzeroSpan','SingleClassLongerSpan','SingleClassZeroSpan','SingleClassTriAllelic','SingleClassQuadAllelic','ObservedWrongFormat','ObservedTooLong','ObservedContainsIupac','ObservedMismatch','MultipleAlignments','NonIntegerChromCount','AlleleFreqSumNot1') NOT NULL,\n" +
                                "  `submitterCount` smallint(5) unsigned NOT NULL,\n" +
                                "  `submitters` longblob NOT NULL,\n" +
                                "  `alleleFreqCount` smallint(5) unsigned NOT NULL,\n" +
                                "  `alleles` longblob NOT NULL,\n" +
                                "  `alleleNs` longblob NOT NULL,\n" +
                                "  `alleleFreqs` longblob NOT NULL,\n" +
                                "  `bitfields` set('clinically-assoc','maf-5-some-pop','maf-5-all-pops','has-omim-omia','microattr-tpa','submitted-by-lsdb','genotype-conflict','rs-cluster-nonoverlapping-alleles','observed-mismatch') NOT NULL,\n" +
                                "  KEY `name` (`name`),\n" +
                                "  KEY `chrom` (`chrom`,`bin`)\n" +
                                ") ENGINE=MyISAM DEFAULT CHARSET=latin1;\n";
                s.executeUpdate(createSNP132Table);
                Logger.info("Created table snp132.");

                // Load data into the memberData table
//                s.executeUpdate("insert into memberData (nameFirst, nameLast, color, phone) values ('Tom', 'Burns', 'green', '319-512-9759')");
//                s.executeUpdate("insert into memberData (nameFirst, nameLast, color, phone) values ('Ed', 'Hill', 'blue', '319-555-1234')");
//                Logger.info("Inserted data into table.");

            } catch (SQLException sql1) {
                Logger.warn("SQL Exception error", sql1);
                sql1.printStackTrace();
                if (s != null) s.close();
            }

        }
        catch (SQLException sqe) {
            Logger.warn("SQL statement is not executed!", sqe);
        }
        catch (Exception e) {
            Logger.warn("Exception!", e);
        }

        DatabaseUtil.release(null, s, conn);

        render();
    }


}
