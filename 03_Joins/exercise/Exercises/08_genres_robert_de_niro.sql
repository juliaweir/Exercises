-- 8. The genres of movies that Robert De Niro has appeared in that were released in 2010 or later, sorted alphabetically.
-- (6 rows)
SELECT DISTINCT genre.genre_name
FROM person
INNER JOIN movie_actor ON person.person_id = movie_actor.actor_id
INNER JOIN movie ON movie_actor.movie_id = movie.movie_id
INNER JOIN movie_genre ON movie.movie_id = movie_genre.movie_id
INNER JOIN genre ON movie_genre.genre_id = genre.genre_id
WHERE person.person_name = 'Robert De Niro' AND movie.release_date >= '2010-01-01'
ORDER BY genre.genre_name ASC;