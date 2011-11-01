import org.junit.Test;
import play.test.UnitTest;
import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.SQLException;


public class DatasourceTest extends UnitTest {

    @Test
    public void connect_To_UCSC_DB() {
        Connection conn = null;
        try {
            conn = DatabaseUtil.getConnection("mysql", "genome-mysql.cse.ucsc.edu", "", "", "genome", "");
            assertTrue("Connection made to database", true);
        } catch (SQLException sql) {
        }

        DatabaseUtil.release(null, null, conn);
    }

    @Test
    public void connect_To_CLCG_Human_Annotation_DB() {
        // #my $dbh = DBI->connect('DBI:mysql:Human_annot_feb09;annot.eng.uiowa.edu', 'clcg', 'annotDBs') || die "Could not connect to database: $DBI::errstr";
        Connection conn = null;
        try {
            conn = DatabaseUtil.getConnection("mysql", "annot.eng.uiowa.edu", "", "Human_annot_mar06", "clcg", "annotDBs");
            assertTrue("Connection made to database", true);
        } catch (SQLException sql) {
        }
        DatabaseUtil.release(null, null, conn);
    }


    @Test
    public void connect_To_CLCG_Cluster_DB() {
//        my $dbh = DBI->connect('DBI:mysql:hg19;cluster', 'clcg', 'annotDBs') || die "Could not connect to database: $DBI::errstr";
        Connection conn = null;
        try {
            conn = DatabaseUtil.getConnection("mysql", "cluster.eng.uiowa.edu", "", "hg19", "clcg", "annotDBs");
            assertTrue("Connection made to database", true);
        } catch (SQLException sql) {
        }
        DatabaseUtil.release(null, null, conn);
    }


    public void testBedFile() {

        // chr14	31346845	31346846

        
    }

}
