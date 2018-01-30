import java.io.*;
import java.text.ParseException;

/**
 * This interface declares a method for obtaining a products catalog.
 *
 * @author iCarnegie
 * @version 1.1.0
 * @see Catalog
 */
public interface CatalogLoader {

	/*
	 * Loads the product catalog with the data in the specified file.
	 *
	 * @param filename The name of a file that contains catalog information.
	 * 
	 * @return the product catalog.
	 * 
	 * @throws FileNotFoundException if the specified file does not exist.
	 * 
	 * @throws IOException if there is an error reading the information in the
	 * specified file.
	 * 
	 * @throws DataFormatException if the file contains badly-formed data.
	 * 
	 * @throws ParseException if the date contains badly-formed data.
	 */
	Catalog loadCatalog(String fileName) throws FileNotFoundException, IOException, DataFormatException, ParseException;
}