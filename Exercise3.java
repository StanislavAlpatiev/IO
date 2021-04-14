// PROG2 VT2021, Övningsuppgift 3
// Grupp 033
// Viggo Asklöf vias2878
// Stanislav Alpatiev stanl5991

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.util.Collections;

public class Exercise3 {

	private final List<Recording> recordings = new ArrayList<>();


	//exporterar det som finns i listan till filen som ges som
	//parameter enligt formatet för exportfilen (se nästa sektion).
	public void exportRecordings(String fileName) {
		try {
			FileWriter utfil = new FileWriter(fileName);
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

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	//importerar det som finns i filen (som är i formatet för
	//importfilen, se nästa sektion) som ges som parameter till listan. Om filen inte
	//finns ska ett undantag av typen FileNotFoundException genereras.
	public void importRecordings(String fileName) throws FileNotFoundException {
		try {
			FileReader reader = new FileReader(fileName);
			BufferedReader in = new BufferedReader(reader);
			String line;
			String title = "";
			String artist = "";
			int year = 0;
			int numberOfRecords = Integer.parseInt(in.readLine());
			int numberOfGenre = 0;

			for (int i = 0; i < numberOfRecords; i++) {
				line = in.readLine();
				var artistTitleYear= line.split(";");
				artist = artistTitleYear[0];
				title = artistTitleYear[1];
				year = Integer.parseInt(artistTitleYear[2]);
				numberOfGenre = Integer.parseInt(in.readLine());
				Set<String> genre = new HashSet<>(numberOfGenre);

				for (int n = 0; n < numberOfGenre; n++) {
					genre.add(in.readLine());
				}

				recordings.add(new Recording(title, artist, year, genre));
			}
			in.close();
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException();
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
		Map<Integer, Double> importedSales = new HashMap<Integer, Double>();
		final int multiplier = 100;
		try {
			FileInputStream is = new FileInputStream(fileName);
			DataInputStream dis = new DataInputStream(is);

			int antalPoster = dis.readInt();
			for (int i = 0; i < antalPoster; i++) {
				int year = dis.readInt();
				int month = dis.readInt();
				int day = dis.readInt();
				double value = dis.readDouble();
				int key = year * multiplier + month;

				if (!importedSales.containsKey(key)) {
					importedSales.put(key, value);
				} else {
					value += importedSales.get(key);
					importedSales.put(key, value);
				}
				//{202103=40.0, 202102=10.0, 202101=20.0, 202105=70.0, 202104=30.0, 0202103=50.0}
			}
			dis.close();
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return importedSales;
	}

	public List<Recording> getRecordings() {
		return Collections.unmodifiableList(recordings);
	}

	public void setRecordings(List<Recording> recordings) {
		this.recordings.clear();
		this.recordings.addAll(recordings);
	}
}