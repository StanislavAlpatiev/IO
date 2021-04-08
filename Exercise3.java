import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Exercise3 {

	private final List<Recording> recordings = new ArrayList<>();


	//exporterar det som finns i listan till filen som ges som
	//parameter enligt formatet för exportfilen (se nästa sektion).
	public void exportRecordings(String fileName) {

	}

	//importerar det som finns i filen (som är i formatet för
	//importfilen, se nästa sektion) som ges som parameter till listan. Om filen inte
	//finns ska ett undantag av typen FileNotFoundException genereras.
	public void importRecordings(String fileName) throws FileNotFoundException {

	}

	//importerar försäljningsdata från filen som ges som parameter (i
	//formatet specificerat i nästa sektion). Innehållet i filen används för att skapa
	//en HashMap<Integer, Double> där nyckeln är år*100+månad (t.ex 202101
	//för januari 2021) och värdet är summan av försäljningar för den aktuella
	//månaden.
	public Map<Integer, Double> importSales(String fileName) throws FileNotFoundException {
		return null;
	}

	public List<Recording> getRecordings() {
		return Collections.unmodifiableList(recordings);
	}

	public void setRecordings(List<Recording> recordings) {
		this.recordings.clear();
		this.recordings.addAll(recordings);
	}
}