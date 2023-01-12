-- 1. The titles and release dates of movies that Tom Hanks has appeared in. 
-- Order the results by release date, newest to oldest.
-- (47 rows)
SELECT title, release_date --title and release date 
FROM movie --from movie 
JOIN movie_actor ON movie.movie_id = movie_actor.movie_id --joins movie and movie actor
JOIN person ON movie_actor.actor_id = person.person_id --joins person table
WHERE person.person_name = 'Tom Hanks' --filter by tom hanks 
ORDER BY release_date DESC; --newest to oldest release date 
