DELIMITER //
 CREATE PROCEDURE getUnansweredQuestions()
   BEGIN
     SELECT MAX(q.`text`) "text", MAX(tmp.`answer`)
       FROM question q INNER JOIN 
		 (SELECT q2.id, 
				SUM(CASE WHEN a2.correctAnswer = 1 THEN 1 ELSE 0 END) "correct_answer",
				COUNT(*) "answer"
		  FROM question q2 INNER JOIN answer a2 ON a2.question = q2.id
		  GROUP BY q2.id) tmp ON tmp.id = q.id
		WHERE tmp.correct_answer = 0
		GROUP BY q.module
        HAVING tmp.`answer` = MAX(tmp.`answer`);
   END //
DELIMITER ;


DELIMITER //
 CREATE PROCEDURE endorsedUsersForWeek(IN start_date DATE, IN end_date DATE)
   BEGIN
     SELECT p.`id`, CONCAT(MAX(p.`firstname`), MAX(p.`lastname`)) "full_name"
       FROM `user` u INNER JOIN `person` p ON p.id = u.person
         INNER JOIN `answer` a ON a.postedBy = p.id
       WHERE a.postedOn BETWEEN start_date AND end_date
         AND a.correctAnswer = 1
	   GROUP BY p.id
       ORDER BY COUNT(*), p.firstname
       LIMIT 5;
   END //
DELIMITER ;