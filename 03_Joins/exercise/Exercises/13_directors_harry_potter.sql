-- 13. The directors of the movies in the "Harry Potter Collection", sorted alphabetically.
-- (4 rows)
--join movie, collection and person, filter by collection name
SELECT DISTINCT person.person_name
FROM person
JOIN movie ON person.person_id = movie.director_id
JOIN collection ON movie.collection_id = collection.collection_id
WHERE collection.collection_name = 'Harry Potter Collection'
ORDER BY person.person_name ASC;
