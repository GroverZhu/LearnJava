import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.StringTokenizer;
import java.io.*;
import java.math.BigDecimal;
import java.sql.Date;
/**
 * @author Grover Zhu
 *
 */
public class FileCatalogLoader implements CatalogLoader {

	public Catalog loadCatalog(String fileName)
			throws FileNotFoundException, IOException, DataFormatException, ParseException {
		// TODO Auto-generated method stub
		File file = new File(fileName);
		List<Product> catalog = new ArrayList<Product>();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String line = null;
		line = bufferedReader.readLine();
		while (line != null){
			StringTokenizer st = new StringTokenizer(line,"_");
			String s = st.nextToken();
			if (s.equals("PureMilk")){
				catalog.add(readPureMilk(line));
			}else if (s.equals("Jelly")){
				catalog.add(readJelly(line));
			}else if (s.equals("Yogurt")){
				catalog.add(readYogurt(line));
			}else if (s.equals("MilkDrink")){
				catalog.add(readMilkDrink(line));
			}
			line = bufferedReader.readLine();
		}
		bufferedReader.close();
		Catalog catalogs = new Catalog(catalog);
		return catalogs;
	}

	private PureMilk readPureMilk (String line) throws DataFormatException, ParseException{
		StringTokenizer stringTokenizer = new StringTokenizer(line, "_");
		if(stringTokenizer.countTokens()!=9){
			throw new DataFormatException(line);
		}else{
			stringTokenizer.nextToken();
			PureMilk pureMilk = new PureMilk(stringTokenizer.nextToken(),stringTokenizer.nextToken(),new BigDecimal(stringTokenizer.nextToken()),
					new SimpleDateFormat("yyyy-MM-dd").parse(stringTokenizer.nextToken()),stringTokenizer.nextToken(),stringTokenizer.nextToken(),stringTokenizer.nextToken(),stringTokenizer.nextToken());
			return pureMilk;
			}
		
	}

	private Jelly readJelly(String line) throws DataFormatException, ParseException {
		StringTokenizer stringTokenizer = new StringTokenizer(line, "_");
		if(stringTokenizer.countTokens()!=8){
			throw new DataFormatException(line);
		}else{
			stringTokenizer.nextToken();
			Jelly jelly = new Jelly(stringTokenizer.nextToken(),stringTokenizer.nextToken(),new BigDecimal(stringTokenizer.nextToken()),
					new SimpleDateFormat("yyyy-MM-dd").parse(stringTokenizer.nextToken()),stringTokenizer.nextToken(),stringTokenizer.nextToken(),stringTokenizer.nextToken());
			return jelly;
			}
	}
	
	private Yogurt readYogurt(String line) throws DataFormatException, ParseException {
		StringTokenizer stringTokenizer = new StringTokenizer(line, "_");
		if(stringTokenizer.countTokens()!=7){
			throw new DataFormatException(line);
		}else{
			stringTokenizer.nextToken();
			Yogurt yogurt = new Yogurt(stringTokenizer.nextToken(),stringTokenizer.nextToken(),new BigDecimal(stringTokenizer.nextToken()),
					new SimpleDateFormat("yyyy-MM-dd").parse(stringTokenizer.nextToken()),stringTokenizer.nextToken(),stringTokenizer.nextToken());
			return yogurt;
			}
	}
	
	private MilkDrink readMilkDrink(String line) throws DataFormatException, ParseException {
		StringTokenizer stringTokenizer = new StringTokenizer(line, "_");
		if(stringTokenizer.countTokens()!=8){
			throw new DataFormatException(line);
		}else{
			stringTokenizer.nextToken();
			MilkDrink milkDrink = new MilkDrink(stringTokenizer.nextToken(),stringTokenizer.nextToken(),new BigDecimal(stringTokenizer.nextToken()),
					new SimpleDateFormat("yyyy-MM-dd").parse(stringTokenizer.nextToken()),stringTokenizer.nextToken(),stringTokenizer.nextToken(),stringTokenizer.nextToken());
			return milkDrink;
			}
	}


}
