-- 14. The names of actors who've appeared in the movies in the "Back to the Future Collection", sorted alphabetically.
-- (28 rows)
--join movie, collection, person and movie actor, filter the results by collection name
SELECT DISTINCT person.person_name --distinct avoids dup results 
FROM person
JOIN movie_actor ON person.person_id = movie_actor.actor_id
JOIN movie ON movie_actor.movie_id = movie.movie_id
JOIN collection ON movie.collection_id = collection.collection_id
WHERE collection.collection_name = 'Back to the Future Collection'
ORDER BY person.person_name ASC;
