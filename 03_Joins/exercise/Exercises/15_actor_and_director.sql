-- 15. The title of the movie and the name of director for movies where the director was also an actor in the same movie.
-- Order the results by movie title (A-Z)
-- (73 rows)
--join movie, person, moive actor, group by moive title and director name
SELECT movie.title, person.person_name
FROM movie
JOIN person ON movie.director_id = person.person_id
JOIN movie_actor ON movie.movie_id = movie_actor.movie_id
WHERE person.person_id = movie_actor.actor_id
GROUP BY movie.title, person.person_name
ORDER BY movie.title ASC;