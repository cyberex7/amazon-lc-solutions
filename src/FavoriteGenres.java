/*
* Given a map Map<String, List<String>> userSongs with user names as keys and a list of all the songs that the user has listened to as values.

Also given a map Map<String, List<String>> songGenres, with song genre as keys and a list of all the songs within that genre as values. The song can only belong to only one genre.

The task is to return a map Map<String, List<String>>, where the key is a user name and the value is a list of the user's favorite genre(s). Favorite genre is the most listened to genre. A user can have more than one favorite genre if he/she has listened to the same number of songs per each of the genres.

Example 1:

Input:
userSongs = {
   "David": ["song1", "song2", "song3", "song4", "song8"],
   "Emma":  ["song5", "song6", "song7"]
},
songGenres = {
   "Rock":    ["song1", "song3"],
   "Dubstep": ["song7"],
   "Techno":  ["song2", "song4"],
   "Pop":     ["song5", "song6"],
   "Jazz":    ["song8", "song9"]
}

Output: {
   "David": ["Rock", "Techno"],
   "Emma":  ["Pop"]
}

Explanation:
David has 2 Rock, 2 Techno and 1 Jazz song. So he has 2 favorite genres.
Emma has 2 Pop and 1 Dubstep song. Pop is Emma's favorite genre.
Example 2:

Input:
userSongs = {
   "David": ["song1", "song2"],
   "Emma":  ["song3", "song4"]
},
songGenres = {}

Output: {
   "David": [],
   "Emma":  []
}
* */


import java.util.*;

public class FavoriteGenres {

    public static Map<String, List<String>> getUserFavoriteGenres(Map<String, List<String>> userSongs, Map<String, List<String>> songGenres) {
        Map<String, String> songToGenre = new HashMap<>();
        Map<String, List<String>> userToGenre = new HashMap<>();

        // Mapping each song to its genre
        for (Map.Entry<String, List<String>> genreEntry : songGenres.entrySet()) {
            for (String song : genreEntry.getValue()) {
                songToGenre.put(song, genreEntry.getKey());
            }
        }

        // Determining favorite genres for each user
        for (Map.Entry<String, List<String>> userEntry : userSongs.entrySet()) {
            Map<String, Integer> genreFrequency = new HashMap<>();
            int maxFrequency = 0;
            List<String> favoriteGenres = new ArrayList<>();

            for (String song : userEntry.getValue()) {
                String genre = songToGenre.get(song);
                if (genre != null) { // Only count if the song has a mapped genre
                    int count = genreFrequency.containsKey(genre) ? genreFrequency.get(genre) + 1 : 1;
                    genreFrequency.put(genre, count);

                    // Check if this genre is now a favorite
                    if (count > maxFrequency) {
                        maxFrequency = count;
                        favoriteGenres.clear();
                        favoriteGenres.add(genre);
                    } else if (count == maxFrequency) {
                        favoriteGenres.add(genre);
                    }
                }
            }

            userToGenre.put(userEntry.getKey(), favoriteGenres);
        }

        return userToGenre;
    }

    public static void main(String[] args) {
        Map<String, List<String>> userSongs = new HashMap<>();
        userSongs.put("David", new ArrayList<String>(List.of("song1", "song2", "song3", "song4", "song8")));
        userSongs.put("Emma", new ArrayList<String>(List.of("song5", "song6", "song7")));

        Map<String, List<String>> songGenres = new HashMap<>();
        songGenres.put("Rock", new ArrayList<String>(List.of("song1", "song3")));
        songGenres.put("Dubstep", new ArrayList<String>(List.of("song7")));
        songGenres.put("Techno", new ArrayList<String>(List.of("song2", "song4")));
        songGenres.put("Pop", new ArrayList<String>(List.of("song5", "song6")));
        songGenres.put("Jazz", new ArrayList<String>(List.of("song8", "song9")));

        Map<String, List<String>> userFavoriteGenres = getUserFavoriteGenres(userSongs, songGenres);

        for (Map.Entry<String, List<String>> entry : userFavoriteGenres.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }


        Map<String, List<String>> userSongs2 = new HashMap<>();
        userSongs2.put("David", new ArrayList<String>(Arrays.asList("song1", "song2")));
        userSongs2.put("Emma", new ArrayList<String>(Arrays.asList("song3", "song4")));

        Map<String, List<String>> songGenres2 = new HashMap<>();

        Map<String, List<String>> userFavoriteGenres2 = getUserFavoriteGenres(userSongs2, songGenres2);

        for (Map.Entry<String, List<String>> entry : userFavoriteGenres2.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
