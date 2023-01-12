-- 6. The genres of "The Wizard of Oz" sorted in alphabetical order (A-Z).
-- (3 rows)
--join movie amd movie genre, filter by movie title 
SELECT genre.genre_name
FROM genre
JOIN movie_genre ON genre.genre_id = movie_genre.genre_id
JOIN movie ON movie_genre.movie_id = movie.movie_id
WHERE movie.title = 'The Wizard of Oz'
ORDER BY genre.genre_name ASC;