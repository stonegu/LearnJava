package apache.commons.cli;

import java.io.PrintWriter;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class OptionGroupExample {
	
	public static void main(String... args) {
		boolean extract = false;
		boolean process = false;
		boolean offers = false;
		
		Options options = new Options();
		CommandLineParser parser = new DefaultParser();
		HelpFormatter formatter = new HelpFormatter();
        //a workaround to keep the options in the same order they were created (for printing purposes only)
        formatter.setOptionComparator(null);
        CommandLine commandLine = null;
		
        OptionGroup actionGroup = new OptionGroup();
	    
		Option encrypt = new Option("extract", "extract xml data into a file");
		Option decrypt = new Option("process", "process xml file");
		Option offersOpt = new Option("offers", "generate offers xml");
	    
	    actionGroup.setRequired(true);
	    actionGroup.addOption(encrypt);
	    actionGroup.addOption(decrypt);
	    actionGroup.addOption(offersOpt);
	    options.addOptionGroup(actionGroup);
	    
	    //a workaround to figure out the action before defining the rest of the options
	    //we do this so we can adjust the requirement property dynamically
	    try {
	    	commandLine = parser.parse(options, args, true);
	    	extract = actionGroup.getSelected().equals("extract");
	    	process = actionGroup.getSelected().equals("process");
	    	offers = actionGroup.getSelected().equals("offers");
        } catch (ParseException exp) {
        	// we don't need to do anything here. The final parser will handle all missing flags.
        }	    
	    
	    Option distributionNumber = new Option("d", "distNumber", true, "distribution number");
	    distributionNumber.setRequired(process);
	    distributionNumber.setArgs(1);
	    options.addOption(distributionNumber);
	    
	    Option sourceFile = new Option("s", "source", true, "source XML file");
	    sourceFile.setRequired(true);
	    sourceFile.setArgs(1);
	    options.addOption(sourceFile);	
	    
	    Option targetFile = new Option("t", "target", true, "target file");
	    targetFile.setRequired(true);
	    targetFile.setArgs(1);
	    options.addOption(targetFile);
	    
	    Option ouacApplicationNumbers = new Option("a", "applNumbers", true, "OUAC application numbers");
	    ouacApplicationNumbers.setRequired(extract);
	    ouacApplicationNumbers.setArgs(Option.UNLIMITED_VALUES);
	    options.addOption(ouacApplicationNumbers);	    
	    
        try {
        	commandLine = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("OUACXML*.jar", options);
            PrintWriter out = new PrintWriter(System.out, true);
            formatter.printUsage(out, 350, "OUACXML*.jar -process -d distributionNumber -s /path_to_source_file/source.xml -t /path_to_target_file/target.pl" );
            formatter.printUsage(out, 350, "OUACXML*.jar -extract -s /path_to_source_file/source.xml -t /path_to_target_file/target.xml -a applicationNumbers");
            System.exit(1);
        }    
        
        if (process) {
        	System.out.println(".......... process");
        } else if (extract) {
        	System.out.println(".......... extract");
        } else if (offers) {
        	System.out.println(".......... offers");
        	System.out.println("...............option s: " + commandLine.getOptionValue("source"));
        	System.out.println("...............option t: " + commandLine.getOptionValue("target"));
        }
	    
	}

}
