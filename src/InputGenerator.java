import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

public class InputGenerator{
    static Random random = new Random();

    public static void main( String[] args ) throws IOException{

        int linesCount = Integer.parseInt( args[0] );
        int duplicatesRatio = Integer.parseInt( args[1] );
        String outputFile = args[2];

        List<String> randomStrings = uniqueRandomStrings( linesCount );

        PrintWriter out = new PrintWriter( new FileWriter( outputFile, false ) );
        out.println( linesCount );
        randomStrings.forEach( randomLine -> {
            if( random.nextInt( 100 ) > duplicatesRatio ){
                out.println( randomLine );
            }
            else{
                out.println( randomStrings.get( random.nextInt( linesCount ) ) );
            }
        } );

        out.flush();
    }

    private static List<String> uniqueRandomStrings( int linesCount ){
        TreeSet<String> randomStrings = new TreeSet<>();
        while( randomStrings.size() < linesCount ){
            randomStrings.add( randomString() );
        }
        return new ArrayList<>( randomStrings );
    }

    public static String randomString(){
        int leftLimit = 48;
        int rightLimit = 122;
        int length = random.nextInt( 255 ) + 6;
        return random.ints( leftLimit, rightLimit + 1 )
                .filter( i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97) )
                .limit( length )
                .collect( StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append )
                .toString();
    }
}
