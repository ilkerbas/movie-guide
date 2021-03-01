package com.appmovieguide.movieguide;

import android.content.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;

public class Management{
	private HashMap<String, Movie> movies;
	private HashMap<String, Player> players;
	private HashMap<String, Director> directors;

	private static final Management ourInstance = new Management();

	public static Management getInstance() {
		return ourInstance;
	}



	private Management() {
		this.movies = new HashMap<String, Movie>();
		this.players = new HashMap<String, Player>();
		this.directors = new HashMap<String, Director>();

	}
	
	
	public ArrayList<ArrayList<Object>> search(String searchParam) {
	
		
		//All results
		ArrayList<ArrayList<Object>> allResults = new ArrayList<ArrayList<Object>>();
		
		ArrayList<Object> res_films = new ArrayList<Object>();
		ArrayList<Object> res_players = new ArrayList<Object>();
		ArrayList<Object> res_directors = new ArrayList<Object>();
		
		//Films
		Set<String> keys = movies.keySet();
		for(String key: keys) {
			if(key.contains(searchParam)){
				res_films.add(movies.get(key).getTitle());
			}
			
		}
		System.out.println(res_films.size() + " movie(s) have found");
		
		//Players
		keys = players.keySet();
		
		for(String key: keys) {
			if(key.contains(searchParam)){
				res_players.add(players.get(key).getName());
			}
		}
		System.out.println(res_players.size() + " player(s) have found");
		

		
		//Director

		keys = directors.keySet();
		
		for(String key: keys) {
			if(key.contains(searchParam)){
				res_directors.add(directors.get(key).getName());
			}
		}
		System.out.println(res_directors.size() + " director(s) have found");
		
		allResults.add(res_films); allResults.add(res_players); allResults.add(res_directors);
		
		return allResults;
		
		
	}
	@SuppressWarnings("rawtypes")
	public ArrayList<Movie> filterBy(String ranking, String year, String runTime, String star, String director) {
		//0-ranking, 1-year, 2-runTime, 3-star, 4-director
		ArrayList<Movie> filteredMovies = new ArrayList<Movie>();
		Set keys = movies.keySet();
		for (Object key : keys) {
			filteredMovies.add(movies.get(key));
		}
		

		
		for (int i = 0; i < filteredMovies.size(); i++) {
			boolean isNull = false;
			try{
			if(!star.equals("") && !isNull) {

				boolean playerExist = players.containsKey(star.toUpperCase());
				if(playerExist) {
					Player player = players.get(star.toUpperCase());
					if(!filteredMovies.get(i).getCast().getPlayerList().contains(player)) {
						filteredMovies.set(i, null);
						isNull = true;
					}
						
				}
				else{
					filteredMovies.clear();
				}
			}
			if(!director.equals("") && !isNull) {

					director = director.toUpperCase();
					if(!filteredMovies.get(i).getDirector().getName().equals(director)) {
						filteredMovies.set(i, null);
						isNull = true;
						
						//System.out.println("a");
					}

			}
			if(!ranking.equals("") && !isNull) {
				String[] splittedArr = ranking.split("-");
				double min = Double.parseDouble(splittedArr[0]);
				double max = Double.parseDouble(splittedArr[1]);
				
			
				double ranking_movie = Double.parseDouble(filteredMovies.get(i).getRanking());
				if(ranking_movie < min || ranking_movie > max) {
					filteredMovies.set(i, null);
					isNull = true;
				}
				
				
			}
			if(!year.equals("") && !isNull) {
				String[] splittedArr = year.split("-");

				double min = Double.parseDouble(splittedArr[0]);
				double max = Double.parseDouble(splittedArr[1]);
				double year_movie = Double.parseDouble(filteredMovies.get(i).getReleaseDate().getYear());
				if(year_movie < min || year_movie > max) {
					filteredMovies.set(i, null);
					isNull = true;
				}



				


				
				
			}
			if(!runTime.equals("") && !isNull) {
				String[] splittedArr = runTime.split("-");
				double min = Double.parseDouble(splittedArr[0]);
				double max = Double.parseDouble(splittedArr[1]);
				
				double runTime_movie = (double)(filteredMovies.get(i).getRunTime());
				if(runTime_movie < min || runTime_movie > max) {
					filteredMovies.set(i, null);
					isNull = true;
				}
				
				
			}}
			catch (Exception e){
				e.printStackTrace();
				filteredMovies.clear();

			}

		}

		
		
		return filteredMovies;
	}
	
	@SuppressWarnings("rawtypes")
	public Queue sort(String whichType) {
		//MovieList
		Queue movieList = null;

		// fills array
		Set keys = movies.keySet();

		if (whichType == "aToZ") {

			movieList = new Queue(new Comparator<Movie>() {
				@Override
				public int compare(Movie m1, Movie m2) {
					return m1.getTitle().compareTo(m2.getTitle());
				}
			});

		} else if (whichType == "zToA") {
			movieList = new Queue(new Comparator<Movie>() {

				@Override
				public int compare(Movie m1, Movie m2) {
					return m2.getTitle().compareTo(m1.getTitle());
				}
			});

		} else if (whichType == "yearInc") {
			movieList = new Queue(new Comparator<Movie>() {
				@Override
				public int compare(Movie m1, Movie m2) {
					return m1.getReleaseDate().getYear().compareTo(m2.getReleaseDate().getYear());
				}
			});
		} else if (whichType == "yearDec")

		{
			movieList = new Queue(new Comparator<Movie>() {
				@Override
				public int compare(Movie m1, Movie m2) {
					return m2.getReleaseDate().getYear().compareTo(m1.getReleaseDate().getYear());
				}
			});

		} else if (whichType == "rankingInc") {
			movieList = new Queue(new Comparator<Movie>() {
				@Override
				public int compare(Movie m1, Movie m2) {
					return m1.getRanking().compareTo(m2.getRanking());
				}
			});
		} else if (whichType == "rankingDec") {
			movieList = new Queue(new Comparator<Movie>() {
				@Override
				public int compare(Movie m1, Movie m2) {
					return m2.getRanking().compareTo(m1.getRanking());
				}
			});

		} else if (whichType == "runTimeInc") {
			movieList = new Queue(new Comparator<Movie>() {
				@Override
				public int compare(Movie m1, Movie m2) {
					return Integer.valueOf(m1.getRunTime()).compareTo(m2.getRunTime());
				}
			});
		} else if (whichType == "runTimeDec") {
			movieList = new Queue(new Comparator<Movie>() {
				@Override
				public int compare(Movie m1, Movie m2) {
					return Integer.valueOf(m2.getRunTime()).compareTo(m1.getRunTime());
				}
			});
		}
		/*
		 * for (int i = 0; i < movieList.size(); i++) {
		 * System.out.println(movieList.get(i).getReleaseDate().getYear() + " " + i);
		 *
		 * }
		 */
		// System.out.println(movieList.size());

		for (Object key : keys) {
			movieList.enqueue(movies.get(key));
		}

		return movieList;
	}
	
	
	public void readDatabase(Context context){

		InputStreamReader in = null;
		try {
			in = new InputStreamReader(context.getAssets().open("database.csv"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(in);


		PersonFactory personFactory = new PersonFactory();

		try (BufferedReader br = reader) {
			br.readLine();
			String line;

			while ((line = br.readLine()) != null) {

				String[] line2Arr = line.split(",");

				Date releaseDate = new Date(line2Arr[4]);
				//Director addedDirector;
				ICinemaPerson addedDirector;

				if(!directors.containsKey(line2Arr[6].toUpperCase())){
					//addedDirector = new Director(line2Arr[6].toUpperCase());
					addedDirector = personFactory.getPerson("DIRECTOR",line2Arr[6].toUpperCase());
					directors.put(addedDirector.getName().toUpperCase(), (Director)addedDirector);
				}
				else{
					addedDirector = directors.get(line2Arr[6].toUpperCase());
				}


				ArrayList<Player> addedCast = new ArrayList<Player>();
				String[] splitPlayers = line2Arr[7].split(";");

				for (int i = 0; i < splitPlayers.length; i++) {
					if (!players.containsKey(splitPlayers[i].toUpperCase())) {
						//Player player = new Player(splitPlayers[i].toUpperCase());
						ICinemaPerson player = personFactory.getPerson("PLAYER",splitPlayers[i].toUpperCase());

						players.put(player.getName().toUpperCase(), (Player) player);
						addedCast.add((Player) player);

					} else {
						addedCast.add(players.get(splitPlayers[i].toUpperCase()));
					}
				}

				String worldwideGross = "X";

				if (line2Arr.length == 11) {
					worldwideGross = line2Arr[10];
				}

				Movie addedMovie = new Movie(line2Arr[1].toUpperCase(), line2Arr[3], line2Arr[5], line2Arr[8], worldwideGross,
						releaseDate, (Director)addedDirector, new Cast(addedCast), Integer.parseInt(line2Arr[9]));
				addMovie(addedMovie);
				addedDirector.addMovie(addedMovie.getTitle().toUpperCase());
				for (int i = 0; i < splitPlayers.length; i++) {
					String playerName = splitPlayers[i].toUpperCase();
					players.get(playerName).addMovie(addedMovie.getTitle());

				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}


	}
	
	public void addMovie(Movie movie) {
		movies.put(movie.getTitle(), movie);
	}
	public void addDirector(Director director) {
		directors.put(director.getName(), director);
	}
	public void addPlayer(Player player) {
		players.put(player.getName(), player);
	}
	//public void editMovie(Movie movie) {
	//	movies.put(movie.getTitle(), movie);
	//}
	
	public HashMap<String, Movie> getMovies() {
		return movies;
	}
	public void setMovies(HashMap<String, Movie> movies) {
		this.movies = movies;
	}
	public HashMap<String, Player> getPlayers() {
		return players;
	}
	public void setPlayers(HashMap<String, Player> players) {
		this.players = players;
	}
	public HashMap<String, Director> getDirectors() {
		return directors;
	}
	public void setDirectors(HashMap<String, Director> directors) {
		this.directors = directors;
	}

	
	public void start() throws IOException{
		//readDatabase();
	}


}
