import org.junit.*;
import play.test.*;
import play.mvc.*;
import play.mvc.Http.*;
import models.*;

public class ApplicationTest extends FunctionalTest {

    @Test
    public void testThatIndexPageWorks() {
        Response response = GET("/");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset(play.Play.defaultWebEncoding, response);
    }


    public void testCode() {
        
        //# read in the bed file
        //while(my $line = <>) {
        //	my @bedCols = split(/\t/, $line);
        //	my $chr = $bedCols[0];
        //	my $start = $bedCols[1];
        //	my $end = $bedCols[2];
        //	my $ampName = $bedCols[3];
        //
        //	my $regionFound = 0;
        //	my $exonFound = 0;
        //	my $a,$b; #where a is the genomic start of the targeted region and b is the genomic end
        //	$sth_exon->execute($chr, $start, $end);
        //	while (my ($refSeq, $chrom, $exonStarts, $exonEnds, $cdsStart, $cdsEnd) = $sth_exon->fetchrow_array()) {
        //		my @starts = split(/,/,$exonStarts);
        //	        my @ends = split(/,/,$exonEnds);
        //		for (my $i = 0; $i<=$#starts; $i++) {
        //			if($i-1>=0 && $exonFound==0 && $ends[$i-1]<=$start && $starts[$i]>=$end) {
        //				#this is in an intron
        //				$regionFound = 1;
        //				if ($start-$ends[$i-1]<($targeted_amp_size/2) && $start-$ends[$i-1]<$starts[$i]-$end) {
        //					#toward the begining of the intron
        //					$a = $end - $targeted_amp_size + $intron_buffer;
        //					$b = $end+$intron_buffer;
        //				} elsif ($starts[$i]-$end<($targeted_amp_size/2)) {
        //					#toward the end of the intron
        //					$a = $start - $intron_buffer;
        //					$b = $end + $targeted_amp_size - $intron_buffer;
        //				} else {
        //					$a = $start - ($targeted_amp_size/2);
        //					$b = $end + ($targeted_amp_size/2);
        //				}
        //			} elsif ($starts[$i]<=$start && $ends[$i]>=$end) {
        //				$regionFound = 1;
        //				$exonFound = 1;
        //				if($ends[$i]-$starts[$i]<=($targeted_amp_size-2*$intron_buffer)) {
        //					# we can design to span the exon
        //					#### print "$chrom\t$starts[$i]\t$ends[$i]\t".$refSeq."_E".($i+1)."_$ampName";
        //					$a = $starts[$i] - $intron_buffer;
        //					$b = $ends[$i] + $intron_buffer;
        //				} elsif ($start-$starts[$i]<($targeted_amp_size/2) && $start-$starts[$i]<$ends[$i]-$end) {
        //					# close to the start of the exon, start there and extend
        //					$a = $starts[$i] - $intron_buffer;
        //					$b = $starts[$i] + $targeted_amp_size - $intron_buffer;
        //				} elsif ($ends[$i]-$end<($targeted_amp_size/2)) {
        //					# close to the end of the exon, end there and prepend
        //					$a = $ends[$i] - $targeted_amp_size + $intron_buffer;
        //					$b = $ends[$i] + $intron_buffer;
        //				} else {
        //					#this exon must be big, just center the read around the variant
        //					$a = $start - ($targeted_amp_size/2);
        //					$b = $end + ($targeted_amp_size/2);
        //				}
        //				# need to call nibfrag here to get sequence + 100 bp flanking
        //				#print "$chrom\t$a\t$b\t".$refSeq."_E".($i+1)."_$ampName";
        //			}
        //		}
        //	}
        //	if ($regionFound==0) {
        //		#not near anything, just center
        //		#print "Nowhere near a gene\t$start\t$end\n";
        //		$a = $start - ($targeted_amp_size/2);
        //		$b = $end + ($targeted_amp_size/2);
        //	}
        //
        //	#now do the nibfrag to get a genomic region.
        //	my $aa = $a-$genomic_padding;
        //	my $bb = $b+$genomic_padding;
        //	my $sequence = `/mnt/r5-genome/BLAT/blatSuite/nibFrag /mnt/r5-genome/BLAT/human/human_feb09_nib/$chr.fa.nib $aa $bb + /dev/stdout -name=`;
        //
        //	my @SNPstarts;
        //	my @SNPends;
        //	my $foundSNP = 0; # if SNP isn't found in genomic region
        //	$sth_SNP->execute($chr, $aa, $bb);
        //	while ( my($startSNP, $endSNP) = $sth_SNP->fetchrow_array()) {
        //		$foundSNP = 1; # set to one if SNP is found
        //		push @SNPstarts, $startSNP;
        //		push @SNPends, $endSNP;
        //		#@SNPstarts = split(/,/,$startSNP);
        //		#@SNPends = split(/,/,$endSNP);
        //	}
        //
        //	$sequence =~ s/[\r\n\>]//g;;
        //	chomp($ampName);
        //	print "SEQUENCE_ID=$ampName\n";
        //	print "SEQUENCE_TEMPLATE=$sequence\n";
        //	print "SEQUENCE_TARGET=$genomic_padding,".($b-$a)."\n";
        //	if ($foundSNP==1) { # if a SNP was found in the genomic region
        //		# exclude all SNPs found in the genomic region from being used in the primer
        //		print "SEQUENCE_EXCLUDED_REGION=";
        //		for (my $i=0; $i<=$#SNPstarts; $i++) {
        //			$SNPstarts[$i] = $SNPstarts[$i]-$aa; # to make into local coordinates
        //			$SNPends[$i] = $SNPends[$i]-$aa;
        //			print "$SNPstarts[$i],".($SNPends[$i]-$SNPstarts[$i])." ";
        //		}
        //		print "\n";
        //	}
        //
        //	print "=\n";
        //
        //	#print "$chr\t$a\t$b\t$ampName";

        //}

    }


    public void testDataLoad() {

        
    }
}