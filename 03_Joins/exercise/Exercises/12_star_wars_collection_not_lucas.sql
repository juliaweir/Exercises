-- 12. The titles of the movies in the "Star Wars Collection" that weren't directed by George Lucas, sorted alphabetically.
-- (5 rows)
--join movie, collection, person, filter result by collection name and director name
SELECT movie.title
FROM movie
JOIN collection ON movie.collection_id = collection.collection_id
JOIN person ON movie.director_id = person.person_id
WHERE collection.collection_name = 'Star Wars Collection' AND person.person_name != 'George Lucas'
ORDER BY movie.title ASC;