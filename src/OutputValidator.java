import static java.lang.System.out;
import static java.util.stream.Collectors.toMap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class OutputValidator{

    public static void main( String[] args ) throws IOException{
        String inputFilename = args[0];
        String outputFilename = args[1];

        Map<String, Long> inputLines = Files.lines( Paths.get( inputFilename ) )
                .collect(
                        toMap(
                                ( line ) -> line,
                                ( line ) -> 1L,
                                ( existing, added ) -> existing + 1
                        ) );

        Set<String> inputDuplicates = inputLines
                .entrySet()
                .stream()
                .filter( entry -> entry.getValue() > 1 )
                .map( Map.Entry::getKey )
                .collect( Collectors.toSet() );

        Map<String, Long> outputLines = Files.lines( Paths.get( outputFilename ) )
                .collect(
                        toMap(
                                ( line ) -> line,
                                ( line ) -> 1L,
                                ( existing, added ) -> existing + 1
                        ) );

        outputLines.entrySet().stream()
                .filter( entry -> entry.getValue() > 1 )
                .forEach( entry -> out.println( "!!! Duplicate line in output: " + entry.getKey() ) );

        inputDuplicates.stream()
                .filter( duplicate -> !outputLines.containsKey( duplicate ) )
                .forEach( duplicate -> out.println( "!!! Missing duplicate from output: " + duplicate ) );

        long falsePositives = outputLines.keySet().stream().filter( outValue -> !inputDuplicates.contains( outValue ) ).count();

        out.println( "Duplicates in input: " + inputDuplicates.size());
        out.println( "Unique line count in output: " + outputLines.size());

        out.println( "False positives count: " + falsePositives );
        out.printf( "False positives ratio: %f %%", ((float)falsePositives / (float)inputLines.size()) * 100 );
    }
}