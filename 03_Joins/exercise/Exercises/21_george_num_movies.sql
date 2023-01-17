-- 21. For every person in the person table with the first name of "George", list the number of movies they've been in. Name the count column 'num_of_movies'.
-- Include all Georges, even those that have not appeared in any movies. Display the names in alphabetical order. 
-- (59 rows)
SELECT person.person_name, COUNT(movie_actor.movie_id) AS num_of_movies
FROM person
LEFT JOIN movie_actor ON person.person_id = movie_actor.actor_id
WHERE person.person_name like 'George %' 
GROUP BY person_id
ORDER BY person_name ASC; 
