import java.io.*;
import java.util.*;

public class Exercise3 {

	private final List<Recording> recordings = new ArrayList<>();


	//exporterar det som finns i listan till filen som ges som
	//parameter enligt formatet för exportfilen (se nästa sektion).
	public void exportRecordings(String fileName) {
		try {
			FileWriter utfil = new FileWriter(fileName + ".txt");
			PrintWriter out = new PrintWriter(utfil);

			for (Recording recording : recordings) {
				out.println("<recording>");
				out.println("	<artist>" + recording.getArtist() + "</artist>");
				out.println("	<title>" + recording.getTitle() + "</title>");
				out.println("	<year>" + recording.getYear() + "</year>");
				out.println("	<genres>");
				ArrayList<String> genres = new ArrayList<String>(recording.getGenre());
				for (String genre : genres) {
					out.println("		<genre>" + genre + "</genre>");
				}
				out.println("	</genres>");
				out.println("</recording>");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//importerar det som finns i filen (som är i formatet för
	//importfilen, se nästa sektion) som ges som parameter till listan. Om filen inte
	//finns ska ett undantag av typen FileNotFoundException genereras.
	public void importRecordings(String fileName) throws FileNotFoundException {
		try {
			FileReader reader = new FileReader(fileName + ".txt");
			BufferedReader in = new BufferedReader(reader);
			String line;
			String title = "";
			String artist = "";
			int year = 0;
			int numberOfRecords = Integer.parseInt(String.valueOf(in.readLine().charAt(0)));
			int numberOfGenre = 0;

			for (int i = 0; i < numberOfRecords; i++) {
				line = in.readLine();
				var ArtistTitleYear= line.split(";");
				artist = ArtistTitleYear[0];
				title = ArtistTitleYear[1];
				year = Integer.parseInt(ArtistTitleYear[2]);
				numberOfGenre = Integer.parseInt(in.readLine());
				Set<String> genre = new HashSet<>(numberOfGenre);

				for (int n = 0; n < numberOfGenre; n++) {
					genre.add(in.readLine());
				}

				recordings.add(new Recording(artist, title, year, genre));

			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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