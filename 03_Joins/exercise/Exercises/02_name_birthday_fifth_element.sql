-- 2. The names and birthdays of actors in "The Fifth Element"
-- Order the results alphabetically (A-Z) by name.
-- (15 rows)
SELECT person.person_name, person.birthday
FROM person
JOIN movie_actor ON person.person_id = movie_actor.actor_id --for name and birthda
JOIN movie ON movie_actor.movie_id = movie.movie_id --to filter by movie
WHERE movie.title = 'The Fifth Element' --filtered by 
ORDER BY person.person_name ASC; --alphabetitcal order 


