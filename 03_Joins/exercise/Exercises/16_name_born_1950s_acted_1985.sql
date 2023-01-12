-- 16. The names and birthdays of actors born in the 1950s who acted in movies that were released in 1985.
-- Order the results by actor from oldest to youngest.
-- (20 rows)
--join movie, movie actor, person, filter by release year and actor birth year 
SELECT person.person_name, person.birthday
FROM person
JOIN movie_actor ON person.person_id = movie_actor.actor_id
JOIN movie ON movie_actor.movie_id = movie.movie_id
WHERE EXTRACT(YEAR FROM person.birthday) BETWEEN 1950 AND 1959 AND EXTRACT(YEAR FROM movie.release_date) = 1985
ORDER BY person.birthday ASC;